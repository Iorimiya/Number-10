package ntou.cs.java2020.classproject;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public abstract class GlobalSettings extends AppCompatActivity {

    protected static boolean musicControl =true, skipControl =false;
//    musicControl：音樂控制選項
//    skipControl：跳關控制選項
    protected static int lastOpenedLevel=0;
//    關卡控制選項，可以遊玩的最後一項關卡
//    opened level control option, the last level which the player can play
    protected static ArrayList<Integer> scoreList=new ArrayList<>(5);
//    計分表
    //CAN_DELETE
    protected static MediaPlayer mediaPlayer;
//    音樂播放器物件
    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}
    protected boolean initializeMediaPlayer(){
        if(GlobalSettings.mediaPlayer==null)
            GlobalSettings.mediaPlayer = MediaPlayer.create(this, R.raw.song_of_the_ancients_devola);
//            新增播放器
//            create music player
        if( GlobalSettings.mediaPlayer == null) return false;
        GlobalSettings.mediaPlayer.setLooping(true);
//            設定迴圈撥放
//            set the single media loop play
        GlobalSettings.mediaPlayer.setOnErrorListener((mp, what, extra) -> {
            mp.release();
            return false;
        });
//        新增錯誤監聽器
//        add the error listener
        GlobalSettings.mediaPlayer.stop();
        return prepareMediaPlayer();
    }

    protected boolean prepareMediaPlayer(){
        try{
            GlobalSettings.mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
//    移動狀態至prepared
//    move the state to "prepared"
}
