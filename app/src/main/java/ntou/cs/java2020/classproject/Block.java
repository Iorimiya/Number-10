package ntou.cs.java2020.classproject;

import android.widget.Button;

public class Block extends OriginPair {

    public Button button;
    public boolean isExist=false,hasValue=false;
    Block(Button button,int row,int column){
        super(row,column);
        this.button=button;
        if(this.button!=null) this.isExist=true;
    }
//    在OriginPair的基礎之下加上在個元素連結的Button物件，就成了Block
//    再來我們在意的是Block是否存在，不存在就代表線可以連過去，像是最外圈永遠不存在，裡面消除的元素也會不存在，因此就有了isExist
//    最後，為避免發牌時發到重覆的元素，所以有hasNumber
}
