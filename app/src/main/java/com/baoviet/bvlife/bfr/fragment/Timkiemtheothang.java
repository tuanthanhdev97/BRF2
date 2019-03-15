package com.baoviet.bvlife.bfr.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.activity.ObservableScrollView;
import com.baoviet.bvlife.bfr.activity.ScrollViewListener;
import com.baoviet.bvlife.bfr.model.SearchMonth;
import com.baoviet.bvlife.bfr.util.SapsepdoanhthuKH;
import com.baoviet.bvlife.bfr.util.Sapxepdoanhthuthang;
import com.baoviet.bvlife.bfr.util.SapxeptuyendungKH;
import com.baoviet.bvlife.bfr.util.Sapxeptuyendungthang;
import com.baoviet.bvlife.bfr.util.StringDefine;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
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
import com.whiteelephant.monthpicker.MonthPickerDialog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Timkiemtheothang extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, ScrollViewListener {

    private Dialog dialog;
    private CombinedChart mChart;
    private Spinner spin2;
    private TextView txtStartmonth,txtEndmonth,txtNoti;
    private TextView getstartmonth,getendmonth;
    private TextView txtInforStartMonth,txtInforEndMonth;
    private TextView txtTP;
    private ArrayList<String> arrCty;
    private ArrayList<Integer> arrTuyenDung;
    private ArrayList<Integer> arrTuyenDungKH;
    private ArrayList<String>  arrCheck;
    private Request.Priority mPriority = Request.Priority.HIGH;
    private String congty;
    private ArrayList<SearchMonth> arrData;
    private TextView txtGet;
    private String token,responseStatus ,responseMessage;
    private String thang;
    private double doanhthu,khthang;
    private String cty;
    private long s;
    private int year,month,day;
    private ImageButton btnBack;
    private ImageView btnSearchMore;
    private String[] namesArr;
    private TextView txtCheck;
    boolean unSortedCT=true;
    boolean unSorted=true;
    boolean unSortedMTD = true;
    private LinearLayout rlContent;
    private JSONObject obj;
    private ProgressDialog progress;
    private ObservableScrollView ScrollViewC = null;
    private ObservableScrollView ScrollViewD = null;
    private TableLayout header,scrollablePart,congty_table,fixedColumn;
    private TableRow row;
    private int[] scrollableColumnWidths;
    private int[] fixedColumnWidths;
    private ArrayList<SearchMonth> mlist;
    private TextView recyclableTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timkiemtheothag_layout, container, false);

        innit(view);
        return view;
    }

    private void getIntanceCal(){
        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day  = c.get(Calendar.DAY_OF_MONTH);

    }

    private void showData(ArrayList<SearchMonth> arrayList) {
        mlist = new ArrayList<>();
        fixedColumn.removeAllViews();
        scrollablePart.removeAllViews();
        mlist = arrayList;

        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        int fixedRowHeight = 90;
        for( int i = 0; i < mlist.size(); i++) {
            row = new TableRow(getActivity());
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.CENTER);
            row.addView(makeTableRowWithText2("T"+mlist.get(i).getThang(), scrollableColumnWidths[0], fixedRowHeight));
            fixedColumn.addView(row);
            row.setClickable(true);

        }

        for( int i = 0; i < mlist.size(); i++) {

//            fixedView = makeTableRowWithText2(arrBCkinhdoanh.get(i).getCongty(), scrollableColumnWidths[0], fixedRowHeight);
//            fixedColumn.addView(fixedView);

            row = new TableRow(getActivity());
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.CENTER);

            row.addView(makeTableRowWithText3(NumberFormat.getNumberInstance(Locale.US).format(mlist.get(i).getDoanhthu()), scrollableColumnWidths[1], fixedRowHeight));
            row.addView(makeTableRowWithText3(NumberFormat.getNumberInstance(Locale.US).format(mlist.get(i).getTuyendung()), scrollableColumnWidths[2], fixedRowHeight));
            row.addView(makeTableRowWithText3(NumberFormat.getNumberInstance(Locale.US).format(mlist.get(i).getKhthang()), scrollableColumnWidths[3], fixedRowHeight));
            row.addView(makeTableRowWithText3(NumberFormat.getNumberInstance(Locale.US).format(mlist.get(i).getTuyenhungKH()), scrollableColumnWidths[4], fixedRowHeight));

            scrollablePart.addView(row);
            row.setClickable(true);
        }

    }

    public TextView makeTableRowWithText(final String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels, int position) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(getActivity());
        recyclableTextView.setText(text);
        recyclableTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        recyclableTextView.setTextColor(Color.WHITE);
        recyclableTextView.setTextSize(16);
        recyclableTextView.setPadding(0,20,0,0);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);

        recyclableTextView.setMaxHeight(fixedHeightInPixels);
        recyclableTextView.setTag(position);
        recyclableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (position==0){
                    sortThang();
                }
                if(position == 1){
                    sortDoanhthu();
                }
                if(position == 2){
                    sortTuyenDung();
                }
                if(position == 3){
                    sortDoanhthuKH();
                }
                if(position == 4){
                    sortTuyendungKH();
                }
            }
        });
        return recyclableTextView;
    }

    public TextView makeTableRowWithText4(final String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(getActivity());
        recyclableTextView.setText(text);
        recyclableTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        recyclableTextView.setTextColor(Color.WHITE);
        recyclableTextView.setPadding(0,20,0,0);
        recyclableTextView.setTextSize(16);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }

    public TextView makeTableRowWithText2(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(getActivity());
        recyclableTextView.setText(text);
        recyclableTextView.setTextColor(Color.BLACK);
        recyclableTextView.setTextSize(16);
        recyclableTextView.setBackgroundResource(R.drawable.border_textview);
        recyclableTextView.setPadding(0,15,0,15);
        recyclableTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }



    public TextView makeTableRowWithText3(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(getActivity());
        recyclableTextView.setText(text+"");
        recyclableTextView.setTextColor(Color.BLACK);
        recyclableTextView.setTextSize(16);
        recyclableTextView.setBackgroundResource(R.drawable.border_textview);
        recyclableTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        recyclableTextView.setPadding(0,15,20,15);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }

    private void innit(View view) {

        if (getUserVisibleHint()){
            getTenCty();
        }

        scrollableColumnWidths = new int[]{25, 30, 30, 30,30,30, 30, 30,30,30, 30, 30,30, 30,30, 30,30,30};
        fixedColumnWidths = new int[]{25, 30, 30, 30,30,30, 30, 30,30,30, 30, 30,30, 30,30, 30,30,30};

        header = (TableLayout) view.findViewById(R.id.table_header);
        congty_table = (TableLayout) view.findViewById(R.id.table_Congty);
        fixedColumn = (TableLayout) view.findViewById(R.id.fixed_column);
        scrollablePart = (TableLayout) view.findViewById(R.id.scrollable_part);

        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        int fixedHeaderHeight = 120;

        row = new TableRow(getActivity());
        row.setLayoutParams(wrapWrapTableRowParams);

        row.addView(makeTableRowWithText4("Công ty", fixedColumnWidths[0], fixedHeaderHeight));
        congty_table.addView(row);

        row = new TableRow(getActivity());
        row.addView(makeTableRowWithText("Doanh thu (tr)", fixedColumnWidths[1], fixedHeaderHeight,1));
        row.addView(makeTableRowWithText("Tuyển Dụng", fixedColumnWidths[2], fixedHeaderHeight,2));
        row.addView(makeTableRowWithText("Doanh thu KH", fixedColumnWidths[3], fixedHeaderHeight,3));
        row.addView(makeTableRowWithText("Tuyển dụng KH", fixedColumnWidths[4], fixedHeaderHeight,4));
        header.addView(row);

        ScrollViewC = view.findViewById(R.id.ScrollViewC);
        ScrollViewC.setScrollViewListener(this);
        ScrollViewD = view.findViewById(R.id.ScrollViewD);
        ScrollViewD.setScrollViewListener(this);

        rlContent = view.findViewById(R.id.rlContent);

        txtTP = view.findViewById(R.id.txtTP);
        txtNoti = view.findViewById(R.id.txtNoti);
        mChart = (CombinedChart) view.findViewById(R.id.chartmonth);
        txtInforEndMonth=view.findViewById(R.id.txtInforEndMonth);
        txtInforStartMonth=view.findViewById(R.id.txtInforStartMonth);
        btnBack = view.findViewById(R.id.btnBackmonth);
        btnBack.setOnClickListener(this);
        btnSearchMore = view.findViewById(R.id.btnSearchMorethang);
        btnSearchMore.setOnClickListener(this);


        getIntanceCal();
        txtInforStartMonth.setText("1/"+year);
        txtInforEndMonth.setText((month+1)+"/"+year);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()){
                getTenCty();
        }
    }

    private void getData() {

        arrData = new ArrayList<>();
        // Nhan du lieu token tu Intent
        // Doc du lieu tu Services
        RequestQueue requestDoanhthu = Volley.newRequestQueue(getActivity());
        obj = new JSONObject();
        try {
            obj.put("strCompName", txtGet.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            obj.put("strFromDate", getstartmonth.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            obj.put("strToDate",  getendmonth.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
            JsonObjectRequest reqDoanhthu = new JsonObjectRequest(Request.Method.POST,StringDefine.URL_REVENUE_DATA_MONTH, obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                responseStatus = response.getString("responseStatus").toString();
                                responseMessage = response.getString("responseMessage");
                                if(responseStatus.equals(StringDefine.responseStatusLoginSuccess)){
                                    JSONObject jsonObject = response.getJSONObject("obj");
                                    JSONArray jsonArray = jsonObject.getJSONArray("lstDoanhThu");
                                    if (jsonArray.length()>=0){
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject obj = jsonArray.getJSONObject(i);
                                            String ngay = obj.getString("dataDate");
                                            s = obj.getLong("dataDate");
                                            Date d = new Date(s);
                                            DateFormat f = new SimpleDateFormat("M/yy");
                                            thang=f.format(d);
                                            Log.e("ngay",thang);
                                            khthang = Math.round(obj.getDouble("khThang"));
                                            doanhthu = Math.round(obj.getDouble("mtd"));
                                            int a = (int) doanhthu;
                                            int b = (int) khthang;
                                            for (int j=0;j<arrCheck.size();j++){
                                                if (arrCheck.get(j).equalsIgnoreCase(ngay)){
                                                    arrData.add(new SearchMonth(thang,a,arrTuyenDung.get(j),b,arrTuyenDungKH.get(j)));
                                                }
                                            }
                                           }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progress.cancel();
                                txtNoti.setVisibility(View.VISIBLE);
                                txtNoti.setText("Không có dữ liệu trong khoảng bạn muốn tìm kiếm . Vui lòng nhập lại ngày tháng");
                            }

                            sortThang();
                            progress.cancel();
                            setUpChart();
                            rlContent.setVisibility(View.VISIBLE);

                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.cancel();
                    txtNoti.setVisibility(View.VISIBLE);
                    txtNoti.setText("Không có dữ liệu trong khoảng bạn muốn tìm kiếm . Vui lòng nhập lại ngày tháng");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", token);
                    return headers;
                }

                @Override
                public Priority getPriority() {
                    return mPriority;
                }
                public void setPriority(Priority priority) {
                    mPriority = priority;
                }
            };

            requestDoanhthu.add(reqDoanhthu);
        }

    private void showDialogSearchMonth() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        dialog = new Dialog(getActivity());
        dialog.setTitle("Thangcode.com");
        dialog.setContentView(R.layout.dialog_theothang);
        dialog.show();

        spin2=(Spinner) dialog.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrCty);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spin2.setAdapter(adapter);
        spin2.setOnItemSelectedListener(this);

        ImageView btnstart = dialog.findViewById(R.id.btnChooseStarMonth);
        ImageView btnend = dialog.findViewById(R.id.btnChooseEndMonth);
        Button btnSearch = dialog.findViewById(R.id.btnsearchthang);
        txtGet = dialog.findViewById(R.id.txtText);
        txtCheck = dialog.findViewById(R.id.txtCheck);
        btnSearch.setOnClickListener(this);
        btnstart.setOnClickListener(this);
        btnend.setOnClickListener(this);

        txtStartmonth=dialog.findViewById(R.id.txtthangbatdau);
        txtEndmonth=dialog.findViewById(R.id.txtthangketthuc);
        getstartmonth=dialog.findViewById(R.id.getstartmonth);
        getendmonth=dialog.findViewById(R.id.getendtmonth);

        txtStartmonth.setText("1/"+year);
        txtEndmonth.setText((month+1)+"/"+year);
        getstartmonth.setText("1/"+"1/"+year);
        getendmonth.setText("1/"+(month+1)+"/"+year);

    }

    private void getTenCty() {
            final ProgressDialog progress = new ProgressDialog(getActivity());
            progress.setMessage("Đang tải dữ liệu...");
            progress.show();
        // Nhan du lieu token tu Intent
            arrCty = new ArrayList<>();
            final Intent intent = getActivity().getIntent();
            final Bundle bundle = intent.getBundleExtra("token");
            if(bundle != null){
                token = bundle.getString("token_login");
            }

            // Doc du lieu tu Services
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("token", token);

            JsonObjectRequest req = new JsonObjectRequest(StringDefine.URL_BCDOANHTHU, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progress.dismiss();
                            try {
                                responseStatus = response.getString("responseStatus").toString();
                                if(responseStatus.equals(StringDefine.responseStatusLoginSuccess)){
                                    JSONObject jsonObject = response.getJSONObject("obj");
                                    arrCty.add("Chọn công ty");
                                    JSONArray jsonArray = jsonObject.getJSONArray("lstDoanhThuNgay");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject obj = jsonArray.getJSONObject(i);
                                        congty = obj.getString("tenCT");
                                        arrCty.add(congty);
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            showDialogSearchMonth();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(),
                            error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", token);
                    return headers;
                }

                @Override
                public Priority getPriority() {
                    return mPriority;
                }

                public void setPriority(Priority priority) {
                    mPriority = priority;
                }
            };

            requestQueue.add(req);
    }

    private void showDialogPickMonth(final boolean check) {

        final Calendar today = Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getActivity(), new MonthPickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                int month = selectedMonth+1;
                if (check == true){
                    txtEndmonth.setText((month)+"/"+selectedYear);
                    getendmonth.setText("1/"+(month)+"/"+selectedYear);
                    txtInforEndMonth.setText((month)+"/"+selectedYear);
                }
                else {
                    txtStartmonth.setText((month)+"/"+selectedYear);
                    getstartmonth.setText("1/"+(month)+"/"+selectedYear);
                    txtInforStartMonth.setText((month)+"/"+selectedYear);
                }
            }
        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

        builder.setActivatedMonth(Calendar.JANUARY)
                .setMinYear(1990)
                .setActivatedYear(today.get(Calendar.YEAR))
                .setMaxYear(2050)
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

    private void getDataTuyendung(){

        arrTuyenDung = new ArrayList<>();
        arrTuyenDungKH = new ArrayList<>();
        arrCheck = new ArrayList<>();

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Đang tải dữ liệu...");
        progress.show();
        RequestQueue requestTuyenDung = Volley.newRequestQueue(getActivity());
        obj = new JSONObject();
        try {
            obj.put("strCompName", txtGet.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            obj.put("strFromDate", getstartmonth.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            obj.put("strToDate",  getendmonth.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest regTuyendung = new JsonObjectRequest(Request.Method.POST,StringDefine.URL_RECR_DATA_MONTH, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            responseStatus = response.getString("responseStatus").toString();
                            responseMessage = response.getString("responseMessage");
                            if(responseStatus.equals(StringDefine.responseStatusLoginSuccess)){
                                JSONObject jsonObject = response.getJSONObject("obj");

                                JSONArray jsonArray = jsonObject.getJSONArray("lstTuyenDung");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    String ngay = obj.getString("dataDate");
                                    int tuyendung =  obj.getInt("sttLKThang");
                                    int sttLKThang = Math.round(obj.getInt("sttLKThang"));
                                    double sttTLChiTieu = Math.round(obj.getDouble("sttTLChiTieu"));
                                    int sttKHThang = (int) Math.round(sttLKThang*100/sttTLChiTieu);
                                    arrTuyenDung.add(tuyendung);
                                    arrTuyenDungKH.add(sttKHThang);
                                    arrCheck.add(ngay);
                                    Log.e("thanhdeptrai",""+sttLKThang+""+sttTLChiTieu+""+tuyendung+"ddd"+sttKHThang);
                                }
                            }
                            getData();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progress.cancel();
                            txtNoti.setVisibility(View.VISIBLE);
                            txtNoti.setText("Không có dữ liệu trong khoảng bạn muốn tìm kiếm . Vui lòng nhập lại ngày tháng");
                        }


                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.cancel();
                txtNoti.setVisibility(View.VISIBLE);
                txtNoti.setText("Không có dữ liệu trong khoảng bạn muốn tìm kiếm . Vui lòng nhập lại ngày tháng");
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", token);
                return headers;
            }

            @Override
            public Priority getPriority() {
                return mPriority;
            }
            public void setPriority(Priority priority) {
                mPriority = priority;
            }
        };

        requestTuyenDung.add(regTuyendung);
    }

    private ArrayList<Entry> getLineEntriesData(ArrayList<Entry> entries){

                for (int i = 0 ; i<arrData.size();i++){
                    float position = i;
                    float data = arrData.get(i).getTuyendung();
                    entries.add(new Entry(position, data));
                }

        return entries;
    }

    private ArrayList<Entry> getLineEntriesData2(ArrayList<Entry> entries){

        for (int i = 0 ; i<arrData.size();i++){
            float position = i;
            float data = arrData.get(i).getTuyenhungKH();
            entries.add(new Entry(position, data));
        }
        return entries;
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries = getLineEntriesData(entries);


        LineDataSet set = new LineDataSet(entries, "Tuyển dụng");
        //set.setColor(Color.rgb(240, 238, 70));
        set.setColors(Color.rgb(244, 177, 131));
        set.setLineWidth(2.5f);
        set.setFillColor(Color.rgb(244, 177, 131));
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setDrawValues(false);

        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        d.addDataSet(set);

        ArrayList<Entry> entries2 = new ArrayList<Entry>();

        entries2 = getLineEntriesData2(entries2);

        LineDataSet set2 = new LineDataSet(entries2, "Tuyển dụng KH");
        //set.setColor(Color.rgb(240, 238, 70));
        set2.setColors(Color.rgb(0, 176, 240));
        set2.setLineWidth(2.5f);
        set2.enableDashedLine(20f,20f, 0f);
        set2.setFillColor(Color.rgb(0, 176, 240));
        set2.setMode(LineDataSet.Mode.LINEAR);
        set2.setDrawValues(false);

        set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        d.addDataSet(set2);

        return d;
    }

    private BarData generateBarData() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries = getBarEnteries(entries);

        BarDataSet set1 = new BarDataSet(entries, "Doanh thu (tr)");
        //set1.setColor(Color.rgb(60, 220, 78));
        set1.setColor(Color.rgb(0 ,149, 218));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setDrawValues(false);

        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();
        entries2 = getBarEnteries2(entries2);

        BarDataSet set2 = new BarDataSet(entries2, "Doanh thu KH");
        //set1.setColor(Color.rgb(60, 220, 78));
        set2.setColors(Color.rgb( 0,176 ,80));
        set2.setValueTextSize(10f);
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setDrawValues(false);


        float barWidth = 0.2f; // x2 dataset
        float groupSpace = 0.56f;
        float barSpace = 0.02f;


        BarData d = new BarData(set2,set1);
        d.setBarWidth(barWidth);
        d.groupBars(0, groupSpace, barSpace); // start at x = 0
        return d;
    }

    private ArrayList<BarEntry> getBarEnteries(ArrayList<BarEntry> entries){
            for (int i = 0; i < arrData.size(); i++) {
                float position = i;
                float data = arrData.get(i).getDoanhthu();
                entries.add(new BarEntry(position, data));
        }

        return  entries;
    }

    private ArrayList<BarEntry> getBarEnteries2(ArrayList<BarEntry> entries){

            for (int i = 0; i < arrData.size(); i++) {
                float position = i;
                float data = arrData.get(i).getKhthang();
                entries.add(new BarEntry(position, data));
        }

        return entries;

    }

    private void setUpChart() {
        Description title = new Description();
        title.setText("");

        namesArr = new String[arrData.size()];
        Collections.reverse(arrData);
        for (int i = 0; i < arrData.size(); i++) {
            namesArr[i] = "T"+arrData.get(i).getThang();
        }


        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR,CombinedChart.DrawOrder.LINE});

        Legend l = mChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setDescription(title);
        mChart.setHighlightFullBarEnabled(false);


        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        mChart.getAxisLeft().setAxisMinimum(0);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return namesArr[(int) value % namesArr.length];
            }
        });

        CombinedData data = new CombinedData();
        data.setData( generateLineData());
        data.setData(generateBarData());

        xAxis.setAxisMaximum(data.getXMax() + 0.9f);
        mChart.setData(data);
        mChart.invalidate();

//        mChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChooseEndMonth:
                showDialogPickMonth(true);
                break;
            case R.id.btnChooseStarMonth:
                showDialogPickMonth(false);
                break;
            case R.id.btnsearchthang:
                if (cty.equalsIgnoreCase("Chọn công ty")){
                    txtCheck.setVisibility(View.VISIBLE);
                }
                else {
                    dialog.dismiss();
                    getDataTuyendung();
                }
                break;
            case R.id.btnSearchMorethang:
                txtNoti.setVisibility(View.GONE);
                rlContent.setVisibility(View.GONE);
                showDialogSearchMonth();
                break;
            case  R.id.btnBackmonth:
                getActivity().onBackPressed();
                break;
        }
    }

    private void sortTuyenDung() {
        if(unSortedMTD) {
            Collections.sort(arrData, new Sapxeptuyendungthang());
            unSortedMTD = false;
            unSortedCT = true;
            unSorted=true;
        }
        else{
            Collections.reverse(arrData);
            unSortedMTD = true;
            unSortedCT = true;
            unSorted=true;
        }
            showData(arrData);
    }

    private void sortDoanhthu() {
        if(unSortedMTD) {
            Collections.sort(arrData, new Sapxepdoanhthuthang());
            unSortedMTD = false;
            unSortedCT = true;
            unSorted=true;
        }
        else{
            Collections.reverse(arrData);
            unSortedMTD = true;
            unSortedCT = true;
            unSorted=true;
        }
            showData(arrData);
    }

    private void sortDoanhthuKH() {
        if(unSortedMTD) {
            Collections.sort(arrData, new SapsepdoanhthuKH());
            unSortedMTD = false;
            unSortedCT = true;
            unSorted=true;
        }
        else{
            Collections.reverse(arrData);
            unSortedMTD = true;
            unSortedCT = true;
            unSorted=true;
        }
        showData(arrData);
    }

    private void sortTuyendungKH() {
        if(unSortedMTD) {
            Collections.sort(arrData, new SapxeptuyendungKH());
            unSortedMTD = false;
            unSortedCT = true;
            unSorted=true;
        }
        else{
            Collections.reverse(arrData);
            unSortedMTD = true;
            unSortedCT = true;
            unSorted=true;
        }
        showData(arrData);
    }

    private void sortThang() {
        Collections.sort(arrData, new Comparator<SearchMonth>() {
            @Override
            public int compare(SearchMonth arg0, SearchMonth arg1) {
                SimpleDateFormat format = new SimpleDateFormat("M/yy");
                int compareResult = 0;
                try {
                    Date arg0Date = format.parse(arg0.getThang());
                    Date arg1Date = format.parse(arg1.getThang());
                    compareResult = arg1Date.compareTo(arg0Date);
                } catch (ParseException e) {
                    e.printStackTrace();
//                                    compareResult = arg0.compareTo(arg1);
                }
                return compareResult;
            }
        });
        showData(arrData);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cty = parent.getItemAtPosition(position).toString();
        txtTP.setText(cty);
        txtGet.setText(cty);
        if (!cty.equalsIgnoreCase("Chọn công ty")){
            txtCheck.setVisibility(View.GONE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == ScrollViewC) {
            ScrollViewD.scrollTo(x, y);
        } else if(scrollView == ScrollViewD) {
            ScrollViewC.scrollTo(x, y);
        }
    }

}
