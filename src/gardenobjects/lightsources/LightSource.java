package gardenobjects.lightsources;

import garden.GardenMap;
import garden.GardenPosition;
import garden.GardenSquare;
import gardenobjects.GardenObject;
import gardenobjects.SearchableLightSource;
import pollen.LightColor;
import pollen.PollenCloud;

import java.util.ArrayList;
import java.util.List;

/* fill */
public abstract class LightSource extends GardenObject implements SearchableLightSource {
    private GardenPosition position;
    private LightColor lightColor;

    /* constructor */
    public LightSource() {
        System.out.println("You need to provide position [GardenPosition] and light color [LightColor] to create a light source.");
        System.exit(0);
    }

    public LightSource(LightColor lightColor, String ID) {
        setLightColor(lightColor);
        setID(ID);
    }

    public LightSource(GardenPosition position, LightColor lightColor) {
        setPosition(position);
        setLightColor(lightColor);
    }

    public LightSource(LightSource org) {
        setPosition(org.getPosition());
        setLightColor(org.getLightColor());
    }

    /*
     * lights up the proper positions in the map
     */
    public void lightUp(GardenMap map, int lightReach, int dy, int dx) {
        for(int i = 0; i < lightReach; i++) {
            int controllingRow = this.getPosition().getRow() + (i + 1) * dy;
            int controllingCol = this.getPosition().getColumn() + (i + 1) * dx;
            /* If controlling position is not in borders,
            * stop the lighting up process. */
            if (controllingCol < 0 || controllingCol >= GardenMap.COLUMN_COUNT || controllingRow < 0 || controllingRow >= GardenMap.ROW_COUNT) {
                break;
            }
            GardenPosition checkingPosition = new GardenPosition(controllingRow, controllingCol);
            GardenSquare checkingSquare = map.getSquare(checkingPosition);
                    /* Create empty but colorized cloud to add it to checking position */
            List<LightColor> colors = new ArrayList<>();
            colors.add(this.getLightColor());
            PollenCloud emptyColorizedCloud = new PollenCloud(new ArrayList<>(), colors);

            checkingSquare.addCloud(emptyColorizedCloud);

            /* If checking position is already occupied,
            * stop the lighting up process after effecting
            * that position. */
            if(checkingSquare.isOccupied()) {
                break;
            }
        }
    };

    public boolean checkByType(String type) {
        return type.equals(this.getClass().getName());
    }

    public boolean checkByID(String id) {
        return this.getID().equals(id);
    }

    public boolean checkByColor(LightColor color) {
        return this.lightColor.equals(color);
    }

    /* different for each light type Large, Small, Spotlight etc. */
    abstract public boolean checkByLightReach(int reach);

    /* returns copy of current position of light source */
    public GardenPosition getPosition() {
        return new GardenPosition(position);
    }

    public void setPosition(GardenPosition position) {
        /* check if given position is valid */
        if(position.getColumn() < 0 || position.getRow() < 0 || position.getColumn() >= GardenMap.COLUMN_COUNT || position.getRow() >= GardenMap.ROW_COUNT) {
            System.out.println("Given position is out of borders.");
            System.exit(0);

//            throw new PositionOutOfBordersException("Given position is out of garden borders.");
        }
        this.position = new GardenPosition(position);
    }

    public LightColor getLightColor() {
        return this.lightColor;
    }

    public void setLightColor(LightColor lightColor) {
        this.lightColor = lightColor;
    }

}
