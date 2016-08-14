package com.seven.medal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by seven on 16-8-14.
 */

public class ListAdapter extends BaseAdapter {
    private DataPares dp;
    private LayoutInflater list;

    public ListAdapter(DataPares dp, Context c) {
        this.dp = dp;
        list = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return dp.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = list.inflate(R.layout.listitem,null);

        TextView tv_top = (TextView) convertView.findViewById(R.id.tv_top);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        ImageView iv_flag = (ImageView) convertView.findViewById(R.id.iv_flag);
        TextView tv_gold = (TextView) convertView.findViewById(R.id.tv_gold);
        TextView tv_sli = (TextView) convertView.findViewById(R.id.tv_sil);
        TextView tv_cu = (TextView) convertView.findViewById(R.id.tv_cu);
        TextView tv_total = (TextView) convertView.findViewById(R.id.tv_total);

        tv_top.setText(dp.getTop(position)+"");
        tv_name.setText(dp.getName(position)+"");
        iv_flag.setImageBitmap(dp.getBitMap(position));
        tv_gold.setText(dp.getGold(position)+"");
        tv_sli.setText(dp.getSli(position)+"");
        tv_cu.setText(dp.getCu(position)+"");
        tv_total.setText(dp.getTotal(position)+"");

        return convertView;
    }
}
