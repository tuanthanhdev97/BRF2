package com.baoviet.bvlife.bfr.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoviet.bvlife.bfr.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BCChiTietTuyenDungActivity extends AppCompatActivity {
    float barWidth;
    float barSpace;
    float groupSpace;
    BarChart chartThang,chartQuy ;
    String Congty;
    double TDThang,TLKHthang,CCBVLNthang,TDLKquy,TDKHquy,TLKHquy,TDLKnam,CCBVLNnam,TDKHnam,TLKHnam ;
    TextView txtCongty,txtTDthangtop,txtTDthang,txtCCBVLNthang,txtTLKHthang,txtTLKHquy,txtTLKHnam,txtTDLKquy,txtTDKHquy,txtTDLKnam,txtCCBVLNnam,txtTDKHnam,txtSoluong,txtSLhoatdong,txtTLhoatdong;
    ImageView btnBack,imgTLKHthang,imgTLKHquy,imgTLKHnam,imgTLhoatdong;
    int Soluong,Soluonghoatdong;
    float TLhoatdong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcchi_tiet_tuyen_dung);
        SharedPreferences sharedPreferences= this.getSharedPreferences("TVV", Context.MODE_PRIVATE);

        if(sharedPreferences!= null) {

            Soluong = sharedPreferences.getInt("Soluong", 90);
            Soluonghoatdong = sharedPreferences.getInt("Soluonghoatdong", 90);
            TLhoatdong = sharedPreferences.getFloat("TLhoatdong",95);
            Log.e("Soluong",Soluong+"");
            Log.e("Soluonghoatdong",Soluonghoatdong+"");
            Log.e("TLhoatdong",TLhoatdong+"");

            } else {
            Toast.makeText(this,"Use the default game setting",Toast.LENGTH_LONG).show();
        }

        innit();
        tuyenDungThang();
        tuyenDungQuy();
//        tuyenDungNam();

    }

    private void innit() {
        txtSoluong   = findViewById(R.id.txtSoluong);
        txtSLhoatdong = findViewById(R.id.txtSLhoatdong);
        txtTLhoatdong = findViewById(R.id.txtTLhoatdong);

        txtTDLKquy = findViewById(R.id.txtTDLKquy);
        txtTDKHquy= findViewById(R.id.txtTDKHquy);
        txtTLKHquy = findViewById(R.id.txtTLKHquy);

        txtTDLKnam  = findViewById(R.id.txtTDLKnam);
        txtCCBVLNnam= findViewById(R.id.txtCCBVLNnam);
        txtTDKHnam  = findViewById(R.id.txtTDKHnam);
        txtTLKHnam  = findViewById(R.id.txtTLKHnam);

        txtTLKHthang = findViewById(R.id.txtTLKHthang);
        txtCongty = findViewById(R.id.txtCongty);
        txtTDthangtop = findViewById(R.id.txtTDthangtop);
        txtTDthang = findViewById(R.id.txtTDthang);
        txtCCBVLNthang = findViewById(R.id.txtCCBVLNthang);
        btnBack = findViewById(R.id.btnBack);
        imgTLKHthang = findViewById(R.id.imgTLKHthang);
        imgTLKHquy = findViewById(R.id.imgTLKHquy);
        imgTLKHnam = findViewById(R.id.imgTLKHnam);
        imgTLhoatdong = findViewById(R.id.imgTLhoatdong);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        chartThang = (BarChart) findViewById(R.id.chartThang);
        chartQuy = findViewById(R.id.chartQuy);
//        chartNam = findViewById(R.id.chartNam);
        Intent intent = getIntent();
        Bundle bundle_token = intent.getBundleExtra("GetDetail");
        Bundle bundle_tokenTDL = intent.getBundleExtra("GetDetailTDL");
        if (bundle_token != null) {
            TDThang = Math.round(bundle_token.getDouble("TDThang"));
            TLKHthang = bundle_token.getDouble("TLKHthang");
            CCBVLNthang = Math.round(bundle_token.getDouble("CCBVLNthang"));
            Congty = bundle_token.getString("congty");

            TDLKquy = Math.round(bundle_token.getDouble("TDLKquy"));
            TDKHquy = Math.round(bundle_token.getDouble("TDKHquy"));
            TLKHquy = bundle_token.getDouble("TLKHquy");

            TDLKnam = Math.round(bundle_token.getDouble("TDLKnam"));
            CCBVLNnam = Math.round(bundle_token.getDouble("CCBVLNnam"));
            TDKHnam = bundle_token.getDouble("TDKHnam");
            TLKHnam = bundle_token.getDouble("TLKHnam");

        }
        if (bundle_tokenTDL != null) {
            TDThang = Math.round(bundle_tokenTDL.getDouble("TDThangTDL"));
            TLKHthang = bundle_tokenTDL.getDouble("TLKHthangTDL");
            CCBVLNthang = Math.round(bundle_tokenTDL.getDouble("CCBVLNthangTDL"));
            Congty = bundle_tokenTDL.getString("congtyTDL");

            TDLKquy = Math.round(bundle_tokenTDL.getDouble("TDLKquyTDL"));
            TDKHquy = Math.round(bundle_tokenTDL.getDouble("TDKHquyTDL"));
            TLKHquy = bundle_tokenTDL.getDouble("TLKHquyTDL");

            TDLKnam = Math.round(bundle_tokenTDL.getDouble("TDLKnamTDL"));
            CCBVLNnam = Math.round(bundle_tokenTDL.getDouble("CCBVLNnamTDL"));
            TDKHnam = bundle_tokenTDL.getDouble("TDKHnamTDL");
            TLKHnam = bundle_tokenTDL.getDouble("TLKHnamTDL");

        }
        Locale lc = new Locale("vi", "VN");
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(lc);

        txtSoluong.setText(decimalFormat.format(Soluong));
        txtSLhoatdong.setText(decimalFormat.format(Soluonghoatdong));
        txtTLhoatdong.setText(decimalFormat.format(TLhoatdong)+"%)");
        if (TLhoatdong>100){
            imgTLhoatdong.setImageResource(R.drawable.up);
        }
        else{
            imgTLhoatdong.setImageResource(R.drawable.down);
        }

        txtCongty.setText(Congty);
        txtTDthang.setText(decimalFormat.format(TDThang));
        txtTDthangtop.setText(decimalFormat.format(TDThang));
        txtCCBVLNthang.setText(decimalFormat.format(CCBVLNthang));
        txtTLKHthang.setText(decimalFormat.format(TLKHthang)+"%)");
        if (TLKHthang>100){
            imgTLKHthang.setImageResource(R.drawable.up);
        }
        else{
            imgTLKHthang.setImageResource(R.drawable.down);
        }

        txtTDLKquy.setText(decimalFormat.format(TDLKquy));
        txtTDKHquy.setText(decimalFormat.format(TDKHquy));
        txtTLKHquy.setText(decimalFormat.format(TLKHquy)+"%)");
        if (TLKHquy>100){
            imgTLKHquy.setImageResource(R.drawable.up);
        }
        else{
            imgTLKHquy.setImageResource(R.drawable.down);
        }

        txtTDLKnam.setText(decimalFormat.format(TDLKnam));
        txtCCBVLNnam.setText(decimalFormat.format(CCBVLNnam));
        txtTDKHnam.setText(decimalFormat.format(TDKHnam));
        txtTLKHnam.setText(decimalFormat.format(TLKHnam)+"%)");
        if (TLKHnam>100){
            imgTLKHnam.setImageResource(R.drawable.up);
        }
        else{
            imgTLKHnam.setImageResource(R.drawable.down);
        }
    }

//    private void tuyenDungNam() {
//        barWidth = 0.2f;
//        barSpace = 0f;
//        groupSpace = 0.4f;
//        chartNam.setDescription(null);
//        chartNam.setPinchZoom(false);
//        chartNam.setScaleEnabled(false);
//        chartNam.setDrawBarShadow(false);
//        chartNam.setDrawGridBackground(false);
//        int groupCount = 12;
//
//        ArrayList xVals = new ArrayList();
//
//        xVals.add("T.1      T.2");
//        xVals.add("T.2");
//        xVals.add("T.3      T.4");
//        xVals.add("T.4");
//        xVals.add("T.5      T.6");
//        xVals.add("T.6");
//        xVals.add("T.7      T.8");
//        xVals.add("T.8 ");
//        xVals.add("T.9      T.10");
//        xVals.add("T.10");
//        xVals.add("T.11     T.12");
//        xVals.add("T.12");
//
//        ArrayList yVals1 = new ArrayList();
//        ArrayList yVals2 = new ArrayList();
//        ArrayList yVals3 = new ArrayList();
//
//        yVals1.add(new BarEntry(0, (float) 1));
//        yVals2.add(new BarEntry(0, (float) 2));
//        yVals3.add(new BarEntry(0, (float) 3));
//
//        yVals1.add(new BarEntry(1, (float) 3));
//        yVals2.add(new BarEntry(1, (float) 4));
//        yVals3.add(new BarEntry(1, (float) 5));
//
//        yVals1.add(new BarEntry(2, (float) 5));
//        yVals2.add(new BarEntry(2, (float) 6));
//        yVals3.add(new BarEntry(2, (float) 7));
//
//        yVals1.add(new BarEntry(3, (float) 7));
//        yVals2.add(new BarEntry(3, (float) 8));
//        yVals3.add(new BarEntry(3, (float) 9));
//
//        yVals1.add(new BarEntry(4, (float) 9));
//        yVals2.add(new BarEntry(4, (float) 10));
//        yVals3.add(new BarEntry(4, (float) 11));
//
//        yVals1.add(new BarEntry(5, (float) 11));
//        yVals2.add(new BarEntry(5, (float) 12));
//        yVals3.add(new BarEntry(5, (float) 13));
//
//        yVals1.add(new BarEntry(6, (float) 12));
//        yVals2.add(new BarEntry(6, (float) 13));
//        yVals3.add(new BarEntry(6, (float) 14));
//
//        yVals1.add(new BarEntry(7, (float) 13));
//        yVals2.add(new BarEntry(7, (float) 14));
//        yVals3.add(new BarEntry(7, (float) 15));
//
//        yVals1.add(new BarEntry(8, (float) 15));
//        yVals2.add(new BarEntry(8, (float) 16));
//        yVals3.add(new BarEntry(8, (float) 17));
//
//        yVals1.add(new BarEntry(9, (float) 17));
//        yVals2.add(new BarEntry(9, (float) 18));
//        yVals3.add(new BarEntry(9, (float) 19));
//
//        yVals1.add(new BarEntry(10, (float) 19));
//        yVals2.add(new BarEntry(10, (float) 20));
//        yVals3.add(new BarEntry(10, (float) 21));
//
//        yVals1.add(new BarEntry(11, (float) 21));
//        yVals2.add(new BarEntry(11, (float) 22));
//        yVals3.add(new BarEntry(11, (float) 23));
//
//        BarDataSet set1, set2,set3;
//        set1 = new BarDataSet(yVals1, "LK tháng");
//        set1.setColor(Color.parseColor("#5B9BD5"));
//        set2 = new BarDataSet(yVals2, "CK năm trước");
//        set2.setColor(Color.parseColor("#ED7D31"));
//        set3 = new BarDataSet(yVals3, "KH tháng");
//        set3.setColor(Color.parseColor("#A5A5A5"));
//        BarData data = new BarData(set1, set2,set3);
//        data.setValueFormatter(new LargeValueFormatter());
//        data.setDrawValues(false);
//        chartNam.setData(data);
//        chartNam.getBarData().setBarWidth(barWidth);
//        chartNam.getXAxis().setAxisMinimum(0);
//        chartNam.getXAxis().setAxisMaximum(12);
//        chartNam.groupBars(0, groupSpace, barSpace);
//        chartNam.getData().setHighlightEnabled(false);
//        chartNam.invalidate();
//        Legend l = chartNam.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        XAxis xAxis = chartNam.getXAxis();
//
//        xAxis.setGranularityEnabled(true);
//        xAxis.setCenterAxisLabels(false);
//        xAxis.setDrawGridLines(false);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
////Y-axis
//        chartNam.getAxisRight().setEnabled(false);
//        YAxis leftAxis = chartNam.getAxisLeft();
//        leftAxis.setValueFormatter(new LargeValueFormatter());
//        leftAxis.setDrawGridLines(true);
//        leftAxis.setSpaceTop(35f);
//        leftAxis.setAxisMinimum(0f);
//
//    }

    private void tuyenDungQuy() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("TD LK quý");
        labels.add("TD KH quý");

        chartQuy.setDrawBarShadow(false);
        chartQuy.setDrawValueAboveBar(true);
        chartQuy.getDescription().setEnabled(false);
        chartQuy.setPinchZoom(false);
        chartQuy.setDrawGridBackground(false);


        XAxis xl = chartQuy.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        CategoryBarChartXaxisFormatter xaxisFormatter = new CategoryBarChartXaxisFormatter(labels);
        xl.setValueFormatter(xaxisFormatter);
        xl.setGranularity(1);
        xl.setTextColor(Color.parseColor("#508030"));

        YAxis yl = chartQuy.getAxisLeft();
        yl.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yl.setDrawGridLines(false);
        yl.setEnabled(false);
        yl.setAxisMinimum(0f);

        YAxis yr = chartQuy.getAxisRight();
        yr.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0, (float) TDLKquy));
        yVals1.add(new BarEntry(1, (float) TDKHquy));

        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "DataSet 1");
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        set1.setColors(new int[]{R.color.colorChartQuy}, this);
        data.setValueTextSize(10f);
        data.setBarWidth(.5f);
        chartQuy.setData(data);
        chartQuy.getLegend().setEnabled(false);
        //ẩn các đường kẻ nằm trong biểu đồ
        chartQuy.getAxisLeft().setDrawGridLines(true);
        chartQuy.getAxisRight().setDrawGridLines(true);
        chartQuy.getXAxis().setDrawGridLines(false);

//
//        //ẩn 2 đường kẻ và giá trị trục Y ở 2 bên
        chartQuy.getAxis(YAxis.AxisDependency.LEFT).setEnabled(false);
        chartQuy.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(true);
    }

    private void tuyenDungThang() {

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Số TD tháng");
        labels.add("CC BVLN tháng");

        chartThang.setDrawBarShadow(false);
        chartThang.setDrawValueAboveBar(true);
        chartThang.getDescription().setEnabled(false);
        chartThang.setPinchZoom(false);
        chartThang.setDrawGridBackground(false);


        XAxis xl = chartThang.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        CategoryBarChartXaxisFormatter xaxisFormatter = new CategoryBarChartXaxisFormatter(labels);
        xl.setValueFormatter(xaxisFormatter);
        xl.setGranularity(1);
        xl.setTextColor(Color.parseColor("#B65B0E"));

        YAxis yl = chartThang.getAxisLeft();
        yl.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yl.setDrawGridLines(false);
        yl.setEnabled(false);
        yl.setAxisMinimum(0f);

        YAxis yr = chartThang.getAxisRight();
        yr.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0, (float) TDThang));
        yVals1.add(new BarEntry(1, (float) CCBVLNthang));

        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "DataSet 1");
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        set1.setColors(new int[]{R.color.colorChartQuy}, this);
        data.setValueTextSize(10f);
        data.setBarWidth(.5f);
        chartThang.setData(data);
        chartThang.getLegend().setEnabled(false);
        //ẩn các đường kẻ nằm trong biểu đồ
        chartThang.getAxisLeft().setDrawGridLines(true);
        chartThang.getAxisRight().setDrawGridLines(true);
        chartThang.getXAxis().setDrawGridLines(false);

//
//        //ẩn 2 đường kẻ và giá trị trục Y ở 2 bên
        chartThang.getAxis(YAxis.AxisDependency.LEFT).setEnabled(false);
        chartThang.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(true);
    }
    public class CategoryBarChartXaxisFormatter implements IAxisValueFormatter {

        ArrayList<String> mValues;

        public CategoryBarChartXaxisFormatter(ArrayList<String> values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int val = (int) value;
            String label = "";
            if (val >= 0 && val < mValues.size()) {
                label = mValues.get(val);
            } else {
                label = "";
            }
            return label;
        }

    }
}
