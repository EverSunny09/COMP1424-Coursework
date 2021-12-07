package com.example.comp1424;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class JsonActivity extends AppCompatActivity {
    public WebView webView,request;
    public String jsonString;
    public List<DetailJson> tripList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        webView = findViewById(R.id.webkit);
        request = findViewById(R.id.request);
        jsonString = createJSON();
        setBrowserJob(jsonString);
    }

    private String generatePage(String content) {
        return "<html><body><p>" + content + "</p></body></html>";
    }

    public void setBrowserJob(String JSON){
        try {
            URL pageURL = new URL("https://stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cw");//new URL(url);
            trustAllHosts();
            HttpURLConnection con = (HttpURLConnection)pageURL.openConnection();

            JsonRequestThread task = new JsonRequestThread(this,con,JSON);
            Thread thread = new Thread(task, "JSON Trip Data");
            String page = generatePage(JSON);
            request.loadData(page, "text/html", "UTF-8");
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        } };
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createJSON(){
        AllTripJsonObject allTripJsonObject = new AllTripJsonObject();
        allTripJsonObject.setUserId("sc2008y");
        allTripJsonObject.setDetailList(getTripDetails());
        Gson jsonObject = new Gson();
        return jsonObject.toJson(allTripJsonObject);
    }

    private List<DetailJson> getTripDetails(){
        DataBaseHelper db = new DataBaseHelper(this);
        ArrayList<NewTrip> allTrips = db.getTripDetails();

        List<DetailJson> detailJsonList = new ArrayList<>();
        for(NewTrip trip : allTrips){
            DetailJson obj= new DetailJson();
            obj.setTripName(trip.getNameofthetrip());
            obj.setTripDestination(trip.getDestination());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
            Date dt= new Date(trip.getDateoftrip()* 1000L);
            obj.setTripDate(simpleDateFormat.format(dt));
            obj.setTripTransportMethod(trip.getTransportmethod());
            obj.setAllExpenseList(getExpenseForTrip(trip.getId()));
            detailJsonList.add(obj);
        }
        return detailJsonList;
    }

    private List<ExpenseJson> getExpenseForTrip(int tripId){
        DataBaseHelper db = new DataBaseHelper(this);
        ArrayList<NewExpense> allExpense = db.getExpenseDetails(String.valueOf(tripId));
        List<ExpenseJson> expenseJsonList = new ArrayList<>();
        for(NewExpense expense:allExpense){
            ExpenseJson obj = new ExpenseJson();
            obj.setAmt(String.valueOf(expense.getAmount()));
            obj.setType(expense.getExpense());
            expenseJsonList.add(obj);
        }
        return expenseJsonList;

    }
}