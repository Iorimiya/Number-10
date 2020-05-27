package ntou.cs.java2020.classproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
//        link page object
        ((Switch)findViewById(R.id.musicSwitch)).setChecked(GlobalSettings.musicControl);
//        依據音樂控制選項設定音樂控制開關的狀態
//        set the state of the music control switch according to the music control option

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
//        新增音樂切換監聽器
//        add the music switch listener

        findViewById(R.id.recordButton).setOnClickListener((v)->{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Setting.this);
            alertDialog.setTitle(getString(R.string.scoreDialog_title));
            String Message="";
            for(int counter=0;counter<5;counter++)
                Message+=getString(getResources().getIdentifier(String.format("global_level%d",counter+1), "string", getPackageName()))+": "+GlobalSettings.scoreList.get(counter)+'\n';
            alertDialog.setMessage(Message);
            alertDialog.setPositiveButton("確定", (DialogInterface dialog, int which)-> {});
            alertDialog.setCancelable(false);
            alertDialog.show();
        });
//        設定顯示最高分數監聽器

        findViewById(R.id.developerOptionButton).setOnClickListener((v)->{
            startActivity(new Intent(Setting.this,DeveloperSetting.class));
            finish();
        });
//        新增開發者選項按鈕的頁面移動監聽器
//        add the page moving listener of the developer settings button

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this,Title.class));
                finish();
            }
        });
//        新增返回按鈕的頁面移動監聽器
//        add the page moving listener of the back button

    }
}
