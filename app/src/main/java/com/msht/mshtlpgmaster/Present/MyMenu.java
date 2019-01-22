package com.msht.mshtlpgmaster.Present;

import android.text.TextUtils;
import android.view.Menu;

import com.google.gson.JsonSyntaxException;
import com.msht.mshtlpgmaster.Bean.ErrorBean;
import com.msht.mshtlpgmaster.Bean.IMyMenuBean;
import com.msht.mshtlpgmaster.callback.DataStringCallback;
import com.msht.mshtlpgmaster.constant.Constants;
import com.msht.mshtlpgmaster.gsonInstance.GsonUtil;
import com.msht.mshtlpgmaster.util.PopUtil;
import com.msht.mshtlpgmaster.util.SharePreferenceUtil;
import com.msht.mshtlpgmaster.viewInterface.IMyMenu;
import com.zhy.http.okhttp.OkHttpUtils;

public class MyMenu {

    public static  void getMyIncome(IMyMenu iMyMenu) {
        OkHttpUtils.get().url(Constants.MENU)
                .tag(iMyMenu)
                .addParams(Constants.URL_PARAMS_LOGIN_TOKEN, SharePreferenceUtil.getInstance().getToken()).
                build().execute(new DataStringCallback(iMyMenu) {
            @Override
            public void onResponse(String s, int i) {
                //先继承再重写或重写覆盖请求错误的场景
                super.onResponse(s, i);
                if (isResponseEmpty) {
                    iMyMenu.onError("接口返回空字符串:");
                    return;
                }
                ErrorBean bean = GsonUtil.getGson().fromJson(s, ErrorBean.class);
                if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "fail")) {
                    iMyMenu.onError(bean.getMsg());

                } else if (!TextUtils.isEmpty(bean.getResult()) && TextUtils.equals(bean.getResult(), "success")) {
                    try{
                        iMyMenu.onGetMyMenuSuccess(s);
                    }catch (JsonSyntaxException e){
                        PopUtil.toastInBottom("GSON转换异常");
                    }
                }
            }

        });

    }
}
