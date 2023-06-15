package program09;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SalaryManager implements Raiseable{
    private int id;
    private double salary;
    private int yearsOfService;

    public SalaryManager() {
        this(1, 100000,1);
    }

    public SalaryManager(int id, double salary, int yearsOfService) {
        this.setId(id);
        this.setSalary(salary);
        this.setYearsOfService(yearsOfService);
    }

    public int getId() {
        return this.id;
    }

    public double getSalary() {
        return this.salary;
    }

    public int getYearsOfService() {
        return this.yearsOfService;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setSalary(double salary) {
        this.salary = salary;
    }

    private void setYearsOfService(int yearsOfService) {
        this.yearsOfService = yearsOfService;
    }


    public void create(String filename) {
        Path path = Paths.get(filename);

        try {
            Files.createFile(path);
        } catch (Exception e) {
            System.out.println("Failed to create file. Printing stacktrace:");
            e.printStackTrace();
        }
    }

    public void display(String fileName) {

    }

    public boolean addTo(String inFileName, String outFileName, int id, double salary, int yearsOfService) {

        return false;
    }

    public boolean removeFrom(String inFileName, String outFileName, int id) {

        return false;
    }

    public void addService(String inFileName, String outFileName) {

    }

    public int raise(String inFileName, String outFileName, int yearsOfService, double salaryIncPercent) {

        return 0;
    }

    public void mergeFiles(String inFileName1, String inFileName2, String outFileName) {

    }
}
