package com.msht.mshtLpg.mshtLpgMaster.customView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.adapter.SimpleRclAdapter;
import com.msht.mshtLpg.mshtLpgMaster.util.DimenUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TimeSelecteDialog extends Dialog {
    private Context context;
    @BindView(R.id.rcl_select_year)
    RecyclerView rcldate;
    @BindView(R.id.rcl_select_month)
    RecyclerView rclTime;
    private Unbinder unbinder;
    private List<String> datelist = new ArrayList<String>();
    private SimpleRclAdapter dateAdapter;
    private SimpleRclAdapter timeAdapter;
    private List<String> timeList = new ArrayList<String>();
    ;
    private int year;
    private int month;
    private int date;
    private int hour;
    private OnRclClickListener onRclClickListener;
    public TimeSelecteDialog(Context context) {
        super(context, R.style.BottomAnimDialogStyle);
        this.context = context;
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        date = Calendar.getInstance().get(Calendar.DATE);
        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        initData();

    }

    private void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (TimeSelecteDialog.this.isShowing()) {
                    TimeSelecteDialog.this.dismiss();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_select_time);
        unbinder = ButterKnife.bind(this);
        WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
        attributes.width = DimenUtil.getScreenWidth() - DimenUtil.dip2px(context.getResources().getDimension(R.dimen.margin) * 2);
        attributes.height = DimenUtil.getScreenHeight() / 3;
        this.getWindow().setAttributes(attributes);
        this.getWindow().setGravity(Gravity.BOTTOM);//关键代码
        //dialog去除底部背景
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        rcldate.setLayoutManager(new LinearLayoutManager(context));
        dateAdapter = new SimpleRclAdapter(datelist, context, new SimpleRclAdapter.ItemClickListener() {
            @Override
            public void onItemClick(String selectString, int selectedPosition) {
                onRclClickListener.onRcldateItemClick(selectString);
            }
        });
        rclTime.setAdapter(dateAdapter);
        rclTime.setLayoutManager(new LinearLayoutManager(context));
        timeAdapter = new SimpleRclAdapter(timeList, context, new SimpleRclAdapter.ItemClickListener() {
            @Override
            public void onItemClick(String selectString, int selectedPosition) {
              onRclClickListener.onRclTimeItemClick(selectString);
            }
        });
        rcldate.setAdapter(timeAdapter);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }

    public void setOnRclClickListener(OnRclClickListener onRclClickListener) {
        this.onRclClickListener = onRclClickListener;
    }

    public interface OnRclClickListener {
        void onRcldateItemClick(String selectString);

        void onRclTimeItemClick(String selectString);
    }
}
