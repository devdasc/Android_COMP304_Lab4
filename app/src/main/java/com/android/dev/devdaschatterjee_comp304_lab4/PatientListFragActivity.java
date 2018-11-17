package com.android.dev.devdaschatterjee_comp304_lab4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.dev.devdaschatterjee_comp304_lab4.Database.DatabaseHandler;
import com.android.dev.devdaschatterjee_comp304_lab4.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientListFragActivity extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_patient_list_frag);
//    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static DatabaseHandler db;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;

    private OnPatientSelectListener mListener;

    public PatientListFragActivity() {
        db=new DatabaseHandler(getActivity());
    }


    public static PatientListFragActivity newInstance(String param1, String param2) {
        PatientListFragActivity fragment = new PatientListFragActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_patient_list_frag, container, false);
        ListView patientListView=(ListView)view.findViewById(R.id.PatientInfoListView);
        populatePatientListView(patientListView);
        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Patient entry = (Patient) parent.getItemAtPosition(position);
                sendPatientIdToActivity(entry.getPatientId());
            }


        });
        SharedPreferences prefs = context.getSharedPreferences(
                "com.android.dev.devdaschatterjee_comp304_lab4", Context.MODE_PRIVATE);
        String Username = prefs.getString("UserName","UserName");
        String type = prefs.getString("Type","Type");
        if(type.equals("Nurse"))
        {
            final Intent intent1 = new Intent(context, PatientAddUpdateActivity.class);
            intent1.putExtra("purpose","add");
            FloatingActionButton addPatientBtn=new FloatingActionButton(context); // button to add new patient
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

            addPatientBtn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_person_add_white_24dp));
            // Button addPatientBtn=new Button(this);
            //addPatientBtn.setText("Add new Pateint");
            final RelativeLayout btnLayout=(RelativeLayout) view.findViewById(R.id.outerMostLayout);
            btnLayout.addView(addPatientBtn);
            addPatientBtn.setLayoutParams(params);
            addPatientBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(intent1);
                }
            });
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPatientSelectListener) {
            mListener = (OnPatientSelectListener) context;
            this.context=context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        db=new DatabaseHandler(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnPatientSelectListener {

        void onItemSelected(int id);
    }
    public void sendPatientIdToActivity(int id)
    {
        if(mListener!=null)
            mListener.onItemSelected(id);
    }
    public void populatePatientListView(ListView listview) {

        List<Patient> patients=db.getAllPatients();
        final ArrayList<Patient> list = new ArrayList<Patient>();
        for (Patient p:patients) {
            list.add(new Patient(p.getPatientId(),p.getFirstName()+" "+p.getLastName(),"","","",""));
        }
        final ArrayAdapter adapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
    }
}
