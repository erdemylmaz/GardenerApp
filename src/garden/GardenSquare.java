package garden;

import gardenobjects.plants.GardenPlant;
import pollen.LightColor;
import pollen.PollenCloud;

import java.util.List;

public class GardenSquare {
    private Placable content;
    private GardenPosition position;
    public GardenSquare() {

    }
    public boolean isOccupied() {
        if (content == null) return false;
        else return !(content instanceof PollenCloud);
    }
    /*
     *  if !isOccupied, add a cloud, infuse if a cloud already exists. return true
     *  else don't touch, return false
     */
    public boolean addCloud(PollenCloud cloud) {
        return true;
    }

    public Placable getContent() {
        return this.content;
    }

    /*
     * setter, getter
     */
    
    /* heldObject.toString'i 6'lı stringe ortala */
    @Override
    public String toString() {
        return null;
    }

    public GardenPosition getPosition() {
        return new GardenPosition(this.position);
    }
}