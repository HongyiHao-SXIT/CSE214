package HW.HW3;

import java.util.Scanner;

public class Analyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Elevator System Simulation");
        System.out.println("--------------------------");
        
        // Get input parameters with validation
        double probability = getValidInput(scanner, 
            "Enter probability of new request per time unit (0.0-1.0): ", 
            0.0, 1.0);
        
        int numFloors = (int) getValidInput(scanner, 
            "Enter number of floors in building (>1): ", 
            2, Integer.MAX_VALUE);
        
        int numElevators = (int) getValidInput(scanner, 
            "Enter number of elevators (>0): ", 
            1, Integer.MAX_VALUE);
        
        int simulationLength = (int) getValidInput(scanner, 
            "Enter simulation length in time units (>0): ", 
            1, Integer.MAX_VALUE);
        
        // Ask if debug mode should be enabled
        System.out.print("Enable debug mode? (y/n): ");
        String debugInput = scanner.next();
        Simulator.setDebug(debugInput.equalsIgnoreCase("y"));
        
        // Run simulation
        Simulator.simulate(probability, numFloors, numElevators, simulationLength);
        
        scanner.close();
    }
    
    private static double getValidInput(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a value between %.1f and %.1f%n", min, max);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
