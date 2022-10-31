package com.mycompany.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;
import com.mycompany.app.Employee;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.*;
import java.util.regex.Pattern;

public class App {

    static int totalAmountEmployees = 0;
    static int totalMinimumSalary = 0;
    static int totalAverageSalary = 0;
    static int totalMaximumSalary = 0;
    static int totalOfTotalSalary = 0;

    public static void main(String[] args) {
        System.out.println("Please enter path to csv file:");
        System.out.println();

        try {
            BufferedReader consoleBufferReader = new BufferedReader(new InputStreamReader(System.in));
            String inputPath = consoleBufferReader.readLine();

            System.out.println("Choose a line separator");

            consoleBufferReader = new BufferedReader(new InputStreamReader(System.in));
            String separetor = consoleBufferReader.readLine();

            File file = new File(inputPath);
            FileReader fileReader = new FileReader(file);
            BufferedReader fileBufferReader = new BufferedReader(fileReader);

            String line = "";
            String[] employeeLine;
            List<Employee> employeesList = new ArrayList<Employee>();
            fileBufferReader.readLine(); //Skips First line because of Columns 
            while ((line = fileBufferReader.readLine()) != null) {
                employeeLine = line.split(Pattern.quote(separetor));

                Employee tempEmployee = new Employee();

                tempEmployee.setId(Integer.parseInt(employeeLine[0]));
                tempEmployee.setName(employeeLine[1]);
                tempEmployee.setdateOfBirth(employeeLine[2]);//parseInt?
                tempEmployee.setdepartment(employeeLine[3]);
                tempEmployee.setsalary(Integer.parseInt(employeeLine[4]));

                employeesList.add(tempEmployee);
            }
            fileBufferReader.close();

            //Create separate department lists
            List<Employee> adminDepartment = new ArrayList<Employee>();
            List<Employee> developmentDepartment = new ArrayList<Employee>();
            List<Employee> financeDepartment = new ArrayList<Employee>();
            List<Employee> humanRDepartment = new ArrayList<Employee>();
            List<Employee> marketingDepartment = new ArrayList<Employee>();
            List<Employee> operationsDepartment = new ArrayList<Employee>();
            List<Employee> salesDepartment = new ArrayList<Employee>();
            List<Employee> supportDepartment = new ArrayList<Employee>();

            //Calculations
            for (Employee employee : employeesList) {
                switch (employee.getdepartment()) {
                    case "Admin":
                        adminDepartment.add(employee);
                        break;
                    case "Development":
                        developmentDepartment.add(employee);
                        break;
                    case "Finance":
                        financeDepartment.add(employee);
                        break;
                    case "HR":
                        humanRDepartment.add(employee);
                        break;
                    case "Marketing":
                        marketingDepartment.add(employee);
                        break;
                    case "Operations":
                        operationsDepartment.add(employee);
                        break;
                    case "Sales":
                        salesDepartment.add(employee);
                        break;
                    case "Support":
                        supportDepartment.add(employee);
                        break;
                }
            }

            List<String> sampleOutput = new ArrayList<String>();
            sampleOutput.add("Department,Employees,MinSalary,AvgSalary,MaxSalary,TotalSalary");
            sampleOutput.add(CalculateInfo(adminDepartment));
            sampleOutput.add(CalculateInfo(developmentDepartment));
            sampleOutput.add(CalculateInfo(financeDepartment));
            sampleOutput.add(CalculateInfo(humanRDepartment));
            sampleOutput.add(CalculateInfo(marketingDepartment));
            sampleOutput.add(CalculateInfo(operationsDepartment));
            sampleOutput.add(CalculateInfo(salesDepartment));
            sampleOutput.add(CalculateInfo(supportDepartment));

            sampleOutput.add("Totals," + totalAmountEmployees + "," + totalMinimumSalary + "," + totalAverageSalary + "," + totalMaximumSalary + "," + totalOfTotalSalary );


            SaveCSVFile(file.getParent() + "\\Output.csv", sampleOutput);

        } catch (Exception e) {
            System.out.println("An error has occured");
            System.out.println("Error: " + e);
        }
    }

    public static String CalculateInfo(List<Employee> empList) {
        int totalSalary = 0;

        for (Employee employee : empList) {
            totalSalary += employee.getsalary();
        }

        String stringInfo = empList.get(0).getdepartment();
        stringInfo = stringInfo + "," + empList.size();

        Collections.sort(empList, new SortBySalaryAscending());

        stringInfo = stringInfo + "," + empList.get(0).getsalary(); //Min

        int average = totalSalary / empList.size();

        stringInfo = stringInfo + "," + average; //average

        int lastIndex = empList.size() - 1;

        stringInfo = stringInfo + "," + empList.get(lastIndex).getsalary(); //Max

        stringInfo = stringInfo + "," + totalSalary;

        totalAmountEmployees += empList.size();
        totalMinimumSalary += empList.get(0).getsalary();
        totalAverageSalary += average;
        totalMaximumSalary += empList.get(lastIndex).getsalary();
        totalOfTotalSalary += totalSalary;
        
        return stringInfo;
    }

    public static void SaveCSVFile(String outputPath, List<String> lines) {
        try {
            File csvFile = new File(outputPath);

            if (csvFile.createNewFile()) {
                System.out.println("File created: " + csvFile.getName());

            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(outputPath, false); //Overwrites

            for (String line : lines) {
                myWriter.write(line);
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

class SortBySalaryAscending implements Comparator<Employee> {

    public int compare(Employee a, Employee b) {
        return a.getsalary() - b.getsalary();
    }
}
