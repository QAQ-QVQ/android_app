package com.hjy.baseui.bean;

import com.flyco.tablayout.listener.CustomTabEntity;
/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class TabEntity implements CustomTabEntity {
    private String title;
    private int selectedIcon;
    private int unSelectedIcon;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
