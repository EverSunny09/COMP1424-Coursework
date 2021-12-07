package com.example.comp1424;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class Search extends AppCompatActivity {
    private Button button1;
    TextView tripDate;
    EditText etDate,nameofthetrip,destination;
    ListView listView;
    ArrayList<NewTrip> filteredList;
    TripAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = findViewById(R.id.listView);
        nameofthetrip = findViewById(R.id.nameofthetrip);
        destination = findViewById(R.id.destination);
        button1=(Button) findViewById(R.id.Back_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        ArrayList<NewTrip> arrayList = new ArrayList<>();
        filteredList = new ArrayList<>();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        arrayList = dataBaseHelper.getTripDetails();

        //Trip adapter
        adapter = new TripAdapter(Search.this,R.layout.list_row,arrayList);
        listView.setAdapter(adapter);


    }
    public void openMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void searchButton(View view){
        if(!nameofthetrip.getText().toString().isEmpty() && !destination.getText().toString().isEmpty()){
            adapter.filterByName("");
            adapter.filterByDestination("");
            adapter.filterByNameDestination(nameofthetrip.getText().toString(),destination.getText().toString());
        }
        else if(!nameofthetrip.getText().toString().isEmpty() && destination.getText().toString().isEmpty()){
            adapter.filterByDestination("");
            adapter.filterByNameDestination("","");
            adapter.filterByName(nameofthetrip.getText().toString());
        }
        else if(nameofthetrip.getText().toString().isEmpty() && !destination.getText().toString().isEmpty()){
            adapter.filterByName("");
            adapter.filterByNameDestination("","");
            adapter.filterByDestination(destination.getText().toString());
        }
        else{
            adapter.filterByName("");
            adapter.filterByDestination("");
            adapter.filterByNameDestination("","");
        }
    }
}

