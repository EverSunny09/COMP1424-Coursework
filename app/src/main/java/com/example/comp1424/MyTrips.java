package com.example.comp1424;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MyTrips extends AppCompatActivity {
    private Button button1;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);

        listView = findViewById(R.id.listView);

        button1=(Button) findViewById(R.id.Back_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        //Create data
        ArrayList<NewTrip> arrayList = new ArrayList<>();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        arrayList = dataBaseHelper.getTripDetails();

        //Trip adapter
        TripAdapter tripAdapter = new TripAdapter(this,R.layout.list_row,arrayList);

        listView.setAdapter(tripAdapter);



    }
    public void openMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}