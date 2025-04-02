package HW.HW3;

public class BooleanSource {
    private double probability;
    
    public BooleanSource(double p) throws IllegalArgumentException {
        if (p < 0.0 || p > 1.0) {
            throw new IllegalArgumentException("Probability must be between 0.0 and 1.0");
        }
        this.probability = p;
    }
    
    public boolean requestArrived() {
        return Math.random() < probability;
    }
}