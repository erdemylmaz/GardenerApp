package storageshed.filters.lightfilters;

import gardenobjects.GardenObject;
import storageshed.filters.SearchFilter;

// Have to be a class because we need to create an instance of it
public class LightFilter implements SearchFilter {
    @Override
    public boolean filter(GardenObject object, String criteria) {
        System.out.println("Please implement the filter in the subclass");
        return false;
    }
}