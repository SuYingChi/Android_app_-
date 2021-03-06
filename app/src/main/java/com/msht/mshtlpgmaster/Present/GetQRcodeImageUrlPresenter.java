package com.msht.mshtlpgmaster.Present;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.GetPayQRCodeBean;
import com.msht.mshtlpgmaster.Bean.GetPayQRErrorBean;
import com.msht.mshtlpgmaster.Bean.WxcodePayErroBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.JsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.viewInterface.IGetPayQRcodeView;
import com.zhy.http.okhttp.OkHttpUtils;

public class GetQRcodeImageUrlPresenter {

    IGetPayQRcodeView iGetPayQRcodeView;

    public GetQRcodeImageUrlPresenter(IGetPayQRcodeView iGetPayQRcodeView) {
        this.iGetPayQRcodeView = iGetPayQRcodeView;
    }

    public void getQRcodeUrl() {
        OkHttpUtils.post().url(Constants.WEIXIN_PAY).addParams("orderId", iGetPayQRcodeView.getOrderId()).tag(iGetPayQRcodeView)
                .addParams("payType", iGetPayQRcodeView.getPayType()).addParams("body", iGetPayQRcodeView.getBody()).addParams("payAmount", iGetPayQRcodeView.getPayAmount()).build().execute(new DataStringCallback(iGetPayQRcodeView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iGetPayQRcodeView.onError("接口返回空字符串:");
                    return;
                }
                if (JsonUtil.isJsonHasKey(s,"msg")) {
                    try{
                    GetPayQRCodeBean bean = GsonUtil.getGson().fromJson(s, GetPayQRCodeBean.class);
                    iGetPayQRcodeView.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            iGetPayQRcodeView.onGetQRCodeImageURLSuccess(bean);
                        }
                    });
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }


            } else if (JsonUtil.isJsonHasKey(s,"error")) {
                    try{
                    WxcodePayErroBean bean = GsonUtil.getGson().fromJson(s, WxcodePayErroBean.class);
                    iGetPayQRcodeView.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                    iGetPayQRcodeView.onGetQRCodeImageURLError(bean);
                       }
                    });
                }catch (JsonSyntaxException e){
                    PopUtil.toastInBottom("GSON转换异常");
                }

            }
            }

        });
    }
    public void getQRcodeUrlNew() {
        OkHttpUtils.post().url(Constants.NEW_WEIXIN_PAY).addParams("id", iGetPayQRcodeView.getOrderId()).tag(iGetPayQRcodeView)
              .build().execute(new DataStringCallback(iGetPayQRcodeView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iGetPayQRcodeView.onError("接口返回空字符串:");
                    return;
                }
                if (JsonUtil.isJsonHasKey(s,"msg")) {
                    try{
                        GetPayQRCodeBean bean = GsonUtil.getGson().fromJson(s, GetPayQRCodeBean.class);
                        iGetPayQRcodeView.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                iGetPayQRcodeView.onGetQRCodeImageURLSuccess(bean);
                            }
                        });
                    }catch (JsonSyntaxException e){
                        PopUtil.toastInBottom("GSON转换异常");
                    }


                } else if (JsonUtil.isJsonHasKey(s,"error")) {
                    try{
                        WxcodePayErroBean bean = GsonUtil.getGson().fromJson(s, WxcodePayErroBean.class);
                        iGetPayQRcodeView.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                iGetPayQRcodeView.onGetQRCodeImageURLError(bean);
                            }
                        });
                    }catch (JsonSyntaxException e){
                        PopUtil.toastInBottom("GSON转换异常");
                    }

                }
            }

        });
    }
}
