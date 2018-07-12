package com.msht.mshtLpg.mshtLpgMaster.Present;

import android.text.TextUtils;

import com.msht.mshtLpg.mshtLpgMaster.Bean.ScanBottleQRCodeBean;
import com.msht.mshtLpg.mshtLpgMaster.callback.DataStringCallback;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;
import com.msht.mshtLpg.mshtLpgMaster.gsonInstance.GsonUtil;
import com.msht.mshtLpg.mshtLpgMaster.viewInterface.IScanCodeDeliverSteelBottleView;
import com.zhy.http.okhttp.OkHttpUtils;

public class IScanbottleCodePresenter {
    private  IScanCodeDeliverSteelBottleView iScanCodeDeliverSteelBottleView;

    public IScanbottleCodePresenter(IScanCodeDeliverSteelBottleView iScanCodeDeliverSteelBottleView) {
        this.iScanCodeDeliverSteelBottleView = iScanCodeDeliverSteelBottleView;
    }

    public void scanbottle() {
        OkHttpUtils.get().url(Constants.SCAN_BOTTLE_QR_CODE).addParams(Constants.URL_PARAMS_BOTTLE_CODE,iScanCodeDeliverSteelBottleView.getBottleId()+"").
                addParams(Constants.URL_PARAMS_VERIFYTYPE,"2").addParams(Constants.URL_PARAMS_LOGIN_TOKEN,iScanCodeDeliverSteelBottleView.getToken()).build().execute(new DataStringCallback(iScanCodeDeliverSteelBottleView) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                ScanBottleQRCodeBean bean = GsonUtil.getGson().fromJson(s, ScanBottleQRCodeBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "failed")) {
                    iScanCodeDeliverSteelBottleView.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {

                    iScanCodeDeliverSteelBottleView.onGetBottleInfoSuccess(bean);
                }
            }

        });
    }
}
