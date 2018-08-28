package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.MyBottleListBean;
import com.msht.mshtLpg.mshtLpgMaster.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBottleRclAdapter extends RecyclerView.Adapter<MyBottleRclAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<MyBottleListBean.DataBean.ListBean> list;
    private Activity activity;

    public MyBottleRclAdapter(List<MyBottleListBean.DataBean.ListBean> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public MyBottleRclAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_rcl_deliver_steel_bottle, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyBottleRclAdapter.ViewHolder holder, int position) {
        MyBottleListBean.DataBean.ListBean bean = list.get(position);
        holder.tvBottleModel.setText(bean.getBottleWeight() + "");
        holder.tvBottleNumber.setText(bean.getBottleCode());
        holder.tvBottleStatus.setText(bean.getIsHeavy() == 0 ? "重瓶" : "空瓶");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bottle_number)
        TextView tvBottleNumber;
        @BindView(R.id.bottle_model)
        TextView tvBottleModel;
        @BindView(R.id.bottle_status)
        TextView tvBottleStatus;

        public ViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }


    }
}
