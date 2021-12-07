package com.example.comp1424;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TripAdapter extends ArrayAdapter<NewTrip> {
    private Context mContext;
    private int mResource;
    LayoutInflater layoutInflater;
    List<NewTrip> tripList;
    ArrayList<NewTrip> tripArrayList;
    //List<NewTrip> tripListNew;

    public TripAdapter(@NonNull Context context, int resource, @NonNull List<NewTrip> objects){
        super(context,resource,objects);
        this.mContext = context;
        this.mResource = resource;
        this.tripList=objects;
        layoutInflater = LayoutInflater.from(mContext);
        this.tripArrayList = new ArrayList<>();
        this.tripArrayList.addAll(this.tripList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView txtName = convertView.findViewById(R.id.nameTrip);

        TextView txtDes = convertView.findViewById(R.id.destination);

        TextView txtDate = convertView.findViewById(R.id.date);

        Button delete = convertView.findViewById(R.id.deleteBtn);

        txtName.setText(getItem(position).getNameofthetrip());

        txtDes.setText(getItem(position).getDestination());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date dt= new Date(getItem(position).getDateoftrip()* 1000L);
        txtDate.setText(simpleDateFormat.format(dt));


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Do you want to delete trip ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DataBaseHelper db = new DataBaseHelper(mContext);
                                    db.deleteTripWithId(String.valueOf(tripList.get(position).getId()));
                                    tripList.remove(position);
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

        Button editButton = convertView.findViewById(R.id.editBtn);
        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int trip_id = tripList.get(position).getId();
                Intent i = new Intent(mContext,ClaimTrip.class);
                i.putExtra("trip_id",String.valueOf(trip_id));
                i.putExtra("tripName",tripList.get(position).getNameofthetrip());
                i.putExtra("tripDescription",tripList.get(position).getDescription());
                i.putExtra("tripDestination",tripList.get(position).getDestination());
                i.putExtra("tripDate",String.valueOf(tripList.get(position).getDateoftrip()));
                i.putExtra("tripTransMethod",tripList.get(position).getTransportmethod());
                i.putExtra("tripVendorName",tripList.get(position).getVendorname());
                mContext.startActivity(i);
            }
        });

        Button expensesButton = convertView.findViewById(R.id.expensesBtn);
        expensesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int trip_id = tripList.get(position).getId();
                Intent i = new Intent(mContext,MyExpenses.class);
                i.putExtra("trip_id",String.valueOf(trip_id));
                i.putExtra("tripName",tripList.get(position).getNameofthetrip());

                mContext.startActivity(i);
            }
        });

        return convertView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterByName(String toSearch){
        tripList.clear();
        if(toSearch.length()>0){
            List<NewTrip> trips = tripArrayList.stream().filter(x->x.getNameofthetrip().toLowerCase(Locale.getDefault()).contains(toSearch.toLowerCase(Locale.getDefault()))).collect(Collectors.toList());
            tripList.addAll(trips);
        }
        else{
            tripList.addAll(tripArrayList);
        }
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterByDestination(String toSearch){
        tripList.clear();
        if(toSearch.length()>0){
            List<NewTrip> trips = tripArrayList.stream().filter(x->x.getDestination().toLowerCase(Locale.getDefault()).contains(toSearch.toLowerCase(Locale.getDefault()))).collect(Collectors.toList());
            tripList.addAll(trips);
        }
        else{
            tripList.addAll(tripArrayList);
        }
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterByNameDestination(String name,String destination){
        tripList.clear();
        if(name.length()>0 &&  destination.length()>0){
            List<NewTrip> trips = tripArrayList.stream().filter(x->(x.getNameofthetrip().toLowerCase(Locale.getDefault()).contains(name.toLowerCase(Locale.getDefault())))
            && (x.getDestination().toLowerCase(Locale.getDefault()).contains(destination.toLowerCase(Locale.getDefault())))).collect(Collectors.toList());
            tripList.addAll(trips);
        }
        else{
            tripList.addAll(tripArrayList);
        }
        notifyDataSetChanged();
    }
}
