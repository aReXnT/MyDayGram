package com.example.arexnt.mydaygram;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import static com.example.arexnt.mydaygram.R.id.week;

/**
 * Created by arexnt on 2016/9/28.
 */

public class dataAdapter extends BaseAdapter {
    private Context mContext;
    private Cursor mCursor;

    public dataAdapter(Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
    }


    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return mCursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.tablelist, null);
        TextView txt_week = (TextView) convertView.findViewById(week);
        TextView txt_day = (TextView) convertView.findViewById(R.id.day);
        TextView txt_content = (TextView) convertView.findViewById(R.id.content);
//        txt_week.setText(mDatas.get(position).getWeek());
//        txt_day.setText(mDatas.get(position).getDay());
//        txt_content.setText(mDatas.get(position).getContent());
        mCursor.moveToPosition(position);
        String content = mCursor.getString(mCursor.getColumnIndex("content"));
        String year = mCursor.getString(mCursor.getColumnIndex("year"));
        String month = mCursor.getString(mCursor.getColumnIndex("month"));
        String week = mCursor.getString(mCursor.getColumnIndex("week"));
        String day = mCursor.getString(mCursor.getColumnIndex("day"));
        txt_content.setText(content);
        txt_week.setText(week);
        txt_day.setText(day);
        Log.i("showDBTime",year + month + week + day);

        return convertView;
    }
}
