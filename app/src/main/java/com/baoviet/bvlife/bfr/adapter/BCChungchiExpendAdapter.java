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
import com.baoviet.bvlife.bfr.model.BCChungchi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 3/13/2018.
 */

public class BCChungchiExpendAdapter extends BaseExpandableListAdapter implements Filterable {

    Context context;
    ArrayList<BCChungchi> arrayBC; // listHeader
    ArrayList<BCChungchi> arrayBCFilter;
    HashMap<String,List<BCChungchi>> listChild;
    ValueFilter valueFilter;


    public BCChungchiExpendAdapter(Context context, ArrayList<BCChungchi> arrayBC, HashMap<String, List<BCChungchi>> listChild) {
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
        TextView congty,mact,tongsothamgia;
        TextView ten,giatri;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_bcchungchi, null);

            // Anh xa
            viewHolder.congty = view.findViewById(R.id.txtdongBCCCCongty);
            viewHolder.mact = view.findViewById(R.id.txtdongBCCCmacongty);
            viewHolder.tongsothamgia = view.findViewById(R.id.txtdongBCCCTongthamgia);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        // Gán giá trị
        BCChungchi bc = (BCChungchi) getGroup(groupPosition);
        viewHolder.congty.setText(bc.getCongty());
        viewHolder.mact.setText(bc.getMct());

        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        viewHolder.tongsothamgia.setText(decimalFormat.format(bc.getTongsothamgia()) + "");
        /*viewHolder.tongsocc.setText(decimalFormat.format(bc.getTongsocc()) + "");
        viewHolder.tongsotruot.setText(decimalFormat.format(bc.getTongsotruot()) + "");*/
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_dong_bcchungchi, null);
            // Anh xa
            viewHolder.ten = convertView.findViewById(R.id.child_bccc_txtTen);
            viewHolder.giatri = convertView.findViewById(R.id.child_bccc_giatri);
            //Gan
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BCChungchi item = (BCChungchi) getChild(groupPosition, childPosition);
        viewHolder.ten.setText(item.getCongty());
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        if(item.getCongty().equals("MCT")){
            viewHolder.giatri.setText(item.getMct());
        }else{
            viewHolder.giatri.setText(decimalFormat.format(item.getTongsothamgia()) + "");
        }

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
                ArrayList<BCChungchi> filterList = new ArrayList<BCChungchi>();
                for (int i = 0; i < arrayBCFilter.size(); i++) {
                    if ( (arrayBCFilter.get(i).getCongty().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {
                        BCChungchi bc = new BCChungchi(
                                arrayBCFilter.get(i).getCongty(),
                                arrayBCFilter.get(i).getMct(),
                                arrayBCFilter.get(i).getTongsothamgia()
                                /*,
                                arrayBCFilter.get(i).getTongsocc(),
                                arrayBCFilter.get(i).getTongsotruot()*/
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
            arrayBC = (ArrayList<BCChungchi>) results.values;
            notifyDataSetChanged();
        }

    }
}