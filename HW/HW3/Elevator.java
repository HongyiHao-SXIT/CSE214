package HW.HW3;

public class Elevator {
    public static final int IDLE = 0;
    public static final int TO_SOURCE = 1;
    public static final int TO_DESTINATION = 2;
    
    private int currentFloor;
    private int elevatorState;
    private Request request;
    
    public Elevator() {
        this.currentFloor = 1;
        this.elevatorState = IDLE;
        this.request = null;
    }
    
    // Accessors
    public int getCurrentFloor() { return currentFloor; }
    public int getElevatorState() { return elevatorState; }
    public Request getRequest() { return request; }
    
    // Mutators
    public void setCurrentFloor(int floor) { this.currentFloor = floor; }
    public void setElevatorState(int state) { this.elevatorState = state; }
    public void setRequest(Request r) { this.request = r; }
    
    public boolean isIdle() {
        return elevatorState == IDLE;
    }
    
    public void move() {
        if (elevatorState == TO_SOURCE) {
            if (currentFloor < request.getSourceFloor()) {
                currentFloor++;
            } else if (currentFloor > request.getSourceFloor()) {
                currentFloor--;
            }
            
            if (currentFloor == request.getSourceFloor()) {
                elevatorState = TO_DESTINATION;
            }
        } else if (elevatorState == TO_DESTINATION) {
            if (currentFloor < request.getDestinationFloor()) {
                currentFloor++;
            } else if (currentFloor > request.getDestinationFloor()) {
                currentFloor--;
            }
            
            if (currentFloor == request.getDestinationFloor()) {
                elevatorState = IDLE;
                request = null;
            }
        }
    }
    
    @Override
    public String toString() {
        String stateStr = "";
        switch(elevatorState) {
            case IDLE: stateStr = "IDLE"; break;
            case TO_SOURCE: stateStr = "TO_SOURCE"; break;
            case TO_DESTINATION: stateStr = "TO_DESTINATION"; break;
        }
        return "Elevator[Floor: " + currentFloor + ", State: " + stateStr + 
               ", Request: " + (request != null ? request : "None") + "]";
    }
}