package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersBean;
import com.msht.mshtLpg.mshtLpgMaster.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersFragmentRclAdapter extends RecyclerView.Adapter{


    private  LayoutInflater inflater;
    private  Activity activity;
    private  List<OrdersBean.DataBean.ListBean> list;
    private OnOrdersFragmentRclClicklistener onOrdersFragmentRclClicklistener;

    public OrdersFragmentRclAdapter(List<OrdersBean.DataBean.ListBean> list, Activity activity,OnOrdersFragmentRclClicklistener onOrdersFragmentRclClicklistener) {
        this.list = list;
        this.inflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.onOrdersFragmentRclClicklistener = onOrdersFragmentRclClicklistener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new OrdersHolder(inflater.inflate(R.layout.item_rcl_home_fragment_order, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OrdersBean.DataBean.ListBean bean = list.get(position);

        if (holder instanceof OrdersHolder){
            //送气待验瓶
        if( holder.getItemViewType() == 1) {
            ((OrdersHolder) holder).order_number.setText(bean.getOrderId()+"");
            ((OrdersHolder) holder).order_statu.setText(bean.getOrderStatus()+"");
            ((OrdersHolder) holder).user.setText(bean.getBuyer());
            ((OrdersHolder) holder).location.setText(bean.getAddress()+bean.getAddressShort()+bean.getFloor()+bean.getRoomNum()+(bean.getIsElevator()==1?"（有电梯）":"（无电梯）"));
            ((OrdersHolder) holder).sex.setText(bean.getSex());
            ((OrdersHolder) holder).order_cost.setText(bean.getRealAmount()+"");
            ((OrdersHolder) holder).fiveBottleNumber.setText(bean.getFiveBottleCount()+"");
            ((OrdersHolder) holder).fifteenBottleNumber.setText(bean.getFifteenBottleCount()+"");
            ((OrdersHolder) holder).fiftyBottleNumber.setText(bean.getFiftyBottleCount()+"");
            ((OrdersHolder) holder).sendBottleTime.setText(bean.getCreateData());
             ((OrdersHolder) holder).orderBtn.setText("付款");
            ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClckOrderButton(bean.getOrderId());
                }
            });
             ((OrdersHolder) holder).sendType.setText(bean.getIsDelivery()==1?"(用户自提)":"");
             ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     onOrdersFragmentRclClicklistener.onClickCallButton(bean.getMobile());
                 }
             });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickItem();
                }
            });
            //送气待付款
        } else if (holder.getItemViewType() == 2) {

            ((OrdersHolder) holder).order_number.setText(bean.getOrderId()+"");
            ((OrdersHolder) holder).order_statu.setText(bean.getOrderStatus()+"");
            ((OrdersHolder) holder).user.setText(bean.getBuyer());
            ((OrdersHolder) holder).location.setText(bean.getAddress()+bean.getAddressShort()+bean.getFloor()+bean.getRoomNum()+(bean.getIsElevator()==1?"（有电梯）":"（无电梯）"));
            ((OrdersHolder) holder).sex.setText(bean.getSex());
            ((OrdersHolder) holder).order_cost.setText(bean.getRealAmount()+"");
            ((OrdersHolder) holder).fiveBottleNumber.setText(bean.getFiveBottleCount()+"");
            ((OrdersHolder) holder).fifteenBottleNumber.setText(bean.getFifteenBottleCount()+"");
            ((OrdersHolder) holder).fiftyBottleNumber.setText(bean.getFiftyBottleCount()+"");
            ((OrdersHolder) holder).sendBottleTime.setText(bean.getCreateData());
            ((OrdersHolder) holder).orderBtn.setText("付款");
            ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClckOrderButton(bean.getOrderId());
                }
            });
            ((OrdersHolder) holder).sendType.setText(bean.getIsDelivery()==1?"(用户自提)":"");
            ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickCallButton(bean.getMobile());
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickItem();
                }
            });
            //送气已完成
        } else if (holder.getItemViewType() == 3) {

            ((OrdersHolder) holder).order_number.setText(bean.getOrderId()+"");
            ((OrdersHolder) holder).order_statu.setText(bean.getOrderStatus()+"");
            ((OrdersHolder) holder).user.setText(bean.getBuyer());
            ((OrdersHolder) holder).location.setText(bean.getAddress()+bean.getAddressShort()+bean.getFloor()+bean.getRoomNum()+(bean.getIsElevator()==1?"（有电梯）":"（无电梯）"));
            ((OrdersHolder) holder).sex.setText(bean.getSex());
            ((OrdersHolder) holder).order_cost.setText(bean.getRealAmount()+"");
            ((OrdersHolder) holder).fiveBottleNumber.setText(bean.getFiveBottleCount()+"");
            ((OrdersHolder) holder).fifteenBottleNumber.setText(bean.getFifteenBottleCount()+"");
            ((OrdersHolder) holder).fiftyBottleNumber.setText(bean.getFiftyBottleCount()+"");
            ((OrdersHolder) holder).sendBottleTime.setText(bean.getCreateData());
            ((OrdersHolder) holder).orderBtn.setText("付款");
            ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClckOrderButton(bean.getOrderId());
                }
            });
            ((OrdersHolder) holder).sendType.setText(bean.getIsDelivery()==1?"(用户自提)":"");
            ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickCallButton(bean.getMobile());
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickItem();
                }
            });
        } //退瓶待验瓶
        else if (holder.getItemViewType() == 4) {

            ((OrdersHolder) holder).order_number.setText(bean.getOrderId()+"");
            ((OrdersHolder) holder).order_statu.setText(bean.getOrderStatus()+"");
            ((OrdersHolder) holder).user.setText(bean.getBuyer()+"");
            ((OrdersHolder) holder).location.setText(bean.getAddress()+bean.getAddressShort()+bean.getFloor()+bean.getRoomNum()+(bean.getIsElevator()==1?"（有电梯）":"（无电梯）"));
            ((OrdersHolder) holder).sex.setText(bean.getSex());
            ((OrdersHolder) holder).order_cost.setText(bean.getRealAmount()+"");
            ((OrdersHolder) holder).fiveBottleNumber.setText(bean.getFiveBottleCount()+"");
            ((OrdersHolder) holder).fifteenBottleNumber.setText(bean.getFifteenBottleCount()+"");
            ((OrdersHolder) holder).fiftyBottleNumber.setText(bean.getFiftyBottleCount()+"");
            ((OrdersHolder) holder).sendBottleTime.setText(bean.getCreateData());
            ((OrdersHolder) holder).orderBtn.setText("付款");
            ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClckOrderButton(bean.getOrderId());
                }
            });
            ((OrdersHolder) holder).sendType.setText(bean.getIsDelivery()==1?"(用户自提)":"");
            ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickCallButton(bean.getMobile());
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickItem();
                }
            });
        }//退瓶待付款
        else if (holder.getItemViewType() == 5) {

            ((OrdersHolder) holder).order_number.setText(bean.getOrderId()+"");
            ((OrdersHolder) holder).order_statu.setText(bean.getOrderStatus()+"");
            ((OrdersHolder) holder).user.setText(bean.getBuyer());
            ((OrdersHolder) holder).location.setText(bean.getAddress()+bean.getAddressShort()+bean.getFloor()+bean.getRoomNum()+(bean.getIsElevator()==1?"（有电梯）":"（无电梯）"));
            ((OrdersHolder) holder).sex.setText(bean.getSex());
            ((OrdersHolder) holder).order_cost.setText(bean.getRealAmount()+"");
            ((OrdersHolder) holder).fiveBottleNumber.setText(bean.getFiveBottleCount()+"");
            ((OrdersHolder) holder).fifteenBottleNumber.setText(bean.getFifteenBottleCount()+"");
            ((OrdersHolder) holder).fiftyBottleNumber.setText(bean.getFiftyBottleCount()+"");
            ((OrdersHolder) holder).sendBottleTime.setText(bean.getCreateData());
            ((OrdersHolder) holder).orderBtn.setText("付款");
            ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClckOrderButton(bean.getOrderId());
                }
            });
            ((OrdersHolder) holder).sendType.setText(bean.getIsDelivery()==1?"(用户自提)":"");
            ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickCallButton(bean.getMobile());
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickItem();
                }
            });
        }
        //退瓶已完成
        else if (holder.getItemViewType() == 6) {

            ((OrdersHolder) holder).order_number.setText(bean.getOrderId()+"");
            ((OrdersHolder) holder).order_statu.setText(bean.getOrderStatus()+"");
            ((OrdersHolder) holder).user.setText(bean.getBuyer());
            ((OrdersHolder) holder).location.setText(bean.getAddress()+bean.getAddressShort()+bean.getFloor()+bean.getRoomNum()+(bean.getIsElevator()==1?"（有电梯）":"（无电梯）"));
            ((OrdersHolder) holder).sex.setText(bean.getSex());
            ((OrdersHolder) holder).order_cost.setText(bean.getRealAmount()+"");
            ((OrdersHolder) holder).fiveBottleNumber.setText(bean.getFiveBottleCount()+"");
            ((OrdersHolder) holder).fifteenBottleNumber.setText(bean.getFifteenBottleCount()+"");
            ((OrdersHolder) holder).fiftyBottleNumber.setText(bean.getFiftyBottleCount()+"");
            ((OrdersHolder) holder).sendBottleTime.setText(bean.getCreateData());
            ((OrdersHolder) holder).orderBtn.setText("付款");
            ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClckOrderButton(bean.getOrderId());
                }
            });
            ((OrdersHolder) holder).sendType.setText(bean.getIsDelivery()==1?"(用户自提)":"");
            ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickCallButton(bean.getMobile());
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOrdersFragmentRclClicklistener.onClickItem();
                }
            });
        }else {
           return;
        }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getOrderType();
    }

     class OrdersHolder extends RecyclerView.ViewHolder {
        /* private  int type;*/
         @BindView(R.id.item_rcl_home_fragment_order_order_number)
         TextView order_number;
        @BindView(R.id.item_rcl_home_fragment_order_statu)
        TextView order_statu;
        @BindView(R.id.item_rcl_home_fragment_order_user)
        TextView user;
        @BindView(R.id.item_rcl_home_fragment_order_time)
         TextView sendBottleTime ;
        @BindView(R.id.item_rcl_home_fragment_order_location)
        TextView location;
        @BindView(R.id.item_rcl_home_fragment_order_call_btn)
         ImageView callBtn;
        @BindView(R.id.tv_item_rcl_home_fragment_order_5_number)
        TextView fiveBottleNumber;
        @BindView(R.id.tv_item_rcl_home_fragment_order_15_number)
        TextView fifteenBottleNumber;
        @BindView(R.id.tv_item_rcl_home_fragment_order_50_number)
        TextView fiftyBottleNumber;
        @BindView(R.id.tv_item_rcl_home_fragment_order_cost)
        TextView order_cost;
        @BindView(R.id.btn_item_rcl_home_fragment_order_pay)
         Button orderBtn;
        @BindView(R.id.item_rcl_home_fragment_order_sex)
        TextView sex;
        @BindView(R.id.item_rcl_home_fragment_order_send_type)
        TextView sendType;
        public OrdersHolder(View inflate/*,int type*/) {
            super(inflate);
           /* this.type = type;*/
            ButterKnife.bind(this, inflate);
        }


     }

     public interface OnOrdersFragmentRclClicklistener{
        void onClickCallButton (String mobile);
        void onClckOrderButton (int orderId);
        void onClickItem ();
     }

}
