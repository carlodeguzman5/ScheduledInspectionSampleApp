package com.example.saperiumdev.samplescheduledinspection.inspection.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saperiumdev.samplescheduledinspection.inspection.inspection.InspectionContract;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by saperiumdev on 7/27/17.
 */

public class HttpHelper extends AsyncTask<String, String, String>{
    public AsyncResponse delegate = null;

    public HttpHelper(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = "";
        HttpURLConnection urlConnection = null;
        URL url = null;
        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod(params[1]);

            if(!params[1].equals("GET")) {
                urlConnection.setRequestProperty("Content-Type", "application/json");
                byte[] outputInBytes = params[2].getBytes("UTF-8");
                OutputStream outputStream = urlConnection.getOutputStream();
                outputStream.write(outputInBytes);
                outputStream.close();
            }

            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();
            while(data != -1) {
                char current = (char) data;
                data = inputStreamReader.read();
                response += current;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.onExecute(result);
    }

//    public HttpHelper(Context context, String url){
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String r) {
//
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            }
//        );
//        queue.add(stringRequest);
//    }
}

