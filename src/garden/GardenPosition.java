package garden;
public class GardenPosition {
    private int row;
    private int column;

    // dummy position
    public GardenPosition() {
        this(-1,-1);
    }

    public GardenPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // copy constructor
    public GardenPosition(GardenPosition positionToCopy) {
        this(positionToCopy.row, positionToCopy.column);
    }

    public int getRow() {return row;}
    public int getColumn() {return column;}
}
