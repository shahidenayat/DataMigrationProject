package com.sparta.shahid.employees;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class EmployeeDTO {

    private String employeeID;
    private String title;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String emailAddress;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private String salary;

    //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern("M/d/yyyy")).toFormatter();


    public EmployeeDTO(String [] details){
        employeeID = details[0];
        title = details[1];
        firstName = details[2];
        middleInitial = details[3];
        lastName = details[4];
        gender = details[5];
        emailAddress = details[6];
        dateOfBirth = LocalDate.parse(details[7],dateTimeFormatter);
        dateOfJoining = LocalDate.parse(details[8],dateTimeFormatter);
        salary = details[9];

    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public String getSalary() {
        return salary;
    }
}
