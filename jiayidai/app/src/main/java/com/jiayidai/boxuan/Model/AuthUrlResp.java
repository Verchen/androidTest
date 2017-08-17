package com.jiayidai.boxuan.Model;

/**
 * Created by qiao on 2017/8/16.
 */

public class AuthUrlResp {

    /**
     * code : 200
     * message : 成功
     * data : {"redirectUrl":"https://tianji.rong360.com/tianjiwapreport/login?data=nD%2BRaNTUBXKJrM1QPpIetBQfspOHuuJJWD6%2By5Qrsz8t83KI%2BqCRvnwJKBtSiyNoYwJLZnAUyfs9Tm0V%2Fl0srkzyBvKtdN6n%2FkxGzHWxzs%2Fupw%2BvOV4op3%2F5FtRmu3ekf5FlR3Jqk9qxdIpDPYamws54pXJKZ2uS4Bzd4t7ZJUlmkRyqxLKehw3g3MRgaP6W6fn4JE2FuM%2BmpBcc2r4R4Ic%2B0uQMOLHvVZzD5KezKzI%3D"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AuthUrlResp{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * redirectUrl : https://tianji.rong360.com/tianjiwapreport/login?data=nD%2BRaNTUBXKJrM1QPpIetBQfspOHuuJJWD6%2By5Qrsz8t83KI%2BqCRvnwJKBtSiyNoYwJLZnAUyfs9Tm0V%2Fl0srkzyBvKtdN6n%2FkxGzHWxzs%2Fupw%2BvOV4op3%2F5FtRmu3ekf5FlR3Jqk9qxdIpDPYamws54pXJKZ2uS4Bzd4t7ZJUlmkRyqxLKehw3g3MRgaP6W6fn4JE2FuM%2BmpBcc2r4R4Ic%2B0uQMOLHvVZzD5KezKzI%3D
         */

        private String redirectUrl;

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "redirectUrl='" + redirectUrl + '\'' +
                    '}';
        }
    }

}
