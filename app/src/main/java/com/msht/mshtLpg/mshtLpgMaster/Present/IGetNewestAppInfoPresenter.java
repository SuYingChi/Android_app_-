package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.AppInfoBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IUpdateVersionView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IGetNewestAppInfoPresenter {

    private  IUpdateVersionView iUpdateVersionView;

    public IGetNewestAppInfoPresenter(IUpdateVersionView iUpdateVersionView){
        this.iUpdateVersionView = iUpdateVersionView;

    }

    public void getNewestAppInfo(){
        OkHttpUtils.get().url(Constants.GET_NEWESTAPPINFO).addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iUpdateVersionView.getToken()).build().execute(new DataStringCallback(iUpdateVersionView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if ((!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail"))) {
                    iUpdateVersionView.onError(bean.getMsg());
                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    AppInfoBean appInfoBean = GsonUtil.getGson().fromJson(s, AppInfoBean.class);
                    iUpdateVersionView.onGetNewestAppInfoSuccess(appInfoBean);
                }
            }
        });
    }
}
