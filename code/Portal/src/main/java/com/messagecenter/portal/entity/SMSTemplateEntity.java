package com.messagecenter.portal.entity;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class SMSTemplateEntity {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public String getInUser() {
        return inUser;
    }

    public void setInUser(String inUser) {
        this.inUser = inUser;
    }

    private int id;
    private int businessType;
    private String inUser;
}
