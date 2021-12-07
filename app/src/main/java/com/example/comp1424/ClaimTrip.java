package com.example.comp1424;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
//import android.R;

public class ClaimTrip extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    private Button button1,confirm_button;
    EditText dateoftrip;
    String dateToDB;
    TextView nameofthetrip,destination,description,transmethod,vendorname,enterdetails;
    Switch risk;
    String trip_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_trip);


        button1=(Button) findViewById(R.id.Back_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        dateoftrip=(EditText) findViewById(R.id.dateoftrip);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

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


        dateoftrip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ClaimTrip.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                updateLabel();
            }
        });



        nameofthetrip =(TextView) findViewById(R.id.nameofthetrip);
        destination =(TextView) findViewById(R.id.destination);
        confirm_button = (MaterialButton)findViewById(R.id.confirm_button);
        description =(TextView) findViewById(R.id.description);
        transmethod =(TextView) findViewById(R.id.transmethod);
        vendorname =(TextView) findViewById(R.id.vendorname);
        enterdetails = (TextView)findViewById(R.id.enterdetails);

        MaterialButton clearbtn = (MaterialButton) findViewById(R.id.clearbtn);
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                nameofthetrip.setText("");
                destination.setText("");
                dateoftrip.setText("");
                description.setText("");
                transmethod.setText("");
                vendorname.setText("");

            }
        });

        Intent i = getIntent();
        trip_id = i.getStringExtra("trip_id");
        if(trip_id != null){
            nameofthetrip.setText(i.getStringExtra("tripName"));
            destination.setText(i.getStringExtra("tripDestination"));
            description.setText(i.getStringExtra("tripDescription"));
            transmethod.setText(i.getStringExtra("tripTransMethod"));
            vendorname.setText(i.getStringExtra("tripVendorName"));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
            Date dt= new Date(Integer.parseInt(i.getStringExtra("tripDate"))* 1000L);
            dateoftrip.setText(simpleDateFormat.format(dt));

            //dateoftrip.setText(i.getStringExtra("tripDate"));
            confirm_button.setText("Update");
            enterdetails.setText("Update the details of the Trip");
            dateToDB = i.getStringExtra("tripDate");
        }
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put hereSimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateoftrip.setText(sdf.format(myCalendar.getTime()));
        dateToDB = String.valueOf(myCalendar.getTime().getTime()/1000);
    }

    public void moveToConfirmation(View view){
        boolean valid = validateForm();
        if(valid){
            moveToNextScreen();
        }
        else{
            Toast.makeText(getApplicationContext(), "Please enter all the details.", Toast.LENGTH_SHORT).show();
        }

    }

    public void moveToNextScreen(){
        Intent i=new Intent(ClaimTrip.this, ConfirmationPage.class);
        String nameofTrip = nameofthetrip.getText().toString();
        i.putExtra("tripName", nameofTrip);

        String Destination = destination.getText().toString();
        i.putExtra("destination", Destination);

        String DateOfTrip = dateoftrip.getText().toString();
        i.putExtra("dateoftrip", DateOfTrip);

        String Description = description.getText().toString();
        i.putExtra("description", Description);

        String TransportMethod = transmethod.getText().toString();
        i.putExtra("transmethod", TransportMethod);

        String VendorName = vendorname.getText().toString();
        i.putExtra("vendorname", VendorName);

        i.putExtra("dateToDB",dateToDB);
        i.putExtra("trip_id",trip_id);
        startActivity(i);
    }

    public boolean validateForm(){
        if(nameofthetrip.getText().toString().length() > 0 &&
                destination.getText().toString().length() > 0 &&
                dateoftrip.getText().toString().length() > 0 ) {
            return true;
        }
        else{
            return false;
        }
    }
    public void openMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}