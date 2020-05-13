package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class LevelWin extends GlobalSettings {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_win_page);
        final int nextLevel=this.getIntent().getIntExtra("NextLevel",0);
        try {
            ((GifImageView)findViewById(R.id.gifImage)).setImageDrawable(new GifDrawable(getResources(), R.drawable.congradulations_page_background ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelWin.this,Title.class));
                finish();
            }
        });
        findViewById(R.id.nextLevelButton).setOnClickListener(new NextLevelAnalysisListener(nextLevel) {
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
                };
                finish();
            }
        });
    }
}