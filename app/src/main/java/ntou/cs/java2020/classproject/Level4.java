package ntou.cs.java2020.classproject;

import android.content.Context;

public class Level4 extends LevelActivity {

    public Level4(){
        this.bonusTime = 60;
        this.nowLevel = 4;
        this.nowLevelLayout = R.layout.level4_page;
        this.nowLevelRow = 8;
        this.nowLevelColumn = 4;
        this.nowLevelObject = Level4.this;
    }

    @Override
    protected boolean expression(int leftNumber1, int leftNumber2) {
        return 2 * (5 - leftNumber1) + leftNumber2 == 10;
    }
}