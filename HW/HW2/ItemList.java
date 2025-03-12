package HW.HW2;
import java.util.Locale;

public class ItemList {
    private ItemInfoNode head;
    private ItemInfoNode tail;

    public ItemList() {
        this.head = null;
        this.tail = null;
    }

    public void insertInfo(String name, String rfidTag, double price, String initPosition) {
        ItemInfo newInfo = new ItemInfo(name, rfidTag, price, initPosition);
        ItemInfoNode newNode = new ItemInfoNode();
        newNode.setInfo(newInfo);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            ItemInfoNode current = head;
            while (current != null && current.getInfo().getRfidTagNumber().compareTo(rfidTag) < 0) {
                current = current.getNext();
            }

            if (current == null) {
                newNode.setPrev(tail);
                tail.setNext(newNode);
                tail = newNode;
            } else if (current == head) {
                newNode.setNext(head);
                head.setPrev(newNode);
                head = newNode;
            } else {
                newNode.setNext(current);
                newNode.setPrev(current.getPrev());
                current.getPrev().setNext(newNode);
                current.setPrev(newNode);
            }
        }
    }

    public void removeAllPurchased() {
        ItemInfoNode current = head;
        while (current != null) {
            ItemInfoNode nextNode = current.getNext();
            if (current.getInfo().getCurrentLocation().equalsIgnoreCase("out")) {
                System.out.println("Removing item: " + current.getInfo().getName() + " (RFID: " + current.getInfo().getRfidTagNumber() + ")");
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                } else {
                    tail = current.getPrev();
                }
            }
            current = nextNode;
        }
    }

    public boolean moveItem(String rfidTag, String source, String dest) {
        if (source.equalsIgnoreCase("out")) {
            throw new IllegalArgumentException("The source location cannot be 'out'.");
        }
        if (!isValidLocation(dest)) {
            throw new IllegalArgumentException("The destination location format is invalid.");
        }

        ItemInfoNode current = head;
        while (current != null) {
            ItemInfo info = current.getInfo();
            if (info.getRfidTagNumber().equals(rfidTag) && info.getCurrentLocation().equals(source)) {
                info.setCurrentLocation(dest);
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void printAll() {
        System.out.println("Item List:");
        System.out.printf("%-20s %-10s %-15s %-15s %-10s\n", "Name", "RFID Tag", "Original Location", "Current Location", "Price");
        ItemInfoNode current = head;
        while (current != null) {
            ItemInfo info = current.getInfo();
            System.out.printf("%-20s %-10s %-15s %-15s %-10.2f\n", info.getName(), info.getRfidTagNumber(), info.getOriginalLocation(), info.getCurrentLocation(), info.getPrice());
            current = current.getNext();
        }
    }

    public void printByLocation(String location) {
        if (!isValidLocation(location)) {
            System.out.println("Invalid location.");
            return;
        }
        System.out.println("Item List at location " + location + ":");
        System.out.printf("%-20s %-10s %-15s %-15s %-10s\n", "Name", "RFID Tag", "Original Location", "Current Location", "Price");
        ItemInfoNode current = head;
        while (current != null) {
            ItemInfo info = current.getInfo();
            if (info.getCurrentLocation().equals(location)) {
                System.out.printf("%-20s %-10s %-15s %-15s %-10.2f\n", info.getName(), info.getRfidTagNumber(), info.getOriginalLocation(), info.getCurrentLocation(), info.getPrice());
            }
            current = current.getNext();
        }
    }

    public void cleanStore() {
        System.out.println("Cleaning the store:");
        System.out.printf("%-20s %-10s %-15s %-15s %-10s\n", "Name", "RFID Tag", "Current Location", "Original Location", "Price");
        ItemInfoNode current = head;
        while (current != null) {
            ItemInfo info = current.getInfo();
            if (!info.getCurrentLocation().equalsIgnoreCase("out") && !info.getCurrentLocation().startsWith("c") && !info.getCurrentLocation().equals(info.getOriginalLocation())) {
                System.out.printf("%-20s %-10s %-15s %-15s %-10.2f\n", info.getName(), info.getRfidTagNumber(), info.getCurrentLocation(), info.getOriginalLocation(), info.getPrice());
                info.setCurrentLocation(info.getOriginalLocation());
            }
            current = current.getNext();
        }
    }

    public double checkOut(String cartNumber) {
        if (!cartNumber.matches("c\\d{3}")) {
            throw new IllegalArgumentException("Invalid cart number.");
        }
        double totalCost = 0;
        System.out.println("Checking out cart " + cartNumber + ":");
        System.out.printf("%-20s %-10s %-15s %-15s %-10s\n", "Name", "RFID Tag", "Original Location", "Current Location", "Price");
        ItemInfoNode current = head;
        while (current != null) {
            ItemInfo info = current.getInfo();
            if (info.getCurrentLocation().equals(cartNumber)) {
                System.out.printf("%-20s %-10s %-15s %-15s %-10.2f\n", info.getName(), info.getRfidTagNumber(), info.getOriginalLocation(), "out", info.getPrice());
                info.setCurrentLocation("out");
                totalCost += info.getPrice();
            }
            current = current.getNext();
        }
        System.out.println("Total cost: " + totalCost);
        return totalCost;
    }

    private boolean isValidLocation(String location) {
        return location.matches("s\\d{5}") || location.matches("c\\d{3}") || location.equalsIgnoreCase("out");
    }
}