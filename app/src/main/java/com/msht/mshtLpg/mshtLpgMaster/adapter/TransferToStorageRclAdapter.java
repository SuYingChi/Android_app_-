package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.TransferStorageListBean;
import com.msht.mshtLpg.mshtLpgMaster.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferToStorageRclAdapter extends RecyclerView.Adapter {


    private LayoutInflater inflater;
    private int transformType;
    private Activity activity;
    private onTransferToStorageRclAdapterClickListener listener;
    private List<TransferStorageListBean.DataBean.ListBean> list;

    public TransferToStorageRclAdapter(Activity activity, onTransferToStorageRclAdapterClickListener listener, List<TransferStorageListBean.DataBean.ListBean> list, int transformType) {
        this.inflater = LayoutInflater.from(activity);
        this.listener = listener;
        this.list = list;
        this.transformType = transformType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:
            case 2:
                View ss = inflater.inflate(R.layout.item_rcl_transfer_to_storage, parent, false);
                return new OrdersHolder(ss);
            case 1:
            case 3:
                View sss = inflater.inflate(R.layout.item_rcl_transfer_to_storage_finish, parent, false);
                return new OrdersFinishHolder(sss);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TransferStorageListBean.DataBean.ListBean bean = list.get(position);
        if (holder instanceof OrdersHolder) {
            int five = bean.getFiveCount();
            int fifteen = bean.getFifteenCount();
            int fifty = bean.getFifthCount();
            int orderId = bean.getId();
            ((OrdersHolder) holder).etFive.setText(five + "");
            ((OrdersHolder) holder).etFive.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s.toString())) {
                        ((OrdersHolder) holder).etFive.setText(0 + "");
                    }
                    String total = Integer.valueOf(((OrdersHolder) holder).etFive.getText().toString()) + Integer.valueOf(((OrdersHolder) holder).etFifteen.getText().toString()) + Integer.valueOf(((OrdersHolder) holder).etFifty.getText().toString()) + "";
                    ((OrdersHolder) holder).tvTotal.setText(total);
                }
            });
            ((OrdersHolder) holder).etFifteen.setText(fifteen + "");
            ((OrdersHolder) holder).etFifteen.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s.toString())) {
                        ((OrdersHolder) holder).etFifteen.setText(0 + "");
                    }
                    String total = Integer.valueOf(((OrdersHolder) holder).etFive.getText().toString()) + Integer.valueOf(((OrdersHolder) holder).etFifteen.getText().toString()) + Integer.valueOf(((OrdersHolder) holder).etFifty.getText().toString()) + "";
                    ((OrdersHolder) holder).tvTotal.setText(total);

                }
            });
            ((OrdersHolder) holder).etFifty.setText(fifty + "");
            ((OrdersHolder) holder).etFifty.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s.toString())) {
                        ((OrdersHolder) holder).etFifty.setText(0 + "");
                    }
                    String total = Integer.valueOf(((OrdersHolder) holder).etFive.getText().toString()) + Integer.valueOf(((OrdersHolder) holder).etFifteen.getText().toString()) + Integer.valueOf(((OrdersHolder) holder).etFifty.getText().toString()) + "";
                    ((OrdersHolder) holder).tvTotal.setText(total);

                }
            });
            ((OrdersHolder) holder).tvOrder.setText(orderId + "");
            ((OrdersHolder) holder).tvTotal.setText(five + fifteen + fifty + "");
            ((OrdersHolder) holder).tvModify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClckModifyBtn(position, ((OrdersHolder) holder).etFive.getText().toString(), ((OrdersHolder) holder).etFifteen.getText().toString(), ((OrdersHolder) holder).etFifty.getText().toString(), orderId + "");
                }
            });
            ((OrdersHolder) holder).tvScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickScanCodeBtn(position, orderId + "");
                }
            });
        } else if (holder instanceof OrdersFinishHolder) {
            int five = bean.getFiveCount();
            int fifteen = bean.getFifteenCount();
            int fifty = bean.getFifthCount();
            int orderId = bean.getId();
            ((OrdersFinishHolder) holder).tvFive.setText(five + "");
            ((OrdersFinishHolder) holder).tvFifteen.setText(fifteen + "");
            ((OrdersFinishHolder) holder).tvFifty.setText(fifty + "");
            ((OrdersFinishHolder) holder).tvOrder.setText(orderId + "");
            ((OrdersFinishHolder) holder).tvTotal.setText(five + fifteen + fifty + "");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        int orderType = list.get(position).getState();
        //"transformType": 气站到网点 1 重瓶入库 0：空瓶出库
        switch (transformType) {
            case 0:
                switch (orderType) {
                    case 0:
                        // 出库待验瓶
                        orderType = 0;
                        break;
                    case 1:
                        // 出库已完成
                        orderType = 1;
                        break;
                    default:
                        break;
                }
                break;
            case 1:
                switch (orderType) {
                    case 0:
                        // 入库待验瓶
                        orderType = 2;
                        break;
                    case 1:
                        // 入库已完成
                        orderType = 3;
                        break;
                    default:
                        break;

                }
                break;
            default:
                break;
        }
        return orderType;
    }

    class OrdersHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_rcl_transfer_to_storage_orders_number)
        TextView tvOrder;
        @BindView(R.id.item_rcl_transfer_to_storage_orders_five_kg_number)
        EditText etFive;
        @BindView(R.id.item_rcl_transfer_to_storage_orders_fifteen_kg_number)
        EditText etFifteen;
        @BindView(R.id.item_rcl_transfer_to_storage_orders_fifty_kg_number)
        EditText etFifty;
        @BindView(R.id.item_rcl_transfer_to_storage_orders_total_number)
        TextView tvTotal;
        @BindView(R.id.item_rcl_transfer_to_storage_orders_modify)
        TextView tvModify;
        @BindView(R.id.tv_scan)
        TextView tvScan;

        public OrdersHolder(View inflate/*,int type*/) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

    }

    class OrdersFinishHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_rcl_transfer_to_storage_orders_number)
        TextView tvOrder;
        @BindView(R.id.item_rcl_transfer_to_storage_orders_five_kg_number)
        TextView tvFive;
        @BindView(R.id.item_rcl_transfer_to_storage_orders_fifteen_kg_number)
        TextView tvFifteen;
        @BindView(R.id.item_rcl_transfer_to_storage_orders_fifty_kg_number)
        TextView tvFifty;
        @BindView(R.id.item_rcl_transfer_to_storage_orders_total_number)
        TextView tvTotal;

        public OrdersFinishHolder(View inflate/*,int type*/) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

    }

    public interface onTransferToStorageRclAdapterClickListener {
        void onClickScanCodeBtn(int itemPosition, String orderId);

        void onClckModifyBtn(final int position, final String five, final String fifteen, final String itemPosition, final String orderId);
    }
}
