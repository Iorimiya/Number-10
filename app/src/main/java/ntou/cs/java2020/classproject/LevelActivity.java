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
    protected ArrayList<ArrayList<Block>> blockSimulatorMap;
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
        blockSimulatorMap=new ArrayList<>();
        for(int rowCounter=0;rowCounter<totalRow+2;rowCounter++){
            ArrayList<Block> rowTemp=new ArrayList<>();
            for(int columnCounter=0;columnCounter<totalColumn+2;columnCounter++){
                if(rowCounter==0||rowCounter==totalRow+1||columnCounter==0||columnCounter==totalColumn+1) rowTemp.add(new Block(null,rowCounter,columnCounter));
                else rowTemp.add(new Block((Button)findViewById(getResources().getIdentifier(String.format("block%02d%d",rowCounter,columnCounter),"id",getPackageName())),rowCounter,columnCounter));
            }
            blockSimulatorMap.add(rowTemp);
        }
        chronometer=findViewById(R.id.chronometerTimer);
        scoreDisplayArea=findViewById(R.id.scoreText);
        //Start Timer
        timerControl(TimerState.start);
        //Add Click Listener
        for(int rowCounter=1;rowCounter<=totalRow;rowCounter++){
            for(int columnCounter=1;columnCounter<=totalColumn;columnCounter++){
                final int finalRowCounter = rowCounter,finalColumnCounter=columnCounter;
                blockSimulatorMap.get(rowCounter).get(columnCounter).button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (nowClickedState){
                            case none:
                                nowClickedState=ClickedState.once;
                                firstClicked=blockSimulatorMap.get(finalRowCounter).get(finalColumnCounter);
                                firstClicked.button.setEnabled(false);
                                break;
                            case once:
                                nowClickedState=ClickedState.none;
                                secondClicked=blockSimulatorMap.get(finalRowCounter).get(finalColumnCounter);
                                secondClicked.button.setEnabled(false);
                                if(connectionAnalysis()){
                                    firstClicked.button.setVisibility(Button.INVISIBLE);
                                    firstClicked.exist=false;
                                    secondClicked.button.setVisibility(Button.INVISIBLE);
                                    secondClicked.exist=false;
                                }else{
                                    firstClicked.button.setEnabled(true);
                                    secondClicked.button.setEnabled(true);
                                }
                                firstClicked=null;
                                secondClicked=null;
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + nowClickedState);
                        }
                    }
                });
            }
        }


    };
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
