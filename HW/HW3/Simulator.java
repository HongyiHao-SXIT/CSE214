package HW.HW3;

public class Simulator {
    private static boolean debug = false;
    
    public static void simulate(double probability, int numFloors, int numElevators, int simulationLength) {
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("Probability must be between 0.0 and 1.0");
        }
        if (numFloors <= 1) {
            throw new IllegalArgumentException("Number of floors must be greater than 1");
        }
        if (numElevators <= 0) {
            throw new IllegalArgumentException("Number of elevators must be greater than 0");
        }
        if (simulationLength <= 0) {
            throw new IllegalArgumentException("Simulation length must be greater than 0");
        }
        
        BooleanSource arrival = new BooleanSource(probability);
        RequestQueue queue = new RequestQueue();
        Elevator[] elevators = new Elevator[numElevators];
        for (int i = 0; i < numElevators; i++) {
            elevators[i] = new Elevator();
        }
        
        int totalWaitTime = 0;
        int requestsCompleted = 0;
        
        for (int time = 0; time < simulationLength; time++) {
            if (debug) {
                System.out.println("\n=== Time Unit " + time + " ===");
            }
            
            // Check for new request
            if (arrival.requestArrived() && (queue.size() == 0 || time > 0)) {
                Request newRequest = new Request(numFloors);
                newRequest.setTimeEntered(time);
                queue.enqueue(newRequest);
                if (debug) {
                    System.out.println("New request added: " + newRequest);
                }
            }
            
            // Assign requests to idle elevators
            while (!queue.isEmpty()) {
                boolean assigned = false;
                for (Elevator elevator : elevators) {
                    if (elevator.isIdle()) {
                        Request request = queue.dequeue();
                        elevator.setRequest(request);
                        elevator.setElevatorState(Elevator.TO_SOURCE);
                        assigned = true;
                        if (debug) {
                            System.out.println("Assigned " + request + " to elevator");
                        }
                        break;
                    }
                }
                if (!assigned) break;
            }
            
            // Move all elevators
            for (Elevator elevator : elevators) {
                if (!elevator.isIdle()) {
                    int prevFloor = elevator.getCurrentFloor();
                    elevator.move();
                    
                    if (debug) {
                        System.out.println("Elevator moved from " + prevFloor + " to " + elevator.getCurrentFloor());
                        System.out.println(elevator);
                    }
                    
                    // Check if reached source floor (wait time ends)
                    if (elevator.getElevatorState() == Elevator.TO_DESTINATION && 
                        elevator.getCurrentFloor() == elevator.getRequest().getSourceFloor()) {
                        int waitTime = time - elevator.getRequest().getTimeEntered();
                        totalWaitTime += waitTime;
                        requestsCompleted++;
                        if (debug) {
                            System.out.println("Request picked up. Wait time: " + waitTime);
                        }
                    }
                }
            }
        }
        
        // Calculate and print results
        if (requestsCompleted > 0) {
            double averageWaitTime = (double) totalWaitTime / requestsCompleted;
            System.out.printf("Average wait time: %.2f time units%n", averageWaitTime);
        } else {
            System.out.println("No requests were completed during the simulation.");
        }
    }
    
    public static void setDebug(boolean debugMode) {
        debug = debugMode;
    }
}