package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.content.Context;
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

public class SimpleRclAdapter extends RecyclerView.Adapter<SimpleRclAdapter.ScanHolder>{
    private final LayoutInflater inflater;
    private final ItemClickListener itemClickListener;
    private  List<String> list ;
    private  Context activity;

    public SimpleRclAdapter(List<String> list, Context activity, ItemClickListener itemClickListener) {
        this.list = list;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        this.itemClickListener = itemClickListener;
    }




    @NonNull
    @Override
    public ScanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScanHolder(inflater.inflate(R.layout.item_rcl_simple,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScanHolder holder, int position) {
            String string = list.get(position);
            holder.tvBottleNumber.setText(string);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.itemView.setBackgroundColor(activity.getResources().getColor(R.color.line_color_lib));
                    itemClickListener.onItemClick(string,position);

                }
            });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ScanHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView tvBottleNumber;
        public ScanHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }
    }

   public interface ItemClickListener{
        void onItemClick(String selectString,int selectedPosition);
   }
}
