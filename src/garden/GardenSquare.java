package garden;

import pollen.PollenCloud;

public class GardenSquare {
    private Placable content;
    private GardenPosition position;
    public GardenSquare() {
        content = null;
    }
    public boolean isOccupied() {
        if (content == null) return false;
        else return !(content instanceof PollenCloud);
    }
    /*
     *  if !isOccupied, add a cloud, infuse if a cloud already exists. return true
     *  else don't touch, return false
     */
    public boolean addCloud(PollenCloud cloud) {
        if (isOccupied()) return false;
        else if (content == null) {
            content = new PollenCloud(cloud);
        }
        else {
            PollenCloud contentPollenCloud = (PollenCloud) content;
            contentPollenCloud.infuse(cloud);
            this.content = contentPollenCloud;
        }
        return true;
    }

    public Placable getContent() {
        return this.content;
    }

    /* align content.getDisplay() to the middle of a 6 character string*/
    @Override
    public String toString() {
        String display = content.getDisplay();
        if (display.length() > 6) {
            System.out.println("display length greater than 6. table will be misaligned.");
        }
        int leftSpaces = (6 - display.length()) / 2;
        return " ".repeat(leftSpaces) + display + " ".repeat(6-display.length()-leftSpaces);
    }

    public GardenPosition getPosition() {
        return new GardenPosition(this.position);
    }
}