package pollen;
import garden.Placable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PollenCloud implements Placable {
    private Set<PollenTypes> pollenTypes;
    private Set<LightColor> infusedColors;

    public PollenCloud() {
        pollenTypes = new HashSet<>();
        infusedColors = new HashSet<>();
    }
    public PollenCloud(Collection<PollenTypes> types, Collection<LightColor> colors) {
        this();
        pollenTypes.addAll(types);
        infusedColors.addAll(colors);
    }
    public PollenCloud(PollenTypes[] types, LightColor[] colors) {
        this();
        pollenTypes.addAll(Arrays.asList(types));
        infusedColors.addAll(Arrays.asList(colors));
    }

    public PollenCloud(PollenCloud org) {
        pollenTypes = new HashSet<>(org.pollenTypes);
        infusedColors = new HashSet<>(org.infusedColors);
    }

    /*
     * infuse this cloud with the cloud received from outside
     */
    public void infuse(PollenCloud cloud) {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        if (pollenTypes.contains(PollenTypes.FLOWER)) builder.append('f');
        else builder.append('.');

        if (pollenTypes.contains(PollenTypes.TREE)) builder.append('t');
        else builder.append('.');

        if (pollenTypes.contains(PollenTypes.BUSH)) builder.append('u');
        else builder.append('.');

        if (infusedColors.contains(LightColor.RED)) builder.append('r');
        else builder.append('.');

        if (infusedColors.contains(LightColor.GREEN)) builder.append('g');
        else builder.append('.');

        if (infusedColors.contains(LightColor.BLUE)) builder.append('b');
        else builder.append('.');

        return builder.toString();
    }
}
