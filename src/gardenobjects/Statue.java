package gardenobjects;

public class Statue extends GardenObject{

    public Statue(String ID) {
        setID(ID);
    }

    @Override
    public boolean checkByType(String type) {
        return false;
    }

    @Override
    public boolean checkByID(String id) {
        return false;
    }
}
