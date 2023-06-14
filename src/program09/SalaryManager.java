package program09;

public class SalaryManager {
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
}
