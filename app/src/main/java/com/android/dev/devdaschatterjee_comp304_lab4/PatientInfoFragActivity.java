package com.android.dev.devdaschatterjee_comp304_lab4;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.dev.devdaschatterjee_comp304_lab4.Database.DatabaseHandler;
import com.android.dev.devdaschatterjee_comp304_lab4.models.Doctor;
import com.android.dev.devdaschatterjee_comp304_lab4.models.Patient;
import com.android.dev.devdaschatterjee_comp304_lab4.models.Test;

import java.util.List;

public class PatientInfoFragActivity extends Fragment {

    private OnPatientDetailsListener mListener;
    private  static DatabaseHandler db;
    private Context context;
    private int patientId;
    public PatientInfoFragActivity() {
        // Required empty public constructor
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight +(listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
    public static PatientInfoFragActivity newInstance(String param1, String param2) {
        PatientInfoFragActivity fragment = new PatientInfoFragActivity();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_patient_info_frag, container, false);
        Button editPatient=(Button)view.findViewById(R.id.editPatientBtn);
        editPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,PatientAddUpdateActivity.class);
                intent.putExtra("purpose","update");
                intent.putExtra("patientId",patientId);
                startActivity(intent);
            }
        });
        // button to add test to a patient
        FloatingActionButton addTest=(FloatingActionButton)view.findViewById(R.id.addTestBtn);
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(context,TestAddUpdateActivity.class);
                intent1.putExtra("purpose","add");
                intent1.putExtra("patientId",patientId);
                startActivity(intent1);
            }
        });

        setPatientDetails(view);
        return view;
    }

    public void setPatientDetails(View view){
        Patient p = db.getPatient(patientId);
        //Nurse n=db.getNurse(p.ge)
        Doctor d = db.getDoctor(p.getDoctorId());
        TextView firstName=(TextView) view.findViewById(R.id.firstnameTxt);
        TextView lastName=(TextView) view.findViewById(R.id.lastnameTxt);
        TextView department=(TextView) view.findViewById(R.id.departmentTxt);
        TextView doctor=(TextView) view.findViewById(R.id.doctorTxt);
        TextView roomEdt=(TextView)view.findViewById(R.id.roomTxt);
        ListView testList=(ListView)view.findViewById(R.id.testListView);
        List<Test> t=db.getTestByPatient(patientId);
        testList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Test entry = (Test) parent.getItemAtPosition(position);
                mListener.onPatientDetails(entry.getTestId());
            }


        });

        final ArrayAdapter adapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,t);

        testList.setAdapter(adapter);
        setListViewHeightBasedOnChildren(testList);
        firstName.setText(p.getFirstName());
        lastName.setText(p.getLastName());
        department.setText(p.getDepartment());
        doctor.setText(d.getFirstName()+" "+d.getLastName());
        roomEdt.setText(p.getRoom());
        // Inflate the layout for this fragment
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPatientDetailsListener) {
            mListener = (OnPatientDetailsListener) context;
            this.context=context;
            db=new DatabaseHandler(context);
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnPatientDetailsListener {
        // TODO: Update argument type and name
        void onPatientDetails(int id);
    }
    public void setPatientDetails(int id)
    {
        try {            patientId=id;        }
        catch (Exception e)
        {        }
    }
}
