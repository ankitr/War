import java.util.*;

@SuppressWarnings("serial")
// Parameterized to make things prettier.
public class Queue<Type> extends LinkedList<Type>{
	public Queue() {}
    
	public void put(Type o) {
        addLast(o);
    }
    
    public Type get() {
        if (!this.isEmpty()) {
            return removeFirst();
        } else {
            System.err.println("You can\'t do that!");
            return null;
        } 
    }
    
    public Type peek() {
        return getFirst();
    }
}
