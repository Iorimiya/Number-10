package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public abstract class GlobalSettings extends AppCompatActivity {

    static boolean musicControl =true, skipControl =false;
    //音樂設定和跳關設定
    static int lastOpenedLevel=0;
    //可以遊玩的最後一項關卡
    static ArrayList<Integer> scoreList=new ArrayList<>(5);
    //計分表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
