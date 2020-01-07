package jrh.library.common.widgets.viewpager.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import jrh.library.common.base.BaseFragment;
import jrh.library.common.utils.ListUtil;

/**
 * 通用FragmentPagerAdapter
 *
 */
public class LooperVpAdapter extends FragmentPagerAdapter {
    List<BaseFragment> fragments;

    public LooperVpAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (ListUtil.isNotEmpty(fragments)){
            position %= fragments.size();
            return fragments.get(position);
        }
        return null;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
