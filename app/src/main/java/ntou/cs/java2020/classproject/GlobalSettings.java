package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public abstract class GlobalSettings extends AppCompatActivity {

    protected static boolean musicControl = true, skipControl = false;
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
    protected static MediaPlayer musicPlayer;
//    音樂播放器物件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalSettings.lastOpenedLevel = getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getInt("hasOpenedLevel", 0);
        GlobalSettings.musicControl = getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getBoolean("musicControl", true);
        if (GlobalSettings.scoreList.size() == 0)
            for (int counter = 0; counter < 5; counter++)
                GlobalSettings.scoreList.add(getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getInt(String.format(Locale.getDefault(), "level%dHighestScore", counter + 1), 0));
//        從Shared Preferences取得相關資料
//        get Data from Shared References
        if (GlobalSettings.musicPlayer == null) {
            createMusic(R.raw.song_of_the_ancients_devola, GlobalSettings.musicControl);
        } else {
            GlobalSettings.playMusic(GlobalSettings.musicControl);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalSettings.playMusic(GlobalSettings.musicControl);
//        依音樂控制選項決定是否撥放
//        start the music player if the music control option is on.
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalSettings.pauseMusic();
    }

    @Override
    protected void onDestroy() {
        GlobalSettings.playMusic(GlobalSettings.musicControl);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GlobalSettings.this, Title.class));
        finish();
    }

    private void createMusic(int song, boolean playControl) {
        GlobalSettings.musicPlayer = MediaPlayer.create(this, song);
        GlobalSettings.musicPlayer.setLooping(true);
        GlobalSettings.musicPlayer.setOnErrorListener((mp, what, extra) -> {
            mp.release();
            return false;
        });
//        新增錯誤監聽器
//        add the error listener
        if (playControl) {
            GlobalSettings.playMusic();
        }
    }

    public static void playMusic() {
        GlobalSettings.musicPlayer.start();
    }

    public static void stopMusic() {
        GlobalSettings.musicPlayer.stop();
        GlobalSettings.musicPlayer.prepareAsync();
        GlobalSettings.musicPlayer.setOnPreparedListener(mp -> {
        });
    }

    public static void playMusic(boolean playControl) {
        if (playControl) {
            GlobalSettings.playMusic();
        } else {
            GlobalSettings.stopMusic();
        }
    }

    public static void pauseMusic() {
        if (GlobalSettings.musicControl) {
            GlobalSettings.musicPlayer.pause();
        }
    }

}
