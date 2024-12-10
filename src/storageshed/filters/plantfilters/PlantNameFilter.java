package storageshed.filters.plantfilters;

import gardenobjects.GardenObject;
import gardenobjects.SearchablePlant;

public class PlantNameFilter extends PlantFilter {
    public boolean filter(GardenObject object, String criteria) {
        SearchablePlant plant = (SearchablePlant) object;
        return plant.checkByName(criteria);
    }
}
