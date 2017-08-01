package com.example.android.cyk.Model;

import java.util.List;

/**
 * Created by qiao on 2017/8/1.
 */

public class ProgressModel {
    private int code;
    private List<itemModel> data;
    private String message;

    public class itemModel{
        private int audit;
        private int day;
        private int inAccountMoney;
        private int interest;
        private int loan_id;
        private String money;
        private int serviceMoney;

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getInAccountMoney() {
            return inAccountMoney;
        }

        public void setInAccountMoney(int inAccountMoney) {
            this.inAccountMoney = inAccountMoney;
        }

        public int getInterest() {
            return interest;
        }

        public void setInterest(int interest) {
            this.interest = interest;
        }

        public int getLoan_id() {
            return loan_id;
        }

        public void setLoan_id(int loan_id) {
            this.loan_id = loan_id;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMoney() {
            return money;
        }

        public void setServiceMoney(int serviceMoney) {
            this.serviceMoney = serviceMoney;
        }

        public int getServiceMoney() {
            return serviceMoney;
        }

    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setData(List<itemModel> data) {
        this.data = data;
    }

    public List<itemModel> getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
