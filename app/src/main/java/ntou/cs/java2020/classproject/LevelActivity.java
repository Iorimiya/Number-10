package ntou.cs.java2020.classproject;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;

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
    protected ArrayList<ArrayList<Integer>> ConnectibleNumbers=new ArrayList<>();

    //game parameter
    protected int score;
//    分數
    protected enum TimerState {start,end}
//    分辨計時參數為開始或結束的列舉
    protected enum ClickedState{none,once}
//    分辨沒有按鈕被按下、按下第一個按鈕和按下第二個按鈕的FSM指示列舉

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    protected void pagePrepare(){
        ((Switch)findViewById(R.id.skipSwitch)).setChecked(GlobalSettings.skipControl);
        ((Switch)findViewById(R.id.skipSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GlobalSettings.skipControl = isChecked;
            }
        });
//        設定跳關開關狀態&&添加跳關開關CheckedListener
//        setting the skip switch's state and checked listener
    }

    protected void gamePrepare(int row,int column){
        //Linking Object to Variable
        totalRow=row;
        totalColumn=column;
        nowClickedState=ClickedState.none;

//        最外圈以外的Block連結到按鈕
        blockSimulatorMap=new ArrayList<>();
        for(int rowCounter=0;rowCounter<totalRow+2;rowCounter++){
            ArrayList<Block> rowTemp=new ArrayList<>();
            for(int columnCounter=0;columnCounter<totalColumn+2;columnCounter++)
                if (rowCounter == 0 || rowCounter == totalRow + 1 || columnCounter == 0 || columnCounter == totalColumn + 1)
                    rowTemp.add(new Block(null, rowCounter, columnCounter));
                else
                    rowTemp.add(new Block((Button) findViewById(getResources().getIdentifier(String.format("block%02d%d", rowCounter, columnCounter), "id", getPackageName())), rowCounter, columnCounter));
            blockSimulatorMap.add(rowTemp);
        }

        chronometer=findViewById(R.id.chronometerTimer);
        scoreDisplayArea=findViewById(R.id.scoreText);
        //Start Timer
        timerControl(TimerState.start);

        //Add Click Listener
        for(int rowCounter=1;rowCounter<=totalRow;rowCounter++)
            for (int columnCounter = 1; columnCounter <= totalColumn; columnCounter++) {
                final int finalRowCounter = rowCounter, finalColumnCounter = columnCounter;
                blockSimulatorMap.get(rowCounter).get(columnCounter).button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (nowClickedState) {
//                            還沒有任何按鈕被按時：設定狀態為按下一顆按鈕，將被按下的按鈕連結到firstClicked上，讓該按鈕失效(避免重複按下+提示使用者被按下的按鈕)。
                            case none:
                                nowClickedState = ClickedState.once;
                                firstClicked = blockSimulatorMap.get(finalRowCounter).get(finalColumnCounter);
                                firstClicked.button.setEnabled(false);
                                break;
//                            按下一顆：如果連結合規->讓該按鈕消失，else：回復原狀
                            case once:
                                nowClickedState = ClickedState.none;
                                secondClicked = blockSimulatorMap.get(finalRowCounter).get(finalColumnCounter);
                                secondClicked.button.setEnabled(false);
                                if (connectionAnalysis()) {
                                    firstClicked.button.setVisibility(Button.INVISIBLE);
                                    firstClicked.isExist = false;
                                    secondClicked.button.setVisibility(Button.INVISIBLE);
                                    secondClicked.isExist = false;
                                } else {
                                    firstClicked.button.setEnabled(true);
                                    secondClicked.button.setEnabled(true);
                                }
                                firstClicked = null;
                                secondClicked = null;
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + nowClickedState);
                        }
                    }
                });
            }
        deal();
    }

    protected void deal(){
        SecureRandom SR=new SecureRandom(ByteBuffer.allocate(4).putInt((int) (System.currentTimeMillis() / 1000)).array());
        int needValueBlocksNumber=0;
        for(ArrayList<Block> RowIterator:blockSimulatorMap)
            for (Block columnIterator : RowIterator)
                if (columnIterator.isExist) needValueBlocksNumber++;

        for(int hasValueCounter=0;hasValueCounter<needValueBlocksNumber;){
            OriginPair firstPair=new OriginPair(SR.nextInt(totalRow)+1,SR.nextInt(totalColumn)+1),secondPair=new OriginPair(SR.nextInt(totalRow)+1,SR.nextInt(totalColumn)+1);
            if((firstPair.row==secondPair.row&&firstPair.column==secondPair.column)||blockSimulatorMap.get(firstPair.row).get(firstPair.column).hasValue||blockSimulatorMap.get(secondPair.row).get(secondPair.column).hasValue||!blockSimulatorMap.get(firstPair.row).get(firstPair.column).isExist||!blockSimulatorMap.get(secondPair.row).get(secondPair.column).isExist) continue;
            int tempSR=SR.nextInt(ConnectibleNumbers.size());
            firstPair.value=ConnectibleNumbers.get(tempSR).get(0);
            secondPair.value=ConnectibleNumbers.get(tempSR).get(1);
            blockSimulatorMap.get(firstPair.row).get(firstPair.column).value=firstPair.value;
            blockSimulatorMap.get(firstPair.row).get(firstPair.column).button.setText(String.valueOf(firstPair.value));
            blockSimulatorMap.get(firstPair.row).get(firstPair.column).hasValue=true;
            blockSimulatorMap.get(secondPair.row).get(secondPair.column).value=secondPair.value;
            blockSimulatorMap.get(secondPair.row).get(secondPair.column).button.setText(String.valueOf(secondPair.value));
            blockSimulatorMap.get(secondPair.row).get(secondPair.column).hasValue=true;
            hasValueCounter+=2;
        }
    }

    protected void redeal(){

    }

    protected boolean connectionAnalysis(){
        System.out.println("f");
        return false;
    }

    protected void timerControl(TimerState control){
        if(control==TimerState.start) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }else if(control==TimerState.end){
            chronometer.stop();
        }
    }

}
