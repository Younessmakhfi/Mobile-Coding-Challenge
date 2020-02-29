package com.example.githubrepo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> allrepos = new ArrayList<String>();
    String[] repoName;
    String[] repoDesri;
    String[] stars;
    String[] username;
    String[] avatar;
    String repoNameS;
    String repoDesriS;
    String starsS;
    String usernameS;
    String avatarS;
    ListView lView;
    ListAdapter lAdapter;
    RelativeLayout loaderView;
    public static String data = null;
    int repoCount = 0;
    public static List<JSONObject> jsonobjectArray = new ArrayList<>();
    List<String> urls = new ArrayList<>();
   // fetchData process = new fetchData();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lView = (ListView) findViewById(R.id.androidList);
        View footerView = ((LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer, null, false);
        lView.addFooterView(footerView);
        loaderView = findViewById(R.id.loaderView);
        storeUrls();
        MyAsyncTask myAsyncTask = new MyAsyncTask(MainActivity.this, urls);
        myAsyncTask.execute();
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Circle();
        progressBar.setIndeterminateDrawable(doubleBounce);
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
            showData(jsonobjectArray.get(repoCount));
            loaderView.setVisibility(View.GONE);

            }
        }.start();
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getBaseContext(), "My Toast Message", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public static String getRoughNumber(long value) {
        if (value <= 999) {
            return String.valueOf(value);
        }

        final String[] units = new String[]{"", "K", "M", "B", "P"};
        int digitGroups = (int) (Math.log10(value) / Math.log10(1000));
        return new DecimalFormat("#,##0.#").format(value / Math.pow(1000, digitGroups)) + "" + units[digitGroups];

    }
    public void JsonDevider(JSONObject json) {
        try {
            //JSONObject reader = new JSONObject(json);
            JSONArray items  = json.getJSONArray("items");
            JSONObject jo;
            JSONObject ownerObject;
            repoNameS = "";
            starsS = "";
            avatarS = "";
            repoDesriS = "";
            usernameS = "";
            for (int i = 0; i < items.length(); i++) {
                jo = items.getJSONObject(i);
                repoNameS += jo.getString("name")+"\n";
                repoDesriS += jo.getString("description") + "\n";
                starsS += jo.getString("stargazers_count") + "\n";
                ownerObject = jo.getJSONObject("owner");
                usernameS += ownerObject.getString("login")+"\n";
                avatarS += ownerObject.getString("avatar_url") + "\n";
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showData(JSONObject jsonObject) {
        //JSONObject jsonObject = MyAsyncTask.jsonURls.get(0);
        //JsonDevider(jsonobjectArray.get(0));
        JsonDevider(jsonObject);
        repoName = repoNameS.split("\n");
        repoDesri = repoDesriS.split("\n");
        stars = starsS.split("\n");
        username = usernameS.split("\n");
        avatar = avatarS.split("\n");
        lAdapter = new ListAdapter(this, repoName, repoDesri, stars, username, avatar);
        lView.setAdapter(lAdapter);
    }

    public void showMore(View view) {
        repoCount++;
        loaderView.setVisibility(View.VISIBLE);
        lView.setVisibility(View.GONE);
        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Toast.makeText(MainActivity.this, "array size is" + jsonobjectArray.size(), Toast.LENGTH_SHORT).show();
                JsonDevider(jsonobjectArray.get(repoCount));
                repoName = repoNameS.split("\n");
                repoDesri = repoDesriS.split("\n");
                stars = starsS.split("\n");
                username = usernameS.split("\n");
                avatar = avatarS.split("\n");
                lAdapter.repoName = repoName;
                lAdapter.repoDesri = repoDesri;
                lAdapter.stars = stars;
                lAdapter.username = username;
                lAdapter.avatar = avatar;
                lAdapter.notifyDataSetChanged();
                lView.setAdapter(lAdapter);
                loaderView.setVisibility(View.GONE);
                lView.setVisibility(View.VISIBLE);
            }

        }.start();

/*        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {

                cancel();
            }

            public void onFinish() {


            }
        }.start();*/

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void storeUrls(){
        //new MyAsyncTask(MainActivity.this, urls).execute();
        LocalDate now = LocalDate.now();
        LocalDate last30Days = now.minusDays( 30 );
        for (int i = 1;i < 34; i++){
            urls.add("https://api.github.com/search/repositories?q=created:%3E"+ last30Days + "&sort=stars&order=desc&page=" + i);
        }



/*        for(int i = 1; i <= 2; i++){
            urls.add("https://api.github.com/search/repositories?q=created:%3E"+ last30Days + "&sort=stars&order=desc&page="+ i);
        }*/

    }
}
