package com.msht.mshtLpg.mshtLpgMaster.customView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliveryDetailDialog extends Dialog {

    @BindView(R.id.dismiss_dialog)
    TextView tvDismiss;
    public DeliveryDetailDialog(Context context) {
        super(context, R.style.Loading_dialog);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(DeliveryDetailDialog.this.isShowing()) {
                    DeliveryDetailDialog.this.dismiss();
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
        ButterKnife.bind(this);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha=0.9f;
        getWindow().setAttributes(attributes);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        tvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DeliveryDetailDialog.this.isShowing()) {
                    DeliveryDetailDialog.this.dismiss();
                }
            }
        });
    }
}
