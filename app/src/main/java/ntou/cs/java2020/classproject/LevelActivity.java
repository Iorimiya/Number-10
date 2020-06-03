package ntou.cs.java2020.classproject;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Locale;

public abstract class LevelActivity extends GlobalSettings{
    //Base Object
    protected int totalRow,totalColumn;
//    該關卡的實際行列數(不含最外圈)
    protected ArrayList<ArrayList<Block>> blockSimulatorMap;
//    以Block組成Map，map的大小會是顯示行列各+2(上下、左右)
    protected Chronometer chronometer;
//    計時器物件
    protected TextView scoreDisplayArea;
//    分數顯示區域

    //Event Object
    protected Block firstClicked,secondClicked;
//    被按到的先後物件
    protected ClickedState nowClickedState;
//    分辨沒有按鈕被按下、按下第一個按鈕和按下第二個按鈕的FSM指示變數
    protected final ArrayList<ArrayList<Integer>> connectibleNumbers=new ArrayList<>();
//    可以被消除的整數清單

    //game parameter
    protected int score=0,existBlockCounter,passedTime,bonusTime;
//    分數、剩餘Block數、經過時間、獎勵時間
    protected enum TimerState {start,stop}
//    分辨計時參數為開始或結束的列舉
    protected enum ClickedState{none,once}
//    分辨沒有按鈕被按下、按下第一個按鈕和按下第二個按鈕的FSM指示列舉

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    protected void pagePrepare(){
        ((Switch)findViewById(R.id.skipSwitch)).setChecked(GlobalSettings.skipControl);
        ((Switch)findViewById(R.id.skipSwitch)).setOnCheckedChangeListener((buttonView, isChecked) -> GlobalSettings.skipControl = isChecked);
//        設定跳關開關狀態&&新增跳關切換監聽器
//        set the skip switch's state listener
        findViewById(R.id.redealButton).setOnClickListener(v -> {
            if(nowClickedState==ClickedState.once){
                nowClickedState=ClickedState.none;
                firstClicked.setClickable(true);
                firstClicked=null;
            }
            deal();
        });
//        新增重發牌監聽器
//        add the re-dealing listener
    }

    protected void gamePrepare(int row,int column){
        //link Object to Variable
        totalRow=row;
        totalColumn=column;
        existBlockCounter=totalRow*totalColumn;
        nowClickedState=ClickedState.none;

        blockSimulatorMap=new ArrayList<>();
        for(int rowCounter=0;rowCounter<totalRow+2;rowCounter++){
            ArrayList<Block> rowTemp=new ArrayList<>();
            for(int columnCounter=0;columnCounter<totalColumn+2;columnCounter++)
                if (rowCounter == 0 || rowCounter == totalRow + 1 || columnCounter == 0 || columnCounter == totalColumn + 1)
                    rowTemp.add(new Block(rowCounter, columnCounter));
                else
                    rowTemp.add(new Block( rowCounter, columnCounter, findViewById(getResources().getIdentifier(String.format(Locale.getDefault(),"block%02d%d", rowCounter, columnCounter), "id", getPackageName()))));
            blockSimulatorMap.add(rowTemp);
        }
//        最外圈以外的Block連結到按鈕

        chronometer=findViewById(R.id.chronometerTimer);
        scoreDisplayArea=findViewById(R.id.scoreContent);
        scoreDisplayArea.setText(String.valueOf(score));
        //Start Timer
        timerControl(TimerState.start);

        //Add Click Listener
        for(int rowCounter=1;rowCounter<=totalRow;rowCounter++)
            for (int columnCounter = 1; columnCounter <= totalColumn; columnCounter++) {
                final int finalRowCounter = rowCounter, finalColumnCounter = columnCounter;
                blockSimulatorMap.get(rowCounter).get(columnCounter).setOnClickListener(v -> {
                    switch (nowClickedState) {
//                            還沒有任何按鈕被按時：設定狀態為按下一顆按鈕，將被按下的按鈕連結到firstClicked上，讓該按鈕失效(避免重複按下+提示使用者被按下的按鈕)。
                        case none:
                            nowClickedState = ClickedState.once;
                            firstClicked = blockSimulatorMap.get(finalRowCounter).get(finalColumnCounter);
                            firstClicked.setClickable(false);
                            break;
//                            按下一顆：如果連結合規->讓該按鈕消失，else：回復原狀
                        case once:
                            nowClickedState = ClickedState.none;
                            secondClicked = blockSimulatorMap.get(finalRowCounter).get(finalColumnCounter);
                            secondClicked.setClickable(false);
                            if (connectionAnalysis()) {
                                firstClicked.setExist(false);
                                secondClicked.setExist(false);
                                score+=20;
                                scoreDisplayArea.setText(String.valueOf(score));
                                existBlockCounter-=2;
                                if(existBlockCounter==0){
                                    finishProcess();
                                }
                            } else {
                                firstClicked.setClickable(true);
                                secondClicked.setClickable(true);
                            }
                            firstClicked = null;
                            secondClicked = null;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + nowClickedState);
                    }
                });
            }
        deal();
    }

    protected void timerControl(TimerState control){
        if(control==TimerState.start) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }else if(control==TimerState.stop){
            chronometer.stop();
        }
    }
//    開啟/關閉計時器

    protected void deal(){
        SecureRandom SR=new SecureRandom(ByteBuffer.allocate(4).putInt((int) (System.currentTimeMillis() / 1000)).array());
        int needValueBlocksNumber=0;
        for(ArrayList<Block> RowIterator:blockSimulatorMap)
            for (Block columnIterator : RowIterator)
                if (columnIterator.isExist()) {needValueBlocksNumber++;columnIterator.setHasValue(false);}
//        取得應給數字的總數
        for(int hasValueCounter=0;hasValueCounter<needValueBlocksNumber;){
            Position firstPosition=new Position(SR.nextInt(totalRow)+1,SR.nextInt(totalColumn)+1),secondPosition=new Position(SR.nextInt(totalRow)+1,SR.nextInt(totalColumn)+1);
            if((firstPosition.getRow()==secondPosition.getRow()&&firstPosition.getColumn()==secondPosition.getColumn())||blockSimulatorMap.get(firstPosition.getRow()).get(firstPosition.getColumn()).isHasValue()||blockSimulatorMap.get(secondPosition.getRow()).get(secondPosition.getColumn()).isHasValue()||!blockSimulatorMap.get(firstPosition.getRow()).get(firstPosition.getColumn()).isExist()||!blockSimulatorMap.get(secondPosition.getRow()).get(secondPosition.getColumn()).isExist()) continue;
            int randomNumberIndex=SR.nextInt(connectibleNumbers.size());
            blockSimulatorMap.get(firstPosition.getRow()).get(firstPosition.getColumn()).setNumber(connectibleNumbers.get(randomNumberIndex).get(0));
            blockSimulatorMap.get(secondPosition.getRow()).get(secondPosition.getColumn()).setNumber(connectibleNumbers.get(randomNumberIndex).get(1));
            hasValueCounter+=2;
        }
//        給數字
    }

    protected boolean connectionAnalysis(){
        if(nullFoldConnection(firstClicked.getPosition(),secondClicked.getPosition())) return true;
        else if(singleFoldConnection(firstClicked.getPosition(),secondClicked.getPosition())) return true;
        else return doubleFoldConnection(firstClicked.getPosition(),secondClicked.getPosition());
    }

    //無折
    protected boolean nullFoldConnection(Position A,Position B){
        if(A.getRow()==B.getRow()){
            for(int counter=Math.min(A.getColumn(),B.getColumn())+1;counter<Math.max(A.getColumn(),B.getColumn());counter++){
                if(blockSimulatorMap.get(A.getRow()).get(counter).isExist())return false;
            }
            return true;
        }
        if(A.getColumn()==B.getColumn()){
            for(int counter=Math.min(A.getRow(),B.getRow())+1;counter<Math.max(A.getRow(),B.getRow());counter++){
                if(blockSimulatorMap.get(counter).get(A.getColumn()).isExist())return false;
            }
            return true;
        }
        return false;
    }

    //一折
    protected  boolean singleFoldConnection(Position A,Position B){
        if(A.getRow()==B.getRow()||A.getColumn()==B.getColumn()) return false;
        return (nullFoldConnection(A, new Position(A.getRow(), B.getColumn())) && nullFoldConnection(B, new Position(A.getRow(), B.getColumn())) && !blockSimulatorMap.get(A.getRow()).get(B.getColumn()).isExist()) || (nullFoldConnection(A, new Position(B.getRow(), A.getColumn())) && nullFoldConnection(B, new Position(B.getRow(), A.getColumn())) && !blockSimulatorMap.get(B.getRow()).get(A.getColumn()).isExist());
    }

    //二折
    protected  boolean doubleFoldConnection(Position A,Position B){
        for(int leftIterator=A.getColumn()-1;leftIterator>=0&&!blockSimulatorMap.get(A.getRow()).get(leftIterator).isExist();leftIterator--){
            if(singleFoldConnection(B,new Position(A.getRow(),leftIterator)))return true;
        }
        for(int rightIterator=A.getColumn()+1;rightIterator<totalColumn+2&&!blockSimulatorMap.get(A.getRow()).get(rightIterator).isExist();rightIterator++){
            if(singleFoldConnection(B,new Position(A.getRow(),rightIterator)))return true;
        }
        for(int upIterator=A.getRow()-1;upIterator>=0&&!blockSimulatorMap.get(upIterator).get(A.getColumn()).isExist();upIterator--){
            if(singleFoldConnection(B,new Position(upIterator,A.getColumn())))return true;
        }
        for(int downIterator=A.getRow()+1;downIterator<totalRow+2&&!blockSimulatorMap.get(downIterator).get(A.getColumn()).isExist();downIterator++){
            if(singleFoldConnection(B,new Position(downIterator,A.getColumn())))return true;
        }
        return false;
    }

    protected void finishProcess(){
        timerControl(TimerState.stop);
        //Log.d("Number10",chronometer.getContentDescription().toString());
        String[] timerString=chronometer.getText().toString().split(":");
        passedTime=Integer.parseInt(timerString[timerString.length-1])+Integer.parseInt(timerString[timerString.length-2])*60;
        if(timerString.length==3) passedTime+=Integer.parseInt(timerString[0])*3600;
        if(passedTime<=bonusTime) score=(int)(score*1.5);
        Toast.makeText(getApplicationContext(),String.format(Locale.getDefault(),"%s：%d",getString(R.string.gameWin_score),score),Toast.LENGTH_SHORT).show();
    }
}
