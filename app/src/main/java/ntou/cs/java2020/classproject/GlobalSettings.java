package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public abstract class GlobalSettings extends AppCompatActivity {

    protected static boolean skipControl = false;
    //    musicControl：音樂控制選項
//    skipControl：跳關控制選項
    protected static int lastOpenedLevel = 0;
    //    關卡控制選項，可以遊玩的最後一項關卡
//    opened level control option, the last level which the player can play
    private static final int totalLevel = 5;
    //    總關卡數
//    total levels
    protected static final ArrayList<Integer> scoreList = new ArrayList<>(totalLevel);
    //    計分表
    protected static MusicPlayer musicPlayer;
//    音樂播放器物件


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalSettings.lastOpenedLevel = getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getInt("hasOpenedLevel", 0);
        if (GlobalSettings.scoreList.size() == 0)
            for (int counter = 0; counter < 5; counter++)
                GlobalSettings.scoreList.add(getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getInt(String.format(Locale.getDefault(), "level%dHighestScore", counter + 1), 0));
//        從Shared Preferences取得相關資料
//        get Data from Shared References
        if (musicPlayer == null) musicPlayer = new MusicPlayer(getApplicationContext(),R.raw.song_of_the_ancients_devola);

    }
    protected void onStop() {
        musicPlayer.pauseByFlag();
        super.onStop();
    }

    protected void onRestart() {
        musicPlayer.startByFlag();
        super.onRestart();
    }

    protected void onDestroy() {
        musicPlayer.startByFlag();
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(GlobalSettings.this, Title.class));
        finish();
    }


}
