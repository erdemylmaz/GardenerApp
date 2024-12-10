package storageshed;

import fileIO.StorageReader;
import gardenobjects.GardenObject;
import storageshed.filters.SearchFilter;
import storageshed.filters.lightfilters.*;
import storageshed.filters.plantfilters.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StorageShed {
    private final List<GardenObject> storage;
    private SearchFilter searchFilter;

    public StorageShed(String fileName) {
        this.storage = StorageReader.read(fileName);
    }

    public StorageShed(List<GardenObject> storage) {
        this.storage = storage;
    }

    // 1 for Plant, 2 for LightSource
    public void chooseType(int type) throws IndexOutOfBoundsException {
        switch (type) {
            case 1:
                searchFilter = new PlantFilter();
                break;
            case 2:
                searchFilter = new LightFilter();
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid type");
        }
    }

    // 1 for type, 2 for id, 3 for color/name, 4 for area
    public void chooseSearchFilter(int filter) throws IndexOutOfBoundsException {
        if (this.searchFilter instanceof PlantFilter) {
            switch (filter) {
                case 1:
                    this.searchFilter = new PlantTypeFilter();
                    break;
                case 2:
                    this.searchFilter = new PlantIDFilter();
                    break;
                case 3:
                    this.searchFilter = new PlantNameFilter();
                    break;
                case 4:
                    this.searchFilter = new PlantAreaOfSpreadFilter();
                    break;
                default:
                    throw new IndexOutOfBoundsException("Invalid filter");
            }
        } else if (this.searchFilter instanceof LightFilter) {
            switch (filter) {
                case 1:
                    this.searchFilter = new LightTypeFilter();
                    break;
                case 2:
                    this.searchFilter = new LightIDFilter();
                    break;
                case 3:
                    this.searchFilter = new LightColorFilter();
                    break;
                case 4:
                    this.searchFilter = new LightAreaOfReachFilter();
                    break;
                default:
                    throw new IndexOutOfBoundsException("Invalid filter");
            }
        } else {
            throw new RuntimeException("Please implement the filter for the chosen type");
        }
    }

    public List<GardenObject> getObjectsWithCriteria(String criteria) throws IllegalArgumentException {
        List<GardenObject> result = new ArrayList<>();
        for (GardenObject currentObject : storage) {
            if (this.searchFilter.filter(currentObject, criteria)) {
                result.add(currentObject);
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
