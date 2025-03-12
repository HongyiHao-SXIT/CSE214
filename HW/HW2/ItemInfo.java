package HW.HW2;

public class ItemInfo {
    private String name;
    private double price;
    private String rfidTagNumber;
    private String originalLocation;
    private String currentLocation;

    public ItemInfo(String name, String rfidTagNumber, double price, String originalLocation) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("The item name cannot be empty.");
        }
        if (!isValidRfidTagNumber(rfidTagNumber)) {
            throw new IllegalArgumentException("The RFID tag number must be a 9-character hexadecimal string.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("The item price must be a positive number.");
        }
        if (!isValidOriginalLocation(originalLocation)) {
            throw new IllegalArgumentException("The original location must start with's' followed by 5 digits.");
        }

        this.name = name;
        this.rfidTagNumber = rfidTagNumber;
        this.price = price;
        this.originalLocation = originalLocation;
        this.currentLocation = originalLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("The item name cannot be empty.");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("The item price must be a positive number.");
        }
        this.price = price;
    }

    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    public void setRfidTagNumber(String rfidTagNumber) {
        if (!isValidRfidTagNumber(rfidTagNumber)) {
            throw new IllegalArgumentException("The RFID tag number must be a 9-character hexadecimal string.");
        }
        this.rfidTagNumber = rfidTagNumber;
    }

    public String getOriginalLocation() {
        return originalLocation;
    }

    public void setOriginalLocation(String originalLocation) {
        if (!isValidOriginalLocation(originalLocation)) {
            throw new IllegalArgumentException("The original location must start with's' followed by 5 digits.");
        }
        this.originalLocation = originalLocation;
        this.currentLocation = originalLocation;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        if (!isValidCurrentLocation(currentLocation)) {
            throw new IllegalArgumentException("The current location format is invalid.");
        }
        this.currentLocation = currentLocation;
    }

    private boolean isValidRfidTagNumber(String rfidTagNumber) {
        return rfidTagNumber != null && rfidTagNumber.matches("[0-9A-Fa-f]{9}");
    }

    private boolean isValidOriginalLocation(String originalLocation) {
        return originalLocation != null && originalLocation.matches("s\\d{5}");
    }

    private boolean isValidCurrentLocation(String currentLocation) {
        return currentLocation != null && (currentLocation.matches("s\\d{5}") || currentLocation.matches("c\\d{3}") || currentLocation.equalsIgnoreCase("out"));
    }
}