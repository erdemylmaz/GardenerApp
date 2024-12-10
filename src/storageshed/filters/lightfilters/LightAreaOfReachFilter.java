package storageshed.filters.lightfilters;

import gardenobjects.GardenObject;
import gardenobjects.SearchableLightSource;

public class LightAreaOfReachFilter extends LightFilter {
    public boolean filter(GardenObject object, String criteria) throws NumberFormatException {
        SearchableLightSource source = (SearchableLightSource) object;
        return source.checkByLightReach(Integer.parseInt(criteria));
    }
}
