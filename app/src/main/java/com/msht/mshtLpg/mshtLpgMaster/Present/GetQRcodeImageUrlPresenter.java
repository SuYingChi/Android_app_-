package com.msht.mshtLpg.mshtLpgMaster.Present;

import com.msht.mshtLpg.mshtLpgMaster.Bean.GetPayQRCodeBean;
import com.msht.mshtLpg.mshtLpgMaster.Bean.GetPayQRErrorBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IGetPayQRcodeView;
import com.zhy.http.okhttp.OkHttpUtils;

public class GetQRcodeImageUrlPresenter {

    IGetPayQRcodeView iGetPayQRcodeView;

    public GetQRcodeImageUrlPresenter(IGetPayQRcodeView iGetPayQRcodeView) {
        this.iGetPayQRcodeView = iGetPayQRcodeView;
    }

    public void getQRcodeUrl() {
        OkHttpUtils.post().url(Constants.WEIXIN_PAY).addParams("orderId", iGetPayQRcodeView.getOrderId())
                .addParams("payType",iGetPayQRcodeView.getPayType()).addParams("body",iGetPayQRcodeView.getBody()).addParams("payAmount",iGetPayQRcodeView.getPayAmount()).build().execute(new DataStringCallback(iGetPayQRcodeView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (s.contains("success")) {
                    GetPayQRCodeBean bean = GsonUtil.getGson().fromJson(s, GetPayQRCodeBean.class);
                  iGetPayQRcodeView.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            iGetPayQRcodeView.onGetQRCodeImageURLSuccess(bean);
                        }
                    });

                } else if (s.contains("error")&&s.contains("生成二维码失败")) {
                  GetPayQRErrorBean bean= GsonUtil.getGson().fromJson(s,GetPayQRErrorBean.class);
                 /*   iGetPayQRcodeView.getHandler().post(new Runnable() {
                        @Override
                        public void run() {*/
                            iGetPayQRcodeView.onGetQRCodeImageURLError(bean);
                    /*    }
                    });*/
                }
            }

        });
    }
}
