package com.example.android.cyk.Model;

/**
 * Created by qiao on 2017/7/31.
 */

public class BorrowDetailModel {
    private int code;
    private Detail data;
    private String message;

    public class Detail{
        private String back;
        private String cardCode;
        private int cardId;
        private int day;
        private int inAccountMoney;
        private int interest;
        private int repaymentAllMoney;
        private String repaymentTime;
        private int serviceMoney;

        public void setBack(String back){
            this.back = back;
        }
        public String getBack(){
            return this.back;
        }
        public void setCardCode(String cardCode){
            this.cardCode = cardCode;
        }
        public String getCardCode(){
            return this.cardCode;
        }
        public void setCardId(int cardId){
            this.cardId = cardId;
        }
        public int getCardId(){
            return this.cardId;
        }
        public void setDay(int day){
            this.day = day;
        }
        public int getDay(){
            return this.day;
        }
        public void setInAccountMoney(int inAccountMoney){
            this.inAccountMoney = inAccountMoney;
        }
        public int getInAccountMoney(){
            return this.inAccountMoney;
        }
        public void setInterest(int interest){
            this.interest = interest;
        }
        public int getInterest(){
            return this.interest;
        }
        public void setRepaymentAllMoney(int repaymentAllMoney){
            this.repaymentAllMoney = repaymentAllMoney;
        }
        public int getRepaymentAllMoney(){
            return this.repaymentAllMoney;
        }
        public void setRepaymentTime(String repaymentTime){
            this.repaymentTime = repaymentTime;
        }
        public String getRepaymentTime(){
            return this.repaymentTime;
        }
        public void setServiceMoney(int serviceMoney){
            this.serviceMoney = serviceMoney;
        }
        public int getServiceMoney(){
            return this.serviceMoney;
        }

    }

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }

    public void setData(Detail data) {
        this.data = data;
    }

    public Detail getData() {
        return data;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
