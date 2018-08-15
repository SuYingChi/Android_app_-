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

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.SendCustomerOrderActivity;
import com.msht.mshtLpg.mshtLpgMaster.util.DimenUtil;
import com.msht.mshtLpg.mshtLpgMaster.util.PopUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TimeSelecteDialog extends Dialog {
    private  Context context;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    ;
    private OnSelectTimeListener onSelectTimeListener;

    public TimeSelecteDialog(Context context,OnSelectTimeListener onSelectTimeListener) {
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
        attributes.height= DimenUtil.getScreenHeight()*2/3;
        attributes.gravity = Gravity.BOTTOM;
        this.getWindow().setAttributes(attributes);
        //dialog去除底部背景
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                TimeSelecteDialog.this.onSelectTimeListener.onSelectTime(year,month,dayOfMonth);
            }
        });
    }


    public interface OnSelectTimeListener {
      void onSelectTime(int year,int month,int date);
    }
}
