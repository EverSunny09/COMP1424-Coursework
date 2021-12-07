package com.example.comp1424;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
       // Button customButton = findViewById(R.id.button1);

        Button button = (Button) findViewById(R.id.SearchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySearch();
            }
        });
        Button button1 =(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityClaimTrip();
            }
        });
        Button button2 =(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMyTrips();
            }
        });
        Button button3 =(Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActiviyAddExpenses();
            }
        });
        Button button4 =(Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityJson();
            }
        });
        /*
        button =(Button) findViewById(R.id.button4);
        button.setOnClickListener(v -> openActivityCovidInformationHub());*/



    }
    public void openActivitySearch()
    {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
    public void openActivityClaimTrip()
    {
        Intent intent = new Intent(MainActivity.this, ClaimTrip.class);
        startActivity(intent);
    }
    public void openActivityMyTrips()
    {
        Intent intent = new Intent(this, MyTrips.class);
        startActivity(intent);
    }
    public void openActiviyAddExpenses()
    {
        Intent intent = new Intent(this, AddExpenses.class);
        startActivity(intent);
    }
    public void openActivityJson()
    {
        Intent intent = new Intent(this, JsonActivity.class);
        startActivity(intent);
    }
}