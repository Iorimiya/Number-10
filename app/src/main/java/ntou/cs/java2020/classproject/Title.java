package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class Title extends GlobalSettings {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_page);
//        連結頁面
//        Linking page object
        GlobalSettings.lastOpenedLevel = getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getInt("hasOpenedLevel", 0);
        GlobalSettings.musicControl=getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getBoolean("musicControl", true);
//        從Shared Preferences取得相關資料
//        get Data from Shared References
        if(GlobalSettings.mediaPlayer==null){
            initializeMediaPlayer();
            if(GlobalSettings.musicControl)
                GlobalSettings.mediaPlayer.start();
        }
//        初始化音樂播放器，並依音樂控制選項決定是否撥放
//        initialize the music player. start it if the music control option is on.
        findViewById(R.id.newGameButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Title.this,Level1.class));
                finish();
            }
        });
//        設定新遊戲按鈕的ClickListener
//        setting the Click listener of the newGameButton
        findViewById(R.id.levelSelectionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Title.this,LevelSelection.class));
                finish();
            }
        });
//        設定關卡選擇按鈕的ClickListener
//        setting the Click listener of the levelSelectionButton
        findViewById(R.id.ruleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Title.this,Rule.class));
            }
        });
//        設定規則按鈕的ClickListener
//        setting the Click listener of the ruleButton
        findViewById(R.id.settingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Title.this,Setting.class));
            }
        });
//        設定設定按鈕的ClickListener
//        setting the Click listener of the settingButton
    }
}