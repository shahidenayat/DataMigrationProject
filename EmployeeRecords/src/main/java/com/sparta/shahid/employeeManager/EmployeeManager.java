package com.sparta.shahid.employeeManager;


import com.sparta.shahid.employees.EmployeeDTO;
import com.sparta.shahid.employees.EmployeeDAO;
import com.sparta.shahid.employees.EmployeeFileReader;

import java.util.ArrayList;

public class EmployeeManager {

    private static final String PATH = "resources/EmployeeRecords.csv";
    public EmployeeManager(){
        Initialise();
    }

    public void Initialise(){
        long nanoStart = System.nanoTime();
        EmployeeFileReader employeeFileReader = new EmployeeFileReader();
        employeeFileReader.getDataRecords(PATH);
        long nanoEnd = System.nanoTime();
        System.out.println("total time =" + (nanoEnd-nanoStart)/1_000_000_000.0+ "seconds");
    }

}
