package com.android.dev.devdaschatterjee_comp304_lab4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.dev.devdaschatterjee_comp304_lab4.Database.DatabaseHandler;
import com.android.dev.devdaschatterjee_comp304_lab4.models.Doctor;
import com.android.dev.devdaschatterjee_comp304_lab4.models.Nurse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Doctor doctor = new Doctor("devdas","devdas","chatterjee","Neurology","password");
            db.addDoctor(doctor);
            Doctor doctor2 = new Doctor("dinesh","dinesh","palle","Neurology","password");
            db.addDoctor(doctor2);
            //db.deleteDoctor(doctor);
            Nurse nurse = new Nurse("devdas","devdas","chatterjee","Neurology","password");
            db.addNurse(nurse);
            Nurse nurse2 = new Nurse("dinesh","dinesh","palle","Neurology","password");
            db.addNurse(nurse2);
            //db.deleteNurse(nurse);
        }
        catch (Exception e)        {        }
    }

    DatabaseHandler db;

    public MainActivity() {
        db = new DatabaseHandler(this);
    }

    public void onLoginClick(View view) {
        EditText usernameEdt = (EditText) findViewById(R.id.editUserName);
        EditText passwordEdt = (EditText) findViewById(R.id.editPassword);
        Spinner typeSpin = (Spinner) findViewById(R.id.TypeDropDown);
        String username = usernameEdt.getText().toString().trim();
        String password = passwordEdt.getText().toString().trim();
        String type = typeSpin.getSelectedItem().toString().trim();

        try {
            if (usernameEdt.length() == 0) {
                usernameEdt.requestFocus();
                usernameEdt.setError("Field must be filled");

            } else if (passwordEdt.length() == 0) {
                passwordEdt.requestFocus();
                passwordEdt.setError("Field must be filled");
            } else if (type.equals("Select Login type")) {
                typeSpin.requestFocus();
                Toast toast = Toast.makeText(getApplicationContext(), "Please select a login type", Toast.LENGTH_LONG);
                toast.show();

            } else {
                if (type.equals("Doctor")) {

                    Doctor doctor = db.getDoctor(username);
                    if (doctor.getFirstName().equals(null) || doctor.getFirstName().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect username", Toast.LENGTH_LONG);
                        toast.show();
                    } else if (doctor.getFirstName() != null && doctor.getFirstName() != "" && doctor.getPassword().equals(password)) {
                        SharedPreferences prefs = this.getSharedPreferences(
                                "com.android.dev", Context.MODE_PRIVATE);
                        prefs.edit().putString("UserName", username).commit();
                        prefs.edit().putString("Type", type).commit();
                        Intent intent = new Intent(this, PatientInfoActivity.class);
                        startActivity(intent);
                        Toast toast = Toast.makeText(getApplicationContext(), "Loged In", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG);
                        toast.show();
                    }
                } else if (type.equals("Nurse")) {

                    Nurse nurse = db.getNurse(username);
                    if (nurse.getFirstName().equals(null) || nurse.getFirstName().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect nurse username", Toast.LENGTH_LONG);
                        toast.show();
                    } else if (nurse.getFirstName() != null && nurse.getFirstName() != "" && nurse.getPassword().equals(password)) {
                        SharedPreferences prefs = this.getSharedPreferences(
                                "com.android.dev", Context.MODE_PRIVATE);
                        prefs.edit().putString("UserName", username).commit();
                        prefs.edit().putString("Type", type).commit();
                        Toast toast = Toast.makeText(getApplicationContext(), "Loged nurse In", Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent = new Intent(this, PatientInfoActivity.class);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect nurse password", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

            }
        } catch (Exception e) {
            Toast toast2 = Toast.makeText(this, "Incorrect username", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }
}
