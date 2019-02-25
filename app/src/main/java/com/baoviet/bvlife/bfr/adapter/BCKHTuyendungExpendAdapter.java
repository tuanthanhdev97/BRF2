package com.baoviet.bvlife.bfr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.model.BCKehoachTuyendung;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 3/13/2018.
 */

public class BCKHTuyendungExpendAdapter extends BaseExpandableListAdapter implements Filterable {

    Context context;
    ArrayList<BCKehoachTuyendung> arrayBC; // listHeader
    ArrayList<BCKehoachTuyendung> arrayBCFilter;
    HashMap<String,List<BCKehoachTuyendung>> listChild;
    ValueFilter valueFilter;


    public BCKHTuyendungExpendAdapter(Context context, ArrayList<BCKehoachTuyendung> arrayBC, HashMap<String, List<BCKehoachTuyendung>> listChild) {
        this.context = context;
        this.arrayBC = arrayBC;
        this.listChild = listChild;
        arrayBCFilter = arrayBC;
    }

    @Override
    public int getGroupCount() {
        return arrayBC.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(listChild.get(arrayBC.get(groupPosition).getCongty()) == null){
            return 0;
        }else{
            return listChild.get(arrayBC.get(groupPosition).getCongty()).size();
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayBC.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(listChild.get(arrayBC.get(groupPosition).getCongty()) == null){
            return null;
        }else{
            return listChild.get(arrayBC.get(groupPosition).getCongty()).get(childPosition);
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolder{
        TextView soTT,congty,tongcongtygia,T1;
        TextView ten,giatri;

    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_bckhtuyendung, null);
            // Anh xa
            viewHolder.congty = view.findViewById(R.id.txtdongBCKHTDCongty);
            viewHolder.tongcongtygia = view.findViewById(R.id.txtdongBCKHTDTongctygiao);
            viewHolder.T1 = view.findViewById(R.id.txtdongBCKHTDT1);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        // Gán giá trị
        BCKehoachTuyendung bc = (BCKehoachTuyendung) getGroup(groupPosition);
        //viewHolder.soTT.setText(( bcKinhdoanh.getSoTT() + ""));
        viewHolder.congty.setText(bc.getCongty());

        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        viewHolder.tongcongtygia.setText(decimalFormat.format(bc.getTongcongtygia())  + "");
        viewHolder.T1.setText(decimalFormat.format(bc.getT1()) + "");
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_dong_bckhtuyendung, null);
            // Anh xa
            viewHolder.ten = convertView.findViewById(R.id.child_bckhtd_txtTen);
            viewHolder.giatri = convertView.findViewById(R.id.child_bckhtd_giatri);
            //Gan
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BCKehoachTuyendung item = (BCKehoachTuyendung) getChild(groupPosition, childPosition);
        viewHolder.ten.setText(item.getCongty());
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        try{
            //Double.parseDouble(String.valueOf(item.getTongsothamgia()));
            viewHolder.giatri.setText(decimalFormat.format(item.getTongcongtygia()) + "");
        }catch (NumberFormatException e) {
            // xử lý khi số nhập vào ko đúng
            //viewHolder.giatri.setText(item.getMct());
        }
        //viewHolder.giatri.setText(decimalFormat.format(item.getTongsothamgia()) + "");

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
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
                ArrayList<BCKehoachTuyendung> filterList = new ArrayList<BCKehoachTuyendung>();
                for (int i = 0; i < arrayBCFilter.size(); i++) {
                    if ( (arrayBCFilter.get(i).getCongty().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {
                        BCKehoachTuyendung bc = new BCKehoachTuyendung(
                                arrayBCFilter.get(i).getCongty(),
                                arrayBCFilter.get(i).getTongcongtygia(),
                                arrayBCFilter.get(i).getT1()
                        );

                        filterList.add(bc);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = arrayBCFilter.size();
                results.values = arrayBCFilter;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            arrayBC = (ArrayList<BCKehoachTuyendung>) results.values;
            notifyDataSetChanged();
        }
    }
}
