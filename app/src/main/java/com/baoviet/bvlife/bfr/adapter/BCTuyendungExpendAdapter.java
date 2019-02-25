package com.baoviet.bvlife.bfr.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.activity.BCKinhdoanhActivity;
import com.baoviet.bvlife.bfr.activity.BCTuyendungActivity;
import com.baoviet.bvlife.bfr.model.BCKinhdoanh;
import com.baoviet.bvlife.bfr.model.BCTuyendung;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 3/12/2018.
 */

public class BCTuyendungExpendAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<BCTuyendung> arrayBCTuyenDung; // listHeader
    ArrayList<BCTuyendung> arrayBCTuyenDungFilter;
    ValueFilter valueFilter;
    //Configuration newConfig ;

    public BCTuyendungExpendAdapter(Context context,ArrayList<BCTuyendung> arrayBCTuyendungContext) {
        super();
        this.context = context;
        this.arrayBCTuyenDung = arrayBCTuyendungContext;
        this.arrayBCTuyenDungFilter =arrayBCTuyendungContext;
    }


    @Override
    public int getCount() {
        return arrayBCTuyenDung.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayBCTuyenDung.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView congty,thuctuyen_datchitieu,thuctuyen_luykethang, thuctuyen_luyke, ccc_luykethang, ccc_luyke;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.dong_bctuyendung,parent,false);
            viewHolder.congty = convertView.findViewById(R.id.txtdongBCTDCongty);
            viewHolder.thuctuyen_datchitieu = convertView.findViewById(R.id.txtdongBCTDthuctuyen_datchitieu);
            viewHolder.thuctuyen_luykethang = convertView.findViewById(R.id.txtdongBCTDthuctuyen_luykethang);
            viewHolder.thuctuyen_luyke = convertView.findViewById(R.id.txtdongBCTD_STTluyke);
            viewHolder.ccc_luykethang = convertView.findViewById(R.id.txtdongBCTD_CCCLKthang);
            viewHolder.ccc_luyke = convertView.findViewById(R.id.txtdongBCTD_CCCLK);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.congty.setText(arrayBCTuyenDung.get(position).getCongty());

        //DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        Locale lc = new Locale("vi","VN");
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(lc);
        viewHolder.thuctuyen_datchitieu.setText(decimalFormat.format(arrayBCTuyenDung.get(position).getThuctuyen_datchitieu()) + "%");
        viewHolder.thuctuyen_luykethang.setText(decimalFormat.format(arrayBCTuyenDung.get(position).getThuctuyen_luykethang()) + "");

        if (BCTuyendungActivity.orientationBCTD == Configuration.ORIENTATION_LANDSCAPE) {
            viewHolder.thuctuyen_luyke.setText(decimalFormat.format(arrayBCTuyenDung.get(position).getThuctuyen_luyke()) + "");
            viewHolder.ccc_luykethang.setText(decimalFormat.format(arrayBCTuyenDung.get(position).getDacapCC_luykethang()) + "");
            viewHolder.ccc_luyke.setText(decimalFormat.format(arrayBCTuyenDung.get(position).getDacapCC_luyke()) + "");
        }else{
            viewHolder.thuctuyen_luyke = null;
            viewHolder.ccc_luykethang = null;
            viewHolder.ccc_luyke = null;
        }

        return convertView;
    }
    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new BCTuyendungExpendAdapter.ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();


            if (constraint != null && constraint.length() > 0) {
                ArrayList<BCTuyendung> filterList = new ArrayList<BCTuyendung>();
                for (int i = 0; i < arrayBCTuyenDungFilter.size(); i++) {
                    if ( (arrayBCTuyenDungFilter.get(i).getCongty().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        BCTuyendung bc = new BCTuyendung(
                                arrayBCTuyenDungFilter.get(i).getCongty(),
                                arrayBCTuyenDungFilter.get(i).getThuctuyen_luykethang(),
                                arrayBCTuyenDungFilter.get(i).getThuctuyen_datchitieu(),
                                arrayBCTuyenDungFilter.get(i).getThuctuyen_luyke(),
                                arrayBCTuyenDungFilter.get(i).getDacapCC_luykethang(),
                                arrayBCTuyenDungFilter.get(i).getDacapCC_luyke()
                        );

                        filterList.add(bc);
                    }

                }

                results.count = filterList.size();
                results.values = filterList;
            }

            else {
                results.count = arrayBCTuyenDungFilter.size();
                results.values = arrayBCTuyenDungFilter;

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayBCTuyenDung = (ArrayList<BCTuyendung>) results.values;
            notifyDataSetChanged();
        }

    }
}
