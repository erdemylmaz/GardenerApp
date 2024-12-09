package garden;

import java.util.ArrayList;

public class GardenMap {
    public static final int ROW_COUNT = 6;
    public static final int COLUMN_COUNT = 8;
    public static final int STATUES = 7;

    private ArrayList<ArrayList<GardenSquare>> map;

    /* constructor will initialize empty map, randomly place 7 statues and determine the goal position, may take randomizer as input */

    public GardenSquare getSquare(GardenPosition position) {
        return map.get(position.getRow()).get(position.getColumn());
    }
}
