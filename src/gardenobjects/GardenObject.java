package gardenobjects;

import garden.Placable;

public abstract class GardenObject implements Placable, Searchable {
    private String id;
    public String getID() {
        return id;
    }
    public void setID(String newId) {
        this.id = newId;
    }
    @Override
    public String getDisplay() {
        return this.getID();
    }
}
