package com.example.Bean;

/**
 * Created by lester.ding on 6/15/2017.
 */

public class ItemBean {

    public int ItemImageResid;
    public String ItemTitle, ItemContent;

    //此类作用封装数据，作为数据与布局一一对应的数据模型基础
    public ItemBean(int itemImageResid, String itemTitle, String itemContent) {
        ItemImageResid = itemImageResid;
        ItemTitle = itemTitle;
        ItemContent = itemContent;
    }

    public int getItemImageResid() {
        return ItemImageResid;
    }

    public void setItemImageResid(int itemImageResid) {
        ItemImageResid = itemImageResid;
    }

    public String getItemTitle() {
        return ItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        ItemTitle = itemTitle;
    }

    public String getItemContent() {
        return ItemContent;
    }

    public void setItemContent(String itemContent) {
        ItemContent = itemContent;
    }
}
