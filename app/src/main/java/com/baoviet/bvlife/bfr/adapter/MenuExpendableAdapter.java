package com.baoviet.bvlife.bfr.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.model.MenuBaoCao;

import java.util.HashMap;
import java.util.List;

/**
 * Created by NguyenHungAnh on 2/27/2018.
 */

public class MenuExpendableAdapter extends BaseExpandableListAdapter {
    Context context;
    List<MenuBaoCao> listHeader;
    HashMap<String,List<String>> listChild;

    //List<String>

    public MenuExpendableAdapter(Context context, List<MenuBaoCao> listHeader, HashMap<String, List<String>> listChild) {
        this.context = context;
        this.listHeader = listHeader;
        this.listChild = listChild;
    }


    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(listChild.get(listHeader.get(groupPosition).getTen()) == null){
            return 0;
        }else {
            return listChild.get(listHeader.get(groupPosition).getTen()).size();
        }
        //return listChild.get(listHeader.get(groupPosition).getTen()).size();

        //return listChild.get(groupPosition).size();
        /*if (this.listHeader == null) {
            Log.e("Debug", "mListDataHeader is null.");
            return 0;
        } else if (groupPosition < 0 || groupPosition >= this.listHeader.size()) {
            Log.e("Debug", "position invalid: " + groupPosition);
            return 0;
        } else if (this.listHeader.get(groupPosition) == null) {
            Log.e("Debug", "Value of mListDataHeader at position is null: " + groupPosition);
            return 0;
        } else if (this.listChild == null) {
            Log.e("Debug", "mListDataChild is null.");
            return 0;
        } else if (!this.listChild.containsKey(this.listHeader.get(groupPosition))) {
            Log.e("Debug", "No key: " + this.listHeader.get(groupPosition));
            return 0;
        } else if (this.listChild.get(this.listHeader.get(groupPosition)) == null) {
            Log.e("Debug", "Value at key is null: " + this.listHeader.get(groupPosition));
            return 0;
        } else {
            return this.listChild.get(this.listHeader.get(groupPosition)).size();
        }*/
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(listChild.get(this.listHeader.get(groupPosition).getTen()) == null){
            return null;
        }
        else {
            return listChild.get(listHeader.get(groupPosition).getTen()).get(childPosition);
        }
        //return listChild.get(listHeader.get(groupPosition).getTen()).get(childPosition);
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
        TextView txtTen;
        ImageView imgHinh;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_menu, null);
            // Anh xa
            viewHolder.txtTen = convertView.findViewById(R.id.groupmenutextviewTen);
            viewHolder.imgHinh = convertView.findViewById(R.id.groupmenuimageviewHinh);
            //Gan
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MenuBaoCao menuBaoCao = (MenuBaoCao) getGroup(groupPosition);
        //String headerTitle = (String) getGroup(groupPosition);

        viewHolder.txtTen.setText(menuBaoCao.getTen());
        //viewHolder.imgHinh.setImageResource(groupPosition);
        viewHolder.imgHinh.setImageResource(menuBaoCao.getHinh());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_menu, null);
            // Anh xa
            viewHolder.txtTen = convertView.findViewById(R.id.childmenutextView);
            //viewHolder.imgHinh = convertView.findViewById(R.id.groupmenuimageviewHinh);
            //Gan
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String item = (String) getChild(groupPosition, childPosition);
        viewHolder.txtTen.setText(item);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;// true
    }
}
