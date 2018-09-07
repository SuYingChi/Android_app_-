package com.msht.mshtlpgmaster.Bean;

public class AppInfoBean {

    /**
     * result : success
     * error : null
     * data : {"version":"100","title":"有新版本啦","desc":"有新版本了\\n有新版本了\\n有新版本了","url":"http:////msbapp.cn/do/download/d/minshengbao.apk"}
     */

    private String result;
    private Object error;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * version : 100
         * title : 有新版本啦
         * desc : 有新版本了\n有新版本了\n有新版本了
         * url : http:////msbapp.cn/do/download/d/minshengbao.apk
         */

        private String version;
        private String title;
        private String desc;
        private String url;
        private String apkSize;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setApkSize(String apkSize) {
            this.apkSize = apkSize;
        }

        public String getApkSize() {
            return apkSize;
        }
    }
}
