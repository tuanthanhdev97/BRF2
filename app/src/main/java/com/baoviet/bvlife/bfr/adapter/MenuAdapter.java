package com.baoviet.bvlife.bfr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.model.MenuBaoCao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenHungAnh on 2/22/2018.
 */

public class MenuAdapter extends BaseAdapter {

    public Context context;
    public int layout;
    public ArrayList<MenuBaoCao> menuBaoCaoList;

    public MenuAdapter(Context context, int layout, ArrayList<MenuBaoCao> menuBaoCaoList) {
        this.context = context;
        this.layout = layout;
        this.menuBaoCaoList = menuBaoCaoList;
    }

    public MenuAdapter(Context context, ArrayList<MenuBaoCao> menuBaoCaoList) {
        this.context = context;
        this.menuBaoCaoList = menuBaoCaoList;
    }


    @Override
    public int getCount() {
        return menuBaoCaoList.size();
    }

    @Override
    public Object getItem(int position) {
        //return null;
        return menuBaoCaoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txtTen;
        ImageView imgHinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.dong_menu,null);

        // Anh xa View
        TextView txtTen = (TextView) convertView.findViewById(R.id.textviewTen);
        ImageView imgHinh = (ImageView) convertView.findViewById(R.id.imageviewHinh);

        // Gan gia tri
        MenuBaoCao menuBaoCao =  menuBaoCaoList.get(position);
        txtTen.setText(menuBaoCao.getTen());
        imgHinh.setImageResource(menuBaoCao.getHinh());*/
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_menu, null);
            // Anh xa
            viewHolder.txtTen = convertView.findViewById(R.id.textviewTen);
            viewHolder.imgHinh = convertView.findViewById(R.id.imageviewHinh);
            //Gan
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MenuBaoCao menuBaoCao = (MenuBaoCao) getItem(position);
        viewHolder.txtTen.setText(menuBaoCao.getTen());
        viewHolder.imgHinh.setImageResource(menuBaoCao.getHinh());

        return convertView;
    }
}
