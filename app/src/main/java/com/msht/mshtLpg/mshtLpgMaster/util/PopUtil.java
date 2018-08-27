package com.msht.mshtLpg.mshtLpgMaster.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.activity.LoginActivity;
import com.msht.mshtLpg.mshtLpgMaster.application.LPGApplication;
import com.msht.mshtLpg.mshtLpgMaster.customView.LoadingDialog;

public class PopUtil {

    private static LoadingDialog centerLoadingDialog;
    private static Toast toast;
    private static Dialog topLoadingDialog;

    public static PopupWindow showPopWindow(Context context, View anchorView, boolean bottom) {

        if (TextUtils.isEmpty(SharePreferenceUtil.getInstance().getToken())) {
            toastInBottom(R.string.please_login);
            Intent goLogin = new Intent(context, LoginActivity.class);
            context.startActivity(goLogin);
            return null;
        }


        View popupView = LayoutInflater.from(context).inflate(R.layout.layout_popupwindow, null);

        PopupWindow window = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        window.setAnimationStyle(R.style.popup_window_anim);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setElevation(20);
        }

        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));

        window.setFocusable(true);

        window.setOutsideTouchable(true);

        window.update();

        //这些参数到时再自己调整
        if (bottom) {
            int windowPos[] = calculatePopWindowPos(anchorView, popupView);
            int xOff = 20; // 可以自己调整偏移
            windowPos[0] -= xOff;
            windowPos[1] -= xOff;
            window.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
        } else {
            anchorView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

            int mShowMorePopupWindowWidth = (int) (-anchorView.getMeasuredWidth() * 2.2);

            window.showAsDropDown(anchorView, mShowMorePopupWindowWidth, 0);
        }
        return window;
    }
    public static void toastInBottom(int stringResourceId) {

            if (toast == null) {
                toast = Toast.makeText(LPGApplication.getLPGApplicationContext(), LPGApplication.getLPGApplicationContext().getString(stringResourceId), Toast.LENGTH_LONG);
            } else {
                toast.setText(LPGApplication.getLPGApplicationContext().getString(stringResourceId));
            }
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 300);
            toast.show();
        }
    public static void toastInBottom(String string) {

        if (toast == null) {
            toast = Toast.makeText(LPGApplication.getLPGApplicationContext(), string, Toast.LENGTH_LONG);
        } else {
            toast.setText(string);
        }
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 300);
        toast.show();
    }
    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     * @param anchorView  呼出window的view
     * @param contentView   window的内容布局
     * @return popwindow显示的左上角的xOff,yOff坐标
     */
    public static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        final int screenHeight = DimenUtil.getScreenHeight();
        final int screenWidth = DimenUtil.getScreenWidth();
        // 测量contentView
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }
    public static void showTopLoadingDialog(Context context) {
        if(topLoadingDialog==null){
            topLoadingDialog = new Dialog(context, R.style.Dialog_Fullscreen);
            topLoadingDialog.setContentView(R.layout.empty_loading_top);
        }else if(context!=null&&!centerLoadingDialog.isShowing()){
            topLoadingDialog.show();
        }
    }

    public static void hideTopLoadingDialog(Context context) {
        if(topLoadingDialog !=null&& topLoadingDialog.isShowing()&&context!=null){
            topLoadingDialog.dismiss();
        }
    }

    public static void showDialog(Context context,Dialog dialog){
       if(context!=null&&dialog!=null&&!dialog.isShowing()){
            topLoadingDialog.show();
        }
    }

    public static void hideDialog(Context context,Dialog dialog){
        if(dialog !=null&& dialog.isShowing()&&context!=null){
            dialog.dismiss();
        }
    }
    public static  void hideInput(Context context, EditText et) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }
    public static void showTipsDialog(Context mContext, String title, String tips, String left, String right,
                                      final View.OnClickListener onCancel,
                                      final View.OnClickListener onOK) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(
                R.layout.dialog_tips, null);
        final AlertDialog tel_dialog = new AlertDialog.Builder(mContext).create();

        TextView tvTitle = (TextView) layout.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        }

        TextView tvtips = (TextView)layout.findViewById(R.id.tv_delete_tips);
        tvtips.setText(tips);
        tel_dialog.show();
        tel_dialog.getWindow().setContentView(layout);
        TextView btnCancel = (TextView)layout.findViewById(R.id.dialog_btn_cancel);
        btnCancel.setText(left);
        TextView btnOk = (TextView)layout.findViewById(R.id.dialog_btn_ok);
        btnOk.setText(right);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onCancel != null)
                    onCancel.onClick(v);

            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
                if (onOK != null)
                    onOK.onClick(v);

            }
        });
    }
    public static void showExchangeBottleTipsDialog(Context mContext) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(
                R.layout.exchange_tips, null);
        final AlertDialog tel_dialog = new AlertDialog.Builder(mContext).create();
        tel_dialog.show();
        tel_dialog.getWindow().setContentView(layout);
        TextView btnOk = (TextView)layout.findViewById(R.id.dialog_btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel_dialog.dismiss();
            }
        });
    }
}
