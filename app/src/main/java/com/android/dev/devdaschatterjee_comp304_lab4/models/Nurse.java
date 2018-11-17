package com.android.dev.devdaschatterjee_comp304_lab4.models;

public class Nurse {
    //private variables
    private String nurseId;
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    // Empty constructor
    public Nurse(){    }

    public Nurse(String nurseId, String firstName, String lastName, String department, String password) {
        this.nurseId = nurseId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }

    public String getNurseId() {return nurseId; }

    public void setNurseId(String nurseId) { this.nurseId = nurseId;}

    public String getFirstName() {return firstName; }

    public void setFirstName(String firstName) {this.firstName = firstName; }

    public String getLastName() {return lastName;   }

    public void setLastName(String lastName) { this.lastName = lastName;    }

    public String getDepartment() {        return department;    }

    public void setDepartment(String department) {        this.department = department;    }

    public String getPassword() {        return password;    }

    public void setPassword(String password) {        this.password = password;    }

    @Override
    public String toString() {        return getFirstName()+" "+getLastName();    }
}
