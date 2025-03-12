package HW.HW2;

import java.util.Scanner;

public class DepartmentStore {
    public static void main(String[] args) {
        ItemList itemList = new ItemList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("I (Insert an item into the list)");
            System.out.println("M (Move an item in the store)");
            System.out.println("L (List items by location)");
            System.out.println("P (Print all items in the store)");
            System.out.println("O (Check out a cart)");
            System.out.println("C (Clean the store)");
            System.out.println("U (Update the inventory system)");
            System.out.println("Q (Exit the program)");
            System.out.print("Please enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "I":
                    try {
                        System.out.print("Please enter the item name: ");
                        String name = scanner.nextLine();
                        System.out.print("Please enter the RFID tag number: ");
                        String rfidTag = scanner.nextLine();
                        System.out.print("Please enter the item price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Please enter the initial location: ");
                        String initPosition = scanner.nextLine();
                        itemList.insertInfo(name, rfidTag, price, initPosition);
                        System.out.println("Item inserted successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                    break;
                case "M":
                    try {
                        System.out.print("Please enter the RFID tag number: ");
                        String rfidTag = scanner.nextLine();
                        System.out.print("Please enter the source location: ");
                        String source = scanner.nextLine();
                        System.out.print("Please enter the destination location: ");
                        String dest = scanner.nextLine();
                        if (itemList.moveItem(rfidTag, source, dest)) {
                            System.out.println("Item moved successfully.");
                        } else {
                            System.out.println("The specified item was not found.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                    break;
                case "L":
                    System.out.print("Please enter the location: ");
                    String location = scanner.nextLine();
                    itemList.printByLocation(location);
                    break;
                case "P":
                    itemList.printAll();
                    break;
                case "O":
                    try {
                        System.out.print("Please enter the cart number: ");
                        String cartNumber = scanner.nextLine();
                        itemList.checkOut(cartNumber);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                    break;
                case "C":
                    itemList.cleanStore();
                    break;
                case "U":
                    itemList.removeAllPurchased();
                    break;
                case "Q":
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }
    }
}