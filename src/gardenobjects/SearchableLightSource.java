package gardenobjects;

import pollen.LightColor;

public interface SearchableLightSource extends Searchable {
    public boolean checkByColor(LightColor color);
    public boolean checkByLightReach(int reach);
}
