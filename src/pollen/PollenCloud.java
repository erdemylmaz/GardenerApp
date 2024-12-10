package pollen;
import garden.Placable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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

    public PollenCloud(PollenCloud cloudToCopy) {
        pollenTypes = new HashSet<>(cloudToCopy.pollenTypes);
        infusedColors = new HashSet<>(cloudToCopy.infusedColors);
    }

    /*
     * infuse this cloud with the cloud received from outside
     * returns true if cloud changed
     * false otherwise
     */
    public boolean infuse(PollenCloud other) {
        return (this.pollenTypes.addAll(other.pollenTypes) || this.infusedColors.addAll(other.infusedColors));
    }

    @Override
    public String getDisplay() {
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

    // ex:  BUSH pollens infused with GREEN color(s).
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        /*
         * append all pollen types
         */
        if (!pollenTypes.isEmpty()) {
            Iterator<PollenTypes> typeIterator = this.pollenTypes.iterator();
            while (typeIterator.hasNext()) {
                builder.append(typeIterator.next());
                if (typeIterator.hasNext()) builder.append(", ");
                else builder.append(' ');
            }
            builder.append("pollens");
        }
        else {
            builder.append("NO pollens");
        }
        /*
         * append all color types
         */
        if (!infusedColors.isEmpty()) {
            if (pollenTypes.isEmpty()) builder.append("infused with");
            Iterator<LightColor> colorIterator = this.infusedColors.iterator();
            while (colorIterator.hasNext()) {
                builder.append(colorIterator.next());
                if (colorIterator.hasNext()) builder.append(", ");
                else builder.append(' ');
            }
            builder.append("color(s).");
        }
        else builder.append('.');

        return builder.toString();
    }
}
