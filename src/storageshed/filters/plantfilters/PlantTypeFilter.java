package storageshed.filters.plantfilters;

import gardenobjects.GardenObject;
import gardenobjects.plants.Bush;
import gardenobjects.plants.Flower;
import gardenobjects.plants.Tree;

public class PlantTypeFilter extends PlantFilter {
    public boolean filter(GardenObject object, String criteria) throws IllegalArgumentException {
        int intCriteria = Integer.parseInt(criteria);
        return switch (intCriteria) {
            case 1 -> object.checkByType(Flower.class.getName());
            case 2 -> object.checkByType(Tree.class.getName());
            case 3 -> object.checkByType(Bush.class.getName());
            default -> throw new IllegalArgumentException("Invalid plant type");
        };
    }
}
