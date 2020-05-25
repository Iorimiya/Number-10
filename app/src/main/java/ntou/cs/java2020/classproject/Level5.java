package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

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
        gamePrepare(12,6);
    }
    @Override
    protected void deal(){
        for(int firstCounter=1;firstCounter<=10;firstCounter++)
            for (int secondCounter = 1; secondCounter <= 10; secondCounter++)
                if (((7 - firstCounter) + 2 * secondCounter) == 10) {
                    boolean duplication = false;
                    for (ArrayList<Integer> pair : ConnectibleNumbers)
                        if ((firstCounter == pair.get(0) && secondCounter == pair.get(1)) || (firstCounter == pair.get(1) && secondCounter == pair.get(0))) {
                            duplication = true;
                            break;
                        }
                    if (!duplication) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(firstCounter);
                        temp.add(secondCounter);
                        ConnectibleNumbers.add(temp);
                    }
                }
        super.deal();
    }
}
