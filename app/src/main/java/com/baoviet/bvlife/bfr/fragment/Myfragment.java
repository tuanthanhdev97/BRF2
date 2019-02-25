package com.baoviet.bvlife.bfr.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.activity.TimkiemActivity;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.Calendar;

public class Myfragment extends DialogFragment implements View.OnClickListener {

    Dialog dialog;
    DatePickerDialog datePickerStartDay, datePickerEndday;
    TextView txtStartmonth, txtEndmonth;

    public static final String tag = Myfragment.class.getSimpleName();

    public static Myfragment getInstance() {
        Myfragment myfragment = new Myfragment();
        return myfragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_theothang, container, false);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        ImageView btnstart = v.findViewById(R.id.btnChooseStarMonth);
        ImageView btnend = v.findViewById(R.id.btnChooseEndMonth);
        Button btnSearch = v.findViewById(R.id.btnsearchthang);
        txtStartmonth = v.findViewById(R.id.txtthangbatdau);
        txtEndmonth = v.findViewById(R.id.txtthangketthuc);
        txtStartmonth.setText("1/" + year);
        txtEndmonth.setText((month + 1) + "/" + year);
        btnstart.setOnClickListener(this);
        btnend.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChooseEndDay:
                datePickerEndday.show();
                break;
            case R.id.btnChooseStarDay:
                datePickerStartDay.show();
                break;
            case R.id.btnChooseEndMonth:
                showDialogPickMonth(true);
                break;
            case R.id.btnChooseStarMonth:
                showDialogPickMonth(false);
                break;
            case R.id.btnsearchthang:
                String mess = txtEndmonth.getText().toString() + txtStartmonth.getText().toString();
                if (mess.isEmpty()) {
                    Toast.makeText(getActivity(), "lon", Toast.LENGTH_LONG).show();
                    return;
                }
                senResult(mess);
                break;

        }
    }

    private void senResult(String mess) {
        if (getTargetFragment() == null) {
            return;
        }

//                Intent i = Timkiemtheothang.newIntent(mess);
//                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
//                dismiss();

        }

    private void showDialogPickMonth(final boolean check) {
        final Calendar today = Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getActivity(), new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                int month = selectedMonth+1;
                if (check == true){
                    txtEndmonth.setText((month)+"/"+selectedYear);
                }
                else {
                    txtStartmonth.setText((month)+"/"+selectedYear);
                }
            }
        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

        builder.setActivatedMonth(Calendar.JANUARY)
                .setMinYear(1990)
                .setActivatedYear(today.get(Calendar.YEAR))
                .setMaxYear(2030)
                .setMinMonth(Calendar.FEBRUARY)
                .setTitle("Chọn tháng và năm muốn tra cứu")
                .setMonthRange(Calendar.JANUARY, Calendar.DECEMBER)
                // .setMaxMonth(Calendar.OCTOBER)
                // .setYearRange(1890, 1890)
                // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
                //.showMonthOnly()
                // .showYearOnly()
                .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                    @Override
                    public void onMonthChanged(int selectedMonth) {

                        // Toast.makeText(MainActivity.this, " Selected month : " + selectedMonth, Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                    @Override
                    public void onYearChanged(int selectedYear) {

                        // Toast.makeText(MainActivity.this, " Selected year : " + selectedYear, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .show();
    }
}