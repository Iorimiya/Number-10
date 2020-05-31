package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class Level4 extends LevelActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level4_page);
//        連結頁面
//        link page object
        pagePrepare();
//        頁面準備
        gamePrepare(8,4);
//        遊戲準備
    }

    @Override
    protected void pagePrepare(){
        super.pagePrepare();
        findViewById(R.id.backButton).setOnClickListener(v -> {
            if(GlobalSettings.skipControl){
                Intent intent=new Intent(Level4.this, LevelWin.class);
                intent.putExtra("NextLevel",5);
                if(GlobalSettings.lastOpenedLevel<5) {
                    GlobalSettings.lastOpenedLevel = 5;
                    getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putInt("hasOpenedLevel", GlobalSettings.lastOpenedLevel).apply();
                }
                startActivity(intent);
            }
//                跳至下一關
//                skip to next level
            else
                startActivity(new Intent(Level4.this, Title.class));
//                回選單
//                back to the title
            finish();
        });
//        設定回選單按鈕功能
    }

    @Override
    protected void deal(){
        for(int firstCounter=1;firstCounter<=10;firstCounter++)
            for (int secondCounter = 1; secondCounter <= 10; secondCounter++)
                if (2 * (5 - firstCounter) + secondCounter == 10) {
                    boolean duplication = false;
                    for (ArrayList<Integer> pair : connectibleNumbers)
                        if ((firstCounter == pair.get(0) && secondCounter == pair.get(1)) || (firstCounter == pair.get(1) && secondCounter == pair.get(0))) {
                            duplication = true;
                            break;
                        }
                    if (!duplication) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(firstCounter);
                        temp.add(secondCounter);
                        connectibleNumbers.add(temp);
                    }
                }
//        創造符合條件的數字清單
//        create the pair list of correct numbers
        super.deal();
    }

    @Override
    protected boolean connectionAnalysis(){
        if(2 * (5 - firstClicked.getNumber()) + secondClicked.getNumber()!=10 && 2 * (5 - secondClicked.getNumber()) + firstClicked.getNumber()!=10 )
            return false;
        return super.connectionAnalysis();
    }

    @Override
    protected void finishProcess(){
        bonusTime=60;
        super.finishProcess();
        if(score>GlobalSettings.scoreList.get(3)) {
            GlobalSettings.scoreList.set(3, score);
            getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putInt("level1HighestScore", GlobalSettings.scoreList.get(3)).apply();
        }
        Intent intent=new Intent(Level4.this, LevelWin.class);
        intent.putExtra("NextLevel",5);
        if(GlobalSettings.lastOpenedLevel<5) {
            GlobalSettings.lastOpenedLevel = 5;
            getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putInt("hasOpenedLevel", GlobalSettings.lastOpenedLevel).apply();
        }
        startActivity(intent);
    }
}
