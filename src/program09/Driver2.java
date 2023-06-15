package program09;

public class Driver2 {
    public static void main(String[] args) {
        SalaryManager sm = new SalaryManager();

        sm.create("Test1");

        sm.addTo("Test1", "Test2", 1, 100000.00, 4);
        sm.addTo("Test2", "Test3", 3, 150000.00, 7);
        sm.addTo("Test3", "Test4", 7, 250000.00, 5);
        sm.addTo("Test4", "Test5", 11, 300000.00, 11);

        sm.addTo("Test5", "Test6", 5, 125000, 3);
    }
}
