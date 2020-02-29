package com.example.githubrepo;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.squareup.picasso.Picasso;

public class ListAdapter extends BaseAdapter {

    Context context;
    public static String [] repoName;
    public static String [] repoDesri;
    public static String [] stars;
    public static String [] username;
    public static String [] avatar;

    public ListAdapter(Context context, String [] repoName, String [] repoDesri, String [] stars, String [] username, String [] avatar){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.repoName = repoName;
        this.repoDesri = repoDesri;
        this.stars = stars;
        this.username = username;
        this.avatar = avatar;
    }

    @Override
    public int getCount() {
        return repoName.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.single_list_item, parent, false);
            viewHolder.repoName = (TextView) convertView.findViewById(R.id.repoNameTV);
            viewHolder.repoDescri = (TextView) convertView.findViewById(R.id.repoDesTV);
            viewHolder.stars = (TextView) convertView.findViewById(R.id.starsTV);
            viewHolder.username = (TextView) convertView.findViewById(R.id.usernameTV);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatarIM);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.repoName.setText(repoName[position]);
        viewHolder.repoDescri.setText(repoDesri[position]);
        String starsS = MainActivity.getRoughNumber(Long.parseLong(stars[position]));
        viewHolder.stars.setText(starsS);
        viewHolder.username.setText(username[position]);
        Picasso.get().load(avatar[position]).into(viewHolder.avatar);

        return convertView;
    }

    private static class ViewHolder {

        TextView repoName;
        TextView repoDescri;
        TextView stars;
        TextView username;
        ImageView avatar;

    }

}