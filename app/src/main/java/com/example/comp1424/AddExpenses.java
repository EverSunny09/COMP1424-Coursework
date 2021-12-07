package com.example.comp1424;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Calendar;

public class AddExpenses extends AppCompatActivity {
    private Button button1;
    Spinner allTrips;
    TextView typeofexpense;
    TextView amountofexpenses;
    String dateToDB;
    TextView  timeoftheexpense;
    int trip_id;
    Long time;
    NewExpense newExpense = new NewExpense();
    TextView additionalcomments;
    String tripName, destination,dateoftrip,description,transmethod,vendorname ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        button1=(Button) findViewById(R.id.Back_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        MaterialButton clearbtn = (MaterialButton) findViewById(R.id.clearbtn);
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                typeofexpense.setText("");
                amountofexpenses.setText("");
                timeoftheexpense.setText("");
                additionalcomments.setText("");

            }
        });

        Intent intent = getIntent();
        tripName = intent.getStringExtra("tripName");
        destination = intent.getStringExtra("destination");
        dateoftrip = intent.getStringExtra("dateoftrip");
        description = intent.getStringExtra("description");
        transmethod = intent.getStringExtra("transmethod");
        vendorname = intent.getStringExtra("vendorname");
        dateToDB = intent.getStringExtra("dateToDB");

        allTrips = (Spinner)findViewById(R.id.spinner);
        spinnerFunction();
        typeofexpense =(TextView) findViewById(R.id.typeofexpense);
        amountofexpenses =(TextView) findViewById(R.id.amountofexpense);
        timeoftheexpense =(TextView) findViewById(R.id.timeoftheexpense);
        timeoftheexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddExpenses.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override public void onTimeSet(TimePicker view, int hourOfDay,
                                                            int minute) {

                                timeoftheexpense.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
                time = mcurrentTime.getTimeInMillis();
            }
        });
        additionalcomments =(TextView) findViewById(R.id.additionalcomments);

        setData();
    }

    private void spinnerFunction(){
        allTrips.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TripNameValue value = (TripNameValue)parent.getSelectedItem();
                trip_id = Integer.parseInt(value.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }

    public void clear(View view) {
        //typeofexpense.setText("");
        amountofexpenses.setText("");
        timeoftheexpense.setText("");
        additionalcomments.setText("");

    }

    public void moveToConfirmation(View view){
        boolean valid = validateForm();
        if(valid){
            saveExpenseDB();
        }
        else{
            Toast.makeText(getApplicationContext(), "Please enter all the details.", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean validateForm(){
        if(typeofexpense.getText().toString().length() > 0  &&
                amountofexpenses.getText().toString().length() > 0 &&
                timeoftheexpense.getText().toString().length() > 0 ) {
            return true;
        }
        else{
            return false;
        }
    }
    public void moveToNextScreen(){
        Intent i=new Intent(AddExpenses.this, ConfirmationPage.class);

        i.putExtra("tripName", tripName);


        i.putExtra("destination", destination);

        i.putExtra("dateoftrip", dateoftrip);


        i.putExtra("description", description);


        i.putExtra("transmethod", transmethod);

        i.putExtra("vendorname", vendorname);


        String TypeOfExpense = typeofexpense.getText().toString();
        i.putExtra("typeofexpense", TypeOfExpense);

        String AmountOfExpense = amountofexpenses.getText().toString();
        i.putExtra("amountofexpense", AmountOfExpense);

        String TimeOfTheExpense = timeoftheexpense.getText().toString();
        i.putExtra("timeoftheexpense", TimeOfTheExpense);

        String AdditionalComments = additionalcomments.getText().toString();
        i.putExtra("additionalcomments", AdditionalComments);

        i.putExtra("dateToDB",dateToDB);
        i.putExtra("tripId",String.valueOf(trip_id));

        startActivity(i);
    }

    public void openMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setData(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        ArrayList<TripNameValue> result = dataBaseHelper.getTripNameValue();
        ArrayAdapter<TripNameValue> adapter = new ArrayAdapter<>(AddExpenses.this, android.R.layout.simple_spinner_dropdown_item,result);
        allTrips.setAdapter(adapter);
        trip_id = Integer.parseInt(result.get(0).getId());
    }

    public void saveExpenseDB(){
        DataBaseHelper db = new DataBaseHelper(this);
        newExpense.setExpense(typeofexpense.getText().toString());
        newExpense.setAmount(Integer.parseInt(amountofexpenses.getText().toString()));
        newExpense.setTime(time);
        newExpense.setComments(additionalcomments.getText().toString());
        newExpense.setTrip_id(trip_id);
        if(db.addExpenses(newExpense) == -1){
            Toast.makeText(getApplicationContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Expenses Details added successfully.", Toast.LENGTH_SHORT).show();
            openMainActivity();
        }

    }

}