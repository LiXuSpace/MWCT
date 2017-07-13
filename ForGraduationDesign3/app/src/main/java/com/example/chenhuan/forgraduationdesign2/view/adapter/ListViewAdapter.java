package com.example.chenhuan.forgraduationdesign2.view.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.bean.FoodBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lixu on 2017/4/27.
 */

public class ListViewAdapter extends BaseAdapter {
    private List<FoodBean> list;
    private Context context;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    public ListViewAdapter(List<FoodBean> list, Context context, View.OnClickListener onClickListener) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder = null;
        if (convertView == null) {
            holder = new viewHolder();
            convertView = inflater.inflate(R.layout.leftsub_lv_item_layout, parent, false);
            holder.imageView = ((ImageView) convertView.findViewById(R.id.iv));
            holder.foodName = ((TextView) convertView.findViewById(R.id.food_name));
            holder.subTitle = ((TextView) convertView.findViewById(R.id.sub_title));
            holder.price = ((TextView) convertView.findViewById(R.id.price));
            holder.floatingActionButton = ((FloatingActionButton) convertView.findViewById(R.id.floating_action_btn));
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.foodName.setText(list.get(position).getFoodName());
        holder.subTitle.setText(getSubTitle(position));
        holder.price.setText(list.get(position).getPrice()+"");
        //加载图片到ImageView
        Picasso.with(context).load(list.get(position).getImgUrl()).into(holder.imageView);

        //给floatingActionBtn设置点击事件
        holder.floatingActionButton.setTag(position);
        holder.floatingActionButton.setOnClickListener(this.onClickListener);
        return convertView;
    }

    public String getSubTitle(int position) {
        String subTitle;
        int monthSaleNum = list.get(position).getMonthSaleNum();
        int praiseNum = list.get(position).getPraiseNum();
        subTitle = "月售"+monthSaleNum+"  "+"赞"+praiseNum;
        return subTitle;
    }

    class viewHolder{
        private ImageView imageView;
        private TextView foodName;
        private TextView subTitle;
        private TextView price;
        private FloatingActionButton floatingActionButton;

    }
}
