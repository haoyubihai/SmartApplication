package jrh.library.common.widgets.tab;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import aly.library.common.base.R;
import jrh.library.common.widgets.recycleview.adapter.CommonAdapter;

public class TabListSegment extends LinearLayout {

    RecyclerView recyclerView;

    List<ItemTab> itemTabs = new ArrayList<>();
    ItemAdapter itemAdapter;
    private Context mContext;
    public TabListSegment(Context context) {
        this(context,null);
    }

    public TabListSegment(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabListSegment(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inflate(context, R.layout.tab_list_fragment,this);
        recyclerView = findViewById(R.id.tabList);
        itemAdapter = new ItemAdapter(itemTabs);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener((adapter, view, position) -> {

            setCurrent(position);

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(adapter, view, position);
            }
        });

    }

    public void setCurrent(int position) {
        for (ItemTab itemTab : itemTabs) {
            itemTab.checked = false;
        }
        itemTabs.get(position).checked = true;
        itemAdapter.setNewData(itemTabs);
    }

    public void setItemTabs(List<ItemTab> itemTabs) {
        this.itemTabs = itemTabs;
        itemAdapter.setNewData(itemTabs);
    }

    BaseQuickAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    class ItemAdapter extends CommonAdapter<ItemTab> {

        TextView tvTitle;
        View lineView;

        public ItemAdapter(@Nullable List<ItemTab> data) {
            super(R.layout.item_tab,data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, ItemTab item) {
            tvTitle = helper.getView(R.id.tabTv);
            lineView = helper.getView(R.id.btLine);
            tvTitle.setText(item.name);
            tvTitle.setTextSize(item.checked?20f:16f);
            tvTitle.setTypeface(Typeface.defaultFromStyle(item.checked?Typeface.BOLD:Typeface.NORMAL));
            tvTitle.setTextColor(item.checked? item.selectedColor:item.normalColor);
            lineView.setVisibility(item.checked?View.VISIBLE:View.INVISIBLE);

        }
    }

}


