package com.msht.mshtLpg.mshtLpgMaster.customView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.util.DimenUtil;

/**
 * Demo class
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yingchi
 * @date 2018/7/2  
 */
public class DeliverFareDialog extends Dialog {

    private TextView tvDimiss;

    public DeliverFareDialog(Context context) {
        super(context,R.style.deliver_fare_dialog);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(DeliverFareDialog.this.isShowing()) {
                    DeliverFareDialog.this.dismiss();
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
        setContentView(R.layout.deliver_fee_layout);
        tvDimiss= (TextView)findViewById(R.id.dismiss_dialog);
        tvDimiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DeliverFareDialog.this.isShowing()) {
                    DeliverFareDialog.this.dismiss();
                }
            }
        });
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = DimenUtil.getScreenWidth();
        getWindow().setAttributes(attributes);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }
}
