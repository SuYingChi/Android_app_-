package com.msht.mshtlpgmaster.adapter;

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

import com.msht.mshtlpgmaster.Bean.ExchangeRclBean;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.util.PopUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeBottleRclAdapter extends RecyclerView.Adapter {

    private int remainFive;
    private int remainFifteen;
    private int remainFifty;
    private OnExchangeRclClicklistener onExchangeRclClicklistener;
    private Activity activity;
    private LayoutInflater inflater;
    private List<ExchangeRclBean> list;
    private long mClickTime;

    public ExchangeBottleRclAdapter(List<ExchangeRclBean> list, Activity activity, OnExchangeRclClicklistener onExchangeRclClicklistener, int remainFive, int remainFifteen, int remainFifty) {
        this.list = list;
        if(list.size()==0){
            ExchangeRclBean data = new ExchangeRclBean();
            List<String> modelList = new ArrayList<>();
            modelList.add(" 5kg ");
            modelList.add("15kg ");
            modelList.add("50kg ");
            List<String> yearList = new ArrayList<>();
            yearList.add(" 1 ");
            yearList.add(" 2 ");
            yearList.add(" 3 ");
            yearList.add(" 4 ");
            yearList.add(" 5 ");
            yearList.add(" 6 ");
            yearList.add("7年以上 ");
            List<String> levelList = new ArrayList<>();
            levelList.add(" A ");
            levelList.add(" B ");
            levelList.add(" C ");
            levelList.add(" D ");
            data.setmBottleModelList(modelList);
            data.setmBottleYearsList(yearList);
            data.setmBottleLevelList(levelList);
            data.setmSelectBottleModeIndex(1);
            data.setmSelectBottleYearsIndex(0);
            data.setSelectBottleLevelIndex(0);
            data.setBottleNum(0);
            data.setDiscount(0);
            list.add(data);
        }
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
            View v = inflater
                    .inflate(R.layout.item_rcl_exchange_steel_bottle, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        } else {
            View v = inflater
                    .inflate(R.layout.item_exchange_bottle_rcl_foot, parent, false);
            ViewHolderFoot vh = new ViewHolderFoot(v);
            return vh;
        }
    }

    //24种折价，回调到actvity去请求折价接口获取折价再计算
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            int index = list.get(holder.getAdapterPosition()).getSelectBottleModeIndex();
            int index2 = list.get(holder.getAdapterPosition()).getSelectBottleYearsIndex();
            int index3 = list.get(holder.getAdapterPosition()).getmSelectBottleLevelIndex();
            ((ViewHolder) holder).mModelSpinnerAdapter.setData(list.get(position).getmBottleModelList());
            ((ViewHolder) holder).mModelSpinnerAdapter.notifyDataSetChanged();
            ((ViewHolder) holder).mYearSpinnerAdapter.setData(list.get(position).getmBottleYearsList());
            ((ViewHolder) holder).mYearSpinnerAdapter.notifyDataSetChanged();
            ((ViewHolder) holder).mLevelSpinnerAdapter.setData(list.get(position).getmBottleLevelList());
            ((ViewHolder) holder).mLevelSpinnerAdapter.notifyDataSetChanged();
            ((ViewHolder) holder).mModelSpinner.setSelection(index);
            ((ViewHolder) holder).mYearsSpinner.setSelection(index2);
            ((ViewHolder) holder).levelSpinner.setSelection(index3);
            ((ViewHolder) holder).tvSteelNum.setText(list.get(holder.getAdapterPosition()).getBottleNum() + "");
            ((ViewHolder) holder).tvAccount.setText(list.get(holder.getAdapterPosition()).getDiscount() + "");
            ((ViewHolder) holder).tvReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(holder.getAdapterPosition()).getBottleNum() > 0) {
                        onExchangeRclClicklistener.onBottleNumChange(holder.getAdapterPosition(), ((ViewHolder) holder).tvAccount, ((ViewHolder) holder).tvSteelNum, list.get(holder.getAdapterPosition()).getBottleNum() - 1);
                    }
                }
            });
            ((ViewHolder) holder).tvPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectBottleModeIndex = list.get(holder.getAdapterPosition()).getSelectBottleModeIndex();
                    int bottleNum = list.get(holder.getAdapterPosition()).getBottleNum();

                    if (selectBottleModeIndex == 0 && bottleNum < remainFive) {
                        PopUtil.toastInBottom("只能置换15kg");
                        // onExchangeRclClicklistener.onBottleNumChange(position,((ViewHolder) holder).tvAccount,((ViewHolder) holder).tvSteelNum,list.get(position).getBottleNum()+1);
                    } else if (selectBottleModeIndex == 1 && bottleNum+totalExchangeBottleNum(1) < remainFifteen) {
                        onExchangeRclClicklistener.onBottleNumChange(position, ((ViewHolder) holder).tvAccount, ((ViewHolder) holder).tvSteelNum, list.get(position).getBottleNum() + 1);
                    } else if (selectBottleModeIndex == 2 && bottleNum < remainFifty) {
                        PopUtil.toastInBottom("只能置换15kg");
                        // onExchangeRclClicklistener.onBottleNumChange(position,((ViewHolder) holder).tvAccount,((ViewHolder) holder).tvSteelNum,list.get(position).getBottleNum()+1);
                    } else {
                        PopUtil.toastInBottom("无法再增加置换空瓶数，同重量钢瓶的置换空瓶数与扫码回收的空瓶数只能小于等于交付用户钢瓶数，");
                    }
                }
            });
            ((ViewHolder) holder).mModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    list.get(position).setmSelectBottleModeIndex(p);
                    switch (p) {
                        case 0:
                            PopUtil.toastInBottom("暂不支持置换5KG钢瓶");
                            break;
                        case 1:
                            onExchangeRclClicklistener.onSpinnerChange(position, ((ViewHolder) holder).tvAccount, p, list.get(position).getSelectBottleYearsIndex(), list.get(position).getmSelectBottleLevelIndex(), list.get(position).getBottleNum());
                            break;
                        case 2:
                            PopUtil.toastInBottom("暂不支持置换50KG钢瓶");
                            break;
                        default:
                            break;
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ((ViewHolder) holder).mYearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    list.get(position).setmSelectBottleYearsIndex(p);
                    onExchangeRclClicklistener.onSpinnerChange(holder.getAdapterPosition(), ((ViewHolder) holder).tvAccount, list.get(holder.getAdapterPosition()).getSelectBottleModeIndex(), p, list.get(holder.getAdapterPosition()).getmSelectBottleLevelIndex(), list.get(holder.getAdapterPosition()).getBottleNum());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ((ViewHolder) holder).levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    list.get(position).setSelectBottleLevelIndex(p);
                    onExchangeRclClicklistener.onSpinnerChange(holder.getAdapterPosition(), ((ViewHolder) holder).tvAccount, list.get(holder.getAdapterPosition()).getSelectBottleModeIndex(), list.get(holder.getAdapterPosition()).getSelectBottleYearsIndex(), p, list.get(holder.getAdapterPosition()).getBottleNum());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopUtil.showComfirmDialog(activity, "移除该项空瓶置换", "确认移除该项空瓶置换", "取消", "确认", null, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    }, true);
                     return  true;
                }
            });

        } else if (holder instanceof ViewHolderFoot) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (totalExchangeBottleNum(1)<remainFifteen) {
                        ExchangeRclBean data = new ExchangeRclBean();
                        List<String> modelList = new ArrayList<>();
                        modelList.add(" 5kg ");
                        modelList.add("15kg ");
                        modelList.add("50kg ");
                        List<String> yearList = new ArrayList<>();
                        yearList.add(" 1 ");
                        yearList.add(" 2 ");
                        yearList.add(" 3 ");
                        yearList.add(" 4 ");
                        yearList.add(" 5 ");
                        yearList.add(" 6 ");
                        yearList.add("7年以上 ");
                        List<String> levelList = new ArrayList<>();
                        levelList.add(" A ");
                        levelList.add(" B ");
                        levelList.add(" C ");
                        levelList.add(" D ");
                        data.setmBottleModelList(modelList);
                        data.setmBottleYearsList(yearList);
                        data.setmBottleLevelList(levelList);
                        data.setmSelectBottleModeIndex(1);
                        data.setmSelectBottleYearsIndex(0);
                        data.setSelectBottleLevelIndex(0);
                        data.setBottleNum(0);
                        data.setDiscount(0);
                        list.add(data);
                        notifyDataSetChanged();
                    }else {
                        PopUtil.toastInBottom("无法再增加置换空瓶数，同重量钢瓶的置换空瓶数与扫码回收的空瓶数只能小于等于交付用户钢瓶数，");
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == list.size() ? 100 : 1;
    }

    public interface OnExchangeRclClicklistener {

        void onBottleNumChange(int rclItemPosition, TextView tvAccount, TextView tvBottleNum, int steelNum);

        void onSpinnerChange(int rclItemPosition, TextView tvAccount, int modelSelectIndex, int yearSelectIndex, int levelSelectIndex, int steelNum);

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
        @BindView(R.id.exchange_steel_weight_spinner)
        Spinner mModelSpinner;
        @BindView(R.id.exchange_steel_years_spinner)
        Spinner mYearsSpinner;
        @BindView(R.id.exchange_steel_level_spinner)
        Spinner levelSpinner;
        SpinnerAdapter mYearSpinnerAdapter;
        SpinnerAdapter mModelSpinnerAdapter;
        SpinnerAdapter mLevelSpinnerAdapter;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            mModelSpinnerAdapter = new SpinnerAdapter(activity);
            mYearSpinnerAdapter = new SpinnerAdapter(activity);
            mLevelSpinnerAdapter = new SpinnerAdapter(activity);
            mModelSpinner.setAdapter(mModelSpinnerAdapter);
            mYearsSpinner.setAdapter(mYearSpinnerAdapter);
            levelSpinner.setAdapter(mLevelSpinnerAdapter);

        }
    }

    public class ViewHolderFoot extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_exchange_foot)
        LinearLayout llExchangeFootl;

        public ViewHolderFoot(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public double getTotalDiscount() {
        double totalDiscount = 0;
        for (ExchangeRclBean itemBean : list) {
            totalDiscount += itemBean.getDiscount();
        }
        return totalDiscount;
    }


    private int totalExchangeBottleNum(int modelIndex) {
        int totalBottleNum = 0 ;
        for(ExchangeRclBean bean :list){
            if(bean.getSelectBottleModeIndex() == modelIndex) {
                totalBottleNum += bean.getBottleNum();
            }
        }
        return totalBottleNum;
    }
}
