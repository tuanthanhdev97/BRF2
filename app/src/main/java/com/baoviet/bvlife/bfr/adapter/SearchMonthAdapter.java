package com.baoviet.bvlife.bfr.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.model.SearchMonth;

import java.util.ArrayList;

public class SearchMonthAdapter  extends ArrayAdapter<SearchMonth> {

    private Context context;
    private int resource;
    private ArrayList<SearchMonth> arrValue;

    public SearchMonthAdapter(Context context, int resource, ArrayList<SearchMonth> arrValue) {
        super(context, resource,arrValue);
        this.context = context;
        this.resource = resource;
        this.arrValue = arrValue;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_searchmont, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtMonthItem = (TextView) convertView.findViewById(R.id.txtMonthItem);
            viewHolder.txtDoanhThuItem = (TextView) convertView.findViewById(R.id.txtDoanhThuItem);
            viewHolder.txtTuyenDung =  convertView.findViewById(R.id.txtDoanhThuItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SearchMonth value = arrValue.get(position);
        viewHolder.txtMonthItem.setText(value.getThang());
        viewHolder.txtDoanhThuItem.setText(value.getDoanhthu()+"");
        viewHolder.txtTuyenDung.setText(value.getTuyendung()+"");
        return convertView;
    }

    public class ViewHolder {
        TextView txtMonthItem, txtDoanhThuItem,txtTuyenDung;

    }
}