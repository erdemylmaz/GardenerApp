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
import java.util.NoSuchElementException;

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
        for (GardenObject currentObject : storage) {
            /* if current currentObject is not type of
            * what we are looking for, skip that currentObject */
            if ((objectType == ObjectType.PLANT && currentObject instanceof LightSource) || (objectType == ObjectType.LIGHT_SOURCE && currentObject instanceof GardenPlant))
                continue;

            switch (searchFilter) {
                case TYPE:
                    if (currentObject.checkByType(criteria)) {
                        result.add(currentObject);
                    }
                    break;
                case NAME_OR_COLOR:
                    if (currentObject instanceof SearchablePlant) {
                        if (((SearchablePlant) currentObject).checkByName(criteria)) {
                            result.add(currentObject);
                        }
                    } else if (currentObject instanceof SearchableLightSource) {
                        try {
                            if (((SearchableLightSource) currentObject).checkByColor(LightColor.valueOf(criteria.toUpperCase()))) {
                                result.add(currentObject);
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: Color must be one of the following: RED, GREEN, BLUE, YELLOW, WHITE");
                            throw new IllegalArgumentException();
                        }
                    }
                    break;
                case ID:
                    if (currentObject.checkByID(criteria)) {
                        result.add(currentObject);
                    }
                    break;
                case AREA:
                    try {
                        if (currentObject instanceof LightSource) {
                            if (((LightSource) currentObject).checkByLightReach(Integer.parseInt(criteria))) {
                                result.add(currentObject);
                            }
                        } else if (currentObject instanceof GardenPlant) {
                            if (((GardenPlant) currentObject).checkByPollenReach(Integer.parseInt(criteria))) {
                                result.add(currentObject);
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

    public GardenObject takeObjectByID(String id) throws NoSuchElementException {
        for (GardenObject object : storage) {
            if (object.checkByID(id)) {
                storage.remove(object);
                return object;
            }
        }

        throw new NoSuchElementException("Object with ID " + id + " not found");
    }
}
