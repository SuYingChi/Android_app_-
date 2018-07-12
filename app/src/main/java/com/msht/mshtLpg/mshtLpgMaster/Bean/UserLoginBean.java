package com.msht.mshtLpg.mshtLpgMaster.Bean;

public class UserLoginBean {


    /**
     * result : success
     * msg : 登录成功
     * data : {"loginToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJwYXNzd29yZCI6ImUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlIiwiaXNzIjoiU2VydmljZSIsIm1vYmlsZSI6IjE4NTk5OTg4ODg4IiwiZXhwIjoxODQ2Mzc5NTkzLCJpYXQiOjE1MzA3NjAzOTN9.2nV1ARc0a-LSW93ETI9igypKFfb3nH5rIkQHkvtCy8s","errorCode":"200","employeeId":6}
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
         * loginToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJwYXNzd29yZCI6ImUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlIiwiaXNzIjoiU2VydmljZSIsIm1vYmlsZSI6IjE4NTk5OTg4ODg4IiwiZXhwIjoxODQ2Mzc5NTkzLCJpYXQiOjE1MzA3NjAzOTN9.2nV1ARc0a-LSW93ETI9igypKFfb3nH5rIkQHkvtCy8s
         * errorCode : 200
         * employeeId : 6
         */

        private String loginToken;
        private String errorCode;
        private int employeeId;

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

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }
    }
}
