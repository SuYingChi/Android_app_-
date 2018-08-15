package com.msht.mshtLpg.mshtLpgMaster.customView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.TimePicker;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.util.DimenUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeSelecteDialog extends Dialog {
    private Context context;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.time_pick)
    TimePicker timePicker;
    ;
    private OnSelectTimeListener onSelectTimeListener;

    public TimeSelecteDialog(Context context, OnSelectTimeListener onSelectTimeListener) {
        super(context, R.style.BottomAnimDialogStyle);
        this.context = context;
        this.onSelectTimeListener = onSelectTimeListener;
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
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        TimeSelecteDialog.this.onSelectTimeListener.onSelectTime(hourOfDay,minute);
                    }
                });

    }


public interface OnSelectTimeListener {
    void onSelectDate(int year, int month, int date);
    void onSelectTime(int hourOfDay, int minute);
}
}
