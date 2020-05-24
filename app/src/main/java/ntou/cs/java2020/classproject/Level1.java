package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.*;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Level1 extends LevelActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1_page);
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
                if(GlobalSettings.skipControl) {
                    Intent intent=new Intent(Level1.this, LevelWin.class);
                    intent.putExtra("NextLevel",2);
                    if(GlobalSettings.lastOpenedLevel<2) {
                        GlobalSettings.lastOpenedLevel = 2;
                        getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putString("hasOpenedLevel", Integer.toString(GlobalSettings.lastOpenedLevel)).apply();
                    }
                    startActivity(intent);
                }else
                    startActivity(new Intent(Level1.this, Title.class));
                finish();
            }
        });
        gamePrepare(6,3);
    }

    @Override
    protected void gamePrepare(int row,int column){
        super.gamePrepare(row,column);
    }
    @Override
    protected boolean connectionAnalysis(){
        System.out.println("s");
        return false;
    }
    @Override
    protected void deal(){
        for(int firstCounter=1;firstCounter<=10;firstCounter++){
            for(int secondCounter=1;secondCounter<=10;secondCounter++){
                if(firstCounter*secondCounter==10){
                    boolean duplication=false;
                    for(ArrayList<Integer>pair:ConnectibleNumbers) {
                        if ((firstCounter == pair.get(0) && secondCounter == pair.get(1)) || (firstCounter == pair.get(1) && secondCounter == pair.get(0))) duplication=true;
                    }
                    if(!duplication) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(firstCounter);
                        temp.add(secondCounter);
                        ConnectibleNumbers.add(temp);
                    }
                }
            }
        }
        super.deal();
    }
}
