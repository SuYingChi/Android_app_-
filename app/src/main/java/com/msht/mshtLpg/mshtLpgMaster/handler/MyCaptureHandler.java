package com.msht.mshtLpg.mshtLpgMaster.handler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.msht.mshtLpg.mshtLpgMaster.fragment.BaseFragment;
import com.msht.mshtLpg.mshtLpgMaster.thread.MyDecodeThread;
import com.uuzuche.lib_zxing.camera.CameraManager;
import com.uuzuche.lib_zxing.decoding.DecodeThread;
import com.uuzuche.lib_zxing.view.ViewfinderResultPointCallback;
import com.uuzuche.lib_zxing.view.ViewfinderView;

import java.util.Vector;

public final class MyCaptureHandler extends Handler {

    private static final String TAG = com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.class.getSimpleName();

    private final BaseFragment fragment;
    private final MyDecodeThread decodeThread;

    public com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State getState() {
        return state;
    }


    private com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State state;




    public MyCaptureHandler(BaseFragment fragment, Vector<BarcodeFormat> decodeFormats,
                            String characterSet, ViewfinderView viewfinderView) {
        this.fragment = fragment;
        decodeThread = new MyDecodeThread(fragment, decodeFormats, characterSet,
                new ViewfinderResultPointCallback(viewfinderView));
        decodeThread.start();
        state = com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State.SUCCESS;
        // Start ourselves capturing previews and decoding.
        CameraManager.get().startPreview();
        Log.d(TAG, "创建 CaptureActivityHandler:,开启 MyDecodeThread,执行restartPreviewAndDecode");
        restartPreviewAndDecode();
    }

    @Override
    public void handleMessage(Message message) {
        if (message.what == com.uuzuche.lib_zxing.R.id.auto_focus) {
            Log.d(TAG, "handleMessage: auto_focus");
            //Log.d(TAG, "Got auto-focus message");
            // When one auto focus pass finishes, start another. This is the closest thing to
            // continuous AF. It does seem to hunt a bit, but I'm not sure what else to do.
            if (state == com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State.PREVIEW) {
                Log.d(TAG, "handleMessage: PREVIEW");
                CameraManager.get().requestAutoFocus(this, com.uuzuche.lib_zxing.R.id.auto_focus);
                Log.d(TAG, "handleMessage: CameraManager 请求焦点");
            }
        } else if (message.what == com.uuzuche.lib_zxing.R.id.restart_preview) {
            Log.d(TAG, "Got restart preview message");
            restartPreviewAndDecode();
        } else if (message.what == com.uuzuche.lib_zxing.R.id.decode_succeeded) {
            Log.d(TAG, "Got decode succeeded message");
            state = com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State.SUCCESS;
            Bundle bundle = message.getData();

            /***********************************************************************/
            Bitmap barcode = bundle == null ? null :
                    (Bitmap) bundle.getParcelable(DecodeThread.BARCODE_BITMAP);//���ñ����߳�

            fragment.handleDecode((Result) message.obj, barcode);//���ؽ��
            /***********************************************************************/
        } else if (message.what == com.uuzuche.lib_zxing.R.id.decode_failed) {
            // We're decoding as fast as possible, so when one decode fails, start another.
            state = com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State.PREVIEW;
            Log.d(TAG, "handleMessage: decode_failed ， 失败后主线程的CaptureActivityHandlerzai重新发送消息给子线程DecodeThread 的DecodeHandler执行decode");
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), com.uuzuche.lib_zxing.R.id.decode);
        } else if (message.what == com.uuzuche.lib_zxing.R.id.return_scan_result) {
            Log.d(TAG, "Got return scan result message");
            fragment.getActivity().setResult(Activity.RESULT_OK, (Intent) message.obj);
            fragment.getActivity().finish();
        } else if (message.what == com.uuzuche.lib_zxing.R.id.launch_product_query) {
            Log.d(TAG, "Got product query message");
            String url = (String) message.obj;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            fragment.getActivity().startActivity(intent);
        }
        else if(message.what == com.uuzuche.lib_zxing.R.id.redecode_after_decodeSuccess){
            state = com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State.PREVIEW;
            Log.d(TAG, "handleMessage: decode_failed ， 成功后主线程的CaptureActivityHandler重新发送消息给子线程DecodeThread 的DecodeHandler执行decode");
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), com.uuzuche.lib_zxing.R.id.decode);
        }
    }

    public void quitSynchronously() {
        state = com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State.DONE;
        CameraManager.get().stopPreview();
        Log.d(TAG, "quitSynchronously: 发送quit消息给DecodeHandler,终止decode循环操作");
        Message quit = Message.obtain(decodeThread.getHandler(), com.uuzuche.lib_zxing.R.id.quit);
        quit.sendToTarget();
        try {
            decodeThread.join();
        } catch (InterruptedException e) {
            // continue
        }

        // Be absolutely sure we don't send any queued up messages
        removeMessages(com.uuzuche.lib_zxing.R.id.decode_succeeded);
        removeMessages(com.uuzuche.lib_zxing.R.id.decode_failed);
    }

    private void restartPreviewAndDecode() {
        Log.d(TAG, "restartPreviewAndDecode: ");
        if (state == com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State.SUCCESS) {
            state = com.uuzuche.lib_zxing.decoding.CaptureActivityHandler.State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), com.uuzuche.lib_zxing.R.id.decode);
            Log.d(TAG, "restartPreviewAndDecode: 发送 decode message  给 decodeThread的decodeHandler，执行decode");
            CameraManager.get().requestAutoFocus(this, com.uuzuche.lib_zxing.R.id.auto_focus);
            Log.d(TAG, "restartPreviewAndDecode: CaptureActivityHandler 给自身发送auto_focas消息");
            Log.d(TAG, "restartPreviewAndDecode: 绘制Viewfinder");
            fragment.drawViewfinder();
        }
    }

}
