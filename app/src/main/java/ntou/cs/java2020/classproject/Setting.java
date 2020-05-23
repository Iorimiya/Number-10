package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Objects;

public class Setting extends GlobalSettings {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_page);
        if((GlobalSettings.lastOpenedLevel = Integer.parseInt(Objects.requireNonNull(getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).getString("hasOpenedLevel", "0"))))!=0)
            findViewById(getResources().getIdentifier("FinishedLV"+GlobalSettings.lastOpenedLevel+"Button", "id", getPackageName())).setEnabled(false);
        else
            findViewById(getResources().getIdentifier("FinishedResetButton", "id", getPackageName())).setEnabled(false);
        ((Switch)findViewById(R.id.musicSwitch)).setChecked(GlobalSettings.musicControl);
        ((Switch)findViewById(R.id.musicSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GlobalSettings.musicControl = isChecked;
            }
        });
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this,Title.class));
                finish();
            }
        });
        findViewById(R.id.FinishedResetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSettings.lastOpenedLevel=0;
                getSharedPreferences("NumberTenSaveData",MODE_PRIVATE).edit().remove("hasOpenedLevel").apply();
                findViewById(R.id.FinishedResetButton).setEnabled(false);
                for(int counter=1;counter<=5;counter++){
                    findViewById(getResources().getIdentifier("FinishedLV"+counter+"Button", "id", getPackageName())).setEnabled(true);
                }
            }
        });

        for(int counter=1;counter<=5;counter++){
            findViewById(getResources().getIdentifier("FinishedLV"+counter+"Button", "id", getPackageName())).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalSettings.lastOpenedLevel=Integer.parseInt(String.valueOf(v.getResources().getResourceName(v.getId()).charAt(43)));
                    getSharedPreferences("NumberTenSaveData",MODE_PRIVATE).edit().putString("hasOpenedLevel" ,Integer.toString(GlobalSettings.lastOpenedLevel)).apply();
                    findViewById(R.id.FinishedResetButton).setEnabled(true);
                    for(int counter=1;counter<=5;counter++){
                        if(GlobalSettings.lastOpenedLevel!=counter)
                            findViewById(getResources().getIdentifier("FinishedLV"+counter+"Button", "id", getPackageName())).setEnabled(true);
                        else
                            findViewById(getResources().getIdentifier("FinishedLV"+counter+"Button", "id", getPackageName())).setEnabled(false);
                    }
                }
            });
        }

    }
}
