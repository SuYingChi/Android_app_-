package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.VerifyBottleBean;
import com.msht.mshtLpg.mshtLpgMaster.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanBottleQRCodeRclAdapter extends RecyclerView.Adapter<ScanBottleQRCodeRclAdapter.ScanHolder>{
    private final LayoutInflater inflater;
    private  List<VerifyBottleBean> list ;
    private  Activity activity;

    public ScanBottleQRCodeRclAdapter( List<VerifyBottleBean> list,Activity activity) {
        this.list = list;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ScanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScanHolder(inflater.inflate(R.layout.item_rcl_deliver_steel_bottle,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScanHolder holder, int position) {
        VerifyBottleBean bean = list.get(position);
            holder.tvBottleModel.setText(bean.getData().getBottleWeight()+"");
            holder.tvBottleNumber.setText(bean.getData().getBottleCode());
            holder.tvBottleStatus.setText(bean.getData().getIsHeavy()==0?"重瓶":"空瓶");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reduceItem(position);
                }
            });
    }

    private void reduceItem(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.d("suyingchi", "getItemCount: "+list.size());
        return list.size();
    }

    class ScanHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bottle_number)
        TextView tvBottleNumber;
        @BindView(R.id.bottle_model)
        TextView tvBottleModel;
        @BindView(R.id.bottle_status)
        TextView tvBottleStatus;
        public ScanHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }


    }


}
