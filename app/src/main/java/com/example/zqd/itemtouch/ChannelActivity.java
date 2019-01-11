package com.example.zqd.itemtouch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ChannelActivity extends AppCompatActivity implements View.OnClickListener, SelectAdapter.OnAction, UnSelectAdapter.OnAction {

    private TextView tv_edit_save;
    private RecyclerView rv_select;
    private RecyclerView rv_un_select;

    private List<ItemBean> itemBeans = new ArrayList<>();
    private List<ItemBean> selectData = new ArrayList<>();
    private List<ItemBean> unSelectData = new ArrayList<>();

    private SelectAdapter selectAdapter;
    private UnSelectAdapter unSelectAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        initView();
        initData();
        initRecyclerView();
    }

    /**
     * setting list
     */
    private void initRecyclerView() {
        selectData.clear();
        unSelectData.clear();
        for (int i = 0; i < itemBeans.size(); i++) {
            if (itemBeans.get(i).isStatus()) {
                selectData.add(itemBeans.get(i));
            } else {
                unSelectData.add(itemBeans.get(i));
            }
        }
        if (selectAdapter == null) {
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchCallBack(selectData));
            itemTouchHelper.attachToRecyclerView(rv_select);
            selectAdapter = new SelectAdapter(R.layout.item_view, selectData, this, itemTouchHelper, this);
            rv_select.setAdapter(selectAdapter);
        } else {
            selectAdapter.notifyDataSetChanged();
        }
        if (unSelectAdapter == null) {
            unSelectAdapter = new UnSelectAdapter(R.layout.item_view, unSelectData, this, this);
            rv_un_select.setAdapter(unSelectAdapter);
        } else {
            unSelectAdapter.notifyDataSetChanged();
        }


    }

    /**
     * create data
     */
    private void initData() {
        /**
         * check data exist
         */
        if (SPUtil.init("data").getString("channel").isEmpty()) {
            List<ItemBean> initData = new ArrayList<>();
            initData.add(new ItemBean("军事", true));
            initData.add(new ItemBean("娱乐", true));
            initData.add(new ItemBean("交通", true));
            initData.add(new ItemBean("国际", true));
            initData.add(new ItemBean("地方", true));
            initData.add(new ItemBean("历史", true));
            initData.add(new ItemBean("科技", true));
            initData.add(new ItemBean("赛车", true));
            initData.add(new ItemBean("篮球", true));
            initData.add(new ItemBean("足球", true));
            initData.add(new ItemBean("排球", true));
            initData.add(new ItemBean("八卦", true));
            initData.add(new ItemBean("政治", true));
            initData.add(new ItemBean("星座", true));
            initData.add(new ItemBean("两性", true));
            initData.add(new ItemBean("生活", true));
            initData.add(new ItemBean("台湾", true));
            initData.add(new ItemBean("两地", false));
            initData.add(new ItemBean("要闻", false));
            initData.add(new ItemBean("数码", false));
            SPUtil.init("data").putString("channel", new Gson().toJson(initData));
        }
        Gson gson = new Gson();
        itemBeans = gson.fromJson(SPUtil.init("data").getString("channel"), new TypeToken<List<ItemBean>>() {
        }.getType());
    }

    /**
     * init views
     */
    private void initView() {
        tv_edit_save = findViewById(R.id.tv_edit_save);
        tv_edit_save.setOnClickListener(this);
        rv_select = findViewById(R.id.rv_select);
        rv_select.setLayoutManager(new GridLayoutManager(this, 4));
        rv_un_select = findViewById(R.id.rv_un_select);
        rv_un_select.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit_save:
                if (TextUtils.equals(tv_edit_save.getText().toString().trim(), "编辑")) {
                    tv_edit_save.setText("保存");
                    selectAdapter.setEdit(true);
                    unSelectAdapter.setEdit(true);
                    selectAdapter.notifyDataSetChanged();
                } else if (TextUtils.equals(tv_edit_save.getText().toString().trim(), "保存")) {
                    tv_edit_save.setText("编辑");
                    selectAdapter.setEdit(false);
                    unSelectAdapter.setEdit(false);
                    selectAdapter.notifyDataSetChanged();
                    save();
                }
                break;
            default:
                break;
        }
    }

    /**
     * save data in sharepreference
     */
    private void save() {
        itemBeans.clear();
        itemBeans.addAll(selectData);
        itemBeans.addAll(unSelectData);
        SPUtil.init("data").putString("channel", new Gson().toJson(itemBeans));
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doCancle(String name) {
        for (int i = 0; i < itemBeans.size(); i++) {
            if (TextUtils.equals(name, itemBeans.get(i).getName())) {
                itemBeans.get(i).setStatus(false);
                initRecyclerView();
            }
        }
    }

    @Override
    public void doWarning() {
        Toast.makeText(this, "至少保留一个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSelect(String name) {
        for (int i = 0; i < itemBeans.size(); i++) {
            if (TextUtils.equals(name, itemBeans.get(i).getName())) {
                itemBeans.get(i).setStatus(true);
                initRecyclerView();
            }
        }
    }
}
