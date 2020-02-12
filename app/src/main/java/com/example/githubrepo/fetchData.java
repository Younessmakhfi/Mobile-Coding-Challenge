package com.example.githubrepo;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String [] idList = {"1","2","3"};
    String [] titleList = {"1","2","3"};
    String[] imageID = {"https://avatars0.githubusercontent.com/u/905434?v=4",
            "https://avatars0.githubusercontent.com/u/905434?v=4",
            "https://avatars0.githubusercontent.com/u/905434?v=4",
    };
    ListAdapter lAdapter;
    private Context context;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://my-json-server.typicode.com/typicode/demo/posts");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                    line = bufferedReader.readLine();
                    data += line;
            }
            JSONArray arr = new JSONArray(data);
          /*  for(int i = 0; i < arr.length(); i++){
                idList[i] = arr.getJSONObject(i).getString("id");
                titleList[i] = arr.getJSONObject(i).getString("title");
            } */
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
         lAdapter = new ListAdapter(context, titleList, idList, imageID);
        MainActivity.lView.setAdapter(lAdapter);

    }
}

