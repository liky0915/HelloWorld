package com.example.Bean;

/**
 * Created by lester.ding on 8/2/2017.
 */

public class ContactBean {

    public int contactImageResId;
    public String contactName, contactNumber;

    public ContactBean(int contactImageResId, String contactName, String contactNumber) {
        this.contactImageResId = contactImageResId;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public int getContactImageResId() {
        return contactImageResId;
    }

    public void setContactImageResId(int contactImageResId) {
        this.contactImageResId = contactImageResId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
