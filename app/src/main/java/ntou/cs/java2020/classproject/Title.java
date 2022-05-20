package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;

public class Title extends GlobalSettings {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_page);
//        連結頁面
//        link page object
        findViewById(R.id.newGameButton).setOnClickListener(v -> {
            if(GlobalSettings.lastOpenedLevel<1) {
                GlobalSettings.lastOpenedLevel = 1;
                getSharedPreferences("NumberTenSaveData", MODE_PRIVATE).edit().putInt("hasOpenedLevel", GlobalSettings.lastOpenedLevel).apply();
            }
            startActivity(new Intent(Title.this,Level1.class));
            finish();
        });
//        新增新遊戲按鈕的頁面移動監聽器
//        add the page moving listener of the new game button
        findViewById(R.id.levelSelectionButton).setOnClickListener(v -> {
            startActivity(new Intent(Title.this,LevelSelection.class));
            finish();
        });
//        新增關卡選擇按鈕的頁面移動監聽器
//        add the page moving listener of the level selection button
        findViewById(R.id.ruleButton).setOnClickListener(v -> {
            startActivity(new Intent(Title.this,Rule.class));
            finish();
        });
//        新增規則按鈕的頁面移動監聽器
//        add the page moving listener of the rule button
        findViewById(R.id.settingButton).setOnClickListener(v -> {
            startActivity(new Intent(Title.this,Setting.class));
            finish();
        });
//        新增設定按鈕的頁面移動監聽器
//        add the page moving listener of the setting button
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}