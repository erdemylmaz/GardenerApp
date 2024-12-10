package storageshed.filters.lightfilters;

import gardenobjects.GardenObject;

public class LightTypeFilter extends LightFilter {
    public boolean filter(GardenObject object, String criteria) {
        return object.checkByType(criteria);
    }
}
