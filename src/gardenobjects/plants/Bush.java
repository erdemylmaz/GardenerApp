package gardenobjects.plants;

import garden.GardenMap;
import garden.GardenPosition;
import garden.GardenSquare;
import garden.Placable;
import pollen.PollenCloud;

import java.util.Random;

/* fill */
public class Bush extends GardenPlant{
//    private static final String SPREAD_DIRECTION = "VERTICALLY";
    private static final int MAX_REACH = 2;
    private static final int MIN_REACH = 1;
    private int reach;

    public Bush() {
        super();
    }

    public Bush(GardenPosition position, String name) {
        super(position, name);
        declareReach();
    }

    public Bush(GardenPosition position, String name, PollenCloud cloud) {
        super(position, name, cloud);
        declareReach();
    }

    public Bush(Bush org) {
        super(org);
        setReach(org.getReach());
    }

    /*
        Spread current pollens to map.
    */
    @Override
    public void bloom(GardenMap map) {
        for(int i = 0; i < reach; i++) {
            /* 0: right side, 1: left side */
            for(int j = 0; j < 2; j++) {
                /* Calculate new row index */
                int newRow = this.getPosition().getRow();
                newRow += j == 0 ? -1 * (i + 1) : (i + 1);
                /*
                    Check if new row is inside garden borders.
                    if not, skip that position.
                */
                if(newRow < 0 || newRow >= GardenMap.ROW_COUNT) {
                    continue;
                }
                GardenPosition controllingPosition = new GardenPosition(newRow, this.getPosition().getColumn());
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
            System.out.println("Given reach is out of borders for bush.");
        }
        this.reach = reach;
    }
    public int getReach() {
        return this.reach;
    }
}
