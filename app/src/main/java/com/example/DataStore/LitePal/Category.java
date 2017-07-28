package com.example.DataStore.LitePal;

import org.litepal.crud.DataSupport;

/**
 * Created by lester.ding on 7/28/2017.
 */

public class Category extends DataSupport{

    private int id, categoryCode;
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
