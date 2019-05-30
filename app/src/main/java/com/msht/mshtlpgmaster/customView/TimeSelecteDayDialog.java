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

public class TimeSelecteDayDialog extends Dialog {

    @BindView(R.id.calendarView)
    CalendarView calendarView;


    private OnSelectTimeListener onSelectTimeListener;

    public TimeSelecteDayDialog(Context context,  OnSelectTimeListener onSelectTimeListener) {
        super(context, R.style.BottomAnimDialogStyle);

        this.onSelectTimeListener = onSelectTimeListener;

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (TimeSelecteDayDialog.this.isShowing()) {
                    TimeSelecteDayDialog.this.dismiss();
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
        setContentView(R.layout.select_day_dialog);
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
                TimeSelecteDayDialog.this.onSelectTimeListener.onSelectDate(year, month, dayOfMonth);
            }
        });


    }


    public void setSendDates(int sendOrderYear, int sendOrderMonth, int sendOrderDate, int sendOrderHour, int sendOrderMinute) {
        calendarView.setDate(DateUtils.getStringToDate(sendOrderYear + "-" + sendOrderMonth + "-" + sendOrderDate, "yyyy-MM-dd"));

    }

    public void reverseDate(int sendOrderYear, int sendOrderMonth, int sendOrderDate) {
        calendarView.setDate(DateUtils.getStringToDate(sendOrderYear + "-" + sendOrderMonth + "-" + sendOrderDate, "yyyy-MM-dd"));
    }


    public interface OnSelectTimeListener {
        void onSelectDate(int year, int month, int date);

    }
}
