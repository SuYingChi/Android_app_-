package com.msht.mshtLpg.mshtLpgMaster.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.Bean.OrdersListBeanV2;
import com.msht.mshtLpg.mshtLpgMaster.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersListRclAdapter extends RecyclerView.Adapter {


    private LayoutInflater inflater;
    private List<OrdersListBeanV2.DataBean.ListBean> list;
    private OnOrdersFragmentRclClicklistener onOrderListRclClicklistener;

    public OrdersListRclAdapter(List<OrdersListBeanV2.DataBean.ListBean> list, Activity activity, OnOrdersFragmentRclClicklistener onOrderListRclClicklistener) {
        this.list = list;
        this.inflater = LayoutInflater.from(activity);
        this.onOrderListRclClicklistener = onOrderListRclClicklistener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new OrdersHolder(inflater.inflate(R.layout.item_rcl_home_fragment_order, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OrdersListBeanV2.DataBean.ListBean bean = list.get(position);

        if (holder instanceof OrdersHolder) {
            //送气待验瓶
            if (holder.getItemViewType() == 0) {
                ((OrdersHolder) holder).order_number.setText(String.valueOf(bean.getOrderId()));
                ((OrdersHolder) holder).order_statu.setText("待验瓶");
                ((OrdersHolder) holder).user.setText(bean.getBuyer());

                ((OrdersHolder) holder).location.setText(new StringBuilder().append(bean.getAddress()).append(bean.getFloor()).append("楼").append(bean.getRoomNum()).append("号").append(bean.getIsElevator() == 1 ? "(有电梯)" : "(无电梯)").append(bean.getIsDelivery() == 1 ? "(自提单)" : "(配送单)").toString());
                ((OrdersHolder) holder).sex.setText(bean.getSex() == 0 ? "  (先生)" : "  (女士)");
                ((OrdersHolder) holder).order_cost.setText(String.valueOf(bean.getRealAmount()));
                ((OrdersHolder) holder).fiveBottleNumber.setText(String.valueOf(bean.getFiveBottleCount()));
                ((OrdersHolder) holder).fifteenBottleNumber.setText(String.valueOf(bean.getFifteenBottleCount()));
                ((OrdersHolder) holder).fiftyBottleNumber.setText(String.valueOf(bean.getFiftyBottleCount()));
                ((OrdersHolder) holder).sendBottleTime.setText(bean.getAppointmentTime());
                ((OrdersHolder) holder).orderBtn.setText("待验瓶");
                ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClckOrderButton(bean.getOrderId(), 0);
                    }
                });
                ((OrdersHolder) holder).sendType.setText(TextUtils.equals("1",String.valueOf(bean.getIsDelivery())) ? "(用户自提)" : "");
                ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickCallButton(bean.getMobile());
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickItem(bean.getOrderId(), 0);
                    }
                });
                //送气待付款
            } else if (holder.getItemViewType() == 1) {

                ((OrdersHolder) holder).order_number.setText(String.valueOf(bean.getOrderId()));
                ((OrdersHolder) holder).order_statu.setText("待付款");
                ((OrdersHolder) holder).user.setText(bean.getBuyer());
                ((OrdersHolder) holder).location.setText(new StringBuilder().append(bean.getAddress()).append(bean.getFloor()).append("楼").append(bean.getRoomNum()).append("号").append(bean.getIsElevator() == 1 ? "(有电梯)" : "(无电梯)").append(bean.getIsDelivery() == 1 ? "(自提单)" : "(配送单)").toString());
                ((OrdersHolder) holder).sex.setText(bean.getSex() == 0 ? " (先生)" : " (女士)");
                ((OrdersHolder) holder).order_cost.setText(String.valueOf(bean.getRealAmount()));
                ((OrdersHolder) holder).fiveBottleNumber.setText(String.valueOf(bean.getFiveBottleCount()));
                ((OrdersHolder) holder).fifteenBottleNumber.setText(String.valueOf(bean.getFifteenBottleCount()));
                ((OrdersHolder) holder).fiftyBottleNumber.setText(String.valueOf(bean.getFiftyBottleCount()));
                ((OrdersHolder) holder).sendBottleTime.setText(bean.getAppointmentTime());
                ((OrdersHolder) holder).orderBtn.setText("待付款");
                ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClckOrderButton(bean.getOrderId(), 1);
                    }
                });
                ((OrdersHolder) holder).sendType.setText("1".equals(bean.getIsDelivery()) ? "(用户自提)" : "");
                ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickCallButton(bean.getMobile());
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickItem(bean.getOrderId(), 1);
                    }
                });
                //送气已完成
            } else if (holder.getItemViewType() == 2) {

                ((OrdersHolder) holder).order_number.setText(String.valueOf(bean.getOrderId()));
                ((OrdersHolder) holder).order_statu.setText("已完成");
                ((OrdersHolder) holder).user.setText(bean.getBuyer());
                ((OrdersHolder) holder).location.setText(new StringBuilder().append(bean.getAddress()).append(bean.getFloor()).append("楼").append(bean.getRoomNum()).append("号").append(bean.getIsElevator() == 1 ? "(有电梯)" : "(无电梯)").append(bean.getIsDelivery() == 1 ? "(自提单)" : "(配送单)").toString());
                ((OrdersHolder) holder).sex.setText(bean.getSex() == 0 ? " (先生)" : " (女士)");
                ((OrdersHolder) holder).order_cost.setText(String.valueOf(bean.getRealAmount()));
                ((OrdersHolder) holder).fiveBottleNumber.setText(String.valueOf(bean.getFiveBottleCount()));
                ((OrdersHolder) holder).fifteenBottleNumber.setText(String.valueOf(bean.getFifteenBottleCount()));
                ((OrdersHolder) holder).fiftyBottleNumber.setText(String.valueOf(bean.getFiftyBottleCount()));
                ((OrdersHolder) holder).sendBottleTime.setText(bean.getAppointmentTime());
                ((OrdersHolder) holder).orderBtn.setText("已完成");
                ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClckOrderButton(bean.getOrderId(), 2);
                    }
                });
                ((OrdersHolder) holder).sendType.setText("1".equals(bean.getIsDelivery()) ? "(用户自提)" : "");
                ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickCallButton(bean.getMobile());
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickItem(bean.getOrderId(), 2);
                    }
                });
            } //退瓶待验瓶
            else if (holder.getItemViewType() == 3) {

                ((OrdersHolder) holder).order_number.setText(String.valueOf(bean.getOrderId()));
                ((OrdersHolder) holder).order_statu.setText("待验瓶");
                ((OrdersHolder) holder).user.setText(bean.getBuyer());
                ((OrdersHolder) holder).location.setText(new StringBuilder().append(bean.getAddress()).append(bean.getFloor()).append("楼").append(bean.getRoomNum()).append("号").append(bean.getIsElevator() == 1 ? "(有电梯)" : "(无电梯)").append(bean.getIsDelivery() == 1 ? "(自提单)" : "(配送单)").toString());
                ((OrdersHolder) holder).sex.setText(bean.getSex() == 0 ? " (先生)" : " (女士)");
                ((OrdersHolder) holder).order_cost.setText(String.valueOf(bean.getRealAmount()));
                ((OrdersHolder) holder).fiveBottleNumber.setText(String.valueOf(bean.getReFiveBottleCount()));
                ((OrdersHolder) holder).fifteenBottleNumber.setText(String.valueOf(bean.getReFifteenBottleCount()));
                ((OrdersHolder) holder).fiftyBottleNumber.setText(String.valueOf(bean.getReFiftyBottleCount()));
                ((OrdersHolder) holder).sendBottleTime.setText(bean.getAppointmentTime());
                ((OrdersHolder) holder).orderBtn.setText("待验瓶");
                ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClckOrderButton(bean.getOrderId(), 3);
                    }
                });
                ((OrdersHolder) holder).sendType.setText("1".equals(bean.getIsDelivery()) ? "(用户自提)" : "");
                ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickCallButton(bean.getMobile());
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickItem(bean.getOrderId(), 3);
                    }
                });
            }//退瓶待付款
            else if (holder.getItemViewType() == 4) {

                ((OrdersHolder) holder).order_number.setText(String.valueOf(bean.getOrderId()));
                ((OrdersHolder) holder).order_statu.setText("已完成");
                ((OrdersHolder) holder).user.setText(bean.getBuyer());
                ((OrdersHolder) holder).user.setText(bean.getBuyer());
                ((OrdersHolder) holder).location.setText(new StringBuilder().append(bean.getAddress()).append(bean.getFloor()).append("楼").append(bean.getRoomNum()).append("号").append(bean.getIsElevator() == 1 ? "(有电梯)" : "(无电梯)").append(bean.getIsDelivery() == 1 ? "(自提单)" : "(配送单)").toString());
                ((OrdersHolder) holder).sex.setText(bean.getSex() == 0 ? " (先生)" : " (女士)");
                ((OrdersHolder) holder).order_cost.setText(String.valueOf(bean.getRealAmount()));
                ((OrdersHolder) holder).fiveBottleNumber.setText(String.valueOf(bean.getReFiveBottleCount()));
                ((OrdersHolder) holder).fifteenBottleNumber.setText(String.valueOf(bean.getReFifteenBottleCount()));
                ((OrdersHolder) holder).fiftyBottleNumber.setText(String.valueOf(bean.getReFiftyBottleCount()));
                ((OrdersHolder) holder).sendBottleTime.setText(bean.getAppointmentTime());
                ((OrdersHolder) holder).orderBtn.setText("已完成");
                ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClckOrderButton(bean.getOrderId(), 4);
                    }
                });
                ((OrdersHolder) holder).sendType.setText("1".equals(bean.getIsDelivery()) ? "(用户自提)" : "");
                ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickCallButton(bean.getMobile());
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickItem(bean.getOrderId(), 4);
                    }
                });
            }
            //退瓶已完成
            else if (holder.getItemViewType() == 5) {

                ((OrdersHolder) holder).order_number.setText(String.valueOf(bean.getOrderId()));
                ((OrdersHolder) holder).order_statu.setText("已取消");
                ((OrdersHolder) holder).user.setText(bean.getBuyer());
                ((OrdersHolder) holder).location.setText(new StringBuilder().append(bean.getAddress()).append(bean.getFloor()).append("楼").append(bean.getRoomNum()).append("号").append(bean.getIsElevator() == 1 ? "(有电梯)" : "(无电梯)").append(bean.getIsDelivery() == 1 ? "(自提单)" : "(配送单)").toString());
                ((OrdersHolder) holder).sex.setText(bean.getSex() == 0 ? " (先生)" : "(女士)");
                ((OrdersHolder) holder).order_cost.setText(String.valueOf(bean.getRealAmount()));
                ((OrdersHolder) holder).fiveBottleNumber.setText(String.valueOf(bean.getReFiveBottleCount()));
                ((OrdersHolder) holder).fifteenBottleNumber.setText(String.valueOf(bean.getReFifteenBottleCount()));
                ((OrdersHolder) holder).fiftyBottleNumber.setText(String.valueOf(bean.getReFiftyBottleCount()));
                ((OrdersHolder) holder).sendBottleTime.setText(bean.getAppointmentTime());
                ((OrdersHolder) holder).orderBtn.setText("已取消");
                ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClckOrderButton(bean.getOrderId(), 5);
                    }
                });
                ((OrdersHolder) holder).sendType.setText("1".equals(bean.getIsDelivery()) ? "(用户自提)" : "");
                ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickCallButton(bean.getMobile());
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickItem(bean.getOrderId(), 5);
                    }
                });
            } else if (holder.getItemViewType() == 6) {
                ((OrdersHolder) holder).order_number.setText(String.valueOf(bean.getOrderId()));
                ((OrdersHolder) holder).order_statu.setText("已取消");
                ((OrdersHolder) holder).user.setText(bean.getBuyer());
                ((OrdersHolder) holder).location.setText(new StringBuilder().append(bean.getAddress()).append(bean.getFloor()).append("楼").append(bean.getRoomNum()).append("号").append(bean.getIsElevator() == 1 ? "(有电梯)" : "(无电梯)").append(bean.getIsDelivery() == 1 ? "(自提单)" : "(配送单)").toString());
                ((OrdersHolder) holder).sex.setText(bean.getSex() == 0 ? " (先生)" : " (女士)");
                ((OrdersHolder) holder).order_cost.setText(String.valueOf(bean.getRealAmount()));
                ((OrdersHolder) holder).fiveBottleNumber.setText(String.valueOf(bean.getFiveBottleCount()));
                ((OrdersHolder) holder).fifteenBottleNumber.setText(String.valueOf(bean.getFifteenBottleCount()));
                ((OrdersHolder) holder).fiftyBottleNumber.setText(String.valueOf(bean.getFiftyBottleCount()));
                ((OrdersHolder) holder).sendBottleTime.setText(bean.getAppointmentTime());
                ((OrdersHolder) holder).orderBtn.setText("已取消");
                ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClckOrderButton(bean.getOrderId(), 6);
                    }
                });
                ((OrdersHolder) holder).sendType.setText("1".equals(bean.getIsDelivery()) ? "(用户自提)" : "");
                ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickCallButton(bean.getMobile());
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickItem(bean.getOrderId(), 6);
                    }
                });
            } else if (holder.getItemViewType() == 7) {
                ((OrdersHolder) holder).order_number.setText(String.valueOf(bean.getOrderId()));
                ((OrdersHolder) holder).order_statu.setText("已取消");
                ((OrdersHolder) holder).user.setText(bean.getBuyer());
                ((OrdersHolder) holder).location.setText(new StringBuilder().append(bean.getAddress()).append(bean.getFloor()).append("楼").append(bean.getRoomNum()).append("号").append(bean.getIsElevator() == 1 ? "（有电梯）" : "（无电梯）").append(bean.getIsDelivery() == 1 ? "（自提单）" : "（配送单）").toString());
                ((OrdersHolder) holder).sex.setText(bean.getSex() == 0 ? " (先生)" : " (女士)");
                ((OrdersHolder) holder).order_cost.setText(String.valueOf(bean.getRealAmount()));
                ((OrdersHolder) holder).fiveBottleNumber.setText(String.valueOf(bean.getReFiveBottleCount() ));
                ((OrdersHolder) holder).fifteenBottleNumber.setText(String.valueOf(bean.getReFifteenBottleCount()));
                ((OrdersHolder) holder).fiftyBottleNumber.setText(String.valueOf(bean.getReFiftyBottleCount()));
                ((OrdersHolder) holder).sendBottleTime.setText(String.valueOf(bean.getAppointmentTime()));
                ((OrdersHolder) holder).orderBtn.setText("已取消");
                ((OrdersHolder) holder).orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClckOrderButton(bean.getOrderId(), 7);
                    }
                });
                ((OrdersHolder) holder).sendType.setText("1".equals(bean.getIsDelivery()) ? "(用户自提)" : "");
                ((OrdersHolder) holder).callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickCallButton(bean.getMobile());
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onOrderListRclClicklistener.onClickItem(bean.getOrderId(), 7);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        int ordertype = list.get(position).getOrderType();
        int orderStatus = list.get(position).getOrderStatus();
        int itemType = 0;
        //送气单
        if (ordertype == 1) {
            switch (orderStatus) {
                //待验瓶
                case 2:
                    itemType = 0;
                    break;
                //待付款
                case 0:
                    itemType = 1;
                    break;
                //已完成
                case 3:
                    itemType = 2;
                    break;
                default:
                    itemType = 6;
                    break;
            }
        }//退瓶单
        else if (ordertype == 0) {
            switch (orderStatus) {
                //待验瓶
                case 7:
                case 6:
                    itemType = 3;
                    break;
                //已完成
                case 3:
                    itemType = 4;
                    break;
                //已取消
                case 5:
                    itemType = 5;
                    break;
                default:
                    itemType = 7;
                    break;
            }
        }

        return itemType;
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
        TextView sendBottleTime;
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

    public interface OnOrdersFragmentRclClicklistener {
        void onClickCallButton(String mobile);

        void onClckOrderButton(int orderId, int orderType);

        void onClickItem(int orderId, int orderType);
    }

}
