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
import com.baoviet.bvlife.bfr.model.BCKehoachAFYP;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 3/14/2018.
 */

public class BCKehoachAFYPExpendAdapter extends BaseExpandableListAdapter implements Filterable {

    Context context;
    ArrayList<BCKehoachAFYP> arrayBC; // listHeader
    ArrayList<BCKehoachAFYP> arrayBCFilter;
    HashMap<String,List<BCKehoachAFYP>> listChild;
    ValueFilter valueFilter;


    public BCKehoachAFYPExpendAdapter(Context context, ArrayList<BCKehoachAFYP> arrayBC, HashMap<String, List<BCKehoachAFYP>> listChild) {
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
        TextView congty,tongcongtygia,T1;
        TextView ten,giatri;

    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_bckehoach_afyp, null);
            // Anh xa
            viewHolder.congty = view.findViewById(R.id.txtdongBCKHAFYPCongty);
            viewHolder.tongcongtygia = view.findViewById(R.id.txtdongBCKHAFYPTongctygiao);
            viewHolder.T1 = view.findViewById(R.id.txtdongBCKHAFYPT1);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        // Gán giá trị
        BCKehoachAFYP bc = (BCKehoachAFYP) getGroup(groupPosition);
        //viewHolder.soTT.setText(( bcKinhdoanh.getSoTT() + ""));
        viewHolder.congty.setText(bc.getCongty());

        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        viewHolder.tongcongtygia.setText(decimalFormat.format(bc.getTongcongtygiao())  + "");
        viewHolder.T1.setText(decimalFormat.format(bc.getT1()) + "");
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_dong_bckehoach_afyp, null);
            // Anh xa
            viewHolder.ten = convertView.findViewById(R.id.child_bckhafyp_txtTen);
            viewHolder.giatri = convertView.findViewById(R.id.child_bckhafyp_giatri);
            //Gan
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BCKehoachAFYP item = (BCKehoachAFYP) getChild(groupPosition, childPosition);
        viewHolder.ten.setText(item.getCongty());
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        try{
            //Double.parseDouble(String.valueOf(item.getTongsothamgia()));
            viewHolder.giatri.setText(decimalFormat.format(item.getTongcongtygiao()) + "");
        }catch (NumberFormatException e) {

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
                ArrayList<BCKehoachAFYP> filterList = new ArrayList<BCKehoachAFYP>();
                for (int i = 0; i < arrayBCFilter.size(); i++) {
                    if ( (arrayBCFilter.get(i).getCongty().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {
                        BCKehoachAFYP bc = new BCKehoachAFYP(
                                arrayBCFilter.get(i).getCongty(),
                                arrayBCFilter.get(i).getTongcongtygiao(),
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
            arrayBC = (ArrayList<BCKehoachAFYP>) results.values;
            notifyDataSetChanged();
        }
    }
}