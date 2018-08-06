package com.msht.mshtLpg.mshtLpgMaster.customView;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.SendBottleOrdersDetailPostActivity;
import com.msht.mshtLpg.mshtLpgMaster.util.DimenUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IDeliveryView;

import java.util.Map;

/**
 * Demo class
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yingchi
 * @date 2018/7/2  
 */
public class DeliverFareDialog extends Dialog {

    private  Context context;
    private  Map<String, String> map;
    private TextView tvDimiss;

    public DeliverFareDialog(Context context,Map<String,String> map) {
        super(context,R.style.deliver_fare_dialog);
        this.map = map;
        this.context = context;

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
        TextView first_five = (TextView) findViewById(R.id.first_five);
        TextView first_fifteen = (TextView) findViewById(R.id.first_fifteen);
        TextView first_fifty = (TextView) findViewById(R.id.first_fifty);
        TextView four_five = (TextView) findViewById(R.id.four_five);
        TextView four_fifteen = (TextView) findViewById(R.id.four_fifteen);
        TextView four_fifty = (TextView) findViewById(R.id.four_fifty);
        TextView six_five = (TextView) findViewById(R.id.six_five);
        TextView six_fifteen = (TextView) findViewById(R.id.six_fifteen);
        TextView six_fifty = (TextView) findViewById(R.id.six_fifty);
        first_five.setText(map.get("first5"));
        first_fifteen.setText(map.get("first15"));
        first_fifty.setText(map.get("first50"));
        four_five.setText(map.get("four5"));
        four_fifteen.setText(map.get("four15"));
        four_fifty.setText(map.get("four50"));
        six_five.setText(map.get("six5"));
        six_fifteen.setText(map.get("six15"));
        six_fifty.setText(map.get("six50"));

        tvDimiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DeliverFareDialog.this.isShowing()) {
                    DeliverFareDialog.this.dismiss();
                }
            }
        });
        WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
        attributes.width= DimenUtil.getScreenWidth()-DimenUtil.dip2px(context.getResources().getDimension(R.dimen.margin_horizontal)*2);
        this.getWindow().setAttributes(attributes);
        //dialog去除底部北京
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }


}
