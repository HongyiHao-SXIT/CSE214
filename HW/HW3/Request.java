public package HW.HW3;

import java.util.Random;

public class Request {
    private int sourceFloor;
    private int destinationFloor;
    private int timeEntered;
    
    public Request(int numFloors) {
        Random rand = new Random();
        this.sourceFloor = rand.nextInt(numFloors) + 1;
        do {
            this.destinationFloor = rand.nextInt(numFloors) + 1;
        } while (destinationFloor == sourceFloor);
    }
    
    // Accessors
    public int getSourceFloor() { return sourceFloor; }
    public int getDestinationFloor() { return destinationFloor; }
    public int getTimeEntered() { return timeEntered; }
    
    // Mutators
    public void setTimeEntered(int time) { this.timeEntered = time; }
    
    @Override
    public String toString() {
        return "Request[From " + sourceFloor + " to " + destinationFloor + ", entered at " + timeEntered + "]";
    }
}