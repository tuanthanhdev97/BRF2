package com.baoviet.bvlife.bfr.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.baoviet.bvlife.bfr.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BCChiTietDoanhThuActivity extends AppCompatActivity {
    ImageView btnback;
    TextView txtCongty, txtLKthang, txtKHthang, txtCKnamtruoc, txtLKquy, txtKHquy, txtDtngay,txtLKnam,txtKHnam,txtLKnamtruoc,txtTLthang,txtTLLKthang,txtTLKHquy,txtTLnam,txtTlLKnnamtruoc;
    String Congty;
    double Lkthang, Cknamtruoc, Khthang, Dtngay, Lkquy, Khquy,LKnamtruoc,KHnam,LKnam,tlthang,tllkthang,TLKHquy,TLnam,TlLKnnamtruoc;
    HorizontalBarChart chartThang, chartQuy;
    BarChart chartNam;
    ImageView imgTLthang,imgTLLKthang,imgTLKHquy,imgLKnam,imgTLLKnam;
    float barWidth;
    float barSpace;
    float groupSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcchitiet);
        Anhxa();
    }


    private void Anhxa() {
        imgTLthang=findViewById(R.id.imgTLthang);
        imgTLLKthang = findViewById(R.id.imgTLLKthang);
        imgTLKHquy = findViewById(R.id.imgTLKHquy);
        imgLKnam  =findViewById(R.id.imgLKnam);
        imgTLLKnam=findViewById(R.id.imgTLLKnam);

        txtTLKHquy = findViewById(R.id.txtTLKHquy);
        txtTLnam = findViewById(R.id.txtTLnam);
        txtTlLKnnamtruoc  = findViewById(R.id.txtTLLKnamtruoc);
        txtTLthang = findViewById(R.id.txtTLthang);
        txtTLLKthang = findViewById(R.id.txtTLLKthang);
        chartQuy = findViewById(R.id.chartQuy);
        chartThang = findViewById(R.id.chartThang);
//        chartNam = findViewById(R.id.chartNam);
        txtCongty = (TextView) findViewById(R.id.txtCongty);
        txtLKthang = (TextView) findViewById(R.id.txtLKthang);
        txtKHthang = (TextView) findViewById(R.id.txtKHthang);
        txtCKnamtruoc = (TextView) findViewById(R.id.txtCKnamtruoc);
        txtLKquy = (TextView) findViewById(R.id.txtLKquy);
        txtKHquy = (TextView) findViewById(R.id.txtKHquy);
        txtDtngay = (TextView) findViewById(R.id.txtDTngay);
        txtLKnam = findViewById(R.id.txtLKnam);
        txtLKnamtruoc = findViewById(R.id.txtLKnamtruoc);
        txtKHnam = findViewById(R.id.txtKHnam);
        btnback = (ImageView) findViewById(R.id.btnBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // Nhan du lieu token tu Intent
        Intent intent = getIntent();
        Bundle bundle_token = intent.getBundleExtra("GetDetail");
        Bundle bundle_tokenTDL = intent.getBundleExtra("GetDetailTDL");
        if (bundle_token != null) {
            Lkthang = Math.round(bundle_token.getDouble("LKthang"));
            Cknamtruoc = Math.round(bundle_token.getDouble("CKnamtruoc"));
            Khthang = Math.round(bundle_token.getDouble("Khthang"));
            Congty = bundle_token.getString("Congty");
            Dtngay = bundle_token.getDouble("Dtngay");
            Lkquy = bundle_token.getDouble("Lkquy");
            Khquy = bundle_token.getDouble("Khquy");

            LKnamtruoc= bundle_token.getDouble("LKnamtruoc");
            KHnam= bundle_token.getDouble("KHNam");
            LKnam= bundle_token.getDouble("LKnam");
            TLnam= bundle_token.getDouble("TLNam");
            TlLKnnamtruoc= bundle_token.getDouble("TLLuyKeNam");

            tlthang=bundle_token.getDouble("TLthang");
            tllkthang=bundle_token.getDouble("Tllkthang");
            TLKHquy=bundle_token.getDouble("TLKHquy");
        }
        if (bundle_tokenTDL != null){
            Lkthang = Math.round(bundle_tokenTDL.getDouble("LKthangTDL"));
            Cknamtruoc = Math.round(bundle_tokenTDL.getDouble("CKnamtruocTDL"));
            Khthang = Math.round(bundle_tokenTDL.getDouble("KhthangTDL"));
            Congty = bundle_tokenTDL.getString("CongtyTDL");
            Dtngay = bundle_tokenTDL.getDouble("DtngayTDL");
            Lkquy = bundle_tokenTDL.getDouble("LkquyTDL");
            Khquy = bundle_tokenTDL.getDouble("KhquyTDL");

            LKnamtruoc= bundle_tokenTDL.getDouble("LKnamtruocTDL");
            KHnam= bundle_tokenTDL.getDouble("KHNamTDL");
            LKnam= bundle_tokenTDL.getDouble("LKnamTDL");
            TLnam= bundle_tokenTDL.getDouble("TLNamTDL");
            TlLKnnamtruoc= bundle_tokenTDL.getDouble("TLLuyKeNamTDL");

            tlthang=bundle_tokenTDL.getDouble("TLthangTDL");
            tllkthang=bundle_tokenTDL.getDouble("TllkthangTDL");
            TLKHquy=bundle_tokenTDL.getDouble("TLKHquyTDL");
        }

        Locale lc = new Locale("vi", "VN");
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(lc);

        txtCongty.setText(Congty);
        txtLKthang.setText(decimalFormat.format(Lkthang));
        txtCKnamtruoc.setText(decimalFormat.format(Cknamtruoc));
        txtKHthang.setText(decimalFormat.format(Khthang));
        txtDtngay.setText(decimalFormat.format(Dtngay));

        txtLKquy.setText(decimalFormat.format(Lkquy));
        txtKHquy.setText(decimalFormat.format(Khquy));
        txtTLKHquy.setText(decimalFormat.format(TLKHquy)+"%)");
        if (TLKHquy>100){
            imgTLKHquy.setImageResource(R.drawable.up);
        }
        else {
            imgTLKHquy.setImageResource(R.drawable.down);
        }

        txtLKnam.setText(decimalFormat.format(LKnam));
        txtLKnamtruoc.setText(decimalFormat.format(LKnamtruoc));
        txtKHnam.setText(decimalFormat.format(KHnam));
        txtTLnam.setText(decimalFormat.format(TLnam)+"%)");
        if (TLnam>100){
            imgLKnam.setImageResource(R.drawable.up);
        }
        else{
            imgLKnam.setImageResource(R.drawable.down);
        }
        txtTlLKnnamtruoc.setText(decimalFormat.format(TlLKnnamtruoc)+"%)");
        if (TlLKnnamtruoc>100){
            imgTLLKnam.setImageResource(R.drawable.up);
        }
        else{
            imgTLLKnam.setImageResource(R.drawable.down);
        }
        txtTLthang.setText(decimalFormat.format(tlthang)+"%)");
        if (tlthang>100){
            imgTLthang.setImageResource(R.drawable.up);
        }
        else{
            imgTLthang.setImageResource(R.drawable.down);
        }
        txtTLLKthang.setText(decimalFormat.format(tllkthang)+"%)");
        if (tllkthang>100){
            imgTLLKthang.setImageResource(R.drawable.up);
        }
        else {
            imgTLLKthang.setImageResource(R.drawable.down);
        }

        initChartThang();
        initchartQuy();
//        initChartNam();
    }

//    private void initChartNam() {
//
//
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

    private void initchartQuy() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("LK quý");
        labels.add("KH quý");

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

        yVals1.add(new BarEntry(0, (float) Lkquy));
        yVals1.add(new BarEntry(1, (float) Khquy));

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

    private void initChartThang() {

        ArrayList<String> labels = new ArrayList<>();
        labels.add("LK tháng");
        labels.add("Ck năm trước");
        labels.add("KH tháng");

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

        yVals1.add(new BarEntry(0, (float) Lkthang));
        yVals1.add(new BarEntry(1, (float) Cknamtruoc));
        yVals1.add(new BarEntry(2, (float) Khthang));


        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "DataSet 1");
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        set1.setColors(new int[]{R.color.colorChartThang}, this);
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
