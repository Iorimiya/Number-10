package ntou.cs.java2020.classproject;

public class Level3 extends LevelActivity {

    public Level3(){
        this.bonusTime = 45;
        this.nowLevel = 3;
        this.nowLevelLayout = R.layout.level3_page;
        this.nowLevelRow = 8;
        this.nowLevelColumn = 4;
        this.nowLevelObject = Level3.this;
    }

    @Override
    protected boolean expression(int leftNumber1, int leftNumber2){
        return leftNumber1 + leftNumber2 == rightNumber;
    }
}
