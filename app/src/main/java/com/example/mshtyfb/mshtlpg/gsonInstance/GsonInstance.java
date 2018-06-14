package com.example.mshtyfb.mshtlpg.gsonInstance;

import com.google.gson.Gson;

public class GsonInstance {
    private static GsonInstance gsonInstance ;
    private  Gson gson;

    private GsonInstance(){
        gson = new Gson();
    }

    public static GsonInstance getGsonInstance() {
       synchronized (GsonInstance.class) {
           if (gsonInstance == null) {
               gsonInstance = new GsonInstance();
           }
       }
        return gsonInstance;
    }

    public Gson getGson() {
        return gson;
    }
}
