package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.MonthCountBean;
import com.msht.mshtLpg.mshtLpgMaster.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonthcountAdapter extends RecyclerView.Adapter<MonthcountAdapter.ViewHolder>{

    private  List<MonthCountBean.ListBean> list;
    private  Activity activity;
    private  LayoutInflater inflate;

    public MonthcountAdapter(List<MonthCountBean.ListBean> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        inflate = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate.inflate(R.layout.item_rcl_month_count,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonthCountBean.ListBean bean = list.get(position);
        holder.tvMonth.setText(bean.getMonth());
        holder.tvIncome.setText(bean.getIncome());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_rcl_month_count_month)
        TextView tvMonth;
        @BindView(R.id.item_rcl_month_count)
        TextView tvIncome;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}