package com.msht.mshtlpgmaster.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.msht.mshtlpgmaster.R;
import com.msht.mshtlpgmaster.util.DimenUtil;

public class TopBarView extends RelativeLayout {
    private ImageView titleBarLeftBtn;
    private TextView titleBarTitle;

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.comman_topbar, this, true);
        setPadding(0, getStateBarHeight()+DimenUtil.dip2px(5),0,DimenUtil.dip2px(5));
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
    private int  getStateBarHeight(){
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
           return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public void setLeftBtnClickListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            titleBarLeftBtn.setOnClickListener(onClickListener);
        }
    }

    public void setTitle(String title) {
        titleBarTitle.setText(title);
    }

    public ImageView getTitleBarLeftBtn() {
        return titleBarLeftBtn;
    }


    public TextView getTitleBarTitle() {
        return titleBarTitle;
    }

}