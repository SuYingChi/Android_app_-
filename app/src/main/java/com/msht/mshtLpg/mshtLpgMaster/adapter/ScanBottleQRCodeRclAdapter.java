package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ScanBottleQRCodeBean;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.fragment.MyCaptureFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanBottleQRCodeRclAdapter extends RecyclerView.Adapter{
    private final LayoutInflater inflater;
    private  List<ScanBottleQRCodeBean> list;
    private  Activity activity;
    private int fiveNum = 0;
    private int fifteenNum = 0;
    private int fiftyNum = 0;

    public ScanBottleQRCodeRclAdapter(List<ScanBottleQRCodeBean> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScanHolder(inflater.inflate(R.layout.item_rcl_deliver_steel_bottle,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ScanBottleQRCodeBean bean = list.get(position);
        if(holder instanceof ScanHolder){
            ((ScanHolder) holder).tvBottleModel.setText(bean.getData().getBottleWeight()+"");
            ((ScanHolder) holder).tvBottleNumber.setText(bean.getData().getBottleNum());
            ((ScanHolder) holder).tvBottleStatus.setText(bean.getData().getIsHeavy()==0?"重瓶":"空瓶");
        }
    }

    @Override
    public int getItemCount() {
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

   public int getFiveNum(){

        for(ScanBottleQRCodeBean bean:list){
            if (bean.getData().getBottleWeight() == 5){
                fiveNum++;
            }
        }
        return fiveNum;
    }

  public   int getFifteenNum(){
        for(ScanBottleQRCodeBean bean:list){
            if (bean.getData().getBottleWeight() == 15){
                fifteenNum++;
            }
        }
        return fifteenNum;
    }

  public   int getFiftyNum(){
        for(ScanBottleQRCodeBean bean:list){
            if (bean.getData().getBottleWeight() == 50){
                fiftyNum++;
            }
        }
        return fiftyNum;
    }
}
