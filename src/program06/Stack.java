package program06;

public class Stack extends DynArray {
    private static final int defaultLength = 5; // default value that is passed in via the default constructor into the specifying constructor

    // default constructor
    public Stack() {
        this(defaultLength);
    }

    // specifying constructor
    public Stack(int length) {
        super(length);
    }

    // returns the number of values which are currently on the stack
    public int size() {
        return super.arraySize();
    }

    // returns true only if there are no values on the stack
    public boolean isEmpty() {
        // check if the index 0 is null. If null, then return true
        if(Double.isNaN(super.at(0))) {
            return true;
        } else {
            return false;
        }
    }

    // add the specified value onto the stack
    public void push(double value) {
        super.insert(value);
    }

    // if the stack is not empty,
    // remove and returns the most currently push'd value on the stack
    // otherwise,
    // returns Double.NaN
    public double pop() {
        if (this.isEmpty()) {
            return Double.NaN;
        } else {
            return super.remove();
        }
    }

    // print all of the values currenty on the stack, in the order that
    // they would be pop'd off of the stack
    public void stackDump() {
        for(int i = arraySize() - 1; i >= 0; i--) {
            if(super.at(i) >= 0) {
                System.out.println("\t"+super.at(i));
            }
        }
    }
}

