package com.example.zqd.itemtouch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SelectAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {

    private OnAction onAction;
    private Context context;
    private boolean isEdit = false;
    private List<ItemBean> data;
    private ItemTouchHelper itemTouchHelper;

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public SelectAdapter(int layoutResId, @Nullable List<ItemBean> data, Context context, ItemTouchHelper itemTouchHelper, OnAction onAction) {
        super(layoutResId, data);
        this.context = context;
        this.data = data;
        this.onAction = onAction;
        this.itemTouchHelper = itemTouchHelper;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ItemBean item) {
        helper.setText(R.id.tv_title, item.getName());
        if (isEdit) {
            helper.setGone(R.id.iv_cancle, true);
        } else {
            helper.setGone(R.id.iv_cancle, false);
        }
        helper.getView(R.id.iv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size() > 1) {
                    onAction.doCancle(item.getName());
                } else {
                    onAction.doWarning();
                }
            }
        });
        helper.getView(R.id.all).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isEdit) {
                    itemTouchHelper.startDrag(helper);
                    return true;
                }
                return false;
            }
        });
    }

    interface OnAction {
        void doCancle(String name);

        void doWarning();
    }
}
