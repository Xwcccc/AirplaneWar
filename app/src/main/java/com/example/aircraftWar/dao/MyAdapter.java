package com.example.aircraftWar.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aircraftWar.R;

import java.util.List;

public class MyAdapter extends ArrayAdapter {

    private LayoutInflater layoutInflater = null;
    private List<Record> records;

    public MyAdapter(Context context, int textViewResourceId, List<Record> objects) {
        super(context, textViewResourceId, objects);
        this.layoutInflater = LayoutInflater.from(context);
        this.records = objects;
    }

    /**
     * 获取adapter里有多少个数据项
     */
    @Override
    public int getCount() {
        return records.size();
    }
    @Override
    public Object getItem(int position) {
        return records.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 创建显示的数据界面
     *
     * Adapter的作用就是ListView界面与数据之间的桥梁，
     * 当列表里的每一项显示到页面时，都会调用Adapter的getView方法返回一个View。
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.listview,null);
            holder = new ViewHolder();
            holder.recordNumber = (TextView)convertView.findViewById(R.id.recordNumber);
            holder.recordName = (TextView)convertView.findViewById(R.id.recordName);
            holder.recordScore = (TextView)convertView.findViewById(R.id.recordScore);
            holder.recordTime = (TextView)convertView.findViewById(R.id.recordTime);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.recordNumber.setText(String.valueOf(position+1));
        holder.recordName.setText(records.get(position).getRecordName());
        holder.recordScore.setText(String.valueOf(records.get(position).getRecordScore()));
        holder.recordTime.setText(records.get(position).getRecordTime());
        return convertView;
    }

    /**
     * 界面上的显示控件
     */
    private static class ViewHolder{
        private TextView recordNumber;
        private TextView recordName;
        private TextView recordScore;
        private TextView recordTime;
    }
}
