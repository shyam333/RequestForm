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
public class BloodRequestFragment extends Fragment implements AdapterView.OnItemSelectedListener {

Button sharebutton;
Spinner spinner1,spinner2;
EditText edtname,bloodamt,selectdate,location,mobile,phone,details;
TextView name,duedate;

final Calendar myCalendar = Calendar.getInstance();

    public BloodRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blood_request, container, false);
        sharebutton = (Button)v.findViewById(R.id.share);
        spinner1 = (Spinner)v.findViewById(R.id.spinner);
        spinner2 = (Spinner)v.findViewById(R.id.spinner2);
        selectdate = (EditText)v.findViewById(R.id.edt3);
        edtname = (EditText)v.findViewById(R.id.edt1);
        bloodamt = (EditText)v.findViewById(R.id.edt2);
        location = (EditText)v.findViewById(R.id.edt4);
        mobile = (EditText)v.findViewById(R.id.edt5);
        phone = (EditText)v.findViewById(R.id.edt6);
        details = (EditText)v.findViewById(R.id.edt7);
        name = (TextView)v.findViewById(R.id.textview1);
        duedate = (TextView)v.findViewById(R.id.txt2);

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

                String data4 = bloodamt.getText().toString();
                if(data4.isEmpty())
                {
                    data4 = "unknown";
                }
                String data5 = selectdate.getText().toString();
                if(data5.isEmpty())
                {
                    data5 = "unknown";
                }
                String data6 = location.getText().toString();
                if(data6.isEmpty())
                {
                    data6 = "unknown";
                }
                String data7 = mobile.getText().toString();
                if(data7.isEmpty())
                {
                    data7 = "unknown";
                }
                String data8 = phone.getText().toString();
                if(data8.isEmpty())
                {
                    data8 = "unknown";
                }
                String data9 = details.getText().toString();
                if(data9.isEmpty())
                {
                    data9 = "unknown";
                }
                final String data2 =  spinner1.getSelectedItem().toString();
                final String data3 =  spinner2.getSelectedItem().toString();

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "#BLOOD REQUEST" + "\n" + "Name: " + data1 + "\n" + "Blood Group: " + data2 + " " + data3 + "\n" + "Units: " + data4 + "\n" + "Due Date: " + data5 + "\n" + "Current Location: " + data6 + "\n" + "Mobile: " + data7 + "\n" + "Phone: " + data8 + "\n" + "Details: " + data9;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Share via"));
            }
        });

        ArrayAdapter<CharSequence>adapter1 = ArrayAdapter.createFromResource(getActivity(),R.array.blood_groups,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence>adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.urgent,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

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
