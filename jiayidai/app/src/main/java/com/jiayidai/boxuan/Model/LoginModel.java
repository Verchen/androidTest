package com.jiayidai.boxuan.Model;

/**
 * Created by qiao on 2017/8/2.
 */

public class LoginModel {

    private String code = "";
    private String message = "";
    private userIdModel data = new userIdModel();

    public class userIdModel{
        private String id = "";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public userIdModel getData() {
        return data;
    }

    public void setData(userIdModel data) {
        this.data = data;
    }
}
