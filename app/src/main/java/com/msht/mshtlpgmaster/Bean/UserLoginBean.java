package com.msht.mshtlpgmaster.Bean;

public class UserLoginBean {

    /**
     * result : success
     * msg : 登录成功
     * data : {"employeeName":"张三","loginToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJwYXNzd29yZCI6ImUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlIiwiaXNzIjoiU2VydmljZSIsIm1vYmlsZSI6IjE4Nzc5ODg0NjQzIiwiZXhwIjoxODQ3NzYxMDk5LCJpYXQiOjE1MzIxNDE4OTl9.-cQ_AqvB0XU0rgxYt8REbxImTK09azUOez4n0EpOZ-A","errorCode":"200","siteId":2,"siteName":"海师站2","employeeId":28}
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
         * employeeName : 张三
         * loginToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJwYXNzd29yZCI6ImUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlIiwiaXNzIjoiU2VydmljZSIsIm1vYmlsZSI6IjE4Nzc5ODg0NjQzIiwiZXhwIjoxODQ3NzYxMDk5LCJpYXQiOjE1MzIxNDE4OTl9.-cQ_AqvB0XU0rgxYt8REbxImTK09azUOez4n0EpOZ-A
         * errorCode : 200
         * siteId : 2
         * siteName : 海师站2
         * employeeId : 28
         */

        private String employeeName;
        private String loginToken;
        private String errorCode;
        private int siteId;
        private String siteName;
        private int employeeId;

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

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

        public int getSiteId() {
            return siteId;
        }

        public void setSiteId(int siteId) {
            this.siteId = siteId;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }
    }
}
