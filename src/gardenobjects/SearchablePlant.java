package gardenobjects;

public interface SearchablePlant extends Searchable {
    public boolean checkByName(String name);
    public boolean checkByPollenReach(int reach);
}
