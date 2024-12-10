package storageshed.filters.lightfilters;

import gardenobjects.GardenObject;
import gardenobjects.lightsources.LargeLamp;
import gardenobjects.lightsources.SmallLamp;
import gardenobjects.lightsources.Spotlight;

public class LightTypeFilter extends LightFilter {
    public boolean filter(GardenObject object, String criteria) throws IllegalArgumentException {
        int intCriteria = Integer.parseInt(criteria);
        return switch (intCriteria) {
            case 1 -> object.checkByType(SmallLamp.class.getName());
            case 2 -> object.checkByType(LargeLamp.class.getName());
            case 3 -> object.checkByType(Spotlight.class.getName());
            default -> throw new IllegalArgumentException("Invalid light type");
        };
    }
}
