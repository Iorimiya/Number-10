package ntou.cs.java2020.classproject;

import android.widget.Button;

public class Block {
    public final int row,column;
    public Button button=null;
    public boolean exist=false;
    Block(Button button,int row,int column){
        this.button=button;
        this.row=row;
        this.column=column;
        this.exist=true;
    }
}
