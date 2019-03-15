package com.baoviet.bvlife.bfr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.model.SearchDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchDayAdapter extends ArrayAdapter<SearchDay> {

    private Context context;
    private int resource;
    private ArrayList<SearchDay> arrValue;

    public SearchDayAdapter(Context context, int resource, ArrayList<SearchDay> arrValue) {
        super(context, resource, arrValue);
        this.context = context;
        this.resource = resource;
        this.arrValue = arrValue;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_searchday, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtDayItem = (TextView) convertView.findViewById(R.id.txtDayItem);
            viewHolder.txtDoanhThuItem = (TextView) convertView.findViewById(R.id.txtDoanhThuItem);
            viewHolder.progValueItem =  convertView.findViewById(R.id.progValueItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SearchDay va = Collections.max(arrValue,new compPopulation());
        SearchDay value = arrValue.get(position);
        int max = 0;
        for(int i=0; i<arrValue.size(); i++){
            if(arrValue.get(i).getDoanhthu() > max){
                max = arrValue.get(i).getDoanhthu();
            }
        }
        viewHolder.txtDayItem.setText(value.getNgay());
        viewHolder.txtDoanhThuItem.setText(""+value.getDoanhthu());
        viewHolder.progValueItem.setMax(max);
        viewHolder.progValueItem.setProgress(value.getDoanhthu());
        return convertView;
    }

    public class ViewHolder {
        TextView txtDayItem, txtDoanhThuItem;
        ProgressBar  progValueItem;

    }
    public class compPopulation implements Comparator<SearchDay> {
        @Override
        public int compare(SearchDay o1, SearchDay o2) {
            if (o1.getDoanhthu() > o1.getDoanhthu())
                return -1; // highest value first
            if (o1.getDoanhthu() == o1.getDoanhthu())
                return 0;
            return 1;
        }
    }
}