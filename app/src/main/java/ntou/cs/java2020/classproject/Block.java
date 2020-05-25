package ntou.cs.java2020.classproject;

import android.view.View;
import android.widget.Button;

public class Block {
//Block為遊戲中基本之物件類別
//Block is the basic object class

    private Position position;
//    該Block位置
//    the position of the block
    private int number;
//    該Block所顯示的數字
//    the number displayed on the block
    private Button button;
//    該Block的按鈕物件，如果該位置沒有按鈕，則為null
//    the Button object of the pair. If no Button exists at the position, the value is "null"
    private boolean isExist=false,hasValue=false;
//    isExist：該Block是否有按鈕存在，若無則線可以穿越。
//    isExist:represent the exist of the button, Connection line can pass through if not.
//    hasValue：該按鈕上是否有數字存在，適用於發牌&洗牌時
//    hasValue:represent the exist of the number of the button, used to dealing and re-dealing


    public Block(int row,int column){
        this(row,column,null);
    }

    public Block(int row,int column,Button button){
        position=new Position(row,column);
        if(button!=null){
            this.button=button;
            this.isExist=true;
        }
    }

    public Position getPosition() {
        return position;
    }

    public int getRow() {
        return position.getRow();
    }

    public int getColumn(){
        return position.getColumn();
    }

    public int getNumber() {
        return number;
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        this.isExist = exist;
        updateButton();
    }

    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
        updateButton();
    }

    public void setNumber(int number) {
        this.number = number;
        this.setHasValue(true);
    }


    public void setOnClickListener(View.OnClickListener onClickListener){
        button.setOnClickListener(onClickListener);
    }

    public void setClickable(boolean Clickable){
        button.setEnabled(Clickable);
    }
    public void updateButton(){
        if(isExist&&button.getVisibility()==Button.INVISIBLE)
            button.setVisibility(Button.VISIBLE);
        else if(!isExist)
            button.setVisibility(Button.INVISIBLE);
        if(hasValue)
            button.setText(String.valueOf(number));
        else
            button.setText("");
    }

}
