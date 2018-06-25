package com.msht.mshtLpg.mshtLpgMaster.Bean;

public class UserLoginBean {


    /**
     * result : success
     * msg : 登录成功
     * data : {"loginToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJwYXNzd29yZCI6ImUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlIiwiaXNzIjoiU2VydmljZSIsIm1vYmlsZSI6IjE4NTk5OTg4ODg4IiwiZXhwIjoxODQ1NTE1MDkyLCJpYXQiOjE1Mjk4OTU4OTJ9.iRu7vTULPhbus5JAze44W4QiOQ1qyOPI8yjsnR8LbTM","errorCode":"200"}
     */

    private String result;
    private String msg;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * loginToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJwYXNzd29yZCI6ImUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlIiwiaXNzIjoiU2VydmljZSIsIm1vYmlsZSI6IjE4NTk5OTg4ODg4IiwiZXhwIjoxODQ1NTE1MDkyLCJpYXQiOjE1Mjk4OTU4OTJ9.iRu7vTULPhbus5JAze44W4QiOQ1qyOPI8yjsnR8LbTM
         * errorCode : 200
         */

        private String loginToken;
        private String errorCode;

        public String getLoginToken() {
            return loginToken;
        }

        public void setLoginToken(String loginToken) {
            this.loginToken = loginToken;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
    }
}
