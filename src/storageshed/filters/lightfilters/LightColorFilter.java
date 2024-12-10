package storageshed.filters.lightfilters;

import gardenobjects.GardenObject;
import gardenobjects.SearchableLightSource;
import pollen.LightColor;

public class LightColorFilter extends LightFilter {
    public boolean filter(GardenObject object, String criteria) throws IndexOutOfBoundsException {
        SearchableLightSource source = (SearchableLightSource) object;
        return source.checkByColor(LightColor.valueOf(criteria));
    }
}
