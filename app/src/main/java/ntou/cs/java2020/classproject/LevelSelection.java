package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;

public class LevelSelection extends GlobalSettings {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_selection_page);
//        連結頁面
//        link page object
        findViewById(R.id.menuButton).setOnClickListener(v -> {
            startActivity(new Intent(LevelSelection.this, Title.class));
            finish();
        });
//        新增下一關按鈕的頁面移動監聽器
//        add the page moving listener of the back button
        findViewById(R.id.level1Button).setOnClickListener(v -> {
            startActivity(new Intent(LevelSelection.this, Level1.class));
            finish();
        });
//        新增第一關按鈕的頁面移動監聽器
//        add the page moving listener of the level 1 button
        findViewById(R.id.level2Button).setOnClickListener(v -> {
            startActivity(new Intent(LevelSelection.this, Level2.class));
            finish();
        });
//        新增第二關按鈕的頁面移動監聽器
//        add the page moving listener of the level 2 button
        findViewById(R.id.level3Button).setOnClickListener(v -> {
            startActivity(new Intent(LevelSelection.this, Level3.class));
            finish();
        });
//        新增第三關按鈕的頁面移動監聽器
//        add the page moving listener of the level 3 button
        findViewById(R.id.level4Button).setOnClickListener(v -> {
            startActivity(new Intent(LevelSelection.this, Level4.class));
            finish();
        });
//        新增第四關按鈕的頁面移動監聽器
//        add the page moving listener of the level 4 button
        findViewById(R.id.level5Button).setOnClickListener(v -> {
            startActivity(new Intent(LevelSelection.this, Level5.class));
            finish();
        });
//        新增第五關按鈕的頁面移動監聽器
//        add the page moving listener of the level 5 button
        if (GlobalSettings.lastOpenedLevel == 0)
            findViewById(getResources().getIdentifier("level1Button", "id", getPackageName())).setEnabled(true);
        else for (int counter = 1; counter <= GlobalSettings.lastOpenedLevel; counter++)
            findViewById(getResources().getIdentifier("level" + counter + "Button", "id", getPackageName())).setEnabled(true);
//        視選項決定開啟那些關卡
//        open the level due to the last opened level
    }
}
