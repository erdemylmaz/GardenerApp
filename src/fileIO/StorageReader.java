package fileIO;

import garden.GardenPosition;
import gardenobjects.GardenObject;
import gardenobjects.lightsources.LargeLamp;
import gardenobjects.lightsources.SmallLamp;
import gardenobjects.lightsources.Spotlight;
import gardenobjects.plants.Bush;
import gardenobjects.plants.Flower;
import gardenobjects.plants.Tree;
import pollen.LightColor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageReader {

    // reads the storage_contents.csv file using format
    // type,id,name,area
    public static List<GardenObject> read(String fileName) {
        List<GardenObject> result = new ArrayList<>();
        int lineNumber = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] pieces = line.split(",");
                if (pieces.length != 4) {
                    // could throw error here
                    System.out.println("Error: Wrong file format for reading storage contents in " + fileName);
                    System.exit(1);
                }
                String type = pieces[0];
                String id = pieces[1];
                String name = pieces[2]; // color for lights
                int area = Integer.parseInt(pieces[3]);
                switch (type) {
                    case "flower":
                        result.add(new Flower(name, id, area));
                        break;
                    case "tree":
                        result.add(new Tree(name, id, area));
                        break;
                    case "bush":
                        result.add(new Bush(name, id, area));
                        break;
                    case "small_lamp":
                        result.add(new SmallLamp(LightColor.valueOf(name.toUpperCase()), id, area));
                        break;
                    case "large_lamp":
                        result.add(new LargeLamp(LightColor.valueOf(name.toUpperCase()), id, area));
                        break;
                    case "spotlight":
                        result.add(new Spotlight(LightColor.valueOf(name.toUpperCase()), id, area));
                        break;
                    default:
                        System.out.println("Error: Unknown type in " + fileName + " at line " + lineNumber);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Error: Could not find the '" + fileName + "' file for reading storage contents");
            e.printStackTrace();
            System.exit(1);
        } catch(IOException e) {
            System.out.println("Error: Could not read the line: " + lineNumber + " of " + fileName);
            e.printStackTrace();
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("Error: Could not parse the number in " + fileName + " at line " + lineNumber);
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Could not parse the color in " + fileName + " at line " + lineNumber);
            e.printStackTrace();
            System.exit(1);
        }
        return result;
    }
}
