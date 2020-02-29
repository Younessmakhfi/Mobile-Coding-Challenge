/*
package com.example.githubrepo;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

public class fetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    ArrayList<String> allrepos = new ArrayList<String>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected Void doInBackground(Void... voids) {
        LocalDate now = LocalDate.now();
        LocalDate last30Days = now.minusDays( 30 );
        data = "";
        try {
            if(MainActivity.repoCount == 0){
                URL url = new URL("https://api.github.com/search/repositories?q=created:%3E"+ last30Days + "&sort=stars&order=desc");
                Log.d("repoNumber",String.valueOf(MainActivity.repoCount));
                MainActivity.repoCount ++;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                bufferedReader.close();
            } else {
                URL url = new URL("https://api.github.com/search/repositories?q=created:%3E"+ last30Days + "&sort=stars&order=desc&page=" + MainActivity.repoCount);
                Log.d("repoNumber",String.valueOf(MainActivity.repoCount));
                MainActivity.repoCount++;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                bufferedReader.close();
            }

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
        //MainActivity.data = this.data;
        MainActivity.data = this.data;
    }
}
*/
