package com.example.comp1424;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationPage extends AppCompatActivity {
    TextView nameOfTrip,DestOfTrip,DescOfTrip,TransMethod,VenName,DateTrip;
    String tripName, destination,dateoftrip,description,transmethod,vendorname,typeofexpense,amountofexpense,timeoftheexpense,additionalcomments;
    TextView TypeOfExpense;
    TextView AmountOfExpenses;
    String dateToDB;
    TextView  TimeOftheExpense;
    TextView AdditionalComments;
    String trip_id;
    NewTrip newTrip = new NewTrip();
    NewExpense newExpense = new NewExpense();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);

        Intent intent = getIntent();
        tripName = intent.getStringExtra("tripName");
        destination = intent.getStringExtra("destination");
        dateoftrip = intent.getStringExtra("dateoftrip");
        description = intent.getStringExtra("description");
        transmethod = intent.getStringExtra("transmethod");
        vendorname = intent.getStringExtra("vendorname");
        dateToDB = intent.getStringExtra("dateToDB");


        nameOfTrip = (TextView) findViewById(R.id.nameofthetrip);
        DateTrip = (TextView) findViewById(R.id.dateoftrip);
        DestOfTrip = (TextView) findViewById(R.id.destination);
        DescOfTrip = (TextView) findViewById(R.id.description);
        TransMethod = (TextView) findViewById(R.id.transmethod);
        VenName = (TextView) findViewById(R.id.vendorname);

        nameOfTrip.setText(tripName);
        DestOfTrip.setText(destination);
        DateTrip.setText(dateoftrip);
        DescOfTrip.setText(description);
        TransMethod.setText(transmethod);
        VenName.setText(vendorname);

        trip_id = intent.getStringExtra("trip_id");


    }
    public void saveToDB(View view){
        DataBaseHelper db = new DataBaseHelper(this);
        newTrip.setNameofthetrip(tripName);
        newTrip.setDestination(destination);
        newTrip.setDateoftrip(Integer.parseInt(dateToDB));
        newTrip.setDescription(description);
        newTrip.setTransportmethod(transmethod);
        newTrip.setVendorname(vendorname);
        if(trip_id==null){
            if(db.addNewTrip(newTrip) == -1){
                Toast.makeText(getApplicationContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Trip Details added successfully.", Toast.LENGTH_SHORT).show();
                onClickSave();
            }
        }
        else{
            newTrip.setId(Integer.parseInt(trip_id));
            if(!db.updateTrip(newTrip)){
                Toast.makeText(getApplicationContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Trip Details updated successfully.", Toast.LENGTH_SHORT).show();
                onClickSave();
            }

        }

    }
    public void saveExpenseDB(){
        DataBaseHelper db = new DataBaseHelper(this);
        newExpense.setExpense(typeofexpense);
        newExpense.setAmount(Integer.parseInt(amountofexpense));
        newExpense.setTime(Integer.parseInt(timeoftheexpense));
        newExpense.setComments(additionalcomments);
        if(db.addExpenses(newExpense) == -1){
            Toast.makeText(getApplicationContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Trip and Expenses Details added successfully.", Toast.LENGTH_SHORT).show();
        }

    }
    public void onBackButton(View view){
        finish();
    }
    public void onClickSave(){
       Intent intent = new Intent(ConfirmationPage.this,MainActivity.class);
       startActivity(intent);
    }

}