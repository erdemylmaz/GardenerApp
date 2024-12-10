package gardenobjects.plants;

import garden.GardenMap;
import garden.GardenPosition;
import garden.GardenSquare;
import garden.Placable;
import pollen.PollenCloud;

import java.util.Random;

/* fill */
public class Flower extends GardenPlant{
//    private static final String SPREAD_DIRECTION = "DIAGONALLY";
    private static final int MAX_REACH = 4;
    private static final int MIN_REACH = 2;
    private int reach;

    public Flower() {
        super();
    }

    public Flower(String name, String ID, int reach) {
        super(name, ID);
        setReach(reach);
    }

    public Flower(GardenPosition position, String name) {
        super(position, name);
        declareReach();
    }

    public Flower(GardenPosition position, String name, PollenCloud cloud) {
        super(position, name, cloud);
        declareReach();
    }

    public Flower(Flower org) {
        super(org);
        setReach(org.getReach());
    }

    /*
        Spread current pollens into map.
    */
    @Override
    public void bloom(GardenMap map) {
        /*
            There is 4 diagonal directions, loop each one
            0: top-right, 1: top-left, 2: bottom-left: 3: bottom-right
         */
        for(int diagonalIndex = 0; diagonalIndex < 4; diagonalIndex++) {
            /*
                for diagonalIndex less than 2, the y direction
                is up therefore -1 and same is applied to x direction
            */
            int dy = diagonalIndex < 2 ? -1 : 1;
            int dx = diagonalIndex == 0 || diagonalIndex == 2 ? -1 : 1;
            for(int i = 0; i < reach; i++) {
                /* Calculate new row and column indexes using dx, dy */
                int newRow = this.getPosition().getRow() + dx * (i + 1);
                int newCol = this.getPosition().getColumn() + dy * (i + 1);
                /*
                    Check if new position is inside garden borders.
                    if not, skip that position.
                */
                if(newRow < 0 || newRow >= GardenMap.ROW_COUNT || newCol < 0 || newCol >= GardenMap.COLUMN_COUNT) {
                    continue;
                }
                GardenPosition controllingPosition = new GardenPosition(newRow, newCol);
                GardenSquare controllingSquare = map.getSquare(controllingPosition);
                /*
                    If controlling position is already occupied,
                    check if it is a pollen, then infuse it and
                    stop the spreading in that direction.
                    otherwise add current cloud to that position.
                 */
                if(controllingSquare.isOccupied()) {
                    Placable content = controllingSquare.getContent();
                    if(content instanceof GardenPlant) {
                        GardenPlant plant = (GardenPlant) content;
                        /*
                         If the pollen cloud of that plant changed
                         then bloom that plant again with updated cloud.
                        */
                        if(plant.infuse(this.getPollenCloud())) {
                            plant.bloom(map);
                        };
                    }
                    break;
                } else {
                    /*
                        If controlling position is empty, then
                        add current cloud to that position.
                    */
                    controllingSquare.addCloud(this.getPollenCloud());
                }
            }
        }
    }

    @Override
    public boolean checkByPollenReach(int reach) {
        return this.reach == reach;
    }

    public void declareReach() {
        Random rd = new Random();
        setReach(rd.nextInt(MIN_REACH, MAX_REACH + 1));
    }

    public void setReach(int reach) {
        if(reach < MIN_REACH || reach > MAX_REACH) {
            System.out.println("Given reach is out of borders for flower.");
        }
        this.reach = reach;
    }
    public int getReach() {
        return this.reach;
    }
}
