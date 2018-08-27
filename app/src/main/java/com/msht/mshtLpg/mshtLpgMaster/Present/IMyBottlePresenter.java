package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.MyBottleListBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IMyBottleView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IMyBottlePresenter {
    private  IMyBottleView iMyBottleView;

    public IMyBottlePresenter(IMyBottleView iMyBottleView) {
        this.iMyBottleView = iMyBottleView;
    }

    public void getMyBottleList() {
        OkHttpUtils.get().url(Constants.MY_BOTTLE_LIST)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iMyBottleView.getToken()).
                build().execute(new DataStringCallback(iMyBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if(isResponseEmpty){
                    iMyBottleView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iMyBottleView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    MyBottleListBean myBottleListBean = GsonUtil.getGson().fromJson(s, MyBottleListBean.class);
                    iMyBottleView.onGetMyBottleListSuccess(myBottleListBean);
                }
            }

        });
    }
}
