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
    boolean firstNewFile = true;
    boolean file1FirstRead = true;
    boolean file2FirstRead = true;

    public SalaryManager() {
        this(1, 100000,1);
    }

    public SalaryManager(int id, double salary, int yearsOfService) {
        this.setId(id);
        this.setSalary(salary);
        this.setYearsOfService(yearsOfService);
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
        String line = "";
        String format = "%-5s%-10s%-50s";
        String[] data = new String[3];

        int id = 0;
        double salary = 0.0;
        int yearsOfService = 0;

        System.out.printf(format, "ID", "Salary", "Years of Service");
        System.out.println();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while((line = reader.readLine()) != null) {
                data = line.split(":");
                System.out.printf(format, data[0], data[1], data[2]);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Encountered an exception opening file. Prining stacktrace:");
            e.printStackTrace();
        }
    }

    public boolean addTo(String inFileName, String outFileName, int id, double salary, int yearsOfService) {
        boolean written = false;
        boolean match = false;

        String line = "";
        int lineId = 0;
        int index = 0;

        this.setId(id);
        this.setSalary(salary);
        this.setYearsOfService(yearsOfService);

        try(BufferedReader reader = new BufferedReader(new FileReader(inFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName))) {
            if(firstNewFile) {
                writer.write(id + ":" + salary + ":" + yearsOfService);
                writer.newLine();

                firstNewFile = false;
                written = true;
            }

            while((line = reader.readLine()) != null) {
                index = line.indexOf(":");
                lineId = Integer.parseInt(line.substring(0, index));

                if(lineId < this.getId() || written) {
                    writer.write(line);
                    writer.newLine();
                    continue;
                } else if(lineId > this.getId() && !written) {
                    writer.write(id + ":" + salary + ":" + yearsOfService);
                    writer.newLine();
                    writer.write(line); // Write the current line we're on
                    writer.newLine();

                    written = true;
                } else if(lineId == this.getId()) {
                    writer.write(line); // Give preference for the current read line

                    match = true;
                    written = true;
                }
            }

            if(!written && !firstNewFile) {
                writer.write(id + ":" + salary + ":" + yearsOfService);
                writer.newLine();
            }
        } catch(Exception e) {
            System.out.println("Encountered exception. Printing stacktrace.");
            e.printStackTrace();
        }

        return match;
    }

    public boolean removeFrom(String inFileName, String outFileName, int id) {
        boolean match = false;

        try(BufferedReader reader = new BufferedReader(new FileReader(inFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName))) {
            String line = "";
            String[] data = new String[3];

            while((line = reader.readLine()) != null) {
                data = line.split(":");

                if(Integer.parseInt(data[0]) != id) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    match = true;
                }
            }
        } catch(Exception e) {
            System.out.println("Encountered an exception while removing from file. Printing stacktrace:");
            e.printStackTrace();
        }

        return match;
    }

    public void addService(String inFileName, String outFileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(inFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName))) {
            String line = "";
            String[] data = new String[3];

            while((line = reader.readLine()) != null) {
                data = line.split(":");

                data[2] = String.valueOf(Integer.parseInt(data[2]) + 1);

                writer.write(data[0] + ":" + data[1] + ":" + data[2]);
                writer.newLine();
            }

        } catch(Exception e) {
            System.out.println("Error adding service years. Printing stacktrace:");
            e.printStackTrace();
        }
    }

    public int raise(String inFileName, String outFileName, int yearsOfService, double salaryIncPercent) {
        String line = "";
        String[] data = new String[3];
        int entriesApplied = 0;
        double updatedSalary = 0.0;

        try(BufferedReader reader = new BufferedReader(new FileReader(inFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName))) {
            while((line = reader.readLine()) != null) {
                data = line.split(":");
                this.setYearsOfService(yearsOfService);

                if(Integer.parseInt(data[2]) >= this.getYearsOfService()) {
                    updatedSalary = Double.parseDouble(data[1]) * (salaryIncPercent/100) + Double.parseDouble(data[1]);
                    data[1] = String.valueOf(updatedSalary);
                    writer.write(data[0] + ":" + data[1] + ":" + data[2]);
                } else {
                    writer.write(line);
                }
            }
        } catch(Exception e) {
            System.out.println("Error increasing salary. Printing stacktrace:");
            e.printStackTrace();
        }

        return 0;
    }

    public void mergeFiles(String inFileName1, String inFileName2, String outFileName) {
        boolean file1Done = false;
        boolean file2Done = false;
        String file1Line = "";
        String file2Line = "";
        String[] file1Data = new String[3];
        String[] file2Data = new String[3];

        try(BufferedReader file1Reader = new BufferedReader(new FileReader(inFileName1));
            BufferedReader file2Reader = new BufferedReader(new FileReader(inFileName2));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outFileName))) {
            // Read the first lines of each file
            file1Line = file1Reader.readLine();
            file2Line = file2Reader.readLine();

            if(file1Line != null && file2Line != null) {
                do {
                    if(file1Line == null) {
                        file1Done = true;
                        break;
                    }

                    if(file2Line == null) {
                        file2Done = true;
                        break;
                    }

                    file1Data = file1Line.split(":");
                    file2Data = file2Line.split(":");

                    if(Integer.parseInt(file1Data[0]) == Integer.parseInt(file2Data[0])) {
                        if(Double.parseDouble(file1Data[1]) >= Double.parseDouble(file2Data[1])) {
                            fileWriter.write(file1Line);
                            fileWriter.newLine();

                            file2Line = file2Reader.readLine();
                        } else {
                            fileWriter.write(file2Line);
                            fileWriter.newLine();

                            file2Line = file2Reader.readLine();
                        }
                    } else if(Integer.parseInt(file1Data[0]) < Integer.parseInt(file2Data[0])) {
                        fileWriter.write(file1Line);
                        fileWriter.newLine();
                        continue; // May or may not need this here
                    } else if(Integer.parseInt(file1Data[0]) > Integer.parseInt(file2Data[0])) {
                        fileWriter.write(file2Line);
                        fileWriter.newLine();

                        while((file2Line = file2Reader.readLine()) != null) {
                            file2Data = file2Line.split(":");

                            if(Integer.parseInt(file2Data[0]) < Integer.parseInt(file1Data[0])) {
                                fileWriter.write(file2Line);
                                fileWriter.newLine();
                                continue; // May or may not need this here
                            } else if(Integer.parseInt(file2Data[0]) > Integer.parseInt(file1Data[0])) {
                                fileWriter.write(file1Line);
                                fileWriter.newLine();
                                break;
                            } else if(Integer.parseInt(file2Data[0]) == Integer.parseInt(file1Data[0])) {
                                if(Double.parseDouble(file2Data[1]) > Double.parseDouble(file1Data[1])) {
                                    fileWriter.write(file2Line);
                                } else {
                                    fileWriter.write(file1Line);
                                }
                            }
                        }

                        if(file2Line == null) {
                            file2Done = true;
                            fileWriter.write(file1Line);
                            fileWriter.newLine();
                        }
                    }
                } while((file1Line = file1Reader.readLine()) != null);
            }

            if(file1Line == null) {
                fileWriter.write(file2Line);
                fileWriter.newLine();
                file1Done = true;
            }

            if(file2Line == null) {
                fileWriter.write(file1Line);
                fileWriter.newLine();
                file2Done = true;
            }

            if(file1Done) {
                while((file2Line = file2Reader.readLine()) != null) {
                    fileWriter.write(file2Line);
                    fileWriter.newLine();
                }
            }

            if(file2Done) {
                while((file1Line = file1Reader.readLine()) != null) {
                    fileWriter.write(file1Line);
                    fileWriter.newLine();
                }
            }

        } catch(Exception e) {
            System.out.println("Encountered exception. Printing stacktrace:");
            e.printStackTrace();
        }
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
