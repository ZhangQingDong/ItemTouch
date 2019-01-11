package com.example.zqd.itemtouch;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyItemTouchCallBack extends ItemTouchHelper.Callback {

    private List<ItemBean> itemBeanList = new ArrayList<>();

    public MyItemTouchCallBack(List<ItemBean> itemBeanList) {
        this.itemBeanList = itemBeanList;
    }

    /**
     * 首先说一下getMovementFlags（），这个方法是设置是否滑动时间，以及拖拽的方向，
     * 所以在这里需要判断一下是列表布局还是网格布局，如果是列表布局的话则拖拽方向为DOWN和UP，如果是网格布局的话则是DOWN和UP和LEFT和RIGHT
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        /**
         * 得到当拖拽的viewHolder的Position
         */
        int fromPosition = viewHolder.getAdapterPosition();
        /**
         * 拿到当前拖拽到的item的viewHolder
         */
        int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(itemBeanList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(itemBeanList, i, i - 1);
            }
        }
        recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }
}
