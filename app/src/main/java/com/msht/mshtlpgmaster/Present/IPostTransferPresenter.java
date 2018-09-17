package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.Bean.PostTransferBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IPostTransferView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IPostTransferPresenter {
    private IPostTransferView iPostTransferView;

    public IPostTransferPresenter(IPostTransferView iPostTransferView) {
        this.iPostTransferView = iPostTransferView;
    }

    public void postTransferOrders() {
        OkHttpUtils.get().url(iPostTransferView.getUrl()).addParams("fiveCount", iPostTransferView.getFiveCount()).
                addParams("fifteenCount", iPostTransferView.getFifteenCount())
                .addParams("fifthCount", iPostTransferView.getFiftyCount())
                .addParams("trackNumber", iPostTransferView.carNum())
                .addParams("transferType", iPostTransferView.getTransferType())
                .addParams("siteId", iPostTransferView.getSiteId())
                .addParams("bottleIds", iPostTransferView.getBottleIds())
                .addParams("transferId", iPostTransferView.getTransferId())
                .addParams("stationId", iPostTransferView.getStationId())
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iPostTransferView.getToken()).build().execute(new DataStringCallback(iPostTransferView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                ErrorBean errorBean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (isResponseEmpty) {
                    iPostTransferView.onError("接口返回空字符串:");
                    return;
                }
                if (!TextUtils.isEmpty(errorBean.getResult()) && TextUtils.equals(errorBean.getResult(), "fail")) {
                    iPostTransferView.onError(errorBean.getMsg());

                } else if (!TextUtils.isEmpty(errorBean.getResult()) && TextUtils.equals(errorBean.getResult(), "success")) {
                    try{
                    PostTransferBean bean = GsonUtil.getGson().fromJson(s, PostTransferBean.class);
                    iPostTransferView.onPostTransfersuccess(bean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }
                }
            }

        });
    }

}
