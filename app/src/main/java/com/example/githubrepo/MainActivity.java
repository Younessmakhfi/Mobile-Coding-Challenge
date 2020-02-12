package com.example.githubrepo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //int[] imagesID = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    String[] imageID = {"https://avatars0.githubusercontent.com/u/905434?v=4",
            "https://avatars0.githubusercontent.com/u/905434?v=4",
            "https://avatars0.githubusercontent.com/u/905434?v=4",
            };
    public static String[] idList;
    public static String[] titleList;
    public static ListView lView;
    String[] version = {"Android Alpha", "Android Beta", "Android Cupcake"};

    String[] versionNumber = {"1.0", "1.1", "1.5"};


    ListAdapter lAdapter;
    public void showRepos(){
        fetchData getData = new fetchData();
        getData.execute();
        //Log.d("title is",titleList[1]);
       // lAdapter = new ListAdapter(MainActivity.this, titleList, idList, imageID);
      //  lView.setAdapter(lAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lView = (ListView) findViewById(R.id.androidList);
        showRepos();
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, version[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();

            }
        });

    }
}