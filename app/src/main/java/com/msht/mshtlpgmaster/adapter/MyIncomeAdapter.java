package com.msht.mshtlpgmaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msht.mshtlpgmaster.Bean.MyIncomeBean;
import com.msht.mshtlpgmaster.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyIncomeAdapter extends RecyclerView.Adapter<MyIncomeAdapter.ViewHolder> {

    private LayoutInflater inflate;
    private Activity activity;
    private List<MyIncomeBean.DataBean.ListBean> list;

    public MyIncomeAdapter(List<MyIncomeBean.DataBean.ListBean> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        inflate = LayoutInflater.from(activity);

    }

    @NonNull
    @Override
    public MyIncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate.inflate(R.layout.item_rcl_my_income, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyIncomeAdapter.ViewHolder holder, int position) {
        MyIncomeBean.DataBean.ListBean bean = list.get(position);
        holder.tvOrderNum.setText(bean.getOrderId() + "");
        holder.tvOrderTime.setText(bean.getFinishTime());
        holder.tvIncome.setText(bean.getDeliveryFee() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_rcl_my_income_orders_number)
        TextView tvOrderNum;
        @BindView(R.id.tv_item_rcl_my_income_orders_time)
        TextView tvOrderTime;
        @BindView(R.id.tv_item_rcl_my_income_orders_income)
        TextView tvIncome;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
