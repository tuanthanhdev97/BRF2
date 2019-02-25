package com.baoviet.bvlife.bfr.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.activity.BCKinhdoanhActivity;
import com.baoviet.bvlife.bfr.activity.BCTuyendungActivity;
import com.baoviet.bvlife.bfr.model.BCKinhdoanh;
import com.baoviet.bvlife.bfr.model.BCTuyendung;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


/**
 * Created by NguyenHungAnh on 3/1/2018.
 */

public class BCKinhdoanhExpendAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<BCKinhdoanh> arrayBCKinhDoanh; // listHeader
    ArrayList<BCKinhdoanh> arrayBCKinhDoanhFilter;
    ValueFilter valueFilter;
    //Configuration newConfig ;

    public BCKinhdoanhExpendAdapter(Context context,ArrayList<BCKinhdoanh> arrayBCTuyendungContext) {
        super();
        this.context = context;
        this.arrayBCKinhDoanh = arrayBCTuyendungContext;
        this.arrayBCKinhDoanhFilter =arrayBCTuyendungContext;
    }


    @Override
    public int getCount() {
        return arrayBCKinhDoanh.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayBCKinhDoanh.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView congty,solieutrongngay,mdtLuykethang,congty1,solieutrongngay1,mdtLuykethang1,CKnamtruoc,TLLKthangCK,LKnamCK;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;


        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.dong_bckinhdoanh,parent,false);
            viewHolder.congty = convertView.findViewById(R.id.txtdongBCkinhdoanhCongty);
            viewHolder.solieutrongngay = convertView.findViewById(R.id.txtdongBCkinhdoanhSolieutrongngay);
            viewHolder.mdtLuykethang = convertView.findViewById(R.id.txtdongBCkinhdoanhMTD);

            viewHolder.congty1 = convertView.findViewById(R.id.txtdongBCkinhdoanhCongty1);
            viewHolder.solieutrongngay1 = convertView.findViewById(R.id.txtdongBCkinhdoanhSolieutrongngay1);
            viewHolder.mdtLuykethang1 = convertView.findViewById(R.id.txtdongBCkinhdoanhMTD1);

            viewHolder.CKnamtruoc = convertView.findViewById(R.id.txtdongBCkinhdoanhCKnamtruoc);
            viewHolder.TLLKthangCK = convertView.findViewById(R.id.txtdongBCkinhdoanhTLLKthangCK);
            viewHolder.LKnamCK = convertView.findViewById(R.id.txtdongBCkinhdoanhLKnamCK);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.congty.setText(arrayBCKinhDoanh.get(position).getCongty());
        viewHolder.congty1.setText(arrayBCKinhDoanh.get(position).getCongty());

        Locale lc = new Locale("vi","VN");
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(lc);

        viewHolder.solieutrongngay.setText(decimalFormat.format(arrayBCKinhDoanh.get(position).getSolieutrongngay()) + "");
        viewHolder.mdtLuykethang.setText(decimalFormat.format(arrayBCKinhDoanh.get(position).getMdtLuykethang()) + "");

        viewHolder.solieutrongngay1.setText(decimalFormat.format(arrayBCKinhDoanh.get(position).getSolieutrongngay()) + "");
        viewHolder.mdtLuykethang1.setText(decimalFormat.format(arrayBCKinhDoanh.get(position).getMdtLuykethang()) + "");

        if (BCKinhdoanhActivity.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewHolder.CKnamtruoc.setText(decimalFormat.format(arrayBCKinhDoanh.get(position).getCungkynamtruoc()) + "");
            viewHolder.TLLKthangCK.setText(decimalFormat.format(arrayBCKinhDoanh.get(position).getSoluykethangCungkynamtruoc()) + "%");
            viewHolder.LKnamCK.setText(decimalFormat.format(arrayBCKinhDoanh.get(position).getLuykenamCungkynamtruoc()) + "");
        }else{
            viewHolder.CKnamtruoc = null;
            viewHolder.TLLKthangCK = null;
            viewHolder.LKnamCK = null;
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new BCKinhdoanhExpendAdapter.ValueFilter();
        }

        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();


            if (constraint != null && constraint.length() > 0) {
                ArrayList<BCKinhdoanh> filterList = new ArrayList<BCKinhdoanh>();
                for (int i = 0; i < arrayBCKinhDoanhFilter.size(); i++) {
                    if ( (arrayBCKinhDoanhFilter.get(i).getCongty().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        BCKinhdoanh bc = new BCKinhdoanh(
                                arrayBCKinhDoanhFilter.get(i).getCongty(),
                                arrayBCKinhDoanhFilter.get(i).getSolieutrongngay(),
                                arrayBCKinhDoanhFilter.get(i).getMdtLuykethang(),
                                arrayBCKinhDoanhFilter.get(i).getCungkynamtruoc(),
                                arrayBCKinhDoanhFilter.get(i).getSoluykethangCungkynamtruoc(),
                                arrayBCKinhDoanhFilter.get(i).getLuykenamCungkynamtruoc(),
                                arrayBCKinhDoanhFilter.get(i).getKehoachthang(),
                                arrayBCKinhDoanhFilter.get(i).getTlthang(),
                                arrayBCKinhDoanhFilter.get(i).getLkquy(),
                                arrayBCKinhDoanhFilter.get(i).getKhquy(),
                                arrayBCKinhDoanhFilter.get(i).getTlkhquy(),
                                arrayBCKinhDoanhFilter.get(i).getLknam(),
                                arrayBCKinhDoanhFilter.get(i).getKhNam(),
                                arrayBCKinhDoanhFilter.get(i).getTlnam(),
                                arrayBCKinhDoanhFilter.get(i).getLknamtruoc(),
                                arrayBCKinhDoanhFilter.get(i).getTlLuyKeNam(),
                                arrayBCKinhDoanhFilter.get(i).getSoLuong(),
                                arrayBCKinhDoanhFilter.get(i).getSlHoatDong(),
                                arrayBCKinhDoanhFilter.get(i).getTlHoatDong()


                        );

                        filterList.add(bc);
                    }

                }

                results.count = filterList.size();
                results.values = filterList;
            }

            else {
                results.count = arrayBCKinhDoanhFilter.size();
                results.values = arrayBCKinhDoanhFilter;

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayBCKinhDoanh = (ArrayList<BCKinhdoanh>) results.values;
            notifyDataSetChanged();
        }

    }

}
