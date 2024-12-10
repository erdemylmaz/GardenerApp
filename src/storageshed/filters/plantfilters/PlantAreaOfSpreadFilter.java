package storageshed.filters.plantfilters;

import gardenobjects.GardenObject;
import gardenobjects.SearchablePlant;

public class PlantAreaOfSpreadFilter extends PlantFilter {
    public boolean filter(GardenObject object, String criteria) throws NumberFormatException {
        SearchablePlant plant = (SearchablePlant) object;
        return plant.checkByPollenReach(Integer.parseInt(criteria));
    }
}
