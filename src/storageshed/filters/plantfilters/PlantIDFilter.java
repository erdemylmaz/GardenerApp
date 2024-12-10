package storageshed.filters.plantfilters;

import gardenobjects.GardenObject;

public class PlantIDFilter extends PlantFilter {
    public boolean filter(GardenObject object, String criteria) {
        return object.checkByID(criteria);
    }
}
