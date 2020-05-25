package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class LevelSelection extends GlobalSettings {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_selection_page);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this,Title.class));
                finish();
            }
        });
        findViewById(R.id.level1Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this,Level1.class));
                finish();
            }
        });
        findViewById(R.id.level2Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this,Level2.class));
                finish();
            }
        });
        findViewById(R.id.level3Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this,Level3.class));
                finish();
            }
        });
        findViewById(R.id.level4Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this, Level4.class));
                finish();
            }
        });
        findViewById(R.id.level5Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this, Level5.class));
                finish();
            }
        });
        if((GlobalSettings.lastOpenedLevel = Integer.parseInt(Objects.requireNonNull(getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getString("hasOpenedLevel", "0"))))==0)
            findViewById(getResources().getIdentifier("level1Button", "id", getPackageName())).setEnabled(true);
        else for (int counter = 1; counter <= GlobalSettings.lastOpenedLevel; counter++)
            findViewById(getResources().getIdentifier("level" + counter + "Button", "id", getPackageName())).setEnabled(true);
    }
}
