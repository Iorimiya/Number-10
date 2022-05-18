package ntou.cs.java2020.classproject;

import android.content.Context;

public class Level1 extends LevelActivity {

    public Level1(){
        this.bonusTime = 15;
        this.nowLevel = 1;
        this.nowLevelLayout = R.layout.level1_page;
        this.nowLevelRow = 6;
        this.nowLevelColumn = 3;
        this.nowLevelObject = Level1.this;
    }

    @Override
    protected boolean expression(int leftNumber1, int leftNumber2){
        return leftNumber1 * leftNumber2 == rightNumber;
    }
}
