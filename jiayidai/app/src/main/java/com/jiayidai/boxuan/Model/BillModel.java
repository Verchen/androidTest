package com.jiayidai.boxuan.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao on 2017/8/1.
 */

public class BillModel {
    private int code ;
    private String message;
    private BillList data;

    public static class BillList{
        private int notYetMoney = 0;
        private int yetMoney = 0;
        private List<BillItem> notYetList = new ArrayList<BillItem>();

        public class BillItem{
            private String audit_time;
            private int day;
            private int id;
            private int interest;
            private String maturityTime;
            private String money;

            public String getAudit_time() {
                return audit_time;
            }

            public void setAudit_time(String audit_time) {
                this.audit_time = audit_time;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getDay() {
                return day;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }

            public void setInterest(int interest) {
                this.interest = interest;
            }

            public int getInterest() {
                return interest;
            }

            public String getMaturityTime() {
                return maturityTime;
            }

            public void setMaturityTime(String maturityTime) {
                this.maturityTime = maturityTime;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            @Override
            public String toString() {
                return "BillItem{" +
                        "audit_time='" + audit_time + '\'' +
                        ", day=" + day +
                        ", id=" + id +
                        ", interest=" + interest +
                        ", maturityTime='" + maturityTime + '\'' +
                        ", money='" + money + '\'' +
                        '}';
            }
        }

        public int getNotYetMoney() {
            return notYetMoney;
        }

        public void setNotYetMoney(int notYetMoney) {
            this.notYetMoney = notYetMoney;
        }

        public int getYetMoney() {
            return yetMoney;
        }

        public void setYetMoney(int yetMoney) {
            this.yetMoney = yetMoney;
        }

        public List<BillItem> getNotYetList() {
            return notYetList;
        }

        public void setNotYetList(List<BillItem> notYetList) {
            this.notYetList = notYetList;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(BillList data) {
        this.data = data;
    }

    public BillList getData() {
        return data;
    }
}

