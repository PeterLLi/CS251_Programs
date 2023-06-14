package program09;

public class Driver {
    public static void main(String[] args) {
        boolean foundDuplicate = false;
        int entriesApplied = 0;

        Employee emp = new Employee();

        // Test creating a file.
        emp.create("Test1");

        // Test addding employees (non-repetitive) in ascending order, i.e., add to end of file.
        emp.addTo("Test1", "Test2", 1, 100000.00, 4);
        emp.addTo("Test2", "Test3", 3, 150000.00, 7);
        emp.addTo("Test3", "Test4", 7, 250000.00, 5);
        emp.addTo("Test4", "Test5", 11, 300000.00, 11);

        // Create a new file for merge testing.
        emp.create("Example1");
        emp.addTo("Example1", "Example2", 11, 600000.00, 30);
        foundDuplicate = emp.addTo("Example2", "Example3", 12, 125000.00, 25);
        System.out.println("Found duplicate: " + foundDuplicate);
        emp.addTo("Example3", "Example4", 14, 80000.00, 2);
        // Test adding an entry that already exists.
        foundDuplicate = emp.addTo("Example4", "Example5", 11, 100000.00, 2);
        System.out.println("Found duplicate: " + foundDuplicate);
        System.out.println();

        // Test merge methods.
        emp.mergeFiles("Test5", "Example5", "Merge1");

        // Create a new file for second merge testing.
        emp.create("Sample1");
        emp.addTo("Sample1", "Sample2", 1, 100000, 12);

        // Test second condition for merging.
        emp.mergeFiles("Sample2", "Test5", "Merge2");
        emp.mergeFiles("Test5", "Sample2", "Merge3");

        // Test remove and display method.
        System.out.println("Merge output: ");
        emp.display("Merge1");
        System.out.println(); // <<<--- Break in between last line and next header
        emp.removeFrom("Merge1", "Remove1", 11);
        System.out.println("Remove: ");
        emp.display("Remove1");
        System.out.println();

        // Test add service.
        emp.addService("Remove1", "AddService1");
        System.out.println("Add service: ");
        emp.display("AddService1");
        System.out.println();

        // Test raise.
        System.out.println("Raise: ");
        entriesApplied = emp.raise("AddService1", "Raise1", 25, 10);
        emp.display("Raise1");
        System.out.println("Entries applied: " + entriesApplied);
        entriesApplied = emp.raise("Raise1", "Raise2", 8, 10);
        System.out.println();
        emp.display("Raise2");
        System.out.println("Entries applied: " + entriesApplied);
    }
}

