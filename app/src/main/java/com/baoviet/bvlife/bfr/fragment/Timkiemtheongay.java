package com.baoviet.bvlife.bfr.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.activity.TimkiemActivity;
import com.baoviet.bvlife.bfr.model.SearchDay;
import com.baoviet.bvlife.bfr.adapter.SearchDayAdapter;


import java.util.ArrayList;
import java.util.Calendar;

public class Timkiemtheongay extends Fragment implements View.OnClickListener {
    private ListView lvday;
    TextView txtStartday,txtEndday;
    Dialog dialogday;
    DatePickerDialog datePickerStartDay,datePickerEndday;
    private Spinner spin;
    String arr[]={
            "Thanh Hóa",
            "Hà Nội",
            "Bắc Ninh",
            "Hà Nội",
            "Nghệ An",
            "Hồ Chí Minh",
            "Cần Thơ",};
    public Timkiemtheongay() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timkiemtheongay_layout, container, false);



        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datePickerStartDay = new DatePickerDialog(
                getActivity(), dateStartDay, year, month, day);
        datePickerEndday = new DatePickerDialog(
                getActivity(), dateEndday, year, month, day);

        lvday = (ListView) view.findViewById(R.id.lvDay);
        ArrayList<SearchDay> arrContact = new ArrayList<>();

        SearchDay contact1 = new SearchDay("10/2/2019",142342);
        SearchDay contact2 = new SearchDay("9/2/2019",245845);
        SearchDay contact3 = new SearchDay("8/2/2019",342345);
        SearchDay contact4 = new SearchDay("7/2/2019",445545);
        SearchDay contact5 = new SearchDay("6/2/2019",514534);
        SearchDay contact6 = new SearchDay("5/2/2019",624934);
        SearchDay contact7 = new SearchDay("4/2/2019",734530);
        SearchDay contact8 = new SearchDay("10/2/2019",142342);
        SearchDay contact9 = new SearchDay("9/2/2019",245845);
        SearchDay contact10 = new SearchDay("8/2/2019",342345);
        SearchDay contact11 = new SearchDay("7/2/2019",445545);
        SearchDay contact12 = new SearchDay("6/2/2019",514534);
        SearchDay contact13 = new SearchDay("5/2/2019",624934);
        SearchDay contact14 = new SearchDay("4/2/2019",734530);

        arrContact.add(contact1);
        arrContact.add(contact2);
        arrContact.add(contact3);
        arrContact.add(contact4);
        arrContact.add(contact5);
        arrContact.add(contact6);
        arrContact.add(contact7);
        arrContact.add(contact8 );
        arrContact.add(contact9 );
        arrContact.add(contact10);
        arrContact.add(contact11);
        arrContact.add(contact12);
        arrContact.add(contact13);
        arrContact.add(contact14);

        SearchDayAdapter customAdaper = new SearchDayAdapter(getContext(),R.layout.item_searchday,arrContact);
        lvday.setAdapter(customAdaper);
//        innit();
        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showDialog();
    }

    private void showDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dialogday = new Dialog(getActivity());
        dialogday.setTitle("Thangcode.com");
        dialogday.setContentView(R.layout.dialog_theongay);
        dialogday.show();

        spin=(Spinner) dialogday.findViewById(R.id.spinner1);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, arr);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);
        ImageView btnstart = dialogday.findViewById(R.id.btnChooseStarDay);
        ImageView btnend = dialogday.findViewById(R.id.btnChooseEndDay);
        Button btnSearch = dialogday.findViewById(R.id.btnsearchngay);
        txtStartday=dialogday.findViewById(R.id.txtngaybatdau);
        txtEndday=dialogday.findViewById(R.id.txtngayketthuc);
        txtStartday.setText("1/"+(month+1)+"/"+year);
        txtEndday.setText(day+"/"+(month+1)+"/"+year);
        btnstart.setOnClickListener(this);
        btnend.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

    }
    private void innit() {
    }
    private DatePickerDialog.OnDateSetListener dateStartDay = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String a =  view.getDayOfMonth()+ " / " + (view.getMonth()+1) + " / " + view.getYear();
            txtStartday.setText(a);
        }
    };

    private DatePickerDialog.OnDateSetListener dateEndday = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String a =  view.getDayOfMonth()+ " / " + (view.getMonth()+1) + " / " + view.getYear();
            txtEndday.setText(a);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChooseEndDay:
                datePickerEndday.show();
                break;
            case R.id.btnChooseStarDay:
                datePickerStartDay.show();
                break;

            case R.id.btnsearchngay:
                break;
        }
    }
}