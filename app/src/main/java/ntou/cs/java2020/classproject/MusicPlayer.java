package ntou.cs.java2020.classproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {
    private final MediaPlayer player;
    public boolean controlFlag;
    public MusicPlayer(Context processContext,int musicResId){
        this.loadControlData(processContext);
        this.player = MediaPlayer.create(processContext,musicResId);
        this.player.setOnErrorListener((player, what, extra) -> {
            player.release();
            return false;
        });
        this.player.setLooping(true);
        this.updateMusicState();

    }
    private void loadControlData(Context processContext){
        this.controlFlag = processContext.getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getBoolean("musicControl", true);
    }

    private void saveControlData(Context processContext) {
        processContext.getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putBoolean("musicControl", this.controlFlag).apply();
    }
    public void setControlData(Context processContext, boolean newControlData){
        this.controlFlag = newControlData;
        this.saveControlData(processContext);
    }
    public boolean getControlData(Context processContext){
        this.loadControlData(processContext);
        return this.controlFlag;
    }
    public void updateMusicState(){
        if(this.player.isPlaying()){
            this.pause();
        }
        if(this.controlFlag){
            this.start();
        }else{
            this.stop();
        }
    }
    public void start(){
        this.player.start();
    }
    public void stop(){
        this.player.stop();
        this.player.prepareAsync();
    }
    public void pause(){
        this.player.pause();
    }

}
