package com.android.dev.devdaschatterjee_comp304_lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.dev.devdaschatterjee_comp304_lab4.Database.DatabaseHandler;
import com.android.dev.devdaschatterjee_comp304_lab4.models.Nurse;
import com.android.dev.devdaschatterjee_comp304_lab4.models.Patient;
import com.android.dev.devdaschatterjee_comp304_lab4.models.Test;

public class TestInfoActivity extends AppCompatActivity {

    private Test test;
    private static DatabaseHandler db;
    public TestInfoActivity()
    {
        db=new DatabaseHandler(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info);
        Intent intent=getIntent();
        int testId=intent.getIntExtra("testId",0);
        test=db.getTest(testId);
        setDetails();
    }
    public void onEditClick(View v)
    {
        Intent intent=new Intent(this,TestAddUpdateActivity.class);
        intent.putExtra("testId",test.getTestId());
        intent.putExtra("purpose","update");
        startActivity(intent);
    }
    public void setDetails()
    {
        TextView patientName=(TextView)findViewById(R.id.patientNameTxt);
        TextView nurse=(TextView)findViewById(R.id.nurseNameTxt);
        TextView BPL=(TextView)findViewById(R.id.bplTxt);
        TextView BPH=(TextView)findViewById(R.id.bphTxt);
        TextView LDLCholesterol=(TextView)findViewById(R.id.LDLCholesterolTxt);
        TextView HDLCholesterol=(TextView)findViewById(R.id.HDLCholesterolTxt);
        TextView MCH=(TextView)findViewById(R.id.MCHTxt);
        TextView ESR=(TextView)findViewById(R.id.ESRTxt);
        TextView PlateletCount=(TextView)findViewById(R.id.PlateletCountTxt);
        TextView temperature=(TextView)findViewById(R.id.temperatureTxt);
        Patient p=db.getPatient(test.getPatientId());
        patientName.setText(p.getFirstName()+" "+p.getLastName());
        Nurse n=db.getNurse(test.getNurseId());
        nurse.setText(n.getFirstName()+" "+n.getLastName());
        BPL.setText(test.getBPL());
        BPH.setText(test.getBPH());
        LDLCholesterol.setText(test.getLDLCholesterol());
        HDLCholesterol.setText(test.getHDLCholesterol());
        MCH.setText(test.getMCH());
        ESR.setText(test.getESR());
        PlateletCount.setText(test.getPlateletCount());
        temperature.setText(test.getTemperature());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

}
