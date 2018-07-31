package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.LocationBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersListBeanV2;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.SelectAddressActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Demo class
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author hong
 * @date 2018/7/31  
 */
public class PoiAdapter extends RecyclerView.Adapter{

    private LayoutInflater inflater;
    private List<LocationBean> list;
    private Context mContext;
    public PoiAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_app_list_poi, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            String title= list.get(position).getTitle();
            String addressDescribe=list.get(position).getTitle();
            ((ViewHolder) holder).tvTitle.setText(title);
            ((ViewHolder) holder).addressDesc.setText(addressDescribe);
        }
    }
    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else {
            return 0;
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.address)
        TextView tvTitle;
        @BindView(R.id.addressDesc)
        TextView addressDesc;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public void setData(List<LocationBean> datas){
        this.list = datas;
    }
}
