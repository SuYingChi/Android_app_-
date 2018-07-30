package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.TransferStorageListBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.UpdateTransferBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.ITransferToStorageView;
import com.zhy.http.okhttp.OkHttpUtils;

public class ITransferToStorageListPresenter {
    private  ITransferToStorageView iTransferToStorageView;

    public ITransferToStorageListPresenter(ITransferToStorageView iTransferToStorageView) {
        this.iTransferToStorageView = iTransferToStorageView;
    }

   public void getTransferOrdersList(){
       OkHttpUtils.get().url(Constants.GET_TAANSFER_LIST).addParams("siteId", iTransferToStorageView.getSiteId())
               .addParams("state",iTransferToStorageView.getState())
               .addParams("pageNum",iTransferToStorageView.getPageNum())
               .addParams("pageSize",iTransferToStorageView.getPageSize())
               .addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iTransferToStorageView.getToken())
               .build().execute(new DataStringCallback(iTransferToStorageView) {
           @Override
           public void onResponse(String s, int i) {
               //先继承再重写或重写覆盖请求错误的场景
               super.onResponse(s, i);
               ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
               if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                   iTransferToStorageView.onError(bean.getMsg());

               } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                   TransferStorageListBean transferStorageListBean = GsonUtil.getGson().fromJson(s, TransferStorageListBean.class);
                   iTransferToStorageView.onGetListSuccess(transferStorageListBean);
               }
           }

       });
    }
    public void updateTransfer(){
        OkHttpUtils.get().url(Constants.UPDATE_TRANSFER).addParams("id", iTransferToStorageView.getId())
                .addParams("fiveCount",iTransferToStorageView.getFiveCount())
                .addParams("fifteenCount",iTransferToStorageView.getFifteenCount())
                .addParams("fifthCount",iTransferToStorageView.getFifthCount())
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iTransferToStorageView.getToken())
                .build().execute(new DataStringCallback(iTransferToStorageView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iTransferToStorageView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    UpdateTransferBean updateTransferBean = GsonUtil.getGson().fromJson(s, UpdateTransferBean.class);
                    iTransferToStorageView.onUpdateTransferSuccess(updateTransferBean);
                }
            }

        });
    }
}
