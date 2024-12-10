package garden;

import java.util.ArrayList;
import java.util.List;

public class GardenMap {
    public static final int ROW_COUNT = 6;
    public static final int COLUMN_COUNT = 8;
    public static final int STATUES = 7;

    private List<List<GardenSquare>> map;

    public GardenMap() {
        initializeMap();
        initializeStatues();
    }

    public boolean putItem(Placable item, GardenPosition position) {

    }

    public GardenSquare getSquare(GardenPosition position) {
        return map.get(position.getRow()).get(position.getColumn());
    }

    private void initializeMap() {
        map = new ArrayList<>();
        /*
         * i and j are dummy counters, never used
         */
        for (int i = 0; i < ROW_COUNT; i++) {
            List<GardenSquare> newRow = new ArrayList<>();
            for (int j = 0; j < COLUMN_COUNT; j++) {
                newRow.add(new GardenSquare());
            }
            map.add(newRow);
        } 
    }
    private void initializeStatues() {

    }
}
