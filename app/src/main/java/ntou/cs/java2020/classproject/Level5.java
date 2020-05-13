package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Level5 extends LevelActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level5_page);
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
                if(GlobalSettings.skipControl)
                    startActivity(new Intent(Level5.this, Congratulations.class));
                else
                    startActivity(new Intent(Level5.this, Title.class));
                finish();
            }
        });
    }
}
