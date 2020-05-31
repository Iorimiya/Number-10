package ntou.cs.java2020.classproject;

import android.content.Intent;
import android.os.Bundle;


public class Rule extends GlobalSettings{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rule_page);
//        連結頁面
//        link page object
        findViewById(R.id.backButton).setOnClickListener(v -> {
            startActivity(new Intent(Rule.this,Title.class));
            finish();
        });
//        新增設定按鈕的頁面移動監聽器
//        add the page moving listener of the back button
    }
}
