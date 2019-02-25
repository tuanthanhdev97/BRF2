package com.baoviet.bvlife.bfr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.model.BCKinhdoanh;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by NguyenHungAnh on 2/23/2018.
 */

public class BCKinhdoanhAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<BCKinhdoanh> arrayBCKinhdoanh;
    ArrayList<BCKinhdoanh> arrayBCKinhdoanhFilter;
    ValueFilter valueFilter;

    public BCKinhdoanhAdapter(Context context, ArrayList<BCKinhdoanh> arrayBCKinhdoanh) {
        this.context = context;
        this.arrayBCKinhdoanh = arrayBCKinhdoanh;
        arrayBCKinhdoanhFilter = arrayBCKinhdoanh;

    }

    @Override
    public int getCount() {
        return arrayBCKinhdoanh.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayBCKinhdoanh.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<BCKinhdoanh> filterList = new ArrayList<BCKinhdoanh>();
                for (int i = 0; i < arrayBCKinhdoanhFilter.size(); i++) {
                    if ( (arrayBCKinhdoanhFilter.get(i).getCongty().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {
                        BCKinhdoanh bcKinhdoanh = new BCKinhdoanh(arrayBCKinhdoanhFilter.get(i).getSoTT(),
                                arrayBCKinhdoanhFilter.get(i).getCongty(),
                                arrayBCKinhdoanhFilter.get(i).getSolieutrongngay(),
                                arrayBCKinhdoanhFilter.get(i).mdtLuykethang,
                                arrayBCKinhdoanhFilter.get(i).getKehoachthang());

                        filterList.add(bcKinhdoanh);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = arrayBCKinhdoanhFilter.size();
                results.values = arrayBCKinhdoanhFilter;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            arrayBCKinhdoanh = (ArrayList<BCKinhdoanh>) results.values;
            notifyDataSetChanged();
        }

    }

    public class ViewHolder{
        TextView soTT,congty,solieutrongngay,mdtLuykethang,kehoachthang,Svalue;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_bckinhdoanh, null);
            // Anh xa
            //viewHolder.soTT = view.findViewById(R.id.txtdongBCkinhdoanhStt);
            viewHolder.congty = view.findViewById(R.id.txtdongBCkinhdoanhCongty);
            viewHolder.solieutrongngay = view.findViewById(R.id.txtdongBCkinhdoanhSolieutrongngay);
            viewHolder.mdtLuykethang = view.findViewById(R.id.txtdongBCkinhdoanhMTD);
            //viewHolder.kehoachthang = view.findViewById(R.id.txtdongBCkinhdoanhkehoachthang);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        // Gán giá trị
        BCKinhdoanh bcKinhdoanh = (BCKinhdoanh) getItem(position);
        viewHolder.soTT.setText(( bcKinhdoanh.getSoTT() + ""));
        viewHolder.congty.setText(bcKinhdoanh.getCongty());

        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        viewHolder.solieutrongngay.setText(decimalFormat.format(bcKinhdoanh.getSolieutrongngay()) + "");
        viewHolder.mdtLuykethang.setText(decimalFormat.format(bcKinhdoanh.getMdtLuykethang()) + "");
        viewHolder.kehoachthang.setText(decimalFormat.format(bcKinhdoanh.getKehoachthang()) + "");

        return view;
    }


}
