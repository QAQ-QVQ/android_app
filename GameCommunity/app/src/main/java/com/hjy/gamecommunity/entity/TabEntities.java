package com.hjy.gamecommunity.entity;

/**
 * Author: zhangqingyou
 * Date: 2020/4/9 10:26
 * Des:
 */
public class TabEntities {
    private String tabTitle;
    private String tabTag;
    private Object tabSelectImg;
    private Object tabUnselectImg;

    public TabEntities(String tabTitle, String tabTag, Object tabSelectImg, Object tabUnselectImg) {
        this.tabTitle = tabTitle;
        this.tabTag = tabTag;
        this.tabSelectImg = tabSelectImg;
        this.tabUnselectImg = tabUnselectImg;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String getTabTag() {
        return tabTag;
    }

    public void setTabTag(String tabTag) {
        this.tabTag = tabTag;
    }

    public Object getTabSelectImg() {
        return tabSelectImg;
    }

    public void setTabSelectImg(Object tabSelectImg) {
        this.tabSelectImg = tabSelectImg;
    }

    public Object getTabUnselectImg() {
        return tabUnselectImg;
    }

    public void setTabUnselectImg(Object tabUnselectImg) {
        this.tabUnselectImg = tabUnselectImg;
    }
}
