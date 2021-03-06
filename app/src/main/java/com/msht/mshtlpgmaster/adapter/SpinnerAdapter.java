package com.msht.mshtlpgmaster.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.msht.mshtlpgmaster.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private Activity activity;
    private List<String> datas;

    public SpinnerAdapter(Activity activity) {
        datas = new ArrayList<>();
        this.activity = activity;
    }

    public void setData(List<String> list) {
        datas = list;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder;
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.spinner_item, null);
            holder = new Holder();
            holder.mTextView = (TextView) view.findViewById(R.id.spinner_text);
            holder.mTextView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);   //设置居中
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.mTextView.setText(datas.get(position));
        return view;
    }

    public class Holder {
        private TextView mTextView;
    }
}
