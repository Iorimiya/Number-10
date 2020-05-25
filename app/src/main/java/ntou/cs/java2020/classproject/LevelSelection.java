package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LevelSelection extends GlobalSettings {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_selection_page);
//        連結頁面
//        Linking page object
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this,Title.class));
                finish();
            }
        });
//        回選單
//        back to the title
        findViewById(R.id.level1Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this,Level1.class));
                finish();
            }
        });
//        第一關
//        goto level 1
        findViewById(R.id.level2Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this,Level2.class));
                finish();
            }
        });
//        第二關
//        goto level 2
        findViewById(R.id.level3Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this,Level3.class));
                finish();
            }
        });
//        第三關
//        goto level 3
        findViewById(R.id.level4Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this, Level4.class));
                finish();
            }
        });
//        第四關
//        goto level 4
        findViewById(R.id.level5Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelSelection.this, Level5.class));
                finish();
            }
        });
//        第五關
//        goto level 5
        if(GlobalSettings.lastOpenedLevel==0)
            findViewById(getResources().getIdentifier("level1Button", "id", getPackageName())).setEnabled(true);
        else for (int counter = 1; counter <= GlobalSettings.lastOpenedLevel; counter++)
            findViewById(getResources().getIdentifier("level" + counter + "Button", "id", getPackageName())).setEnabled(true);
//        視選項決定開啟那些關卡
//        open the level due to the last Opened Level
    }
}
