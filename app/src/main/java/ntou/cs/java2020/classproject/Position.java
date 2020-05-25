package ntou.cs.java2020.classproject;
public class Position {

    private final int row,column;

    Position(){
        this(0,0);
    }

    Position(int row, int column){
        this.row=row;
        this.column=column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
