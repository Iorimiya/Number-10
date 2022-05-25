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
        this.player.setOnPreparedListener((player) -> {});
        this.player.setLooping(true);
        this.startByFlag();

    }
    public void setControlData(Context processContext, boolean newControlData){
        this.controlFlag = newControlData;
        this.saveControlData(processContext);
    }
    public boolean getControlData(Context processContext){
        this.loadControlData(processContext);
        return this.controlFlag;
    }
    public void startByFlag(){
        if(this.controlFlag) this.player.start();
    }
    public void pauseByFlag(){
        if(this.controlFlag) this.player.pause();

    }
    public void switchMode(Context processContext, boolean newStatus){
        this.setControlData(processContext,newStatus);
        if(this.controlFlag) this.player.start();
        else this.stop();
    }

    private void loadControlData(Context processContext){
        this.controlFlag = processContext.getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getBoolean("musicControl", true);
    }

    private void saveControlData(Context processContext) {
        processContext.getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putBoolean("musicControl", this.controlFlag).apply();
    }


    private void stop(){
        this.player.stop();
        this.player.prepareAsync();
    }

}
