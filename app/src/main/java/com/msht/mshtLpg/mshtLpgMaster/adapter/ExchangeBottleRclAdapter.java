package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ExchangeRclBean;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExchangeBottleRclAdapter extends RecyclerView.Adapter {

    private int remainFive;
    private int remainFifteen;
    private int remainFifty;
    private OnExchangeRclClicklistener onExchangeRclClicklistener;
    private Activity activity;
    private LayoutInflater inflater;
    private List<ExchangeRclBean> list;

    public ExchangeBottleRclAdapter(List<ExchangeRclBean> list, Activity activity, OnExchangeRclClicklistener onExchangeRclClicklistener, int remainFive, int remainFifteen, int remainFifty) {
        this.list = list;
        this.inflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.onExchangeRclClicklistener = onExchangeRclClicklistener;
        this.remainFive = remainFive;
        this.remainFifteen = remainFifteen;
        this.remainFifty = remainFifty;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_rcl_exchange_steel_bottle, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        } else if (viewType == 100) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_exchange_bottle_rcl_foot, parent, false);
            ViewHolderFoot vh = new ViewHolderFoot(v);
            return vh;
        } else {
            return null;
        }
    }

    //24种折价，回调到actvity去请求折价接口获取折价再计算
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            int index = list.get(position).getSelectBottleModeIndex();
            int index2 = list.get(position).getSelectBottleYearsIndex();
            ((ViewHolder) holder).mModelSpinnerAdapter.setData(list.get(position).getmBottleModelList());
            ((ViewHolder) holder).mModelSpinnerAdapter.notifyDataSetChanged();
            ((ViewHolder) holder).mYearSpinnerAdapter.setData(list.get(position).getmBottleYearsList());
            ((ViewHolder) holder).mYearSpinnerAdapter.notifyDataSetChanged();
            ((ViewHolder) holder).mModelSpinner.setSelection(index);
            ((ViewHolder) holder).mYearsSpinner.setSelection(index2);
            ((ViewHolder) holder).tvSteelNum.setText(list.get(position).getBottleNum() + "");
            ((ViewHolder) holder).tvAccount.setText(list.get(position).getDiscount()+ "");
            ((ViewHolder) holder).tvReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectBottleModeIndex = list.get(position).getSelectBottleModeIndex();
                    int selectBottleModeYear = list.get(position).getSelectBottleYearsIndex();
                    if (list.get(position).getBottleNum() > 0) {
                        list.get(position).setBottleNum(list.get(position).getBottleNum() - 1);
                        ((ViewHolder) holder).tvSteelNum.setText(list.get(position).getBottleNum() + "");
                        onExchangeRclClicklistener.onBottleNumChange(list.get(position).getBottleNum(), ((ViewHolder) holder).tvAccount,selectBottleModeIndex , selectBottleModeYear);
                    }
                }
            });
            ((ViewHolder) holder).tvPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectBottleModeIndex = list.get(position).getSelectBottleModeIndex();
                    int selectBottleModeYear = list.get(position).getSelectBottleYearsIndex();
                    int bottleNum = list.get(position).getBottleNum();

                    if(selectBottleModeIndex == 0 && bottleNum < remainFive){
                        list.get(position).setBottleNum(list.get(position).getBottleNum() + 1);
                        ((ViewHolder) holder).tvSteelNum.setText(list.get(position).getBottleNum() + "");
                        onExchangeRclClicklistener.onBottleNumChange(list.get(position).getBottleNum(), ((ViewHolder) holder).tvAccount,selectBottleModeIndex ,selectBottleModeYear );
                    }else if(selectBottleModeIndex == 1 && bottleNum < remainFifteen){
                        list.get(position).setBottleNum(list.get(position).getBottleNum() + 1);
                        ((ViewHolder) holder).tvSteelNum.setText(list.get(position).getBottleNum() + "");
                        onExchangeRclClicklistener.onBottleNumChange(list.get(position).getBottleNum(), ((ViewHolder) holder).tvAccount,selectBottleModeIndex ,selectBottleModeYear );
                    }else if(selectBottleModeIndex == 2 && bottleNum < remainFifty){
                        list.get(position).setBottleNum(list.get(position).getBottleNum() + 1);
                        ((ViewHolder) holder).tvSteelNum.setText(list.get(position).getBottleNum() + "");
                        onExchangeRclClicklistener.onBottleNumChange(list.get(position).getBottleNum(), ((ViewHolder) holder).tvAccount,selectBottleModeIndex ,selectBottleModeYear );
                    }
                }
            });
            ((ViewHolder) holder).mModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    list.get(position).setmSelectBottleModeIndex(p);
                    onExchangeRclClicklistener.onSpinnerChange(p,list.get(position).getSelectBottleYearsIndex(),list.get(position).getBottleNum(),((ViewHolder) holder).tvAccount);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ((ViewHolder) holder).mYearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    list.get(position).setmSelectBottleYearsIndex(p);
                    onExchangeRclClicklistener.onSpinnerChange(list.get(position).getSelectBottleModeIndex(),p,list.get(position).getBottleNum(),((ViewHolder) holder).tvAccount);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //日后补上dialog
                    PopUtil.toastInBottom("移除该条空瓶置换");
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        } else if (holder instanceof ViewHolderFoot) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExchangeRclBean data = new ExchangeRclBean();
                    List<String> modelList = new ArrayList<>();
                    modelList.add("5kg");
                    modelList.add("15kg");
                    modelList.add("50kg");
                    List<String> yearList = new ArrayList<>();
                    yearList.add("1");
                    yearList.add("2");
                    yearList.add("3");
                    yearList.add("4");
                    yearList.add("5");
                    yearList.add("6");
                    yearList.add("6年以上");
                    data.setmBottleModelList(modelList);
                    data.setmBottleYearsList(yearList);
                    data.setmSelectBottleModeIndex(0);
                    data.setmSelectBottleYearsIndex(0);
                    data.setBottleNum(0);
                    data.setDiscount(0);
                    list.add(data);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public interface OnExchangeRclClicklistener {

        void onBottleNumChange(int steelNum, TextView tvAccount, int modelSelectIndex, int yearSelectIndex);

        void onSpinnerChange(int modelIndex, int yearsIndex, int bottleNum, TextView tvAccount);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.exchange_steel_plus)
        TextView tvPlus;
        @BindView(R.id.exchange_steel_number)
        TextView tvSteelNum;
        @BindView(R.id.exchange_reduce)
        TextView tvReduce;
        @BindView(R.id.exchange_steel_account)
        TextView tvAccount;
        @BindView(R.id.exchange_steel_type_spinner)
        Spinner mModelSpinner;
        @BindView(R.id.exchange_steel_years_spinner)
        Spinner mYearsSpinner;
        SpinnerAdapter mYearSpinnerAdapter;
        SpinnerAdapter mModelSpinnerAdapter;

        public ViewHolder(View v) {
            super(v);
            mModelSpinnerAdapter = new SpinnerAdapter(activity);
            mYearSpinnerAdapter = new SpinnerAdapter(activity);
            mModelSpinner.setAdapter(mModelSpinnerAdapter);
            mYearsSpinner.setAdapter(mYearSpinnerAdapter);

        }
    }

    public class ViewHolderFoot extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_exchange_foot)
        LinearLayout llExchangeFootl;

        public ViewHolderFoot(View v) {
            super(v);
        }
    }
}
