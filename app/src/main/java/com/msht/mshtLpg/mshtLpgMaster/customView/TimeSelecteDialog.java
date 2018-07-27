package com.msht.mshtLpg.mshtLpgMaster.customView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.msht.mshtLpg.mshtLpgMaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TimeSelecteDialog extends Dialog {
    @BindView(R.id.rcl_select_year)
    RecyclerView rclYear;
    @BindView(R.id.rcl_select_month)
    RecyclerView rclMonth;
    private Unbinder unbinder;

    public TimeSelecteDialog(Context context) {
        super(context,R.style.BottomAnimDialogStyle);
        getWindow().setGravity(Gravity.BOTTOM);//关键代码
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(TimeSelecteDialog.this.isShowing()) {
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
        unbinder =  ButterKnife.bind(this);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha=0.9f;
        getWindow().setAttributes(attributes);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }
}
