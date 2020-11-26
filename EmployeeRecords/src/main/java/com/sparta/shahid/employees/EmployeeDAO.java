package com.sparta.shahid.employees;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class EmployeeDAO {

    private String URL = "jdbc:mysql://localhost:3306/mylocal?serverTimeZone=GMT ";
    private Properties properties = new Properties();
    private Connection connection = null;
    private String insertEmployee = "INSERT INTO employeeRecords VALUES (?,?,?,?,?,?,?,?,?,?)";

    private Connection connectToDatabase() {
        try {
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public void persistTheData(ArrayList<EmployeeDTO> employeeRecords) {
//
        try {
            Connection connection = connectToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(insertEmployee);
            connection.setAutoCommit(false);
            int numberOfIteration = 1;
            for(EmployeeDTO employee : employeeRecords){
                preparedStatement.setString(1,employee.getEmployeeID());
                preparedStatement.setString(2,employee.getTitle());
                preparedStatement.setString(3,employee.getFirstName());
                preparedStatement.setString(4,employee.getMiddleInitial());
                preparedStatement.setString(5,employee.getLastName());
                preparedStatement.setString(6,employee.getGender());
                preparedStatement.setString(7,employee.getEmailAddress());
                preparedStatement.setDate(8, Date.valueOf(employee.getDateOfBirth()));
                preparedStatement.setDate(9,Date.valueOf(employee.getDateOfJoining()));
                preparedStatement.setString(10,employee.getSalary());
                preparedStatement.addBatch();

                if(employeeRecords.size() == numberOfIteration){
//                    try {
                        int[] batchedArray = preparedStatement.executeBatch();
//                    }catch (BatchUpdateException batchUpdateException){
//                        batchUpdateException.getMessage();
//                    }
                    connection.commit();
                }
                numberOfIteration++;
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //close connection
        finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

}
