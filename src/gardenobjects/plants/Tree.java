package gardenobjects.plants;

import garden.GardenMap;
import garden.GardenPosition;
import garden.GardenSquare;
import garden.Placable;
import pollen.PollenCloud;

import java.util.Random;

/* fill */
public class Tree extends GardenPlant{
//    private static final String SPREAD_DIRECTION = "HORIZONTALLY";
    private static final int MAX_REACH = 5;
    private static final int MIN_REACH = 3;
    private int reach;

    public Tree() {
        super();
    }

    public Tree(GardenPosition position, String name) {
        super(position, name);
        declareReach();
    }

    public Tree(GardenPosition position, String name, PollenCloud cloud) {
        super(position, name, cloud);
        declareReach();
    }

    public Tree(Flower org) {
        super(org);
        setReach(org.getReach());
    }

    //    TALK TO TEAM
    @Override
    public void bloom(GardenMap map) {
        for(int i = 0; i < reach; i++) {
            // 2 for right and left
            // 0: right, 1: left
            for(int j = 0; j < 2; j++) {
                int newCol = this.getPosition().getRow();
                newCol += j == 0 ? (i + 1) : -1 * (i + 1);
                /* if controlling row is out of map borders,
                 * then continue to process it  */
                if(newCol < 0 || newCol >= GardenMap.COLUMN_COUNT) {
                    continue;
                }
                GardenPosition controllingPosition = new GardenPosition(this.getPosition().getRow(), newCol);
                GardenSquare controllingSquare = map.getSquare(controllingPosition);
                if(controllingSquare.isOccupied()) {
                    Placable content = controllingSquare.getContent();
                    if(content instanceof GardenPlant) {
                        GardenPlant plant = (GardenPlant) content;
                        if(plant.infuse(this.getPollenCloud())) {
                            plant.bloom(map);
                        }
                    }
                    break;
                } else {
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
