package com.example.android.cyk.Model;

import java.util.List;

/**
 * Created by qiao on 2017/8/4.
 */

public class RelationResp {
    private String code = "";
    private String message = "";
    private List<RelationModel> data;

    public class RelationModel {
        private String id = "";
        private String name = "";
        private String sort = "";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getSort() {
            return sort;
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

    public void setData(List<RelationModel> data) {
        this.data = data;
    }

    public List<RelationModel> getData() {
        return data;
    }
}
