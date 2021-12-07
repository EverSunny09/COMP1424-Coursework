package com.example.comp1424;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpenseAdapter extends ArrayAdapter<NewExpense> {
    private Context mContext;
    private int mResource;
    ArrayList<NewExpense> expenseList;

    public ExpenseAdapter(@NonNull Context context, int resource, @NonNull ArrayList<NewExpense> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.expenseList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView txtName = convertView.findViewById(R.id.nameTrip);

        TextView txtType = convertView.findViewById(R.id.typeofExpense);

        TextView txtAmount = convertView.findViewById(R.id.amount);

        TextView txtTime = convertView.findViewById(R.id.time);

        TextView txtComments = convertView.findViewById(R.id.comments);

        Button delete = convertView.findViewById(R.id.deleteBtn);

        txtType.setText("Type of expense: " +getItem(position).getExpense());

        txtAmount.setText("Amount of the expense: "+String.valueOf(getItem(position).getAmount()));

        txtTime.setText("Time of the expense: "+ getDateTime(getItem(position).getTime()));

        txtComments.setText("Additional comments: "+getItem(position).getComments());


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Do you want to delete this expense?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataBaseHelper db = new DataBaseHelper(mContext);
                                db.deleteExpenseWithId(String.valueOf(expenseList.get(position).getId()));
                                expenseList.remove(position);
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });


        return convertView;
    }

    private String getDateTime(Long date){
        Date dateNew = new Date(date);
        Format newDate = new SimpleDateFormat("HH:mm:ss");
        return newDate.format(dateNew);
    }

}
