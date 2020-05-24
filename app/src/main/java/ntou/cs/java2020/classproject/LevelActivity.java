package ntou.cs.java2020.classproject;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;

public abstract class LevelActivity extends GlobalSettings{
    //Base Object
    protected int totalRow,totalColumn;
    protected ArrayList<ArrayList<Block>> BlockSimulatorMap;
    protected Chronometer chronometer;
    protected TextView scoreDisplayArea;

    //Event Object
    protected Block firstClicked,secondClicked;
    protected ClickedState nowClickedState;

    //game parameter
    protected int score;
    protected enum TimerState {start,end;}
    protected enum ClickedState{none,once;}


    //method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void gamePrepare(int row,int column){
        //Linking Object to Variable
        totalRow=row;
        totalColumn=column;
        nowClickedState=ClickedState.none;
        /*
        Blocks=new ArrayList<>();

        for(int rowCounter=0;rowCounter<totalRow;rowCounter++){
            ArrayList<Block> rowTemp =new ArrayList<>();
            for(int columnCounter=0;columnCounter<totalColumn;columnCounter++){
                String blockName=String.format("block%02d%d",rowCounter,columnCounter);
                int blockID=getResources().getIdentifier(blockName, "id", getPackageName());
                View blockView=findViewById(blockID);
                Button blockButton=(Button)blockView;
                Block blockObject=new Block(blockButton,rowCounter,columnCounter);
                rowTemp.add(blockObject);
                rowTemp.get(columnCounter).button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch(nowClickedState){
                            case none:
                                //nowClickedState=ClickedState.once;
                                v.setEnabled(false);
                                System.out.println(v.getResources().getResourceEntryName(v.getId()));
                                break;
                            case once:
                                nowClickedState=ClickedState.none;
                                break;
                        }
                    }
                });
            }
            Blocks.add(rowTemp);
        }
        blockSimulatorMap=new ArrayList<>(totalRow+2);
        for(int rowCounter=0;rowCounter<blockSimulatorMap.size();rowCounter++){
            ArrayList<Boolean> rowTemp=new ArrayList<>(totalColumn+2);
            for(int columnCounter=0;columnCounter<rowTemp.size();columnCounter++){
               if(rowCounter==0||rowCounter==blockSimulatorMap.size()-1||columnCounter==0||columnCounter<rowTemp.size()-1)
                   rowTemp.set(columnCounter,false);
               else rowTemp.set(columnCounter,true);
            }
            blockSimulatorMap.set(rowCounter,rowTemp);
        }
        */
        chronometer=findViewById(R.id.chronometerTimer);
        scoreDisplayArea=findViewById(R.id.scoreText);
        //Start Timer
        timerControl(TimerState.start);
    };
    protected void redeal(){

    }
    protected void analyzeConnection(){

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
