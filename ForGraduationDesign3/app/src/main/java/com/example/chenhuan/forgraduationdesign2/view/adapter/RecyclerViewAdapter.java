package com.example.chenhuan.forgraduationdesign2.view.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.bean.OrderBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lixu on 2017/5/8.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private Context context;
    private List<OrderBean> list;
    private View.OnClickListener onClickListener;

    public RecyclerViewAdapter(Context context, List<OrderBean> list, View.OnClickListener onClickListener) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.dingdan_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        OrderBean orderBean = list.get(position);
        viewHolder.dingdanFoodName.setText(orderBean.getOrderName());
        viewHolder.dingdanPrice.setText( orderBean.getOrderPrice()+"");
        Picasso.with(context).load(list.get(position).getImgUrl()).into(viewHolder.dingdanIV);
        viewHolder.dingdanFab.setTag(position);
        viewHolder.dingdanFab.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView dingdanFoodName;
        private final ImageView dingdanIV;
        private final TextView dingdanPrice;
        private final FloatingActionButton dingdanFab;


        public MyViewHolder(View itemView) {
            super(itemView);

            dingdanFoodName = ((TextView) itemView.findViewById(R.id.dingdan_food_name));
            dingdanIV = ((ImageView) itemView.findViewById(R.id.dingdan_iv));
            dingdanPrice = ((TextView) itemView.findViewById(R.id.dingdan_price));
            dingdanFab = ((FloatingActionButton) itemView.findViewById(R.id.dingdan_fab));

        }
    }
}
