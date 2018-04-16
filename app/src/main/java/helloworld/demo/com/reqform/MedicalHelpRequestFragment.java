package helloworld.demo.com.reqform;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicalHelpRequestFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Button sharebutton;
    Spinner spinner1;
    EditText edtname,age,problem,amt,selectdate,location,mobile,phone,details;
    TextView name,duedate;

    final Calendar myCalendar = Calendar.getInstance();

    public MedicalHelpRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.medical_help_request, container, false);

        sharebutton = (Button)v.findViewById(R.id.sharebtn);
        spinner1 = (Spinner)v.findViewById(R.id.spinner);
        selectdate = (EditText)v.findViewById(R.id.edtdate);
        edtname = (EditText)v.findViewById(R.id.edtname);
        age = (EditText)v.findViewById(R.id.edtage);
        problem = (EditText)v.findViewById(R.id.edtpbm);
        amt = (EditText)v.findViewById(R.id.edtamt);
        location = (EditText)v.findViewById(R.id.edtaddrss);
        mobile = (EditText)v.findViewById(R.id.edtmob);
        phone = (EditText)v.findViewById(R.id.edtphn);
        details = (EditText)v.findViewById(R.id.edtdetails);
        name = (TextView)v.findViewById(R.id.textview1);
        duedate = (TextView)v.findViewById(R.id.textview2);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        selectdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data1 = edtname.getText().toString();
                if(data1.isEmpty())
                {
                    data1 = "unknown";
                }
                String data2 =  age.getText().toString();
                if(data2.isEmpty())
                {
                    data2 = "unknown";
                }
                String data4 = problem.getText().toString();
                if(data4.isEmpty())
                {
                    data4 = "unknown";
                }
                String data5 = amt.getText().toString();
                if(data5.isEmpty())
                {
                    data5 = "unknown";
                }
                String data6 = selectdate.getText().toString();
                if(data6.isEmpty())
                {
                    data6 = "unknown";
                }
                String data7 = location.getText().toString();
                if(data7.isEmpty())
                {
                    data7 = "unknown";
                }
                String data8 = mobile.getText().toString();
                if(data8.isEmpty())
                {
                    data8 = "unknown";
                }
                String data9 = phone.getText().toString();
                if(data9.isEmpty())
                {
                    data9 = "unknown";
                }
                String data10 = details.getText().toString();
                if(data10.isEmpty())
                {
                    data10 = "unknown";
                }
                final String data3 = spinner1.getSelectedItem().toString();

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "#MEDICAL HELP REQUEST" + "\n" + "Name: " + data1 + "\n" + "Age: " + data2 + " " + data3 + "\n" + "Problem: " + data4 + "\n" + "Amount Required: " + data5 + "\n" + "Due Date: " + data6 + "\n" + "Current Location: " + data7 + "\n" + "Mobile: " + data8 + "\n" + "Phone: " + data9 +  "\n" + "Details: " + data10;
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Share via"));
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.emergency,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);


        return v;
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        selectdate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
