package com.msht.mshtyLpg.mshtLpgUser.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.msht.mshtyLpg.mshtLpgUser.R;
import com.msht.mshtyLpg.mshtLpgUser.customView.LPGRefreshHeader;
import com.msht.mshtyLpg.mshtLpgUser.netRequestPresent.MLoggerInterceptor;
import com.msht.mshtyLpg.mshtLpgUser.sharepreferenceUtil.SharePreferenceUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import okhttp3.OkHttpClient;

public class LPGApplication extends Application {

    private static LPGApplication instance;
    private SharePreferenceUtil spu;
    static {

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new LPGRefreshHeader(context);
            }
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                ClassicsFooter footer = new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
                footer.setDrawableArrowSize(14);//设置箭头的大小（dp单位）
                footer.setDrawableProgressSize(14);//设置图片的大小（dp单位）、
                footer.setProgressDrawable(context.getResources().getDrawable(R.drawable.loading));
                footer.setAccentColor(context.getResources().getColor(R.color.text_hint));//设置强调颜色
                footer.setTextSizeTitle(14);
                footer.setFinishDuration(0);
                return footer;
            }
        });


    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        BGASwipeBackHelper.init(this, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MLoggerInterceptor("http", true))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)

                .build();

        OkHttpUtils.initClient(okHttpClient);
//        oList = new ArrayList<>();
        spu = SharePreferenceUtil.getSharePreferenceUtilInstance();
    }


    public static LPGApplication getLPGApplication() {

        return instance;

    }
}
