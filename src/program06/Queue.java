package program06;

public class Queue extends DynArray{
    private static final int defaultLength = 5; // default value that is passed in via the default constructor into the specifying constructor

    // default constructor
    public Queue() {
        this(defaultLength);
    }

    // specifying constructor
    public Queue(int length) {
        super(length);
    }

    // returns the number of values which are currently in the queue
    public int size() {
        return super.arraySize();
    }

    // returns true only if there are no values in the queue
    public boolean isEmpty() {
        // Check if the index 0 is null. If null, then return true
        if(Double.isNaN(super.at(0))) {
            return true;
        } else {
            return false;
        }
    }

    // add the specified value into the queue
    public void que(double value) {
        super.insert(value);
    }

    // if the queue is not empty,
    // remove and returns the least currently que'd value in the queue
    // otherwise, returns Double.NaN
    public double deQue() {
        if(!this.isEmpty()) {
            return super.removeAt(0);
        } else {
            return Double.NaN;
        }
    }

    // print all of the values currenty in the queue, in the order that
    // they would be deQue'd from the queue
    public void queueDump() {
        for(int i = 0; i <= super.arraySize() - 1; i++) {
            if(super.at(i) >= 0) {
                System.out.println("\tarray.at(" + i + ") = " + super.at(i));
            }
        }
    }
}

