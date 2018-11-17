package com.android.dev.devdaschatterjee_comp304_lab4;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.dev.devdaschatterjee_comp304_lab4.Database.DatabaseHandler;

public class PatientInfoActivity extends AppCompatActivity implements PatientListFragActivity.OnPatientSelectListener,PatientInfoFragActivity.OnPatientDetailsListener{
    DatabaseHandler db;
    Context context;
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    public PatientInfoActivity()
    {
        db= new DatabaseHandler(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        final TextView welcomeTxt = (TextView) findViewById(R.id.txtWelcomeTest);
        SharedPreferences prefs = this.getSharedPreferences(
                "com.android.dev", Context.MODE_PRIVATE);
        String Username = prefs.getString("UserName","UserName");
        String type = prefs.getString("Type","Type");
        welcomeTxt.setText("Welcome "+Username+" to Patient information management system");
        final Intent intent=getIntent();
        final Intent intent1 = new Intent(this, PatientAddUpdateActivity.class);
        intent1.putExtra("purpose","add");
        context=this;
        //FragmentActivity.getSupportFragmentManager()
        //FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.patientInfoLayout, new PatientListFragActivity());

        ft.commit();
        if(type.equals("Nurse"))
        {
            FloatingActionButton addPatientBtn=new FloatingActionButton(this);// button to add new button
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

            addPatientBtn.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_person_add_white_24dp));
          // Button addPatientBtn=new Button(this);
            //addPatientBtn.setText("Add new Pateint");
            final RelativeLayout btnLayout=(RelativeLayout) findViewById(R.id.outerMostLayouts);
           btnLayout.addView(addPatientBtn);
            addPatientBtn.setLayoutParams(params);
            addPatientBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   startActivity(intent1);
                }
            });
        }

    }
    public void onPatientListClick(View v)
    {
        addPatientListFragment();
    }
    public void addPatientListFragment(){
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.patientInfoLayout, new PatientListFragActivity()).addToBackStack(BACK_STACK_ROOT_TAG);
        ft.commit();
    }
    @Override
    public void onItemSelected(int id)
    {
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        PatientInfoFragActivity fragment=new PatientInfoFragActivity();
        fragment.setPatientDetails(id);
        ft.replace(R.id.patientInfoLayout, fragment);

        ft.commit();
    }

    @Override
    public void onPatientDetails(int id) {
        Intent intent=new Intent(this,TestInfoActivity.class);
        intent.putExtra("testId",id);
        startActivity(intent);
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
   /* public void onListBtnClick(View view) {
        final ListView listview = (ListView) findViewById(R.id.PatientInfoListView);
        List<Patient> patients=db.getAllPatients();
        final ArrayList<String> list = new ArrayList<String>();
        for (Patient p:patients) {
            list.add(p.getFirstName()+" "+p.getLastName());
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
    }*/
}
