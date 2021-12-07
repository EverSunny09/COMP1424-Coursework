package com.example.comp1424;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URLEncoder;

public class JsonRequestThread implements Runnable{

    private AppCompatActivity activity;
    private HttpURLConnection con;
    private String jsonPayLoad;

    public JsonRequestThread(AppCompatActivity activity, HttpURLConnection con, String jsonPayload)
    {
        this.activity = activity;
        this.con = con;
        this.jsonPayLoad = jsonPayload;
    }

    @Override
    public void run()
    {
        String response = "";
        if (prepareConnection()) {
            response = postJson();
        } else {
            response = "Error preparing the connection";
        }
        showResult(response);
    }


    private void showResult(String response) {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                String page = generatePage(response);
                ((JsonActivity)activity).webView.loadData(page, "text/html", "UTF-8");
            }
        });
    }

    private String postJson() {
        String response = "";
        try {
            String postParameters = "jsonpayload=" + URLEncoder.encode(jsonPayLoad, "UTF-8");
            con.setFixedLengthStreamingMode(postParameters.getBytes().length);
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(postParameters);
            out.close();
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                response = readStream(con.getInputStream());
            } else {
                response = "Error contacting server: " + responseCode;
            }
        } catch (Exception e) {
            response = e.toString();//"Error executing code";
        }
        return response;
    }

    private String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String generatePage(String content) {
        return "<html><body><p>" + content + "</p></body></html>";
    }


    private boolean prepareConnection() {
        try {
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            return true;

        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        return false;
    }
}

