package com.hjy.gamecommunity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/11 11:39
 * 描述: Fragment Adapter
 */
public class FragmentStatePageAdapter extends FragmentStatePagerAdapter {
    private FragmentManager fm;
    private List<String> mTitles;
    private List<Fragment> mFragments;
    private boolean isDestroyItem;

    public FragmentStatePageAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.fm = fm;
        this.mFragments = mFragments;

    }

    public FragmentStatePageAdapter(FragmentManager fm, List<String> mTitles, List<Fragment> mFragments) {
        super(fm);
        this.fm = fm;
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }

    public void setDestroyItem(boolean destroyItem) {
        isDestroyItem = destroyItem;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null)
            return mTitles.get(position);
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (isDestroyItem) {
            FragmentTransaction fragmentTransaction;
            if (this.fm == null) {
                fragmentTransaction = this.fm.beginTransaction();
                fragmentTransaction.remove((Fragment) object);
            }
        }


    }
}
