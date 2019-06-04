package com.msht.mshtlpgmaster.customView;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.util.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelecteMapDialog extends Dialog {

    @BindView(R.id.baidu)
    TextView baidu;
    @BindView(R.id.gaode)
    TextView gaode;

    private OnSelectMapListener listener;

    public SelecteMapDialog(Context context, OnSelectMapListener listener) {
        super(context, R.style.BottomAnimDialogStyle);

        this.listener = listener;

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (SelecteMapDialog.this.isShowing()) {
                    SelecteMapDialog.this.dismiss();
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
        setContentView(R.layout.select_map_dialog);
        ButterKnife.bind(this);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        this.getWindow().setAttributes(attributes);
        baidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectBaidu();
            }
        });
        gaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectGaode();
            }
        });
    }







    public interface OnSelectMapListener {
        void onSelectGaode();

        void onSelectBaidu();
    }
}
