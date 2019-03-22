package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.Bean.TransferStorageListBean;
import com.msht.mshtlpgmaster.Bean.UpdateTransferBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.ITransferToStorageView;
import com.zhy.http.okhttp.OkHttpUtils;

public class ITransferToStorageListPresenter {
    private ITransferToStorageView iTransferToStorageView;

    public ITransferToStorageListPresenter(ITransferToStorageView iTransferToStorageView) {
        this.iTransferToStorageView = iTransferToStorageView;
    }

    public void getTransferOrdersList() {
        OkHttpUtils.get().url(Constants.GET_TAANSFER_LIST).addParams("siteId", iTransferToStorageView.getSiteId())
                .addParams("state", iTransferToStorageView.getState())
                .addParams("pageNum", iTransferToStorageView.getPageNum())
                .addParams("pageSize", iTransferToStorageView.getPageSize())
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iTransferToStorageView.getToken())
                .tag(iTransferToStorageView)
                .build().execute(new DataStringCallback(iTransferToStorageView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iTransferToStorageView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iTransferToStorageView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    try{
                    TransferStorageListBean transferStorageListBean = GsonUtil.getGson().fromJson(s, TransferStorageListBean.class);
                    iTransferToStorageView.onGetListSuccess(transferStorageListBean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }
                }
            }

        });
    }

    public void updateTransfer() {
        OkHttpUtils.get().url(Constants.UPDATE_TRANSFER).addParams("id", iTransferToStorageView.getId())
                .addParams("fiveCount", iTransferToStorageView.getFiveCount())
                .addParams("fifteenCount", iTransferToStorageView.getFifteenCount())
                .addParams("fifthCount", iTransferToStorageView.getFifthCount())
                .addParams("fiveFullCount", iTransferToStorageView.getFiveFullCount())
                .addParams("fifteenFullCount", iTransferToStorageView.getFifteenFullCount())
                .addParams("fiftyFullCount", iTransferToStorageView.getFifthFullCount())
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, iTransferToStorageView.getToken())
                .build().execute(new DataStringCallback(iTransferToStorageView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iTransferToStorageView.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iTransferToStorageView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    try{
                    UpdateTransferBean updateTransferBean = GsonUtil.getGson().fromJson(s, UpdateTransferBean.class);
                    iTransferToStorageView.onUpdateTransferSuccess(updateTransferBean);
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }
                }
            }

        });
    }
}
