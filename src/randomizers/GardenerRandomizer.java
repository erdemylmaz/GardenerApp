package randomizers;

import garden.GardenMap;
import garden.GardenPosition;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import pollen.LightColor;
import pollen.PollenCloud;
import pollen.PollenTypes;

public class GardenerRandomizer {
    private final Random randomizer;

    public GardenerRandomizer() {
        randomizer = new Random();
    }

    public GardenPosition randomPosition() {
        return new GardenPosition(randomizer.nextInt(GardenMap.ROW_COUNT), randomizer.nextInt(GardenMap.COLUMN_COUNT));
    }
    public PollenCloud randomCloud() {
        Set<PollenTypes> types = new HashSet<>();
        if (randomizer.nextBoolean()) types.add(PollenTypes.BUSH);
        if (randomizer.nextBoolean()) types.add(PollenTypes.TREE);
        if (randomizer.nextBoolean()) types.add(PollenTypes.FLOWER);

        if (types.isEmpty()) { // ensure there is at least one pollen type in the cloud
            types.add(PollenTypes.values()[randomizer.nextInt(3)]);
        }

        Set<LightColor> colors = new HashSet<>();
        if (randomizer.nextBoolean()) colors.add(LightColor.RED);
        if (randomizer.nextBoolean()) colors.add(LightColor.BLUE);
        if (randomizer.nextBoolean()) colors.add(LightColor.GREEN);

        return new PollenCloud(types, colors);
    }
}
