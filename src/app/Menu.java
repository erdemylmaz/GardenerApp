package app;

import gardenobjects.GardenObject;
import pollen.LightColor;
import storageshed.StorageShed;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    public static final int MAX_GARDEN_OBJECT = 7;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void displayGoal(String[] pollen, LightColor[] color) {
        System.out.print("Welcome to Garden Puzzle App. Your goal Square needs ");
        for (int i = 0; i < pollen.length; i++) {
            System.out.print(pollen[i]);
            if (i < pollen.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(" infused with ");
        for (int i = 0; i < color.length; i++) {
            System.out.print(color[i]);
            if (i < color.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" color(s).");
    }

    public List<GardenObject> selectGardenObjectsFromStorageShed(StorageShed storageShed) {
        return selectGardenObjectFromStorageShed(new ArrayList<>(), storageShed, 0);
    }

    private List<GardenObject> selectGardenObjectFromStorageShed(List<GardenObject> objects, StorageShed storageShed, int count) {
        System.out.println("==> Please search for Garden Objects from the Storage Shed. You can take up to " + count + " object(s).");
        int type = selectType(storageShed);
        int searchFilter = selectSearchFilter(storageShed, type);
        List<GardenObject> selectedObjects = selectCriteria(storageShed, type, searchFilter);
        displaySelectedObjects(selectedObjects, type);
        GardenObject selectedObject = selectByID(storageShed, selectedObjects);
        objects.add(selectedObject);
        count++;
        if (count == MAX_GARDEN_OBJECT) {
            return objects;
        } else {
            System.out.print("You have taken " + count + " Garden Object(s). Would you like to select another one? ([1] Yes, [2] No): ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                return selectGardenObjectFromStorageShed(objects, storageShed, count);
            } else {
                return objects;
            }
        }
    }

    private int selectType(StorageShed storageShed) {
        System.out.print("Please select the type of object ([1] Plant, [2] Light): ");
        int type = scanner.nextInt();
        try {
            storageShed.chooseType(type);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid type. Please try again.");
            return selectType(storageShed);
        }
        return type;
    }

    private int selectSearchFilter(StorageShed storageShed, int type) {
        switch (type) {
            case 1:
                System.out.print("Please select search filter for Plants ([1] Plant type, [2] Plant name, [3] Plant id, [4] Area of spread):");
                break;
            case 2:
                System.out.print("Please select search filter for Light Sources ([1] Light type, [2] Light id, [3] Color, [4] Area of reach):");
                break;
            default:
                System.out.println("Something went wrong. Please try again.");
        }
        int searchFilter = scanner.nextInt();
        try {
            storageShed.chooseSearchFilter(searchFilter);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid search filter. Please try again.");
            return selectSearchFilter(storageShed, type);
        }
        return searchFilter;
    }

    private List<GardenObject> selectCriteria(StorageShed storageShed, int type, int searchFilter) {
        switch (type) {
            case 1:
                switch (searchFilter) {
                    case 1:
                        System.out.print("Please enter a plant type([1] Flower, [2] Tree, [3] Bush): ");
                        break;
                    case 2:
                        System.out.print("Please enter a plant name: ");
                        break;
                    case 3:
                        System.out.print("Please enter the plant id: ");
                        break;
                    case 4:
                        System.out.print("Please enter the area of spread value between 1 and 5: ");
                        break;
                    default:
                        System.out.println("Something went wrong. Please try again.");
                }
                break;
            case 2:
                switch (searchFilter) {
                    case 1:
                        System.out.print("Please enter the light type([1] Small Lamp, [2] Large Lamp, [3] Spotlight): ");
                        break;
                    case 2:
                        System.out.print("Please enter the light id: ");
                        break;
                    case 3:
                        System.out.print("Please enter the light color: ");
                        break;
                    case 4:
                        System.out.print("Please enter the light area of reach value between 1 and 5: ");
                        break;
                    default:
                        System.out.println("Something went wrong. Please try again.");
                }
                break;
            default:
                System.out.println("Something went wrong. Please try again.");
        }
        String criteria = scanner.nextLine();
        try {
            return storageShed.getObjectsWithCriteria(criteria);
        } catch (NumberFormatException e) {
            System.out.println("Expected a number. Please try again.");
            return selectCriteria(storageShed, type, searchFilter);
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter a valid argument. Please try again.");
            return selectCriteria(storageShed, type, searchFilter);
        }
    }

    private void displaySelectedObjects(List<GardenObject> selectedObjects, int type) {
        System.out.println("The " + (type == 1 ? "Plants" : "Light Sources") + " that fit the given criteria: ");
        for (GardenObject object : selectedObjects) {
            System.out.println(object.info());
        }
    }

    private GardenObject selectByID(StorageShed storageShed, List<GardenObject> selectedObjects) {
        System.out.print("Please enter the ID of the object you want to take: ");
        String id = scanner.nextLine();
        try {
            for (GardenObject object : selectedObjects) {
                if (object.getID().equals(id)) {
                    return storageShed.takeObjectByID(id);
                }
            }
            System.out.println("No object found with the given ID in the list. Please try again.");
            return selectByID(storageShed, selectedObjects);
        } catch (NoSuchElementException e) {
            System.out.println("No object found with the given ID. Please try again.");
            return selectByID(storageShed, selectedObjects);
        }
    }
}
