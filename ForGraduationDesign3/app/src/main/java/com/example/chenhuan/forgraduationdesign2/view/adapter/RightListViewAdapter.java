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
import com.example.chenhuan.forgraduationdesign2.model.bean.DrinksBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lixu on 2017/5/16.
 */

public class RightListViewAdapter extends BaseAdapter {
    private List<DrinksBean> list;
    private Context context;
    private LayoutInflater inflater;
    private View.OnClickListener listener;
    public RightListViewAdapter(List<DrinksBean> list, Context context, View.OnClickListener onClickListener) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listener = onClickListener;
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
            convertView = inflater.inflate(R.layout.rightsub_lv_item_layout, parent, false);
            holder.imageView = ((ImageView) convertView.findViewById(R.id.iv1));
            holder.drinksName = ((TextView) convertView.findViewById(R.id.drinks_name1));
            holder.subTitle = ((TextView) convertView.findViewById(R.id.sub_title1));
            holder.price = ((TextView) convertView.findViewById(R.id.price1));
            holder.fab = (FloatingActionButton) convertView.findViewById(R.id.floating_action_btn_right);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.drinksName.setText(list.get(position).getDrinksName());
        holder.subTitle.setText(getSubTitle(position));
        holder.price.setText(list.get(position).getPrice()+"");
        //加载图片到ImageView
        Picasso.with(context).load(list.get(position).getImgUrl()).into(holder.imageView);
        holder.fab.setTag(position);
        holder.fab.setOnClickListener(listener);
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
        private TextView drinksName;
        private TextView subTitle;
        private TextView price;
        private FloatingActionButton fab;

    }
}
