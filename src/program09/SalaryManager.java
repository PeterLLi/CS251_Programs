package program09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
        boolean written = false;
        String line = "";
        int lineId = 0;
        int index = 0;

        this.setId(id);
        this.setSalary(salary);
        this.setYearsOfService(yearsOfService);

        try(BufferedReader reader = new BufferedReader(new FileReader(inFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName))) {
            while((line = reader.readLine()) != null) {
                index = line.indexOf(":");
                lineId = Integer.parseInt(line.substring(0, index));

                if(lineId < this.getId() || written) {
                    writer.write(line);
                    continue;
                } else if(lineId > this.getId() && !written) {
                    writer.write(id+":" + salary + ":" + yearsOfService);
                    written = true;
                    writer.write(line); // Write the current line we're on
                }
            }
        } catch(Exception e) {
            System.out.println("Encountered exception. Printing stacktrace.");
            e.printStackTrace();
        }

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
