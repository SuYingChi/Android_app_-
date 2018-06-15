package com.msht.mshtLpg.mshtLpgMaster.gsonInstance;

import com.google.gson.Gson;

public class GsonInstance {
    private static volatile GsonInstance gsonInstance ;
    private  Gson gson;

    private GsonInstance(){
        gson = new Gson();
    }

    public static GsonInstance getGsonInstance() {
        if(gsonInstance == null) {
            synchronized (GsonInstance.class) {
                if (gsonInstance == null) {
                    gsonInstance = new GsonInstance();
                }
            }
        }
        return gsonInstance;
    }

    public Gson getGson() {
        return gson;
    }
}
