package com.example.white.skindoctor.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.white.skindoctor.R;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> ids;
    private ArrayList<String> names;
    private ArrayList<String> names1;

    public ItemAdapter(Context context, ArrayList<Integer> ids, ArrayList<String> names,ArrayList<String> names1) {
        this.context = context;
        this.ids=ids;
        this.names = names;
        this.names1 = names1;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    public int getCounts(){return names1.size();}

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    public Object getItem1(int position) {
        return names1.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_items, null);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);
        TextView tv = (TextView) convertView.findViewById(R.id.textView);
        TextView tv1 = (TextView) convertView.findViewById(R.id.textView2);
        img.setImageResource(ids.get(position));
        tv.setText(names.get(position));
        tv1.setText(names1.get(position));
        return convertView;
    }
}
