package com.msht.mshtLpg.mshtLpgMaster.customView;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.DispatchCustomerOrderActivity;
import com.msht.mshtLpg.mshtLpgMaster.util.DateUtils;
import com.msht.mshtLpg.mshtLpgMaster.util.DimenUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeSelecteDialog extends Dialog {
    private int systemYear;
    private int systemMonth;
    private int systemDate;
    private int systemHour;
    private int systemMinute;
    private Context context;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.time_pick)
    TimePicker timePicker;
    ;
    private OnSelectTimeListener onSelectTimeListener;

    public TimeSelecteDialog(Context context, int systemYear, int systemMonth, int systemDate,int systemHour,int systemMinute,OnSelectTimeListener onSelectTimeListener) {
        super(context, R.style.BottomAnimDialogStyle);
        this.context = context;
        this.onSelectTimeListener = onSelectTimeListener;
        this.systemYear  =systemYear;
        this.systemMonth = systemMonth;
        this.systemDate = systemDate;
        this.systemHour =systemHour;
        this.systemMinute = systemMinute;
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
        ButterKnife.bind(this);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        this.getWindow().setAttributes(attributes);
        //dialog去除底部背景
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                TimeSelecteDialog.this.onSelectTimeListener.onSelectDate(year, month, dayOfMonth);
            }
        });
        calendarView.setMinDate(DateUtils.getStringToDate(systemYear+"-"+systemMonth+"-"+systemDate+"-","yyyy-MM-dd"));
        timePicker.setIs24HourView(true);
/*        *//*接下来就是一些需要用到反射的方法了，比如更改分割线的样式，设置起始截止时间等：
        首先我们要通过反射获取TimePicker源码里hour和minute的id：*//*
        Resources systemResources = Resources.getSystem();
        int hourNumberPickerId = systemResources.getIdentifier("hour", "id", "android");
        int minuteNumberPickerId = systemResources.getIdentifier("minute", "id", "android");
        //然后用我们定义的TimePicker来获取这个id并转换成hour和minute对应的NumberPicker:
        NumberPicker hourNumberPicker = (NumberPicker) timePicker.findViewById(hourNumberPickerId);
        NumberPicker minuteNumberPicker = (NumberPicker) timePicker.findViewById(minuteNumberPickerId);
        //通过获取到的hourNumberPicker和minuteNumberPicker我们可以先进行TimePicker的时间限制：
        //设置最小hour
        hourNumberPicker.setMinValue(systemHour);
        //设置最小minute
        minuteNumberPicker.setMinValue(systemMinute);*/
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        TimeSelecteDialog.this.onSelectTimeListener.onSelectTime(hourOfDay,minute);
                    }
                });


    }

    public void setSystemTime(int systemYear, int systemMonth, int systemDate, int systemHour, int systemMinute) {
        this.systemYear = systemYear;
        this.systemMonth = systemMonth;
        this.systemDate = systemDate;
        this.systemHour = systemHour;
        this.systemMinute = systemMinute;
    }

    public void reverseTime(int systemHour, int systemMinute) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(systemHour);
            timePicker.setMinute(systemMinute);
        }
    }


    public interface OnSelectTimeListener {
    void onSelectDate(int year, int month, int date);
    void onSelectTime(int hourOfDay, int minute);
}
}
