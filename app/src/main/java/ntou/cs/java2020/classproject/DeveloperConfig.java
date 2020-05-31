package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;

import java.util.Locale;

public class DeveloperConfig extends GlobalSettings {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_config_page);
//        連結頁面
//        link page object
        if(GlobalSettings.lastOpenedLevel!=0)
            findViewById(getResources().getIdentifier("lastOpenedLV"+GlobalSettings.lastOpenedLevel+"Button", "id", getPackageName())).setEnabled(false);
        else
            findViewById(R.id.resetLevelButton).setEnabled(false);
//        依據關卡控制選項啟用按鈕
//        enable the button according the level control option

        ((Switch)findViewById(R.id.skipLevelSwitch)).setChecked(GlobalSettings.skipControl);
//        依據跳關控制選項設定音樂控制開關的狀態
//        set the state of the skip control switch according to the skip control option
        ((Switch)findViewById(R.id.skipLevelSwitch)).setOnCheckedChangeListener((buttonView,isChecked)-> GlobalSettings.skipControl = isChecked);
//        新增跳關設定監聽器
//        add the level skipping setting listener
        for(int counter=1;counter<=5;counter++)
            findViewById(getResources().getIdentifier("lastOpenedLV" + counter + "Button", "id", getPackageName())).setOnClickListener(v -> {
                GlobalSettings.lastOpenedLevel = Integer.parseInt(String.valueOf(v.getResources().getResourceName(v.getId()).split("LV")[1].charAt(0)));
                getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putInt("hasOpenedLevel", GlobalSettings.lastOpenedLevel).apply();
                findViewById(R.id.resetLevelButton).setEnabled(true);
                for (int counter1 = 1; counter1 <= 5; counter1++)
                    if (GlobalSettings.lastOpenedLevel != counter1)
                        findViewById(getResources().getIdentifier("lastOpenedLV" + counter1 + "Button", "id", getPackageName())).setEnabled(true);
                    else
                        findViewById(getResources().getIdentifier("lastOpenedLV" + counter1 + "Button", "id", getPackageName())).setEnabled(false);
            });
//        新增關卡記錄設定監聽器
//        add the level record setting listener
        findViewById(R.id.resetLevelButton).setOnClickListener(v -> {
            GlobalSettings.lastOpenedLevel=0;
            getSharedPreferences("NumberTenSaveData",MODE_PRIVATE).edit().remove("hasOpenedLevel").apply();
            findViewById(R.id.resetLevelButton).setEnabled(false);
            for(int counter=1;counter<=5;counter++){
                findViewById(getResources().getIdentifier("lastOpenedLV"+counter+"Button", "id", getPackageName())).setEnabled(true);
            }
        });
//        新增關卡記錄消除監聽器
//        add the level record clear listener


        findViewById(R.id.resetMusicButton).setOnClickListener((view)-> getSharedPreferences("NumberTenSaveData",MODE_PRIVATE).edit().remove("musicControl").apply());
//        新增音樂記錄消除監聽器
//        add the music clear listener

        findViewById(R.id.resetRecordButton).setOnClickListener((view)->{
            for(int counter=0;counter<5;counter++) {
                GlobalSettings.scoreList.set(counter, 0);
                if (getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getInt(String.format(Locale.getDefault(),"level%dHighestScore", counter + 1), 0) != 0)
                    getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().remove(String.format(Locale.getDefault(),"level%dHighestScore", counter + 1)).apply();
            }
        });
//        新增分數記錄消除監聽器
//        add the score clear listener
        findViewById(R.id.backButton).setOnClickListener(v -> {
            startActivity(new Intent(DeveloperConfig.this,Setting.class));
            finish();
        });
//        新增返回按鈕的頁面移動監聽器
//        add the page moving listener of the back button
    }
    

}
