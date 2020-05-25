package ntou.cs.java2020.classproject;
public class OriginPair {
//OriginPair為遊戲中最基本的元素物件，以列、行、數值組成
//OriginPair class is the basic element object in the game, compare with row, column and value.

    public final int row,column;
    public int value;

    OriginPair(int row,int column){
        this.row=row;
        this.column=column;
    }
}
