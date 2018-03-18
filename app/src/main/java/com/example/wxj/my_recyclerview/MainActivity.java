package com.example.wxj.my_recyclerview;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    /*多线程*/
    private Handler mHandler=new Handler();
    /*recyclerview.Adapter显示*/
    private RecyclerView recyclerView;
    private List<Map<String, Object>> mDataList;
    private MenuListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
   /*SwipeRefershLayout上拉下拉*/
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         /*1.recyclerview.Adapter显示*/
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.menu_list);
        mAdapter=new MenuListAdapter(this,mDataList);/*通过MenuListAdapter类中的构造方法
        创建MenuListAdapter的对象并赋值*/
        mLinearLayoutManager=new LinearLayoutManager(this);/*线性布局*/
        recyclerView.setLayoutManager(mLinearLayoutManager);/*设置布局方式*/
        recyclerView.setAdapter(mAdapter);

        /*2.SwipeRefershLayout上拉下拉*/
        mSwipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.layout_swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        /*2.1下拉加载*/
        recyclerView.addOnScrollListener(new MenuOnScrollListener() {
            @Override
            public void lodemore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Map<String,Object> map=new HashMap<>();
                        map.put("menu_image",R.drawable.x);
                        map.put("menu_title","zzzz");
                        map.put("menu_info","zz");
                        mDataList.add(map);
                        mAdapter.notifyDataSetChanged();/*数据重新加载完成后，提示数据发生改变，
                        并且设置现在不在刷新*/
                        recyclerView.smoothScrollToPosition(mDataList.size()-1);
                        /*开始平滑滚动到适配器位置
                        Parameters
                        position	The adapter position to scroll to
                        */
                    }
                },1000);
            }
        });
        /*3.添加下划线，对MenuItemDecoration类进行实例化*/
        recyclerView.addItemDecoration(new MenuItemDecoration(this));
    }

    private void initData() {
        int[] image = {R.drawable.gongbaojiding, R.drawable.shuizhuroupian,
                R.drawable.xihucuyu, R.drawable.yuxiangrousi,
                R.drawable.suanlajidantang};
        String[] title = {"宫保鸡丁", "水煮肉片", "西湖醋鱼", "鱼香肉丝", "酸辣鸡蛋汤"};
        String[] content = {
                "宫保鸡丁，是一道闻名中外的特色传统名菜。鲁菜、川菜、贵州菜中都有收录，原料、做法有差别。该菜式的起源与鲁菜中的酱爆鸡丁，和贵州菜的胡辣子鸡丁有关，后被清朝山东巡抚、四川总督丁宝桢改良发扬，形成了一道新菜式——宫保鸡丁，并流传至今，此道菜也被归纳为北京宫廷菜。之后宫保鸡丁也流传到国外。",
                "水煮肉片是一道地方新创名菜，起源于自贡，发扬于西南，属于川菜中著名的家常菜。其起源于上世纪30年代， 自贡名厨范吉安(1887 -1982年)" +
                        "，创新出风味突出的水煮肉片。因肉片未经划油，以水煮熟故名水煮肉片。",
                "西湖醋鱼别名为叔嫂传珍，宋嫂鱼，是浙江杭州饭店的一道传统地方风味名菜。",
                "鱼香肉丝（英文名：Stir-fried Pork Strips in Fish " +
                        "Sauce）是一道特色传统名菜，以鱼香调味而得名，属川菜。相传灵感来自老菜泡椒肉丝，民国年间由四川籍厨师创制而成。",
                "酸辣鸡蛋汤是一款简单的汤类美食，主要原料有猪肉50克、胡萝卜、竹笋各50克等。逢年过节，或寒冬腊月，煮上一锅热腾腾的酸辣鸡蛋汤，暖身提神，回味无穷"};

        mDataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("menu_image", image[i]);
            map.put("menu_title", title[i]);
            map.put("menu_info", content[i]);
            mDataList.add(map);
        }
    }
/*2.2上拉刷新*/
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //清空原始数据
                recyclerView.removeAllViews();
                mDataList.clear();
                for (int i = 0; i < 10; i++) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("menu_image", R.drawable.w);
                    map.put("menu_title", "aaaa");
                    map.put("menu_info", "aa");
                    mDataList.add(map);
                }
                mAdapter.notifyDataSetChanged();/*数据重新加载完成后，
                 提示数据发生改变，并且设置现在不在刷新*/
                mSwipeRefreshLayout.setRefreshing(false);
            }
        },1000);
    }
}
