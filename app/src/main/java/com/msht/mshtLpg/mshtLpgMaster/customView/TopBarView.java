package com.msht.mshtLpg.mshtLpgMaster.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.msht.mshtLpg.mshtLpgMaster.R;

public class TopBarView extends RelativeLayout {
    private ImageView titleBarLeftBtn;
    private TextView titleBarTitle;

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.comman_topbar, this, true);
        titleBarLeftBtn = (ImageView) findViewById(R.id.return_btn);
        titleBarTitle = (TextView) findViewById(R.id.tv_comman_topbar_title);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.topBar);
        if (attributes != null) {
                //如果不是图片标题 则获取文字标题
                String titleText = attributes.getString(R.styleable.topBar_title_text);
                if (!TextUtils.isEmpty(titleText)) {
                    titleBarTitle.setText(titleText);
                }
            attributes.recycle();
            }
        }


    public void setLeftBtnClickListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            titleBarLeftBtn.setOnClickListener(onClickListener);
        }
    }
    public void setTitle(String title){
        titleBarTitle.setText(title);
    }
    public ImageView getTitleBarLeftBtn() {
        return titleBarLeftBtn;
    }


    public TextView getTitleBarTitle() {
        return titleBarTitle;
    }

}