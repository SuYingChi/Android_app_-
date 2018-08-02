package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.MyBottleListBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.MyIncomeBean;
import com.msht.mshtLpg.mshtLpgMaster.activity.MyIncomeActivity;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IMyIncomeView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IMyIncomePresenter {
    private  IMyIncomeView iMyIncomeView;

    public IMyIncomePresenter(IMyIncomeView iMyIncomeView) {
        this.iMyIncomeView = iMyIncomeView;
    }

    public void getMyIncome(){
        OkHttpUtils.get().url(Constants.MY_INCOME)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iMyIncomeView.getToken()).
            build().execute(new DataStringCallback(iMyIncomeView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iMyIncomeView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    MyIncomeBean myIncomeBean = GsonUtil.getGson().fromJson(s, MyIncomeBean.class);
                    iMyIncomeView.onGetMyIncomeSuccess(myIncomeBean);
                }
            }

        });

    }
}
