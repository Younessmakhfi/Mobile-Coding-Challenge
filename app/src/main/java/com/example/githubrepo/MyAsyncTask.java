package com.example.githubrepo;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class MyAsyncTask extends AsyncTask<Void, Void, List<JSONObject>> {
    private Activity activity;
    private List<String> urls;
    private int i = 0;
    public static List<JSONObject> jsonURls = new ArrayList<>();
    public MyAsyncTask(Activity activity, List<String> urls) {
        this.urls = urls;
        this.activity = activity;
    }
    @Override
    protected List<JSONObject> doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try{
            for (String url : urls) {
                URL link = new URL(url);
                connection = (HttpURLConnection) link.openConnection();
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("GET");
                connection.connect();

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                    sb.append(line);

                reader.close();
                JSONObject jsonResult = new JSONObject(sb.toString());
                //jsonURls.add(jsonResult);
                MainActivity.jsonobjectArray.add(jsonResult);
                if(i < 10){

                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(activity, "URL error", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
          //  Toast.makeText(activity, "Connection error", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(activity, "JSON Parsing error", Toast.LENGTH_SHORT).show();
        }
        return jsonURls;
    }

    @Override
    protected void onPostExecute(List<JSONObject> jsonObjects) {
        super.onPostExecute(jsonObjects);
        // Process your JSONs
    }
}