package com.example.wxj.my_recyclerview;
/*显示RecyclerView适配器视图*/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;


public class MenuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Map<String, Object>> mDataList;
    //两个final int类型表示ViewType的两种类型

    private final int ITEM_TYPE = 0;
    private final int FOOT_TYPE = 1;

    //*有参构造方法，对类进行初始化*/
    public MenuListAdapter(Context context, List<Map<String, Object>> mDataList) {
        this.context = context;
        this.mDataList = mDataList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_view, parent, false);/*inflate()从指定的xml资源中扩充新的视图层次结构*/

        View footView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.foot_view, parent, false);
        if (viewType == FOOT_TYPE)//?
            return new ViewHolder(footView);//调用有参构造
        else return new ViewHolder(itemView);
    }
    /*viewType	The view type of the new View.*/


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        if (getItemViewType(position) == FOOT_TYPE) {
            viewHolder.tvFootView.setText("加载中...");
        } else {
            viewHolder.menu_image.setImageResource((int) mDataList.get(position).get("menu_image"));
            viewHolder.menu_title.setText((String) mDataList.get(position).get("menu_title"));
            viewHolder.menu_info.setText((String) mDataList.get(position).get("menu_info"));
        }
    }

    /*此方法由RecyclerView调用以在指定位置显示数据。此方法应更新itemView的内容以反映给定位置处的项目。*/
    /*position	The position of the item within the adapter's data set.*/
    @Override
    public int getItemCount() {

        return mDataList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mDataList.size()) {
            return FOOT_TYPE;
        } else
            return ITEM_TYPE;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        /*声明控件对象*/
        ImageView menu_image;
        TextView menu_title;
        TextView menu_info;
        TextView tvFootView;

        public ViewHolder(View itemView) {
            super(itemView);//调用父类有参构造方法
        /*获取控件对象*/
            menu_image = (ImageView) itemView.findViewById(R.id.menu_image);
            menu_title = (TextView) itemView.findViewById(R.id.menu_title);
            menu_info = (TextView) itemView.findViewById(R.id.menu_info);
            tvFootView = (TextView) itemView.findViewById(R.id.tv_foot_view);
        }
    }
}
/*思路：1.在public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
方法中中通过LayoutInflater.from(parent.getContext()).inflate获取两个xml文件中的资源
2.通过ViewHolder类获取两个xml中的控件对象
3.在public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)方法中
中通过VIewHolder设置各种资源使其显示
*/