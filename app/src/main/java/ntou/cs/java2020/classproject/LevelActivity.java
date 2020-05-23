package ntou.cs.java2020.classproject;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

public abstract class LevelActivity extends GlobalSettings{
    protected Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
