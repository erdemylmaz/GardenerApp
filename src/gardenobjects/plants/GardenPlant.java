package gardenobjects.plants;
import garden.GardenMap;
import garden.GardenPosition;
import gardenobjects.GardenObject;
import gardenobjects.SearchablePlant;
import pollen.PollenCloud;

public abstract class GardenPlant extends GardenObject implements SearchablePlant {
    private String name;
    private PollenCloud cloudToSpread;
    private GardenPosition position;

    public GardenPlant() {
        System.out.println("You need to provide at least position and name? to create a GardenPlant");
        System.exit(0);
    }

//    Name, ID, etc.
    public GardenPlant(String name, String ID) {
        setName(name);
        setID(ID);
    }

    public GardenPlant(GardenPosition position, String name) {
        setPosition(position);
        setName(name);
    }

    public GardenPlant(GardenPosition position, String name, PollenCloud cloudToSpread) {
        setPosition(position);
        setPollenCloud(cloudToSpread);
        setName(name);
    }

    /* Copy constructor */
    public GardenPlant(GardenPlant org) {
        setPosition(org.getPosition());
        setPollenCloud(org.getPollenCloud());
        setName(org.getName());
    }

    @Override
    public boolean checkByID(String id) {
        return this.getID().equals(id);
    }

    @Override
    public boolean checkByType(String type) {
        return type.equals(this.getClass().getName());
    }

    @Override
    public boolean checkByName(String name) {
        return this.name.equals(name);
    }

    @Override
    abstract public boolean checkByPollenReach(int reach);

    /*
     * infuse cloudToSpread with the cloud received from outside
     * 
     * returns true if cloudToSpread changed, false otherwise
     */
    public boolean infuse(PollenCloud outerCloud) {
        return cloudToSpread.infuse(outerCloud);
    }

    /*
     * spread its pollen on the map
     */
    public abstract void bloom(GardenMap map);

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PollenCloud getPollenCloud() {
        return cloudToSpread;
    }

    public void setPollenCloud(PollenCloud cloudToSpread) {
        this.cloudToSpread = new PollenCloud(cloudToSpread);
    }

    public void setPosition(GardenPosition position) {
        /* check if given position is valid */
        if(position.getColumn() < 0 || position.getRow() < 0 || position.getColumn() >= GardenMap.COLUMN_COUNT || position.getRow() >= GardenMap.ROW_COUNT) {
            System.out.println("Given position is out of borders.");
            System.exit(0);
        }
        this.position = new GardenPosition(position);
    }

    public GardenPosition getPosition() {
        return new GardenPosition(position);
    }
}
