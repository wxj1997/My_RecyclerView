package com.example.wxj.my_recyclerview;
/*添加下划线*/
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class MenuItemDecoration extends RecyclerView.ItemDecoration {
    private float height=5;
    private Paint mPaint=new Paint();
    private Context mContext;
    /*有参构造方法*/
    public MenuItemDecoration(Context mContext){
        this.mContext=mContext;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        /*第一个ItemView不需要在上面绘制分割线*/
        if(parent.getChildAdapterPosition(view)!=0){
            outRect.top=5;
        }
    }
    /*检索给定项目的任何偏移量。每个字段outRect指定项目视图应插入的像素数量，类似于填充或边距。默认实
      现将outRect的边界设置为0并返回.如果此ItemDecoration不影响项目视图的位置，则应outRect在返回之前将
      （左侧，顶部，右侧，底部）的所有四个字段设置为零.如果您需要访问Adapter以获取其他数据，则可以调用
      getChildAdapterPosition(View)以获取View的适配器位置
          参数
          outRect	      直接接收输出。
          view         子视图进行装饰
          parent	     RecyclerView此ItemDecoration是装饰
          state	     RecyclerView的当前状态。
          */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount=parent.getChildCount();
        for (int i=0; i<childCount; i++){
            View view=parent.getChildAt(i);/*返回组中指定位置的视图*/
            int index=parent.getChildAdapterPosition(view);/*返回给定子视图对应的适配器位置*/
            /*第一个ItemView不需要绘制*/
            if (index==0){
                continue;
            }
            float top=view.getTop()-height;
            float left=parent.getPaddingLeft();
            float right=parent.getWidth()-view.getPaddingRight();
            float bottom=view.getTop();
            if (i%2==0){
                mPaint.setColor(Color.YELLOW);
        }else
                mPaint.setColor(Color.RED);
            c.drawRect(left,top,right,bottom,mPaint);
    }


}/*在提供给RecyclerView的Canvas中绘制任何适当的装饰。
任何由此方法绘制的内容都将在绘制项目视图之前绘制，并因此出现在视图下方。
参数
c	Canvas to draw into
parent	RecyclerView this ItemDecoration is drawing into
state	The current state of RecyclerView
*/
}
