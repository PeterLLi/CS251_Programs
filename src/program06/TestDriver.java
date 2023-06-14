package program06;

public class TestDriver {
    public static void main(String[] args) {
        Queue myQueue = new Queue();
        double value;
        System.out.println("\n");
        System.out.println("Filling Queue:");
        for (int q = 1; q < 11; ++q) {
            value = 2 * q + q / 10.0;
            System.out.println("\tqueing " + value);
            myQueue.que(value);
        }
        System.out.println("\nQueue Dump:");
        myQueue.queueDump();
        System.out.println("\nEmptying Queue:");
        while (!myQueue.isEmpty()) {
            value = myQueue.deQue();
            System.out.println("\tdeQue = " + value);
        }
        System.out.println("\nQueue Dump:");
        myQueue.queueDump();
        System.out.println("\nA deQue too far = " + myQueue.deQue());
    }
}
