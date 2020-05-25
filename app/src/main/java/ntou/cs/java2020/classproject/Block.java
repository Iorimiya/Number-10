package ntou.cs.java2020.classproject;

import android.widget.Button;

public class Block extends OriginPair {
//Block在OriginPair的類別中新增了該元素在頁面中可供使用者點擊的按鈕物件，以及遊戲內關聯於該按鈕物件的相關屬性
//Block class extend the button object which is used to click by user on the page and the attribute associated to the button object in the game from the OriginPair class

    public Button button;
//    該Pair的按鈕物件，如果該位置沒有按鈕，則為null
//    the Button object of the pair. If no Button exists at the position, the value is "null"
    public boolean isExist=false,hasValue=false;
//    isExist：該Block是否有按鈕存在，若無則線可以穿越。
//    isExist:represent the exist of the button, Connection line can pass through if not.
//    hasValue：該按鈕上是否有數字存在，適用於發牌&洗牌時
//    hasValue:represent the exist of the number of the button, used to dealing and re-dealing

    Block(Button button,int row,int column){
        super(row,column);
        this.button=button;
        if(this.button!=null) this.isExist=true;
    }
}
