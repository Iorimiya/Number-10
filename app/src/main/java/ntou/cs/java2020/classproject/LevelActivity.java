package ntou.cs.java2020.classproject;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
public abstract class LevelActivity extends GlobalSettings{
    protected Chronometer chronometer;
    protected enum State {start,end;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected abstract boolean gamePrepare();
    protected void timerControl(State control){
        if(control==State.start) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }else if(control==State.end){
            chronometer.stop();
        }
    }
}
