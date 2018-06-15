package com.msht.mshtLpg.mshtLpgMaster.dialogUtil;

import android.content.Context;
import com.msht.mshtLpg.mshtLpgMaster.customView.LoadingDialog;

public class DialogUtil {

    private static LoadingDialog loadingDialog;

    public static void showLodingDialog(Context context) {
        if(loadingDialog==null){
            loadingDialog = new LoadingDialog(context);
        }
        if(context!=null&&!loadingDialog.isShowing()){
            loadingDialog.show();
        }
    }


    public static void hideLoadingDialog(Context context){
        if(loadingDialog!=null&&loadingDialog.isShowing()&&context!=null){
           loadingDialog.dismiss();
        }
    }
}
