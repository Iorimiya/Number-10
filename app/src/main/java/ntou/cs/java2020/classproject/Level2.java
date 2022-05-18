package ntou.cs.java2020.classproject;

import android.content.Context;

public class Level2 extends LevelActivity {

    public Level2(){
        this.bonusTime = 20;
        this.nowLevel = 2;
        this.nowLevelLayout = R.layout.level2_page;
        this.nowLevelRow = 6;
        this.nowLevelColumn = 3;
        this.nowLevelObject = Level2.this;
    }

    @Override
    protected boolean expression(int leftNumber1, int leftNumber2){
        return 3 * (2 + leftNumber1) - leftNumber2 == rightNumber;
    }
}
