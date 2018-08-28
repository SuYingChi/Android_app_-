package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.GetBottleInfo;
import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IRegisterBottleView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IRegisterBottlePresenter {

    private IRegisterBottleView iRegisterBottleView;


    public IRegisterBottlePresenter(IRegisterBottleView iRegisterBottleView) {
        this.iRegisterBottleView = iRegisterBottleView;
    }

    public void getBottleInfo() {
        OkHttpUtils.get().url(Constants.GET_BOOTLEINFO_BY_ID).addParams(Constants.URL_PARAMS_BOTTLE_CODE, iRegisterBottleView.getBottleCode()).build().execute(new DataStringCallback(iRegisterBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iRegisterBottleView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iRegisterBottleView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    GetBottleInfo getBottleInfoBean = GsonUtil.getGson().fromJson(s, GetBottleInfo.class);
                    iRegisterBottleView.onGetBottleInfoSuccess(getBottleInfoBean);
                }
            }

        });
    }

    public void update_bottle_info() {
        OkHttpUtils.get().url(Constants.UPDATE_BOTTLE_INFO).addParams(Constants.URL_PARAMS_BOTTLE_CODE, iRegisterBottleView.getBottleCode())
                .addParams("bottleNum", iRegisterBottleView.getBottleNum()).
                addParams("bottleWeight", iRegisterBottleView.getBottleWeight())
                .addParams("producer", iRegisterBottleView.getProducer())
                .addParams("propertyUnit", iRegisterBottleView.getPropertyUnit())
                .addParams("createTime", iRegisterBottleView.getCreateTime())
                .addParams("nextCheckTime", iRegisterBottleView.getNextCheckTime()).
                build().execute(new DataStringCallback(iRegisterBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                String result = s;
                if (isResponseEmpty) {
                    iRegisterBottleView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iRegisterBottleView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    iRegisterBottleView.onUpdateBottleInfoSuccess(bean);
                }
            }

        });
    }

}
