package gardenobjects.lightsources;

import garden.GardenMap;
import garden.GardenPosition;
import pollen.LightColor;

import java.util.Random;

/* fill */
public class Spotlight extends LightSource{
    private static final int MAX_REACH = 5;
    private static final int MIN_REACH = 4;
    private static final int dy = -1; // -1 to up, 1 to bottom
    private static final int dx = 0; // -1 to left, 1 to right
    private int lightReach; // random 3-4

    public Spotlight() {
        super();
    }

    public Spotlight(GardenPosition position, LightColor color) {
        super(position, color);
        declareLightReach();
    }

    public Spotlight(GardenPosition position, LightColor color, int lightReach) {
        super(position, color);
        setLightReach(lightReach);
    }

    public Spotlight(SmallLamp org) {
        super(org);
        setLightReach(org.getLightReach());
    }

    @Override
    public boolean checkByLightReach(int reach) {
        return this.lightReach == reach;
    }

    /* declares large lamp's random light react in between boundaries [1, 3] {1, 2, 3}*/
    private void declareLightReach() {
        Random rd = new Random();
        setLightReach(rd.nextInt(MIN_REACH, MAX_REACH + 1));
    }

    public void lightUp(GardenMap map) {
        super.lightUp(map, lightReach, dy, dx);
    }

    public int getLightReach() {
        return this.lightReach;
    }

    public void setLightReach(int lightReach) {
        if(lightReach < MIN_REACH || lightReach > MAX_REACH) {
            System.out.println("Given lightReach is out of range for spotlight.");
        }
        this.lightReach = lightReach;
    }

}
