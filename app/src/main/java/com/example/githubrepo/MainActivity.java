package com.example.githubrepo;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String [] repoName;
    String [] repoDesri;
    String [] stars;
    String [] username;
    String [] avatar;
    String repoNameS;
    String repoDesriS;
    String starsS;
    String usernameS;
    String avatarS;
    int counter=0;
    ArrayList<String> avatarAR =new ArrayList<String>();

    public static String thisData;
    ListView lView;
    ListAdapter lAdapter;
    public static TextView dataTV;
    public void showRepos(){
        fetchData getData = new fetchData();
        getData.execute();
        //Log.d("title is",titleList[1]);
       // lAdapter = new ListAdapter(MainActivity.this, titleList, idList, imageID);
      //  lView.setAdapter(lAdapter);
    }
    public static Toast toast;
    String  data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lView = (ListView) findViewById(R.id.androidList);
        dataTV = findViewById(R.id.data);
        fetchData process = new fetchData();
        process.execute();
        data = JsonTOString("data.json");


        // Toast.makeText(this, "data is :" + thisData, Toast.LENGTH_SHORT).show();
       /* showRepos();
        fetchData getData = new fetchData();
        getData.execute();
        lAdapter = new ListAdapter(this, titleList, idList, imageID);
        lView.setAdapter(lAdapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(MainActivity.this, version[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();

            }
        }); */

    }
    //this function can take a json local link and give this
    // file as a String do what you wan't with them ^_^
    public String JsonTOString(String fileLink){
        String json = "";
        String allData = "";
        try {
            InputStream is = getAssets().open(fileLink);
            //int size = is.available();
            //byte[] buffer = new byte[size];
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            //wen can't start storing lines in the json
            // String if the line is null -_-
            while(line != null){
                line = bufferedReader.readLine();
                json += line + "\n";
            }
            is.read();
            is.close();
           // json = new String(buffer,"UTF-8");

//in this line we send the Json file as one string
// so we need more process to divide the Items
            return json;

        } catch (IOException e) {
            e.printStackTrace();
        }
        // in case that the process failed so return NO DATA
        return "no Data";
    }
    public void JsonDevider(String json)  {
        //the reason wy i create two functions and not one that i wan't to test if we can do
        //the same process that we do in a Json list , for a list
        // inside jason list so crazy i know

        String allData ="";
        try {
            JSONObject reader = new JSONObject(json);
            JSONArray items  = reader.getJSONArray("items");
            JSONObject jo;
            String [] ownerInfo = {};
            JSONObject ownerObject;

            for ( int i = 0; i< items.length(); i++) {
                jo = items.getJSONObject(i);
                repoNameS += jo.getString("stargazers_count") + "\n";
                repoDesriS += jo.getString("description") + "\n";
                starsS += jo.getString("stargazers_count") + "\n";
                //                repoNameS += jo.getString("stargazers_count") + "\n";
                //               // repoDesri [i] = jo.getString("description");
               // stars [i] = jo.getString("stargazers_count");
                ownerObject = jo.getJSONObject("owner");
                avatarS += ownerObject.getString("avatar_url") + "\n";
                usernameS += ownerObject.getString("login") + "\n";
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public  String JsonNested(String data) throws JSONException {
        String nameId = "";
        JSONObject mainObj = new JSONObject(data);
        try {
            JSONArray js = new JSONArray(data);
            JSONObject jo;
            for ( int i = 0; i< js.length(); i++) {
                jo = js.getJSONObject(i);
                nameId += jo.getString("id") + "\n";
            }
            return nameId;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "no Data -_-";
    }
    public void showData(View view) throws JSONException {
        //String data = JsonTOString("https://api.github.com/search/repositories?q=created:%3E2017-10-22&sort=stars&order=desc");

        //getData.execute();
       // Toast.makeText(this, "this is the data:"+data, Toast.LENGTH_SHORT).show();
        //
        //String [] names = dividedData.split("\n");
        //dataTV.setText(dividedData);
       // String dividedData =
         //data = JsonDevider(data,"total_count");
    }
    public void showData2(View view) throws JSONException {
        //String data = JsonTOString("https://api.github.com/search/repositories?q=created:%3E2017-10-22&sort=stars&order=desc");
        //
        //String dividedData = JsonDevider(thisData,"total_count");
        //getData.execute();
        // Toast.makeText(this, "this is the data:"+data, Toast.LENGTH_SHORT).show();
        //String [] names = dividedData.split("\n");
        //dataTV.setText(dividedData);
        JsonDevider(data);
        avatar = avatarS.split("\n");
        username = usernameS.split("\n");
        repoName = repoNameS.split("\n");
        repoDesri = repoDesriS.split("\n");
        stars = starsS.split("\n");
        lAdapter = new ListAdapter(this, titleList, idList, imageID);
        lView.setAdapter(lAdapter);
    }
}
