package jrh.library.common.widgets.viewpager;

import android.content.Context;
import android.database.DataSetObserver;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:无限循环
 * Created by jiarh on 2018/8/18 10:17.
 */

public class LooperViewPager extends LockViewPager {


    public LooperViewPager(Context context) {
        super(context);
        init();
    }

    public LooperViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        super.addOnPageChangeListener(new InnerOnPageChangeListener());
    }

    private InnerPagerAdapter mInnerAdapter;

    @Override
    public void setAdapter(PagerAdapter adapter) {
        mInnerAdapter = new InnerPagerAdapter(adapter);
        super.setAdapter(mInnerAdapter);
        super.setCurrentItem(1);
    }

    @Override
    public void setCurrentItem(int position) {
        if (position >= mInnerAdapter.getCount()) {
            throw new IndexOutOfBoundsException("Index is " + position + " , max is " + mInnerAdapter.getCount());
        }
        super.setCurrentItem(position + 1);
    }


    private List<OnPageChangeListener> mOnPageChangeListeners = new ArrayList<>();

    @Override
    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.mOnPageChangeListeners.add(listener);
    }

    @Override
    public void removeOnPageChangeListener(OnPageChangeListener listener) {
        if (mOnPageChangeListeners != null) {
            mOnPageChangeListeners.remove(listener);
        }
    }

    private class InnerOnPageChangeListener implements OnPageChangeListener {
        private int position;

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (position == mInnerAdapter.getCount() - 1) {
                    LooperViewPager.super.setCurrentItem(1, false);
                } else if (position == 0) {
                    LooperViewPager.super.setCurrentItem(mInnerAdapter.getCount() - 2, false);
                }
            }
            if (mOnPageChangeListeners != null && mOnPageChangeListeners.size() > 0) {
                for (OnPageChangeListener mPageChangeListener : mOnPageChangeListeners) {
                    mPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            if (mOnPageChangeListeners != null && mOnPageChangeListeners.size() > 0) {
                for (OnPageChangeListener mPageChangeListener : mOnPageChangeListeners) {
                    mPageChangeListener.onPageScrolled(arg0, arg1, arg2);
                }
            }
        }

        @Override
        public void onPageSelected(int curPosition) {
            position = curPosition;
            if (mOnPageChangeListeners != null && mOnPageChangeListeners.size() > 0) {
                for (OnPageChangeListener mPageChangeListener : mOnPageChangeListeners) {
                    //最前面一个和最后面一个不能走onPageSelected
                    if (position != 0 && position != mInnerAdapter.getCount() - 1) {
                        mPageChangeListener.onPageSelected(position - 1);
                    }
                }
            }
        }
    }

    private class InnerPagerAdapter extends PagerAdapter {

        private PagerAdapter mOuterAdapter;

        InnerPagerAdapter(PagerAdapter adapter) {
            this.mOuterAdapter = adapter;
            adapter.registerDataSetObserver(new DataSetObserver() {

                @Override
                public void onChanged() {
                    notifyDataSetChanged();
                }

                @Override
                public void onInvalidated() {
                    notifyDataSetChanged();
                }

            });
        }

        @Override
        public int getCount() {
            return mOuterAdapter.getCount() + 2;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return mOuterAdapter.isViewFromObject(arg0, arg1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (position == 0) {
                position = mOuterAdapter.getCount() - 1;
            } else if (position == mOuterAdapter.getCount() + 1) {
                position = 0;
            } else {
                position -= 1;
            }
            return mOuterAdapter.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            mOuterAdapter.destroyItem(container, position, object);
        }

    }
}
