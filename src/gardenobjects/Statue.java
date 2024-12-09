package gardenobjects;

public class Statue extends GardenObject{

    @Override
    public boolean checkByType(String type) {
        return false;
    }

    @Override
    public boolean checkByID(String id) {
        return false;
    }
}
