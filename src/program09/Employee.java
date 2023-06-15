package program09;

import java.io.*;
import java.nio.file.*;
public class Employee implements Raiseable{
    private int id;
    private double salary;
    private int yearsOfService;

    public Employee() {
        this(1, 100000.00, 1);
    }

    public Employee(int id, double salary, int yearsOfService) {
        this.setId(id);
        this.setSalary(salary);
        this.setYearsOfService(yearsOfService);
    }

    // Accessors
    public int getId() {
        return this.id;
    }

    public double getSalary() {
        return this.salary;
    }

    public int getYearsOfService() {
        return this.yearsOfService;
    }

    // Mutators
    private void setId(int id) {
        this.id = id;
    }

    private void setSalary(double salary) {
        this.salary = salary;
    }

    private void setYearsOfService(int yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    /*
     * Creates a new (empty) file that can later be used to add entries of the above
     * format.
     */
    public void create(String fileName) {
        Path path = Paths.get(fileName);

        try {
            Files.createFile(path);
        } catch(Exception e) {
            System.out.println("Encountered an exception. Printing stacktrace: ");
            e.printStackTrace();
        }
    }

    /*
     * Formats and displays the contents of the specified file to the screen
     */
    public void display(String fileName) {
        String format = "%-5s%-10s%-50s";
        BufferedReader buffReader = null;

        try {
            // Read the inFile.
            buffReader = new BufferedReader(new FileReader(fileName));

            // Checks to see if there's anything to read.
            if(buffReader.readLine() == null) {
                System.out.println("Nothing to read!");
            } else {
                String line = "";

                // Print out the headers in the terminal.
                System.out.printf(format, "ID: ", "Salary: ", "Years of service: ");
                System.out.println();

                while(((line = buffReader.readLine()) != null)) {
                    if(line.contains(":")) {
                        // Breaks down string to obtain id.
                        int index = line.indexOf(":");
                        String lineId = line.substring(0, index); // Id format: "<#>"
                        int parsedId = Integer.parseInt(lineId.replaceAll("[< >]", ""));
                        this.setId(parsedId);

                        // Breaks down the remaining half of string to obtain salary.
                        String salaryAndYoS = line.substring(index + 1); // Format of second half of separated string: "<#.#>:<#><EOL>"
                        int index2 = salaryAndYoS.indexOf(":");
                        String lineSalary = salaryAndYoS.substring(0, index2); // Salary format: "<#.#>"
                        double parsedSalary = Double.parseDouble(lineSalary.replaceAll("[< >]", ""));
                        this.setSalary(parsedSalary);

                        // Gets the second of of the broken down string from above to obtain years of
                        // service.
                        String lineYoS = salaryAndYoS.substring(index2 + 1); // Years of Service format: <#><EOL>
                        lineYoS = lineYoS.replace("<EOL>", "");
                        int parsedYoS = Integer.parseInt(lineYoS.replaceAll("[< >]", ""));
                        this.setYearsOfService(parsedYoS);

                        // Print out the individual components under their respective headers.
                        System.out.printf(format, this.getId(), this.getSalary(), this.getYearsOfService());
                        System.out.println();
                    }
                }
            }

        } catch(Exception e) {
            System.out.println("Encountered an exception. Printing stacktrace: ");
            e.printStackTrace();
        } finally {
            try {
                // Close the reader.
                if(buffReader != null) {
                    buffReader.close();
                }
            } catch(Exception e) {
                System.out.println("Encountered an exception. Printing stacktrace: ");
                e.printStackTrace();
            }
        }

    }

    /*
     * Creates a new file that is a copy of the given input file - with the
     * specified entry added such that the new file is sorted by the entries' ids.
     * Return true if no matching id was found / entry added; false if matching
     * id found.
     */
    public boolean addTo(String inFileName, String outFileName, int id, double salary, int yearsOfService) {
        boolean isWritten = false; // This keeps track on if we wrote the passed in parameters.
        boolean foundDuplicate = false;

        // Create the header and footer.
        String defaultHeaderAndFooter = "-----------------------------------------------------";

        // Set all the passed in values.
        this.setId(id);
        this.setSalary(salary);
        this.setYearsOfService(yearsOfService);

        // Create the out file
        this.create(outFileName);
        BufferedWriter buffWriter = null;
        BufferedReader buffReader = null;

        try {
            // Create the writers.
            FileWriter fileWriter = new FileWriter(outFileName); // FileWriter is required for BufferedWriter to work.
            buffWriter = new BufferedWriter(fileWriter);

            // Outfile header.
            buffWriter.write(defaultHeaderAndFooter + "\n");

            // Read the inFile.
            buffReader = new BufferedReader(new FileReader(inFileName));

            // First, check to see if the file is empty.
            if(buffReader.readLine() == null) {
                // Format the line string in required format.
                String formattedId = "<" + this.getId() + ">";
                String formattedSalary = "<" + this.getSalary() + ">";
                String formattedYoS = "<" + this.getYearsOfService() + ">";

                buffWriter.write(formattedId + ":" + formattedSalary + ":" + formattedYoS + "<EOL>\n");

                // Write the footer of the file.
                buffWriter.write("<EOF>\n");
                buffWriter.write(defaultHeaderAndFooter);
            } else { // Else, if it's not empty, do this.
                String line = "";

                // Line cannot be null and must contain ":" in order to be processed
                while(((line = buffReader.readLine()) != null)) {
                    if(line.contains(":")) {
                        int index = line.indexOf(":");
                        String lineId = line.substring(0, index); // Id: "<#>"
                        int parsedId = Integer.parseInt(lineId.replaceAll("[< >]", ""));

                        // Checks to see if the passed in id is greater than the id provided in the line
                        // or if the line exists currently (kept track via the "isWritten" variable).
                        // 1. First write the line down then skip the rest of the loop OR
                        // 2. If the passed in id is lower, format the input and print it.
                        if((this.getId() > parsedId) || (isWritten == true)) {
                            buffWriter.write(line + "\n");
                            continue;
                        } else if((this.getId() < parsedId) && (isWritten == false)) {
                            String formattedId = "<" + this.getId() + ">";
                            String formattedSalary = "<" + this.getSalary() + ">";
                            String formattedYoS = "<" + this.getYearsOfService() + ">";

                            // If our passed in id is less than the id parsed from the line,
                            // we want to write our id first, then the line from inFile.
                            buffWriter.write(formattedId + ":" + formattedSalary + ":" + formattedYoS + "<EOL>\n");
                            buffWriter.write(line + "\n");
                            isWritten = true; // Passed in parameters is written.
                        } else if((this.getId() == parsedId) && (isWritten == false)) {
                            buffWriter.write(line + "\n");
                            isWritten = true;
                            foundDuplicate = true;
                        }
                    } else if((line.contains("EOF")) && (isWritten == false)) {
                        // Format the line string in required format.
                        String formattedId = "<" + this.getId() + ">";
                        String formattedSalary = "<" + this.getSalary() + ">";
                        String formattedYoS = "<" + this.getYearsOfService() + ">";

                        buffWriter.write(formattedId + ":" + formattedSalary + ":" + formattedYoS + "<EOL>\n");
                    }
                }

                // Write the footer of the file.
                buffWriter.write("<EOF>\n");
                buffWriter.write(defaultHeaderAndFooter);
            }
        } catch(Exception e) {
            System.out.println("Encountered an exception. Printing stacktrace: ");
            e.printStackTrace();
        } finally {
            try {
                // Close the writer.
                if(buffWriter != null) {
                    buffWriter.close();
                }

                // Close the reader.
                if(buffReader != null) {
                    buffReader.close();
                }
            } catch(Exception e) {
                System.out.println("Encountered an exception. Printing stacktrace: ");
                e.printStackTrace();
            }
        }

        return foundDuplicate;
    }

    /*
     * Creates a new file that is a copy of the given input file - with the
     * specified entry removed.
     * Returns true if a matching id was found / entry removed; false if no matching
     * id found
     */
    public boolean removeFrom(String inFileName, String outFileName, int id) {
        boolean returnValue = false;

        // Create the header and footer.
        String defaultHeaderAndFooter = "-----------------------------------------------------";

        // Set all the passed in values.
        this.setId(id);

        // Create the out file
        this.create(outFileName);
        BufferedWriter buffWriter = null;
        BufferedReader buffReader = null;

        try {
            // Create the writers.
            FileWriter fileWriter = new FileWriter(outFileName); // FileWriter is required for BufferedWriter to work.
            buffWriter = new BufferedWriter(fileWriter);

            // Outfile header.
            buffWriter.write(defaultHeaderAndFooter + "\n");

            // Read the inFile.
            buffReader = new BufferedReader(new FileReader(inFileName));

            // Check the inFile to see if it's null.
            if(buffReader.readLine() != null) {
                String line = "";

                // Loop over every line that has a ":" as this denotes a line we can parse.
                // If we find the item, set returnValue to true, then continue to the next line
                // to be printed.
                while((line = buffReader.readLine()) != null) {
                    if(line.contains(":")) {
                        int index = line.indexOf(":");
                        String lineId = line.substring(0, index); // Id: "<4>"
                        int parsedId = Integer.parseInt(lineId.replaceAll("[< >]", ""));

                        if(parsedId != this.getId()) {
                            buffWriter.write(line + "\n");
                        } else {
                            returnValue = true;
                            continue;
                        }
                    }
                }
            }

            // Write the footer of the file.
            buffWriter.write("<EOF>\n");
            buffWriter.write(defaultHeaderAndFooter);
        } catch(Exception e) {
            System.out.println("Encountered an exception. Printing stacktrace: ");
            e.printStackTrace();
        } finally {
            try {
                // Close the writer.
                if(buffWriter != null) {
                    buffWriter.close();
                }

                // Close the reader.
                if(buffReader != null) {
                    buffReader.close();
                }
            } catch(Exception e) {
                System.out.println("Encountered an exception. Printing stacktrace: ");
                e.printStackTrace();
            }
        }

        return returnValue;
    }

    /*
     * Creates a new file that is a copy of the given input file - where all entries
     * have had their years of service increased by one.
     * Returns nothing.
     */
    public void addService(String inFileName, String outFileName){
        // Create the file header and footer.
        String defaultHeaderAndFooter = "-----------------------------------------------------";

        // Create the out file.
        this.create(outFileName);

        // Create the readers and writers.
        BufferedWriter buffWriter = null;
        BufferedReader buffReader = null;

        try {
            // Create the writers.
            FileWriter fileWriter = new FileWriter(outFileName); // FileWriter is required for BufferedWriter to work.
            buffWriter = new BufferedWriter(fileWriter);

            // Outfile header.
            buffWriter.write(defaultHeaderAndFooter + "\n");

            // Read the inFile
            buffReader = new BufferedReader(new FileReader(inFileName));

            // Make sure file isn't empty.
            if(buffReader.readLine() != null) {
                String line = "";

                // Line cannot be null and must contain ":" in order to be processed
                while(((line = buffReader.readLine()) != null)) {
                    if(line.contains(":")) {
                        // Breaks down string to obtain id.
                        int index = line.indexOf(":");
                        String lineId = line.substring(0, index); // Id: format "<#>"
                        int parsedId = Integer.parseInt(lineId.replaceAll("[< >]", ""));
                        this.setId(parsedId);

                        // Breaks down the remaining half of string to obtain salary.
                        String salaryAndYoS = line.substring(index + 1); // Format of second half of separated string: "<#.#>:<#><EOL>"
                        int index2 = salaryAndYoS.indexOf(":");
                        String lineSalary = salaryAndYoS.substring(0, index2); // Salary format: "<#.#>"
                        double parsedSalary = Double.parseDouble(lineSalary.replaceAll("[< >]", ""));
                        this.setSalary(parsedSalary);

                        // Gets the second of of the broken down string from above to obtain years of
                        // service.
                        String lineYoS = salaryAndYoS.substring(index2 + 1); // Years of Service format: <#><EOL>
                        lineYoS = lineYoS.replace("<EOL>", "");
                        int parsedYoS = Integer.parseInt(lineYoS.replaceAll("[< >]", ""));
                        this.setYearsOfService(parsedYoS + 1);

                        // Format the line string in required format.
                        String formattedId = "<" + this.getId() + ">";
                        String formattedSalary = "<" + this.getSalary() + ">";
                        String formattedYoS = "<" + this.getYearsOfService() + ">";

                        buffWriter.write(formattedId + ":" + formattedSalary + ":" + formattedYoS + "<EOL>\n");
                    }
                }
            }

            // Write the footer of the file.
            buffWriter.write("<EOF>\n");
            buffWriter.write(defaultHeaderAndFooter);
        } catch(Exception e) {
            System.out.println("Encountered an exception. Printing stacktrace: ");
            e.printStackTrace();
        } finally {
            try {
                // Close the writer.
                if(buffWriter != null) {
                    buffWriter.close();
                }

                // Close the reader.
                if(buffReader != null) {
                    buffReader.close();
                }
            } catch(Exception e) {
                System.out.println("Encountered an exception. Printing stacktrace: ");
                e.printStackTrace();
            }
        }
    }

    /*
     * Creates a new file that is a copy of the given input file - where all entries
     * whose years of service are greater than or equal to yearsOfService have had
     * their salaries increased by salaryIncPercent Return the number of entries
     * that the raise was applied to.
     */
    public int raise(String inFileName, String outFileName, int yearsOfService, double salaryIncPercent) {
        // Create the header and footer.
        String defaultHeaderAndFooter = "-----------------------------------------------------";
        double updatedSalary = 0.0;
        int entriesApplied = 0;

        // Create the out file
        this.create(outFileName);
        BufferedWriter buffWriter = null;
        BufferedReader buffReader = null;

        try {
            // Create the writers.
            FileWriter fileWriter = new FileWriter(outFileName); // FileWriter is required for BufferedWriter to work.
            buffWriter = new BufferedWriter(fileWriter);

            // Outfile header.
            buffWriter.write(defaultHeaderAndFooter + "\n");

            // Read the inFile
            buffReader = new BufferedReader(new FileReader(inFileName));

            // Make sure file isn't empty.
            if(buffReader.readLine() != null) {
                String line = "";

                // Line cannot be null and must contain ":" in order to be processed
                while(((line = buffReader.readLine()) != null)) {
                    if(line.contains(":")) {
                        // Breaks down string to obtain id.
                        int index = line.indexOf(":");
                        String lineId = line.substring(0, index); // Id format: "<#>"
                        int parsedId = Integer.parseInt(lineId.replaceAll("[< >]", ""));
                        this.setId(parsedId);

                        // Breaks down the remaining half of string to obtain salary.
                        String salaryAndYoS = line.substring(index + 1); // Format of second half of separated string: "<#.#>:<#><EOL>"
                        int index2 = salaryAndYoS.indexOf(":");
                        String lineSalary = salaryAndYoS.substring(0, index2); // Salary format: "<#.#>"
                        double parsedSalary = Double.parseDouble(lineSalary.replaceAll("[< >]", ""));
                        this.setSalary(parsedSalary);

                        // Gets the second of of the broken down string from above to obtain years of
                        // service.
                        String lineYoS = salaryAndYoS.substring(index2 + 1); // Years of Service format: <#><EOL>
                        lineYoS = lineYoS.replace("<EOL>", "");
                        int parsedYoS = Integer.parseInt(lineYoS.replaceAll("[< >]", ""));
                        this.setYearsOfService(parsedYoS);

                        // If we find an entry that matches the criteria, then we should increase the salary.
                        if(this.getYearsOfService() >= yearsOfService) {
                            updatedSalary = this.getSalary() + (this.getSalary() * (salaryIncPercent/100)); // Salary + (Salary / percentage increase).
                            this.setSalary(updatedSalary);

                            // Format the line string in required format.
                            String formattedId = "<" + this.getId() + ">";
                            String formattedSalary = "<" + this.getSalary() + ">";
                            String formattedYoS = "<" + this.getYearsOfService() + ">";

                            buffWriter.write(formattedId + ":" + formattedSalary + ":" + formattedYoS + "<EOL>\n");

                            // Increment the amount tracker by 1.
                            ++entriesApplied;
                        } else {
                            // If nothing happens, just write the current line.
                            buffWriter.write(line + "\n");
                        }
                    }
                }

                // Write the footer of the file.
                buffWriter.write("<EOF>\n");
                buffWriter.write(defaultHeaderAndFooter);
            }
        } catch(Exception e) {
            System.out.println("Encountered an exception. Printing stacktrace: ");
            e.printStackTrace();
        } finally {
            try {
                // Close the writer.
                if (buffWriter != null) {
                    buffWriter.close();
                }

                // Close the reader.
                if (buffReader != null) {
                    buffReader.close();
                }
            } catch (Exception e) {
                System.out.println("Encountered an exception. Printing stacktrace: ");
                e.printStackTrace();
            }
        }

        return entriesApplied;
    }

    /*
     * Creates a new file that is a sorted merge of the two given (sorted) input
     * files. In case of duplicate entries, only the one with the highest salary is
     * kept in outFileName.
     */
    public void mergeFiles(String inFileName1, String inFileName2, String outFileName) {
        // Create the file header and footer.
        String defaultHeaderAndFooter = "----------------------------------------------------\n";

        boolean file1End = false;
        boolean file2End = false;

        // Create the out file.
        this.create(outFileName);

        // Create the readers and writers.
        BufferedWriter buffWriter = null;
        BufferedReader buffReader1 = null;
        BufferedReader buffReader2 = null;

        try {
            // Create the writers.
            FileWriter fileWriter = new FileWriter(outFileName); // FileWriter is required for BufferedWriter to work.
            buffWriter = new BufferedWriter(fileWriter);

            // Outfile header.
            buffWriter.write(defaultHeaderAndFooter);

            // Read the inFiles
            buffReader1 = new BufferedReader(new FileReader(inFileName1));
            buffReader2 = new BufferedReader(new FileReader(inFileName2));

            // Check if both files are empty.
            if(buffReader1.readLine() != null) { // If file 1 is not empty.
                if(buffReader2.readLine() !=null) { // If file 2 is not empty, we can compare them.
                    String file1Line = buffReader1.readLine();
                    String file2Line = buffReader2.readLine();

                    // Here's the loop.
                    do {
                        if(file1Line.contains("EOF")) {
                            file1End = true;
                            break;
                        }

                        if(file2Line.contains("EOF")) {
                            file2End = true;
                            break;
                        }

                        /*
                         * Obtain information from file 1 line.
                         */
                        // Breaks down string to obtain id from file 1.
                        int file1Index = file1Line.indexOf(":");
                        String file1LineId = file1Line.substring(0, file1Index); // Id format: "<#>"
                        int file1LineParsedId = Integer.parseInt(file1LineId.replaceAll("[< >]", ""));

                        // Breaks down the remaining half of string to obtain salary from file 1.
                        String file1LineSalaryAndYoS = file1Line.substring(file1Index + 1); // Format: "<#.#>:<#><EOL>"
                        int file1Index2 = file1LineSalaryAndYoS.indexOf(":");
                        String file1LineSalary = file1LineSalaryAndYoS.substring(0, file1Index2); // Salary format: "<#.#>"
                        double file1LineParsedSalary = Double.parseDouble(file1LineSalary.replaceAll("[< >]", ""));

                        /*
                         * Obtain information from file 2 line.
                         */

                        // Breaks down string to obtain id from file 2.
                        int file2index = file2Line.indexOf(":");
                        String file2LineId = file2Line.substring(0, file2index); // Id format: "<#>"
                        int file2LineParsedId = Integer.parseInt(file2LineId.replaceAll("[< >]", ""));

                        // Breaks down the remaining half of string to obtain salary from file 2.
                        String file2LineSalaryAndYoS = file2Line.substring(file2index + 1); // Format: "<#.#>:<#><EOL>"
                        int file2Index2 = file2LineSalaryAndYoS.indexOf(":");
                        String file2LineSalary = file2LineSalaryAndYoS.substring(0, file2Index2); // Salary format: "<#.#>"
                        double file2LineParsedSalary = Double.parseDouble(file2LineSalary.replaceAll("[< >]", ""));

                        if(file1LineParsedId > file2LineParsedId) {
                            // Second loop for file 2.
                            // If file 2 line is lower than file 1 line, read the next line in file 2.
                            buffWriter.write(file2Line + "\n");
                            file2Line = buffReader2.readLine();

                            do {
                                // Breaks down string to obtain id from file 2.
                                int tempIndex = file2Line.indexOf(":");
                                String tempLineId = file2Line.substring(0, tempIndex); // Id format: "<#>"
                                int tempLineParsedId = Integer.parseInt(tempLineId.replaceAll("[< >]", ""));

                                // Breaks down the remaining half of string to obtain salary from file 2.
                                String tempLineSalaryAndYoS = file2Line.substring(tempIndex + 1); // Format: "<#.#>:<#><EOL>"
                                int tempIndex2 = tempLineSalaryAndYoS.indexOf(":");
                                String tempLineSalary = tempLineSalaryAndYoS.substring(0, tempIndex2); // Salary format: "<#.#>"
                                double tempLineParsedSalary = Double.parseDouble(tempLineSalary.replaceAll("[< >]", ""));

                                if(file1LineParsedId < tempLineParsedId) {
                                    buffWriter.write(file1Line + "\n");
                                    break;
                                } else if(file1LineParsedId == tempLineParsedId) {
                                    if(file1LineParsedSalary < tempLineParsedSalary) {
                                        buffWriter.write(file2Line + "\n");
                                        file2Line = buffReader2.readLine();
                                        break;
                                    } else {
                                        buffWriter.write(file1Line + "\n");
                                        file2Line = buffReader2.readLine();
                                        break;
                                    }
                                } else {
                                    buffWriter.write(file2Line + "\n");
                                }
                            } while(((file2Line = buffReader2.readLine()) != null) && file2End == false);
                        } else if(file1LineParsedId < file2LineParsedId) {
                            buffWriter.write(file1Line + "\n");
                            continue;
                        } else if(file1LineParsedId == file2LineParsedId) {
                            if(file1LineParsedSalary < file2LineParsedSalary) {
                                buffWriter.write(file2Line + "\n");
                            } else {
                                buffWriter.write(file1Line + "\n");
                            }

                            if(file2End == false) {
                                file2Line = buffReader2.readLine();
                            }
                        }
                    } while(((file1Line = buffReader1.readLine()) != null) && file1End == false);

                    if(file1End == true && file2End == false) {
                        do {
                            if(file2Line.contains(":")) {
                                buffWriter.write(file2Line + "\n");
                            } else if(file2Line.contains("EOF")) {
                                file2End = true;
                            }
                        } while(((file2Line = buffReader2.readLine()) != null) && file2End == false);
                    } else if(file1End == false && file2End == true) {
                        do {
                            if (file1Line.contains(":")) {
                                buffWriter.write(file1Line + "\n");
                            } else if (file1Line.contains("EOF")) {
                                file1End = true;
                            }
                        } while (((file1Line = buffReader1.readLine()) != null) && file1End == false);
                    }

                    // Write the footer of the file.
                    buffWriter.write("<EOF>\n");
                    buffWriter.write(defaultHeaderAndFooter);
                } else { // Else file 2 is empty, we should be able to copy everything from file 1.
                    String file1Line = "";

                    while((file1Line = buffReader1.readLine()) != null) {
                        if(file1Line.contains(":")) {
                            buffWriter.write(file1Line + "\n");
                        }
                    }

                    buffWriter.write("<EOF>\n");
                    buffWriter.write(defaultHeaderAndFooter);
                }
            } else { // Else (file 1 is empty), we should check file 2.
                String file2Line = "";
                if(buffReader2.readLine() != null) { // We should be able to copy everything from file 2
                    while((file2Line = buffReader2.readLine()) != null) {
                        if(file2Line.contains(":")) {
                            buffWriter.write(file2Line + "\n");
                        }
                    }

                    // Write the footer of the file.
                    buffWriter.write("<EOF>\n");
                    buffWriter.write(defaultHeaderAndFooter);
                }
            }
        } catch(Exception e) {
            System.out.println("Encountered an exception. Printing stacktrace: ");
            e.printStackTrace();
        } finally {
            try {
                // Close the writer.
                if(buffWriter != null) {
                    buffWriter.close();
                }

                // Close the readers.
                if(buffReader1 != null) {
                    buffReader1.close();
                }

                if(buffReader2 != null) {
                    buffReader1.close();
                }
            } catch(Exception e) {
                System.out.println("Encountered an exception. Printing stacktrace: ");
                e.printStackTrace();
            }
        }
    }
}

