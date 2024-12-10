package storageshed.filters.lightfilters;

import gardenobjects.GardenObject;

public class LightIDFilter extends LightFilter {
    public boolean filter(GardenObject object, String criteria) {
        return object.checkByID(criteria);
    }
}
