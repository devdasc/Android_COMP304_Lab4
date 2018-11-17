package com.android.dev.devdaschatterjee_comp304_lab4.models;

import java.util.Objects;

public class Doctor {
    //private variables
    private String doctorId;
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    // Empty constructor
    public Doctor() {    }

    public Doctor(String doctorId, String firstName, String lastName, String department, String password) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }
    public String getDoctorId() {return doctorId; }

    public void setDoctorId(String doctorId) {    this.doctorId = doctorId;    }

    public String getFirstName() {        return firstName;    }

    public void setFirstName(String firstName) {        this.firstName = firstName;    }

    public String getLastName() {        return lastName;    }

    public void setLastName(String lastName) {        this.lastName = lastName;    }

    public String getDepartment() {        return department;    }

    public void setDepartment(String department) {        this.department = department;    }

    public String getPassword() {        return password;    }

    public void setPassword(String password) {        this.password = password;    }

    @Override
    public String toString() {        return getFirstName() + " " + getLastName();        }

}
