package com.msht.mshtlpgmaster.Bean;

import android.os.Parcelable;

import java.io.Serializable;

public class SaveFileBean implements Serializable{

    public float getProgress() {
        return progress;
    }

    private final float progress;

    public long getTotal() {
        return total;
    }

    private final long total;

    public SaveFileBean(float v, long total) {
        progress = v;
        this.total = total;
    }
}
