package ntou.cs.java2020.classproject;

public class Level5 extends LevelActivity {

    public Level5(){
        this.bonusTime = 90;
        this.nowLevel = 5;
        this.nowLevelLayout = R.layout.level5_page;
        this.nowLevelRow = 12;
        this.nowLevelColumn = 6;
        this.nowLevelObject = Level5.this;
    }

    @Override
    protected boolean expression(int leftNumber1, int leftNumber2){
        return (7 - leftNumber1) + 2 * leftNumber2 == 10;
    }
}
