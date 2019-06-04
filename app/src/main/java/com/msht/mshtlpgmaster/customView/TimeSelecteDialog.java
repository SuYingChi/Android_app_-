package com.msht.mshtlpgmaster.customView;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.TimePicker;

import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.util.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeSelecteDialog extends Dialog {

    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.time_pick)
    TimePicker timePicker;

    private OnSelectTimeListener onSelectTimeListener;

    public TimeSelecteDialog(Context context,  OnSelectTimeListener onSelectTimeListener) {
        super(context, R.style.BottomAnimDialogStyle);

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
        attributes.gravity = Gravity.BOTTOM;
        this.getWindow().setAttributes(attributes);
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
                TimeSelecteDialog.this.onSelectTimeListener.onSelectTime(hourOfDay, minute);
            }
        });


    }

    public void reverseTime(int systemHour, int systemMinute) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(systemHour);
            timePicker.setMinute(systemMinute);
        }
    }

    public void setSendDates(int sendOrderYear, int sendOrderMonth, int sendOrderDate, int sendOrderHour, int sendOrderMinute) {
        calendarView.setDate(DateUtils.getStringToDate(sendOrderYear + "-" + sendOrderMonth + "-" + sendOrderDate, "yyyy-MM-dd"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(sendOrderHour);
            timePicker.setMinute(sendOrderMinute);
        }
    }

    public void reverseDate(int sendOrderYear, int sendOrderMonth, int sendOrderDate) {
        calendarView.setDate(DateUtils.getStringToDate(sendOrderYear + "-" + sendOrderMonth + "-" + sendOrderDate, "yyyy-MM-dd"));
    }


    public interface OnSelectTimeListener {
        void onSelectDate(int year, int month, int date);

        void onSelectTime(int hourOfDay, int minute);
    }
}
