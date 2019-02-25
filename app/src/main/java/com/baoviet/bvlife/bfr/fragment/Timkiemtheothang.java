package com.baoviet.bvlife.bfr.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.adapter.SearchDayAdapter;
import com.baoviet.bvlife.bfr.adapter.SearchMonthAdapter;
import com.baoviet.bvlife.bfr.model.SearchDay;
import com.baoviet.bvlife.bfr.model.SearchMonth;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

public class Timkiemtheothang extends Fragment implements View.OnClickListener {
    private Dialog dialog;
    private TextView txtStartday,txtEndday;
    private ListView lvThang;
    private CombinedChart mChart;
    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "June"
    };
    //Overriden method onCreateView
    public static final  int TARGET_FRAGMENT_REQUEST_CODE = 1;
    public static final  String EXTRA_GREETING_MESSAGE = "message";


    DatePickerDialog datePickerStartDay,datePickerEndday;


    public Timkiemtheothang() {
        // Required empty public constructor
    }


    private ArrayList<Entry> getLineEntriesData(ArrayList<Entry> entries){
        entries.add(new Entry(1, 20));
        entries.add(new Entry(2, 10));
        entries.add(new Entry(3, 8));
        entries.add(new Entry(4, 40));
        entries.add(new Entry(5, 37));

        return entries;
    }
    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries = getLineEntriesData(entries);

        LineDataSet set = new LineDataSet(entries, "Doanh thu (tr)");
        //set.setColor(Color.rgb(240, 238, 70));
        set.setColors(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }

    private BarData generateBarData() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries = getBarEnteries(entries);

        BarDataSet set1 = new BarDataSet(entries, "Tuyển dụng");
        //set1.setColor(Color.rgb(60, 220, 78));
        set1.setColors(Color.rgb(60, 220, 78));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        float barWidth = 0.45f; // x2 dataset


        BarData d = new BarData(set1);
        d.setBarWidth(barWidth);


        return d;
    }
    private ArrayList<BarEntry> getBarEnteries(ArrayList<BarEntry> entries){
        entries.add(new BarEntry(1, 25));
        entries.add(new BarEntry(2, 30));
        entries.add(new BarEntry(3, 38));
        entries.add(new BarEntry(4, 10));
        entries.add(new BarEntry(5, 15));
        return  entries;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timkiemtheothag_layout, container, false);
        lvThang = (ListView) view.findViewById(R.id.lvThang);

        mChart = (CombinedChart) view.findViewById(R.id.chart1);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR,  CombinedChart.DrawOrder.LINE
        });
        Legend l = mChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) value % mMonths.length];
            }
        });
        CombinedData data = new CombinedData();

        data.setData( generateLineData());
        data.setData(generateBarData());
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        mChart.setData(data);
        mChart.invalidate();
        ArrayList<SearchMonth> arrContact = new ArrayList<>();

        SearchMonth contact1 = new SearchMonth("1/2019",142342,34);
        SearchMonth contact2 = new SearchMonth("2/2019",245845,67);
        SearchMonth contact3 = new SearchMonth("3/2019",342345,13);
        SearchMonth contact4 = new SearchMonth("4/2019",445545,90);
        SearchMonth contact5 = new SearchMonth("5/2019",514534,32);
        SearchMonth contact6 = new SearchMonth("6/2019",624934,16);
        SearchMonth contact7 = new SearchMonth("7/2019",734530,47);
        SearchMonth contact8 = new SearchMonth("8/2019",142342,43);
        SearchMonth contact9 = new SearchMonth("9/2019",245845,65);
        SearchMonth contact10 = new SearchMonth("10/2019",342345,24);
        SearchMonth contact11 = new SearchMonth("11/2019",445545,78);
        SearchMonth contact12 = new SearchMonth("12/2019",514534,43);
        SearchMonth contact13 = new SearchMonth("1/2020",624934,98);
        SearchMonth contact14 = new SearchMonth("2/2020",734530,43);

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

        SearchMonthAdapter customAdaper = new SearchMonthAdapter(getContext(),R.layout.item_searchmont,arrContact);
        lvThang.setAdapter(customAdaper);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    private void showDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dialog = new Dialog(getActivity());
        dialog.setTitle("Thangcode.com");
        dialog.setContentView(R.layout.dialog_theongay);
        dialog.show();

        ImageView btnstart = dialog.findViewById(R.id.btnChooseStarDay);
        ImageView btnend = dialog.findViewById(R.id.btnChooseEndDay);
        Button btnSearch = dialog.findViewById(R.id.btnsearchngay);
        txtStartday=dialog.findViewById(R.id.txtngaybatdau);
        txtEndday=dialog.findViewById(R.id.txtngayketthuc);
        txtStartday.setText("1/"+(month+1)+"/"+year);
        txtEndday.setText(day+"/"+(month+1)+"/"+year);
        btnstart.setOnClickListener(this);
        btnend.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}