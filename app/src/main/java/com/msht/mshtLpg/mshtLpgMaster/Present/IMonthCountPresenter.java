package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.MonthCountBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.MyIncomeBean;
import com.msht.mshtLpg.mshtLpgMaster.activity.MonthCountActivity;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IMonthCountView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IMonthCountPresenter {
    private  IMonthCountView iMonthCountView;

    public IMonthCountPresenter(IMonthCountView iMonthCountView) {
        this.iMonthCountView = iMonthCountView;
    }
    public void getMonthCount(){
        OkHttpUtils.get().url(Constants.MONTH_COUNT)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iMonthCountView.getToken()).
                build().execute(new DataStringCallback(iMonthCountView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iMonthCountView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    MonthCountBean monthCountBean = GsonUtil.getGson().fromJson(s, MonthCountBean.class);
                    iMonthCountView.onGetMonthCountSuccess(monthCountBean);
                }
            }

        });
    }
}
