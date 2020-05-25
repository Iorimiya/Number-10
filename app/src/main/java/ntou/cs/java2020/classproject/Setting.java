package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Setting extends GlobalSettings {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_page);
//        連結頁面
//        Linking page object
        ((Switch)findViewById(R.id.musicSwitch)).setChecked(GlobalSettings.musicControl);
//        依據音樂控制選項設定音樂控制開關的狀態
//        set the state of the music control switch according to the music control option.
        if(GlobalSettings.lastOpenedLevel!=0)
            findViewById(getResources().getIdentifier("FinishedLV"+GlobalSettings.lastOpenedLevel+"Button", "id", getPackageName())).setEnabled(false);
        else
            findViewById(getResources().getIdentifier("FinishedResetButton", "id", getPackageName())).setEnabled(false);
        ((Switch)findViewById(R.id.musicSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GlobalSettings.musicControl = isChecked;
                getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putBoolean("musicControl",GlobalSettings.musicControl).apply();

                if(musicControl)
                    GlobalSettings.mediaPlayer.start();
                else {
                    GlobalSettings.mediaPlayer.stop();
                    prepareMediaPlayer();
                }
            }
        });
        findViewById(R.id.FinishedResetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSettings.lastOpenedLevel=0;
                getSharedPreferences("NumberTenSaveData",MODE_PRIVATE).edit().remove("hasOpenedLevel").apply();
                findViewById(R.id.FinishedResetButton).setEnabled(false);
                for(int counter=1;counter<=5;counter++){
                    findViewById(getResources().getIdentifier("FinishedLV"+counter+"Button", "id", getPackageName())).setEnabled(true);
                }
            }
        });
        for(int counter=1;counter<=5;counter++)
            findViewById(getResources().getIdentifier("FinishedLV" + counter + "Button", "id", getPackageName())).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalSettings.lastOpenedLevel = Integer.parseInt(String.valueOf(v.getResources().getResourceName(v.getId()).charAt(43)));
                    getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putInt("hasOpenedLevel", GlobalSettings.lastOpenedLevel).apply();
                    findViewById(R.id.FinishedResetButton).setEnabled(true);
                    for (int counter = 1; counter <= 5; counter++)
                        if (GlobalSettings.lastOpenedLevel != counter)
                            findViewById(getResources().getIdentifier("FinishedLV" + counter + "Button", "id", getPackageName())).setEnabled(true);
                        else
                            findViewById(getResources().getIdentifier("FinishedLV" + counter + "Button", "id", getPackageName())).setEnabled(false);
                }
            });
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this,Title.class));
                finish();
            }
        });
    }
}
