package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersBean;

import java.util.List;

public class RclHomeAdapter extends RecyclerView.Adapter{
    public RclHomeAdapter(List<OrdersBean> p0, FragmentActivity activity) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
