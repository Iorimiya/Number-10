package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.view.View;

import static androidx.core.content.ContextCompat.startActivity;

public abstract class NextLevelAnalysisListener implements View.OnClickListener {
    private int nextLevel;
    NextLevelAnalysisListener(int nextLevel){
        this.nextLevel=nextLevel;
    }
    @Override
    public abstract void onClick(View v);
}