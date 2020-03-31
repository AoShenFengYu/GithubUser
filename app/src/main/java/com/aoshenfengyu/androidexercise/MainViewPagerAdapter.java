package com.aoshenfengyu.androidexercise;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.aoshenfengyu.androidexercise.fragment.AllUsersFragment;
import com.aoshenfengyu.androidexercise.fragment.MyInfoFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<CharSequence> mTitles = new ArrayList<>();
    private final SparseArray<Fragment> mFragments = new SparseArray<>();

    private final static int POSITION_FRAGMENT_ALL_USERS = 0;
    private final static int POSITION_FRAGMENT_MY_INFO = 1;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mTitles.add(POSITION_FRAGMENT_ALL_USERS, "All users");
        mTitles.add(POSITION_FRAGMENT_MY_INFO, "My info");
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POSITION_FRAGMENT_ALL_USERS:
                return AllUsersFragment.Companion.newInstance();
            case POSITION_FRAGMENT_MY_INFO:
                return MyInfoFragment.Companion.newInstance();
        }
        throw new RuntimeException("Unknown position " + position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public String getPageTitle(int position) {
        return mTitles.get(position).toString();
    }

    @NotNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Fragment fragment =
                (Fragment) super.instantiateItem(container, position);
        mFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object obj) {
        super.destroyItem(container, position, obj);
        mFragments.remove(position);
    }

    public Fragment getFragment(int position) {
        return mFragments.get(position);
    }
}
