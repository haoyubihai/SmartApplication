package jrh.library.common.widgets.viewpager.adapter;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import jrh.library.common.utils.ListUtil;

/**
 * 通用FragmentPagerAdapter
 *
 */
public class CommonVpAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragments;

    public CommonVpAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (ListUtil.isNotEmpty(fragments)){
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (ListUtil.isNotEmpty(fragments))
            return fragments.size();
        return 0;
    }

    private int mChildCount = 0;

    @Override public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
