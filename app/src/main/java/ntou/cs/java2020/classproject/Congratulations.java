package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class Congratulations extends GlobalSettings {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congratulations_page);
        try {
            ((GifImageView)findViewById(R.id.gifImage)).setImageDrawable(new GifDrawable(getResources(), R.drawable.congradulations_page_background ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Congratulations.this,Title.class));
                finish();
            }
        });
    }
}
