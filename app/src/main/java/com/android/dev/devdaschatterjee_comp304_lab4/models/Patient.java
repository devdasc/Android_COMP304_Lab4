package com.android.dev.devdaschatterjee_comp304_lab4.models;

public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private String department;
    private String doctorId;
    private String room;

    // Empty constructor
    public Patient(){    }
    // constructor
    public Patient(int patientId, String firstName, String lastName, String department, String doctorId, String room) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.doctorId = doctorId;
        this.room = room;
    }

    public int getPatientId() {        return patientId;    }

    public void setPatientId(int patientId) {        this.patientId = patientId;    }

    public String getFirstName() {        return firstName;    }

    public void setFirstName(String firstName) {        this.firstName = firstName;    }

    public String getLastName() {        return lastName;    }

    public void setLastName(String lastName) {        this.lastName = lastName;    }

    public String getDepartment() {        return department;    }

    public void setDepartment(String department) {        this.department = department;    }

    public String getDoctorId() {        return doctorId;    }

    public void setDoctorId(String doctorId) {        this.doctorId = doctorId;    }

    public String getRoom() {        return room;    }

    public void setRoom(String room) {        this.room = room;    }

    public String toString() {
        return getFirstName()+" "+getLastName();
    }
}
