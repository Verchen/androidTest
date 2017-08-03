package com.example.android.cyk.Model;

/**
 * Created by qiao on 2017/8/2.
 */

public class UserInfroResp {
    private String code = "";
    private String message = "";
    private UserModel data = new UserModel();

    public static class UserModel{
        private String tel = "";
        private String reg_time = "";
        private String login_time = "";

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(UserModel data) {
        this.data = data;
    }

    public UserModel getData() {
        return data;
    }
}
