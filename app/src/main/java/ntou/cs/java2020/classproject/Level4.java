package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Level4 extends LevelActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level4_page);
        ((Switch)findViewById(R.id.skipSwitch)).setChecked(GlobalSettings.skipControl);
        ((Switch)findViewById(R.id.skipSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                GlobalSettings.skipControl = isChecked;
            }
        });
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GlobalSettings.skipControl){
                    Intent intent=new Intent(Level4.this, LevelWin.class);
                    intent.putExtra("NextLevel",5);
                    if(GlobalSettings.lastOpenedLevel<5) {
                        GlobalSettings.lastOpenedLevel = 5;
                        getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putString("hasOpenedLevel", Integer.toString(GlobalSettings.lastOpenedLevel)).apply();
                    }
                    startActivity(intent);
                }
                else
                    startActivity(new Intent(Level4.this, Title.class));
                finish();
            }
        });
    }
}
