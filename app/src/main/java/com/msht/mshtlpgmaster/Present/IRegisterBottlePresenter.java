package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.GetBottleInfo;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;
import com.msht.mshtlpgmaster.viewInterface.IRegisterBottleView;
import com.uuzuche.lib_zxing.decoding.Intents;
import com.zhy.http.okhttp.OkHttpUtils;

public class IRegisterBottlePresenter {

    private IRegisterBottleView iRegisterBottleView;


    public IRegisterBottlePresenter(IRegisterBottleView iRegisterBottleView) {
        this.iRegisterBottleView = iRegisterBottleView;
    }

    public void getBottleInfo() {
        OkHttpUtils.get().url(Constants.GET_BOOTLEINFO_BY_ID).tag(iRegisterBottleView).addParams(Constants.URL_PARAMS_BOTTLE_CODE, iRegisterBottleView.getBottleCode()).build().execute(new DataStringCallback(iRegisterBottleView) {
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
        OkHttpUtils.get().url(Constants.UPDATE_BOTTLE_INFO).tag(iRegisterBottleView).addParams(Constants.URL_PARAMS_BOTTLE_CODE, iRegisterBottleView.getBottleCode())
                .addParams("bottleNum", iRegisterBottleView.getBottleNum()).
                addParams("bottleWeight", iRegisterBottleView.getBottleWeight())
                .addParams("producer", iRegisterBottleView.getProducer())
                .addParams("propertyUnit", iRegisterBottleView.getPropertyUnit())
                .addParams("createTime", iRegisterBottleView.getCreateTime())
                .addParams("nextCheckTime", iRegisterBottleView.getNextCheckTime()).
                addParams("employeeId", SharePreferenceUtil.getLoginSpStringValue(Constants.EMPLOYERID)).
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
                    try{
                    iRegisterBottleView.onUpdateBottleInfoSuccess(bean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }
                }
            }

        });
    }

}
