package com.sparta.shahid.employees;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class EmployeeFileReader {

    ArrayList<Thread> threads = new ArrayList<>();

    public ArrayList<EmployeeDTO> getDataRecords(String path) {
        ArrayList<EmployeeDTO> employeeRecords = new ArrayList<>();
        String row, firstRow;

        HashSet<String> seenIds = new HashSet<String>();
        HashSet<String> duplicates = new HashSet<String>();
        HashSet<String> invalidData = new HashSet<String>();

        try {
            FileReader file = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(file);
            firstRow = bufferedReader.readLine();
            row=bufferedReader.readLine();
            while (row != null) {
                String[] employeeDetails = row.trim().split(",");
                EmployeeDTO employee = new EmployeeDTO(employeeDetails);
                Validation validation = new Validation();
                if(validation.isValid(employeeDetails)){
                    if (!seenIds.contains(employee.getEmployeeID())){
                        employeeRecords.add(employee);
                        seenIds.add(employee.getEmployeeID());
                    }else{
                        duplicates.add(employee.getEmployeeID());
                    }
                }else{
                    invalidData.add(employee.getEmployeeID());
                }
                if(employeeRecords.size() == 1000){
                    //System.out.println("current employee size: " +employeeRecords.size());
                    threadedFileReader(employeeRecords);
                    employeeRecords = new ArrayList<>(); //.clear instead
                }
                row = bufferedReader.readLine();

            }
          threadedFileReader(employeeRecords);
            for(Thread thread: threads){
                thread.join();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return employeeRecords;
    }

    public void threadedFileReader(ArrayList<EmployeeDTO> records){
        Runnable runnable = () -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
                employeeDAO.persistTheData(records);
        };
        Thread thread = new Thread(runnable);
        thread.start();
        threads.add(thread);

    }


}
