package jrh.library.common.widgets.recycleview.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * desc: recycleView Adapter使用 {@link "https://github.com/CymChad/BaseRecyclerViewAdapterHelper"}
 * Created by jiarh
 */

public abstract class CommonAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {

    protected List<T> mData;


    public CommonAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public CommonAdapter(@Nullable List<T> data) {
        super(data);
    }

    public CommonAdapter(int layoutResId) {
        super(layoutResId);
    }
}
