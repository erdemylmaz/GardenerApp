package storageshed.filters.plantfilters;

import gardenobjects.GardenObject;

public class PlantTypeFilter extends PlantFilter {
    public boolean filter(GardenObject object, String criteria) {
        return object.checkByType(criteria);
    }
}
