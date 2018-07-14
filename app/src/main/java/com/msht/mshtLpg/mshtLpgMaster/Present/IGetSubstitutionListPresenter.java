package com.msht.mshtLpg.mshtLpgMaster.Present;


import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.SubstitutionListBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IExchangeSteelBottleView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IGetSubstitutionListPresenter {

    IExchangeSteelBottleView iExchangeSteelBottleView;
    public IGetSubstitutionListPresenter(IExchangeSteelBottleView iExchangeSteelBottleView){
        this.iExchangeSteelBottleView = iExchangeSteelBottleView;
    }

    public void getSubstitutionList(){
        OkHttpUtils.get().url(Constants.SUBSTITUTION_LIST)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iExchangeSteelBottleView.getToken()).build().execute(new DataStringCallback(iExchangeSteelBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                SubstitutionListBean bean = GsonUtil.getGson().fromJson(s, SubstitutionListBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "failed")) {
                    iExchangeSteelBottleView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iExchangeSteelBottleView.onGetSubstitutionListSuccess(bean);
                }
            }

        });
    }
}
