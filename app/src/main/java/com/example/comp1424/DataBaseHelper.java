package com.example.comp1424;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String databaseName = "expenseManagement.db";

    public static final String TRIPS_TABLE = "TRIPS_TABLE";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TRIP_ID = "trip_id";
    public static final String COLUMN_DESTINATION = "destination";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TRANSPORT_METHOD = "transport_method";
    public static final String COLUMN_VENDOR_NAME = "vendor_name";

    public static final String EXPENSE_TABLE = "EXPENSE_TABLE";
    public static final String COLUMN_EXPENSE_ID = "expense_id";
    public static final String COLUMN_EXPENSE = "expense";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_COMMENTS = "comments";

    public SQLiteDatabase ExpenseManagementDatabase;
    public DataBaseHelper(Context context){
        super(context,databaseName,null,4);
        ExpenseManagementDatabase = getWritableDatabase();
    }


    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //this is called the first time a database is accessed. Code to generate a new table
    @Override
    public void onCreate(SQLiteDatabase db) {
            String createTripTableStatement = "CREATE TABLE TRIPS_TABLE (" + COLUMN_TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_DESTINATION + " TEXT, " + COLUMN_DATE + " NUMERIC, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_TRANSPORT_METHOD + " TEXT, " + COLUMN_VENDOR_NAME + " TEXT)";
            String createExpenseTableStatement = "CREATE TABLE EXPENSE_TABLE (" + COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EXPENSE + " TEXT, " + COLUMN_AMOUNT + " NUMERIC, " + COLUMN_TIME + " NUMERIC, " + COLUMN_COMMENTS + " TEXT, " + COLUMN_TRIP_ID + " INTEGER)";

        db.execSQL(createTripTableStatement);
        db.execSQL(createExpenseTableStatement);

    }

    //this is called if the database version number changes. It prevents apps from breaking when you change the databse design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TRIPS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+EXPENSE_TABLE);
        onCreate(db);
    }

    public long addNewTrip(NewTrip trip){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,trip.getNameofthetrip());
        cv.put(COLUMN_DESTINATION,trip.getDestination());
        cv.put(COLUMN_DATE,trip.getDateoftrip());
        cv.put(COLUMN_DESCRIPTION,trip.getDescription());
        cv.put(COLUMN_TRANSPORT_METHOD,trip.getTransportmethod());
        cv.put(COLUMN_VENDOR_NAME,trip.getVendorname());
        long insert = db.insert(TRIPS_TABLE,null,cv);
        return insert;
    }

    public long addExpenses(NewExpense expense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EXPENSE,expense.getExpense());
        cv.put(COLUMN_AMOUNT,expense.getAmount());
        cv.put(COLUMN_TIME,expense.getTime());
        cv.put(COLUMN_COMMENTS,expense.getComments());
        cv.put(COLUMN_TRIP_ID,expense.getTrip_id());
        long insert = db.insert(EXPENSE_TABLE,null,cv);
        return insert;
    }

    public ArrayList<TripNameValue> getTripNameValue(){
        ArrayList<TripNameValue> allTrips = new ArrayList<>();
        Cursor output =  ExpenseManagementDatabase.rawQuery("Select trip_id,name from TRIPS_TABLE",null);
        output.moveToNext();
        while (!output.isAfterLast()){
            TripNameValue trip = new TripNameValue();
            trip.setId(output.getString(0));
            trip.setTripName(output.getString(1));
            allTrips.add(trip);

            output.moveToNext();
        }

        return allTrips;
    }
    public ArrayList<NewTrip> getTripDetails(){
        ArrayList<NewTrip> allTrips = new ArrayList<>();
        Cursor output =  ExpenseManagementDatabase.rawQuery("Select * from TRIPS_TABLE",null);
        output.moveToNext();
        while (!output.isAfterLast()){
            NewTrip trip = new NewTrip();
            trip.setId(output.getInt(0));
            trip.setNameofthetrip(output.getString(1));
            trip.setDestination(output.getString(2));
            trip.setDateoftrip(output.getInt(3));
            trip.setDescription(output.getString(4));
            trip.setTransportmethod(output.getString(5));
            trip.setVendorname(output.getString(6));
            allTrips.add(trip);

            output.moveToNext();
        }

        return allTrips;
    }

    public void deleteTripWithId(String trip_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EXPENSE_TABLE,COLUMN_TRIP_ID+"="+trip_id,null);
        db.delete(TRIPS_TABLE,COLUMN_TRIP_ID+"="+trip_id,null);
    }

    public void deleteExpenseWithId(String expense_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EXPENSE_TABLE,COLUMN_EXPENSE_ID+"="+expense_id,null);
    }

    public boolean updateTrip(NewTrip trip){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,trip.getNameofthetrip());
        cv.put(COLUMN_DESTINATION,trip.getDestination());
        cv.put(COLUMN_DATE,trip.getDateoftrip());
        cv.put(COLUMN_DESCRIPTION,trip.getDescription());
        cv.put(COLUMN_TRANSPORT_METHOD,trip.getTransportmethod());
        cv.put(COLUMN_VENDOR_NAME,trip.getVendorname());
        int result = db.update(TRIPS_TABLE,cv,"trip_id = ?",new String[]{String.valueOf(trip.getId())});
        return result > 0;
    }

    public ArrayList<NewExpense> getExpenseDetails(String trip_id){
        ArrayList<NewExpense> allExpenses = new ArrayList<>();
        Cursor output =  ExpenseManagementDatabase.rawQuery("Select * from "+ EXPENSE_TABLE +" where "+COLUMN_TRIP_ID+" = ?",new String[]{trip_id});
        output.moveToNext();
        while (!output.isAfterLast()){
            NewExpense expense = new NewExpense();
            expense.setId(output.getInt(0));
            expense.setExpense(output.getString(1));
            expense.setAmount(output.getInt(2));
            expense.setTime(output.getInt(3));
            expense.setComments(output.getString(4));
            expense.setTrip_id(output.getInt(5));
            allExpenses.add(expense);

            output.moveToNext();
        }

        return allExpenses;
    }
}
