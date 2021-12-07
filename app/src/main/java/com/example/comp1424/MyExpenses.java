package com.example.comp1424;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MyExpenses extends AppCompatActivity {
    private Button button1;
    ListView listView;
    String trip_id,tripName;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expenses);
        Intent intent = getIntent();
        trip_id = intent.getStringExtra("trip_id");
        tripName = intent.getStringExtra("tripName");
        title = findViewById(R.id.ExpensesTitle);
        listView = findViewById(R.id.expenseListView);

        title.setText("Expenses for "+tripName);

        button1 = (Button) findViewById(R.id.Back_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyTrips();
            }
        });
        //Create data
        ArrayList<NewExpense> arrayList = new ArrayList<>();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        arrayList = dataBaseHelper.getExpenseDetails(trip_id);

        //Expense adapter
        ExpenseAdapter expenseAdapter = new ExpenseAdapter(this,R.layout.list_expenses,arrayList);

        listView.setAdapter(expenseAdapter);
    }
    public void openMyTrips()
    {
        Intent intent = new Intent(this, MyTrips.class);
        startActivity(intent);
    }



}