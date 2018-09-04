package com.msht.mshtLpg.mshtLpgMaster.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import com.msht.mshtLpg.mshtLpgMaster.Bean.SaveFileBean;
import com.msht.mshtLpg.mshtLpgMaster.R;
import com.msht.mshtLpg.mshtLpgMaster.constant.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

public class SaveFileThread extends Thread {

    private final Response response;
    private final Handler mainThreadHandler;
    private String destFileDir;
    private String destFileName;

    SaveFileThread(String destFileDir, String destFileName, Response response, Handler mainThreadHandler){
        this.response= response;
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        this.mainThreadHandler = mainThreadHandler;
    }

    @Override
    public void run() {
        super.run();
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();

            long sum = 0;

            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                Message message = mainThreadHandler.obtainMessage(R.id.send_save_file_progress );
                Bundle b = new Bundle();
                b.putSerializable("MyObject", new SaveFileBean(finalSum * 1.0f / total, total));
                message.setData(b);
                mainThreadHandler.sendMessage(message);
            }
            fos.flush();
            Message message = mainThreadHandler.obtainMessage(R.id.finish_save_file_progress,file);
            mainThreadHandler.sendMessage(message);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally

        {
            try {
                response.body().close();
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }

        }
    }
}
