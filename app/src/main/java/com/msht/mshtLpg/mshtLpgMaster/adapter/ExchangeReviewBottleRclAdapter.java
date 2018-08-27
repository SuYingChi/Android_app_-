package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ExchangeReviewBean;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.ExchangeReviewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeReviewBottleRclAdapter extends RecyclerView.Adapter {
    private  List<ExchangeReviewBean.DataBean> list;
    private  LayoutInflater inflater;
    private  Activity activity;

    public ExchangeReviewBottleRclAdapter(List<ExchangeReviewBean.DataBean> dataList, Activity activity) {
        this.list = dataList;
        this.inflater = LayoutInflater.from(activity);
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExhcnageHolder(inflater.inflate(R.layout.item_rcl_exchange_steel_bottle_header, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       if(holder instanceof ExhcnageHolder){
           ((ExhcnageHolder) holder).discount.setText(list.get(position).getReplacePrice()+"");
            ((ExhcnageHolder) holder).level.setText(list.get(position).getCorrosionType());
            ((ExhcnageHolder) holder).num.setText(list.get(position).getBottleCount()+"");
            ((ExhcnageHolder) holder).weight.setText(list.get(position).getBottleWeight()+"");
            ((ExhcnageHolder) holder).year.setText(list.get(position).getYears()+"");
       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ExhcnageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.weight)
        TextView weight;
        @BindView(R.id.year)
        TextView year;
        @BindView(R.id.level)
        TextView level;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.discount)
        TextView discount;

        public ExhcnageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
