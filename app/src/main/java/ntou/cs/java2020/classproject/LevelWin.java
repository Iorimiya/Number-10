package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class LevelWin extends GlobalSettings {

    private int nextLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_win_page);
//        連結頁面
//        link page object
        nextLevel=this.getIntent().getIntExtra("NextLevel",0);
//        取得下一關資料
//        get the next level data
        try {
            ((GifImageView)findViewById(R.id.gifImage)).setImageDrawable(new GifDrawable(getResources(), R.drawable.congradulations_page_background ));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        設定背景gif
//        set the background image(gif)
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelWin.this,Title.class));
                finish();
            }
        });
//        新增設定按鈕的頁面移動監聽器
//        add the page moving listener of the setting button
        findViewById(R.id.nextLevelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(nextLevel){
                    case 2:
                        startActivity(new Intent(LevelWin.this,Level2.class));
                        break;
                    case 3:
                        startActivity(new Intent(LevelWin.this,Level3.class));
                        break;
                    case 4:
                        startActivity(new Intent(LevelWin.this,Level4.class));
                        break;
                    case 5:
                        startActivity(new Intent(LevelWin.this,Level5.class));
                        break;
                    default:
                        startActivity(new Intent(LevelWin.this,Title.class));
                        break;
                }
                finish();
            }
        });
//        新增下一關按鈕的頁面移動監聽器
//        add the page moving listener of the next page button
    }
}