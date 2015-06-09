package edu.washington.apache78.FlashStudy.fragments;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import edu.washington.apache78.FlashStudy.R;

/**
 * Created by apache78 on 6/8/2015.
 */
public class CardAdapter extends BaseAdapter {
    Context mContext;
    String[] Terms = new String[] {"Term1", "Term2", "Term3","Term4","Term5","Term6"};
    public CardAdapter(Context context) {
        mContext = context;
    }
    @Override
    public int getCount() {
        return Terms.length;
    }

    @Override
    public Object getItem(int position) {
        return Terms[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        if(convertView == null){
            tv = new TextView(mContext);
            tv.setLayoutParams(new GridView.LayoutParams(475,475));
        }else{
            tv = (TextView) convertView;
        }
        tv.setBackgroundResource(R.color.main_app_color);
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setText(Terms[position]);
        return tv;
    }
}
