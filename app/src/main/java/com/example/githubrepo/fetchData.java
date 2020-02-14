package com.example.githubrepo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class fetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String [] idList = {"1","2","3"};
    String [] titleList = {"1","2","3"};
    String[] imageID = {"https://avatars0.githubusercontent.com/u/905434?v=4",
            "https://avatars0.githubusercontent.com/u/905434?v=4",
            "https://avatars0.githubusercontent.com/u/905434?v=4",
    };
    String allData = "";
    private Context context;
    ArrayList<String> items = new ArrayList<String>();
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            try {
                JSONArray js = new JSONArray(data);
                JSONObject jo;
                for ( int i = 0; i< js.length(); i++) {
                    jo = js.getJSONObject(i);
                    allData += jo.getString("id") + "\n";
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
//            JSONArray arr = new JSONArray(data);
          /*  for(int i = 0; i < arr.length(); i++){
                idList[i] = arr.getJSONObject(i).getString("id");
                titleList[i] = arr.getJSONObject(i).getString("title");
            } */

        }
     catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

        return null;
}

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //MainActivity.dataTV.setText(data);
        MainActivity.thisData = this.data;
        Log.d("i'ts done","data come");
       // Toast.makeText(context, "this is : " + data, Toast.LENGTH_SHORT).show();

    }
}

