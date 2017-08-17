package com.jiayidai.boxuan.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao on 2017/8/4.
 */

public class ContactResp {
    private String code = "";
    private String message = "";
    private List<ContactModel> data = new ArrayList<>();

    public class ContactModel{
        private String add_time = "";
        private String id = "";
        private String name = "";
        private String relation_id = "";
        private String tel = "";
        private String user_id = "";

        public String getAddTime() {
            return add_time;
        }

        public void setAddTime(String addTime) {
            this.add_time = addTime;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRelationId() {
            return relation_id;
        }

        public void setRelationId(String relationId) {
            this.relation_id = relationId;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTel() {
            return tel;
        }

        public String getUserId() {
            return user_id;
        }

        public void setUserId(String userId) {
            this.user_id = userId;
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

    public void setData(List<ContactModel> data) {
        this.data = data;
    }

    public List<ContactModel> getData() {
        return data;
    }

}
