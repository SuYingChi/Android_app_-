package com.msht.mshtlpgmaster.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msht.mshtlpgmaster.Bean.LocationBean;
import com.msht.mshtlpgmaster.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Demo class
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author hong
 * @date 2018/8/1
 */
public class PoiSearchAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<LocationBean> list;
    private ArrayList<HashMap<String, String>> mList = new ArrayList<HashMap<String, String>>();
    private Context mContext;
    private OnItemSelectClickListener onItemClickListener;

    public interface OnItemSelectClickListener {
        /**
         * 触发Item
         *
         * @param v
         * @param position
         */
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemSelectClickListener listener) {
        this.onItemClickListener = listener;
    }

    public PoiSearchAdapter(Context context, ArrayList<HashMap<String, String>> list) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_app_list_poi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            String title = mList.get(position).get("addressName");
            String addressDescribe = mList.get(position).get("addressDescribe");
            ((ViewHolder) holder).tvTitle.setText(title);
            ((ViewHolder) holder).addressDesc.setText(addressDescribe);
            ((ViewHolder) holder).layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.address)
        TextView tvTitle;
        @BindView(R.id.addressDesc)
        TextView addressDesc;
        @BindView(R.id.id_layout_item)
        View layoutItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
