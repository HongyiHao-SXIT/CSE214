package HW.HW3;
import java.util.ArrayList;

public class RequestQueue extends ArrayList<Request> {
    public RequestQueue() {
        super();
    }
    
    public void enqueue(Request r) {
        this.add(r);
    }
    
    public Request dequeue() {
        if (this.isEmpty()) {
            return null;
        }
        return this.remove(0);
    }
    
    public int size() {
        return super.size();
    }
    
    public boolean isEmpty() {
        return super.isEmpty();
    }
}