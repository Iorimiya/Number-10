package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class Congratulations extends GlobalSettings {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congratulations_page);
//        連結頁面
//        link page object
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
                startActivity(new Intent(Congratulations.this,Title.class));
                finish();
            }
        });
//        新增設定按鈕的頁面移動監聽器
//        add the page moving listener of the back button
    }
}
