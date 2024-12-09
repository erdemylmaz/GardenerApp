package app;

import fileIO.StorageReader;
import gardenobjects.GardenObject;
import gardenobjects.SearchableLightSource;
import gardenobjects.SearchablePlant;
import gardenobjects.lightsources.LightSource;
import gardenobjects.plants.GardenPlant;
import pollen.LightColor;

import java.util.ArrayList;
import java.util.List;

public class StorageShed {
    private final List<GardenObject> storage;
    enum ObjectType {
        PLANT,
        LIGHT_SOURCE
    }
    ObjectType objectType;

    enum SearchFilter {
        TYPE,
        ID,
        NAME_OR_COLOR,
        AREA
    }
    SearchFilter searchFilter;

    public StorageShed(String fileName) {
        this.storage = StorageReader.read(fileName);
    }

    public StorageShed(List<GardenObject> storage) {
        this.storage = storage;
    }

    // 1 for Plant, 2 for LightSource
    public void chooseType(int type) throws IndexOutOfBoundsException {
        objectType = ObjectType.values()[type - 1];
    }

    // 1 for type, 2 for id, 3 for color/name, 4 for area
    public void chooseSearchFilter(int filter) throws IndexOutOfBoundsException {
        searchFilter = SearchFilter.values()[filter - 1];
    }

    public List<GardenObject> getObjectsWithCriteria(String criteria) {
        List<GardenObject> result = new ArrayList<>();
        for (GardenObject object : storage) {
            if ((objectType == ObjectType.PLANT && object instanceof LightSource) || (objectType == ObjectType.LIGHT_SOURCE && object instanceof GardenPlant))
                continue;

            switch (searchFilter) {
                case TYPE:
                    if (object.checkByType(criteria)) {
                        result.add(object);
                    }
                    break;
                case NAME_OR_COLOR:
                    if (object instanceof SearchablePlant) {
                        if (((SearchablePlant) object).checkByName(criteria)) {
                            result.add(object);
                        }
                    } else if (object instanceof SearchableLightSource) {
                        try {
                            if (((SearchableLightSource) object).checkByColor(LightColor.valueOf(criteria.toUpperCase()))) {
                                result.add(object);
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: Color must be one of the following: RED, GREEN, BLUE, YELLOW, WHITE");
                            throw new IllegalArgumentException();
                        }
                    }
                    break;
                case ID:
                    if (object.checkByID(criteria)) {
                        result.add(object);
                    }
                    break;
                case AREA:
                    try {
                        if (object instanceof LightSource) {
                            if (((LightSource) object).checkByLightReach(Integer.parseInt(criteria))) {
                                result.add(object);
                            }
                        } else if (object instanceof GardenPlant) {
                            if (((GardenPlant) object).checkByPollenReach(Integer.parseInt(criteria))) {
                                result.add(object);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Area must be an integer.");
                        throw new NumberFormatException();
                    }
                    break;
            }
        }
        return result;
    }

    public GardenObject takeObjectByID(String id) {
        for (GardenObject object : storage) {
            if (object.checkByID(id)) {
                storage.remove(object);
                return object;
            }
        }
        return null;
    }
}
