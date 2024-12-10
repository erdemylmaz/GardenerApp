package storageshed.filters;

import gardenobjects.GardenObject;

public interface SearchFilter {
    boolean filter(GardenObject object, String criteria);
}
