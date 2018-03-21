package com.example.wxj.my_recyclerview;
/*下拉刷新，上拉加载*/
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public abstract class  MenuOnScrollListener extends RecyclerView.OnScrollListener {
    private int lastVisibleItem = -1;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            /*返回最后一个可见视图的适配器位置。*/
        }
    }

    /*   回滚方法在RecyclerView被滚动时被调用。这将在滚动完成后调用。
        如果在布局计算后可见项目范围发生更改，则也会调用此回调。在这种情况下，dx和dy将为0。
        参数
        recyclerView	滚动的RecyclerView。
        DX	水平滚动量。
        DY	垂直滚动量。
    */
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recyclerView
                .getAdapter().getItemCount()){
            lodemore();
        }
    }
    /*
             回调方法在RecyclerView的滚动状态改变时被调用。
             参数
             recyclerView	RecyclerView的滚动状态已更改。
             newState	更新后的滚动状态。其中之一SCROLL_STATE_IDLE（RecyclerView目前不在滚动），
              SCROLL_STATE_DRAGGING（RecyclerView目前正在被外部输入如用户触摸输入拖动）
              或SCROLL_STATE_SETTLING（RecyclerView目前正在动画到最终位置，而不受外界控制）
        */
    public abstract void lodemore() ;

}
