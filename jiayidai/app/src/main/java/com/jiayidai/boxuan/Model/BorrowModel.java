package com.jiayidai.boxuan.Model;

import java.util.List;

/**
 * Created by qiao on 2017/7/28.
 */

public class BorrowModel {
    private String code = "";
    private String message = "";
    private List<borrowItem> data;

    public class borrowItem {
        private String id = "";
        private String money = "";
        private String day = "";
        private String lv = "";
        private String sort = "";
        private String lock = "";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getLv() {
            return lv;
        }

        public void setLv(String lv) {
            this.lv = lv;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getSort() {
            return sort;
        }

        public String getLock() {
            return lock;
        }

        public void setLock(String lock) {
            this.lock = lock;
        }

        @Override
        public String toString() {
            return "borrowItem{" +
                    "id='" + id + '\'' +
                    ", money='" + money + '\'' +
                    ", day='" + day + '\'' +
                    ", lv='" + lv + '\'' +
                    ", sort='" + sort + '\'' +
                    ", lock='" + lock + '\'' +
                    '}';
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

    public List<borrowItem> getData() {
        return data;
    }

    public void setData(List<borrowItem> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BorrowModel{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
