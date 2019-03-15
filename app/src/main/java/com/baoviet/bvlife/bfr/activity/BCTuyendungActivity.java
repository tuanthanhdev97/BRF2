package com.baoviet.bvlife.bfr.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
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
import com.baoviet.bvlife.bfr.adapter.BCChungchiExpendAdapter;
import com.baoviet.bvlife.bfr.adapter.BCKinhdoanhExpendAdapter;
import com.baoviet.bvlife.bfr.adapter.BCTuyendungExpendAdapter;
import com.baoviet.bvlife.bfr.adapter.MenuExpendableAdapter;
import com.baoviet.bvlife.bfr.model.BCChungchi;
import com.baoviet.bvlife.bfr.model.BCKinhdoanh;
import com.baoviet.bvlife.bfr.model.BCTuyendung;
import com.baoviet.bvlife.bfr.model.MenuBaoCao;
import com.baoviet.bvlife.bfr.util.KD_CongtyComparator;
import com.baoviet.bvlife.bfr.util.MTDComparator;
import com.baoviet.bvlife.bfr.util.SolieutrongngayComparator;
import com.baoviet.bvlife.bfr.util.StringDefine;
import com.baoviet.bvlife.bfr.util.TD_CongtyComparator;
import com.baoviet.bvlife.bfr.util.TD_DCT_Comparator;
import com.baoviet.bvlife.bfr.util.TD_LKT_Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BCTuyendungActivity extends AppCompatActivity implements ScrollViewListener {

    private HorizontalScrollView HorizontalScrollViewD,HorizontalScrollViewB;
    private ObservableScrollView ScrollViewC = null;
    private ObservableScrollView ScrollViewD = null;

    Toolbar toolbar;
    SearchView svBc;
//    ListView ListViewBc,ListViewDTL;
    HashMap<String, List<BCTuyendung>> listdataChild;
    Request.Priority mPriority = Request.Priority.HIGH;
    ArrayList<BCTuyendung> listdataHeader,listQuy,listnam,listdataTDL,listQuyTDL,listnamTDL;
    ArrayList<BCKinhdoanh> listTVVTHT,listTVV;
    BCTuyendungExpendAdapter bcExpendAdapter,bcExpendAdapterTDl;

    ExpandableListView expandableListViewMenu;
    HashMap<String,List<String>> listdataChildMenu;
    List<MenuBaoCao> listdataHeaderMenu;
    DrawerLayout drawerLayout;
    MenuExpendableAdapter menuExpendableAdapter;
    String test;
    String token, responseMessage, responseStatus;
    String congty, stUpdateTime, stUpLoadTime;
    public double thuctuyen_luykethang;
    public double thuctuyen_datchitieu;//%
    public double thuctuyen_luyke;
    public double dacapCC_luykethang;
    public double dacapCC_luyke;
    public double quy_thuctuyen;
    public double quy_chitieu;
    public double quy_datchitieu;
    public double kehoachnam;
    public double datkehoachnam;
    public int soLuong;
    public int slHoatDong;
    public double tlHoatDong;
    double STD = 0;
    double TLKHthang = 0;
    double TDLKnam = 0;
    double CCBVLNthang = 0;
    double CCBVLNnam = 0;

    double  TDLKquy = 0;
    double  TDKHquy = 0;
    double  TLKHquy = 0;

    double  TDKHnam = 0;
    double  TLKHnam = 0;


    //child dong bao cao
    TextView giatri, updateTime;
    //Textview header
//    TextView txtLuykethang, txtDatchitieu, txtheaderBCTDCongty;

    boolean unSortedCT=true;
    boolean unSorted=true;
    boolean unSortedMTD = true;

    ImageView imgMenu,imgSearch;

    public static int orientationBCTD;

    private TextView recyclableTextView;
    private TextView fixedView;
    int i ;
    private TableLayout header,scrollablePart,congty_table,fixedColumn;
    private TableRow row;
    private ArrayList<BCTuyendung> arrBCkinhdoanh;
    int[] scrollableColumnWidths;
    int[] fixedColumnWidths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bctuyendung);

        orientationBCTD = getResources().getConfiguration().orientation;
        if (orientationBCTD == Configuration.ORIENTATION_LANDSCAPE){
            scrollableColumnWidths = new int[]{25, 15, 15, 15,15,15, 15, 15,15,15, 15, 15,15, 15,15, 15,15, 15};
            fixedColumnWidths = new int[]{25, 15, 15, 15,15,15, 15, 15,15,15, 15, 15,15, 15,15, 15,15, 15};
        }
        else{
            scrollableColumnWidths = new int[]{40, 25, 25, 30,35,35, 30, 30,28,30, 25, 35,35, 35,35, 35,35, 35};
            fixedColumnWidths = new int[]{40, 25, 25, 30,35,35, 30, 30,28,30, 25, 35,35, 35,35, 35,35, 35};
        }
        Anhxa();
        ActionToolbar();
        getReportData();

    }
    private void getData(ArrayList<BCTuyendung> arrayList) {
        arrBCkinhdoanh = new ArrayList<>();
        fixedColumn.removeAllViews();
        scrollablePart.removeAllViews();
        arrBCkinhdoanh = arrayList;

        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        int fixedRowHeight = 100;
        for( i = 0; i < arrBCkinhdoanh.size(); i++) {
            row = new TableRow(this);
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.CENTER);
            row.addView(makeTableRowWithText(arrBCkinhdoanh.get(i).getCongty(), scrollableColumnWidths[0], fixedRowHeight));
            fixedColumn.addView(row);
            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int rowIndex = fixedColumn.indexOfChild(view);
                    intentData(arrBCkinhdoanh.get(rowIndex).getCongty());
                }
            });
        }

        for( i = 0; i < arrBCkinhdoanh.size(); i++) {

//            fixedView = makeTableRowWithText2(arrBCkinhdoanh.get(i).getCongty(), scrollableColumnWidths[0], fixedRowHeight);
//            fixedColumn.addView(fixedView);

            row = new TableRow(this);
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.CENTER);

            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getThuctuyen_luykethang()), scrollableColumnWidths[1], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getThuctuyen_datchitieu()), scrollableColumnWidths[2], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getThuctuyen_luyke()), scrollableColumnWidths[3], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getDacapCC_luykethang()), scrollableColumnWidths[4], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getDacapCC_luyke()), scrollableColumnWidths[5], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getQuy_thuctuyen()), scrollableColumnWidths[6], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getQuy_chitieu()), scrollableColumnWidths[7], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getQuy_datchitieu()), scrollableColumnWidths[8], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getKehoachnam()), scrollableColumnWidths[9], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getDatkehoachnam()), scrollableColumnWidths[10], fixedRowHeight));
            scrollablePart.addView(row);
            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int rowIndex = scrollablePart.indexOfChild(view);
                    intentData(arrBCkinhdoanh.get(rowIndex).getCongty());
                }
            });

        }

    }
    private void intentData(String a) {
        for (int t = 0;t<listdataHeader.size();t++){
            if (a.equalsIgnoreCase(listdataHeader.get(t).getCongty())){
                Intent intent_bc = new Intent(BCTuyendungActivity.this,BCChiTietTuyenDungActivity.class);

                                                Bundle bundle_get = new Bundle();


                                                Double TDThang =listdataHeader.get(t).getThuctuyen_luykethang();
                                                Double TLKHthang =listdataHeader.get(t).getThuctuyen_datchitieu();
                                                Double CCBVLNthang =listdataHeader.get(t).getDacapCC_luykethang();
                                                String congty = listdataHeader.get(t).getCongty();

                                                Double TDLKquy =listdataHeader.get(t).getQuy_thuctuyen();
                                                Double TDKHquy =listdataHeader.get(t).getQuy_chitieu();
                                                Double TLKHquy =listdataHeader.get(t).getQuy_datchitieu();

                                                Double TDLKnam  =listdataHeader.get(t).getThuctuyen_luyke();
                                                Double CCBVLNnam =listdataHeader.get(t).getDacapCC_luyke();
                                                Double TDKHnam  =listdataHeader.get(t).getKehoachnam();
                                                Double TLKHnam  =listdataHeader.get(t).getDatkehoachnam();

                                                int Soluong  =listTVV.get(t).getSoLuong();
                                                int Soluonghoatdong =listTVV.get(t).getSlHoatDong();
                                                double e  =listTVV.get(t).getTlHoatDong();
                                                float TLhoatdong = (float) e;

                                                SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("TVV", Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putInt("Soluong", Soluong);
                                                editor.putInt("Soluonghoatdong", Soluonghoatdong);
                                                editor.putFloat("TLhoatdong",TLhoatdong);
                                                editor.apply();

                                                bundle_get.putDouble("TDThang", TDThang);
                                                bundle_get.putDouble("TLKHthang", TLKHthang);
                                                bundle_get.putDouble("CCBVLNthang", CCBVLNthang);
                                                bundle_get.putString("congty", congty);

                                                bundle_get.putDouble("TDLKquy", TDLKquy);
                                                bundle_get.putDouble("TDKHquy", TDKHquy);
                                                bundle_get.putDouble("TLKHquy", TLKHquy);

                                                bundle_get.putDouble("TDLKnam", TDLKnam);
                                                bundle_get.putDouble("CCBVLNnam", CCBVLNnam);
                                                bundle_get.putDouble("TDKHnam", TDKHnam);
                                                bundle_get.putDouble("TLKHnam", TLKHnam);
//
//                                        bundle_get.putDouble("Lkquy", lkquy);
//                                        bundle_get.putDouble("Khquy", khquy);
//
//
                                                intent_bc.putExtra("GetDetail",bundle_get);
//
                                                startActivity(intent_bc);
            }

        }


    }
    public TextView makeTableRowWithText(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(this);
        recyclableTextView.setBackgroundResource(R.drawable.border_textview);
        recyclableTextView.setText(text);
        recyclableTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        recyclableTextView.setPadding(0,15,20,15);
        recyclableTextView.setTextColor(Color.BLACK);
        recyclableTextView.setTextSize(16);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }

    public TextView makeTableRowWithText(final String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels, int position) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(this);
        recyclableTextView.setText(text);
        recyclableTextView.setTextColor(Color.WHITE);
        recyclableTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        recyclableTextView.setPadding(0,20,0,0);
        recyclableTextView.setTextSize(16);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        recyclableTextView.setTag(position);
        recyclableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (position==1){
                    sortData();
                }
                if(position == 2){
                    sortDataMTD();
                }
            }
        });
        return recyclableTextView;
    }

    private void sortDataMTD() {
                        if(unSortedMTD) {
                                            Collections.sort(listdataHeader, new TD_DCT_Comparator());
                                            unSortedMTD = false;
                                            unSortedCT = true;
                                            unSorted=true;
                                        }
                                        else{
                                            Collections.reverse(listdataHeader);
                                            unSortedMTD = true;
                                            unSortedCT = true;
                                            unSorted=true;
                                        }
                            getData(listdataHeader);
    }

    private void sortData() {
        if(unSorted) {
                                            Collections.sort(listdataHeader, new TD_LKT_Comparator());
                                            unSorted = false;
                                            unSortedCT = true;
                                            unSortedMTD = true;
                                        }
                                        else {
                                            Collections.reverse(listdataHeader);
                                            unSorted = true;
                                            unSortedCT = true;
                                            unSortedMTD = true;
                                        }
                            getData(listdataHeader);
    }

    public TextView makeTableRowWithText2(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(this);
        recyclableTextView.setText(text);
        recyclableTextView.setTextColor(Color.BLACK);
        recyclableTextView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        recyclableTextView.setPadding(0,15,20,15);
        recyclableTextView.setBackgroundResource(R.drawable.border_textview);
        recyclableTextView.setTextSize(16);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }

    private void sortDataCongty() {
        if(unSortedCT) {
            Collections.sort(listdataHeader, new TD_CongtyComparator());
            unSortedCT=false;
            unSorted =true;
            unSortedMTD = true;
        }
        else {
            Collections.reverse(listdataHeader);
            unSortedCT=true;
            unSorted =true;
            unSortedMTD = true;
        }
//        bcExpendAdapter = new BCTuyendungExpendAdapter(getApplicationContext(),listdataHeader);
//        ListViewBc.setAdapter(bcExpendAdapter);
    }




    private void getReportData() {
        // Add a header to the ListView
        LayoutInflater inflater = getLayoutInflater();
//        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.header_bctuyendung, ListViewBc,false);
//        ListViewBc.addHeaderView(header);
//
//        //header
//        txtLuykethang = (TextView)findViewById(R.id.txtheaderBCTDthuctuyen_luykethang);
//        txtDatchitieu = findViewById(R.id.txtheaderBCTDthuctuyen_datchitieu);
//        txtheaderBCTDCongty = findViewById(R.id.txtheaderBCTDCongty);

        // Nhan du lieu token tu Intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("token");
        if(bundle != null){
            token = bundle.getString("token_login");
        }

        // Doc du lieu tu Services
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        getTTTTT(params);
        JsonObjectRequest req = new JsonObjectRequest(StringDefine.URL_BCTUYENDUNG, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            responseStatus = response.getString("responseStatus").toString();
                            responseMessage = response.getString("responseMessage");
                            //int[] colors = {0, 0x080808, 0}; // red for the example #080808
                            if(responseStatus.equals(StringDefine.responseStatusLoginSuccess)){
                                JSONObject jsonObject = response.getJSONObject("obj");

                                stUpdateTime = jsonObject.getString("maxDataDate");
                                stUpLoadTime = jsonObject.getString("maxUploadDate");
                                updateTime.setText(updateTime.getText() + " " + stUpdateTime + " cập nhật lúc " + stUpLoadTime);

                                JSONArray jsonArray = jsonObject.getJSONArray("lstTuyenDung");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    congty = obj.getString("tenCT");
                                    thuctuyen_luykethang = Math.round(obj.getDouble("sttLKThang"));
                                    thuctuyen_datchitieu = obj.getDouble("sttTLChiTieu");
                                    //expandListViewBc.setChildDivider(getResources().getDrawable(R.color.cardview_dark_background));

                                    thuctuyen_luyke = Math.round(obj.getDouble("sttLK"));
                                    dacapCC_luykethang = Math.round(obj.getDouble("cccLKThang"));
                                    dacapCC_luyke = Math.round(obj.getDouble("cccLK"));

                                    quy_thuctuyen = obj.getDouble("qThucTuyen");
                                    quy_chitieu = obj.getDouble("qChiTieu");
                                    quy_datchitieu = obj.getDouble("qTLChiTieu");
                                    kehoachnam = Math.round(obj.getDouble("khNAM"));
                                    datkehoachnam = obj.getDouble("tlKHNam");

                                    STD = STD+thuctuyen_luykethang;
                                    TLKHthang = TLKHthang+thuctuyen_datchitieu;
                                    TDLKnam = TDLKnam+thuctuyen_luyke;
                                    CCBVLNthang = CCBVLNthang+dacapCC_luykethang;
                                    CCBVLNnam = CCBVLNnam+dacapCC_luyke;
                                    TDLKquy= TDLKquy+quy_thuctuyen;
                                    TDKHquy=TDKHquy+quy_chitieu;
                                    TLKHquy=TLKHquy+quy_datchitieu;
                                    TDKHnam=TDKHnam+kehoachnam;
                                    TLKHnam=TLKHnam+datkehoachnam;


                                    listdataHeader.add(new BCTuyendung(congty,thuctuyen_luykethang,thuctuyen_datchitieu,thuctuyen_luyke,dacapCC_luykethang,dacapCC_luyke,quy_thuctuyen,quy_chitieu,quy_datchitieu,kehoachnam,datkehoachnam));
//                                    listQuy.add(new BCTuyendung(quy_thuctuyen,quy_chitieu,quy_datchitieu));
//                                    listnam.add(new BCTuyendung(thuctuyen_luyke,dacapCC_luyke,kehoachnam,datkehoachnam));

//                                    List<BCTuyendung> Solieu = new ArrayList<>();
//                                    Solieu.add(new BCTuyendung("Số TD tháng",thuctuyen_luykethang + ""));
//                                    Solieu.add(new BCTuyendung("TL KH tháng",thuctuyen_datchitieu + "%"));
//                                    //Solieu.add(new BCTuyendung("---------------------------------------------------------------","--------------------------"));
//                                    //expandListViewBc.setChildDivider(getResources().getDrawable(R.color.cardview_dark_background));
//                                    //linearLayoutBCTD.getShowDividers();
//                                    //Solieu.add(new BCTuyendung("Số TD LK năm",thuctuyen_luyke + ""));
//                                    Solieu.add(new BCTuyendung("CC BVLN tháng",dacapCC_luykethang+ ""));
//                                    Solieu.add(new BCTuyendung("CC BVLN năm",dacapCC_luyke + ""));
//                                    //expandListViewBc.setChildDivider(getResources().getDrawable(R.color.colorPrimaryDark));
//                                    //Solieu.add(new BCTuyendung("----------------------------------------------------------------","--------------------------"));
//
//                                    Solieu.add(new BCTuyendung("Số TD LK quý",quy_thuctuyen + ""));
//                                    Solieu.add(new BCTuyendung("Số TD KH quý",quy_chitieu + ""));
//                                    Solieu.add(new BCTuyendung("TL KH quý",quy_datchitieu + "%"));
//
//                                    Solieu.add(new BCTuyendung("Số TD LK năm",thuctuyen_luyke + ""));
//                                    Solieu.add(new BCTuyendung("Số TD KH năm",kehoachnam + ""));
//                                    Solieu.add(new BCTuyendung("TL KH năm    ",datkehoachnam +"%"));
//
//                                    listdataChild.put(listdataHeader.get(i).getCongty(),Solieu);
                                }
                                Log.e("STD",STD+"");
                                Log.e("TLKHthang",TLKHthang/listdataHeader.size()+"");
                                double TLKHthangphantram = TLKHthang/listdataHeader.size();
                                Log.e("TDLKnam",TDLKnam+"");
                                Log.e("CCBVLNthang",CCBVLNthang+"");
                                Log.e("CCBVLNnam",CCBVLNnam+"");
                                Log.e("TDLKquy",TDLKquy+"");
                                Log.e("TDKHquy",TDKHquy+"");
                                Log.e("TLKHquy",TLKHquy/listdataHeader.size()+"");
                                double TLKHquyphantram = TLKHquy/listdataHeader.size();
                                Log.e("TDKHnam",TDKHnam+"");
                                Log.e("TLKHnam",TLKHnam/listdataHeader.size()+"");
                                double TLKHnamphantram = TLKHnam/listdataHeader.size();
                                listdataTDL.add(new BCTuyendung("Toàn Hệ thống",STD,TLKHthangphantram,TDLKnam,CCBVLNthang,CCBVLNnam));
                                listQuyTDL.add(new BCTuyendung(TDLKquy,TDKHquy,TLKHquyphantram));
                                listnamTDL.add(new BCTuyendung(TDLKnam,CCBVLNnam,TDKHnam,TLKHnamphantram));

//                                bcExpendAdapterTDl = new BCTuyendungExpendAdapter(getApplicationContext(),listdataTDL);
//                                ListViewDTL.setAdapter(bcExpendAdapterTDl);
//                                ListViewDTL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(AdapterView<?> parent, View view, int position,
//                                                            long id) {
//                                        for (int i = 0;i<listdataTDL.size();i++) {
//
//                                                Intent intent_bc = new Intent(BCTuyendungActivity.this,BCChiTietTuyenDungActivity.class);
//                                                Bundle bundle_get = new Bundle();
//
//
//                                                Double TDThang =listdataTDL.get(i).getThuctuyen_luykethang();
//                                                Double TLKHthang =listdataTDL.get(i).getThuctuyen_datchitieu();
//                                                Double CCBVLNthang =listdataTDL.get(i).getDacapCC_luykethang();
//                                                String congty = listdataTDL.get(i).getCongty();
//
//                                                Double TDLKquy =listQuyTDL.get(i).getQuy_thuctuyen();
//                                                Double TDKHquy =listQuyTDL.get(i).getQuy_chitieu();
//                                                Double TLKHquy =listQuyTDL.get(i).getQuy_datchitieu();
//
//                                                Double TDLKnam  =listnamTDL.get(i).getThuctuyen_luyke();
//                                                Double CCBVLNnam =listnamTDL.get(i).getDacapCC_luyke();
//                                                Double TDKHnam  =listnamTDL.get(i).getKehoachnam();
//                                                Double TLKHnam  =listnamTDL.get(i).getDatkehoachnam();
//
//                                                int Soluong  =listTVVTHT.get(i).getSoLuong();
//                                                int Soluonghoatdong =listTVVTHT.get(i).getSlHoatDong();
//                                                double e  =listTVVTHT.get(i).getTlHoatDong();
//                                                float TLhoatdong = (float) e;
//
//                                                SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("TVV", Context.MODE_PRIVATE);
//                                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                                editor.putInt("Soluong", Soluong);
//                                                editor.putInt("Soluonghoatdong", Soluonghoatdong);
//                                                editor.putFloat("TLhoatdong",TLhoatdong);
//                                                editor.apply();
//
//                                                bundle_get.putDouble("TDThangTDL", TDThang);
//                                                bundle_get.putDouble("TLKHthangTDL", TLKHthang);
//                                                bundle_get.putDouble("CCBVLNthangTDL", CCBVLNthang);
//                                                bundle_get.putString("congtyTDL", congty);
//                                                bundle_get.putDouble("TDLKquyTDL", TDLKquy);
//                                                bundle_get.putDouble("TDKHquyTDL", TDKHquy);
//                                                bundle_get.putDouble("TLKHquyTDL", TLKHquy);
//
//                                                bundle_get.putDouble("TDLKnamTDL", TDLKnam);
//                                                bundle_get.putDouble("CCBVLNnamTDL", CCBVLNnam);
//                                                bundle_get.putDouble("TDKHnamTDL", TDKHnam);
//                                                bundle_get.putDouble("TLKHnamTDL", TLKHnam);
////
////                                              bundle_get.putDouble("Lkquy", lkquy);
////                                              bundle_get.putDouble("Khquy", khquy);
////
////
//                                                intent_bc.putExtra("GetDetailTDL",bundle_get);
////
//                                                startActivity(intent_bc);
//
//                                        }
//                                    }
//                                });

//                                bcExpendAdapter = new BCTuyendungExpendAdapter(getApplicationContext(),listdataHeader);
//                                ListViewBc.setAdapter(bcExpendAdapter);

//                                ListViewBc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(AdapterView<?> parent, View view, int position,
//                                                            long id) {
//                                        for (int i = 0;i<listdataHeader.size();i++) {
//                                            if (bcExpendAdapter.getItem(position).toString().equalsIgnoreCase(listdataHeader.get(i).getCongty())) {
//                                                Intent intent_bc = new Intent(BCTuyendungActivity.this,BCChiTietTuyenDungActivity.class);
//
//                                                Bundle bundle_get = new Bundle();
//
//
//                                                Double TDThang =listdataHeader.get(i).getThuctuyen_luykethang();
//                                                Double TLKHthang =listdataHeader.get(i).getThuctuyen_datchitieu();
//                                                Double CCBVLNthang =listdataHeader.get(i).getDacapCC_luykethang();
//                                                String congty = listdataHeader.get(i).getCongty();
//
//                                                Double TDLKquy =listdataHeader.get(i).getQuy_thuctuyen();
//                                                Double TDKHquy =listdataHeader.get(i).getQuy_chitieu();
//                                                Double TLKHquy =listdataHeader.get(i).getQuy_datchitieu();
//
//                                                Double TDLKnam  =listdataHeader.get(i).getThuctuyen_luyke();
//                                                Double CCBVLNnam =listdataHeader.get(i).getDacapCC_luyke();
//                                                Double TDKHnam  =listdataHeader.get(i).getKehoachnam();
//                                                Double TLKHnam  =listdataHeader.get(i).getDatkehoachnam();
//
//                                                int Soluong  =listTVV.get(i).getSoLuong();
//                                                int Soluonghoatdong =listTVV.get(i).getSlHoatDong();
//                                                double e  =listTVV.get(i).getTlHoatDong();
//                                                float TLhoatdong = (float) e;
//
//                                                SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("TVV", Context.MODE_PRIVATE);
//                                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                                editor.putInt("Soluong", Soluong);
//                                                editor.putInt("Soluonghoatdong", Soluonghoatdong);
//                                                editor.putFloat("TLhoatdong",TLhoatdong);
//                                                editor.apply();
//
//                                                bundle_get.putDouble("TDThang", TDThang);
//                                                bundle_get.putDouble("TLKHthang", TLKHthang);
//                                                bundle_get.putDouble("CCBVLNthang", CCBVLNthang);
//                                                bundle_get.putString("congty", congty);
//
//                                                bundle_get.putDouble("TDLKquy", TDLKquy);
//                                                bundle_get.putDouble("TDKHquy", TDKHquy);
//                                                bundle_get.putDouble("TLKHquy", TLKHquy);
//
//                                                bundle_get.putDouble("TDLKnam", TDLKnam);
//                                                bundle_get.putDouble("CCBVLNnam", CCBVLNnam);
//                                                bundle_get.putDouble("TDKHnam", TDKHnam);
//                                                bundle_get.putDouble("TLKHnam", TLKHnam);
////
////                                        bundle_get.putDouble("Lkquy", lkquy);
////                                        bundle_get.putDouble("Khquy", khquy);
////
////
//                                                intent_bc.putExtra("GetDetail",bundle_get);
////
//                                                startActivity(intent_bc);
//                                            }
//                                        }
//                                        }
//                                });
                                //Search View
                                svBc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                    @Override
                                    public boolean onQueryTextSubmit(String query) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String newText) {
                                        ArrayList<BCTuyendung> filterList = new ArrayList<BCTuyendung>();
                                        for (int i = 0; i < listdataHeader.size(); i++) {
                                            if ((listdataHeader.get(i).getCongty().toUpperCase()).contains(newText.toString().toUpperCase())) {
                                                BCTuyendung bc = new BCTuyendung(
                                                        listdataHeader.get(i).getCongty(),
                                                        listdataHeader.get(i).getThuctuyen_luykethang(),
                                                        listdataHeader.get(i).getThuctuyen_datchitieu(),
                                                        listdataHeader.get(i).getThuctuyen_luyke(),
                                                        listdataHeader.get(i).getDacapCC_luykethang(),
                                                        listdataHeader.get(i).getDacapCC_luyke());
                                                filterList.add(bc);
                                            }
                                        }
                                        getData(filterList);
                                        return false;
                                    }
                                });
                                sortDataCongty();
                                getData(listdataHeader);
                                // Sort
//                                txtheaderBCTDCongty.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        sortDataCongty();
//                                    }
//                                    private void sortDataCongty() {
//                                        if(unSortedCT) {
//                                            Collections.sort(listdataHeader, new TD_CongtyComparator());
//                                            unSortedCT=false;
//                                            unSorted =true;
//                                            unSortedMTD = true;
//                                        }
//                                        else {
//                                            Collections.reverse(listdataHeader);
//                                            unSortedCT=true;
//                                            unSorted =true;
//                                            unSortedMTD = true;
//                                        }
//                                        bcExpendAdapter = new BCTuyendungExpendAdapter(getApplicationContext(),listdataHeader);
//                                        ListViewBc.setAdapter(bcExpendAdapter);
//                                    }
//                                });
//                                txtLuykethang.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        sortData();
//                                    }
//                                    private void sortData() {
//                                        if(unSorted) {
//                                            Collections.sort(listdataHeader, new TD_LKT_Comparator());
//                                            unSorted = false;
//                                            unSortedCT = true;
//                                            unSortedMTD = true;
//                                        }
//                                        else {
//                                            Collections.reverse(listdataHeader);
//                                            unSorted = true;
//                                            unSortedCT = true;
//                                            unSortedMTD = true;
//                                        }
//                                        bcExpendAdapter = new BCTuyendungExpendAdapter(getApplicationContext(),listdataHeader);
//                                        ListViewBc.setAdapter(bcExpendAdapter);
//                                    }
//                                });
//
//                                txtDatchitieu.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        sortDataMTD();
//                                    }
//                                    private void sortDataMTD() {
//                                        if(unSortedMTD) {
//                                            Collections.sort(listdataHeader, new TD_DCT_Comparator());
//                                            unSortedMTD = false;
//                                            unSortedCT = true;
//                                            unSorted=true;
//                                        }
//                                        else{
//                                            Collections.reverse(listdataHeader);
//                                            unSortedMTD = true;
//                                            unSortedCT = true;
//                                            unSorted=true;
//                                        }
//                                        bcExpendAdapter = new BCTuyendungExpendAdapter(getApplicationContext(),listdataHeader);
//                                        ListViewBc.setAdapter(bcExpendAdapter);
//                                    }
//                                });

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            /**
             * Passing some request headers
             */
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

            @Override
            public Map<String , String> getHeaders() throws AuthFailureError {
                HashMap<String , String> headers = new HashMap<String, String>();
                headers.put("Authorization", token);
                return headers;
            }
        };

        requestQueue.add(req);
    }

    private void getTTTTT(HashMap<String, String> params) {
        RequestQueue requestQueue_dt = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest req_dt = new JsonObjectRequest(StringDefine.URL_BCDOANHTHU, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            responseStatus = response.getString("responseStatus").toString();
                            responseMessage = response.getString("responseMessage");
                            if(responseStatus.equals(StringDefine.responseStatusLoginSuccess)){
                                JSONObject jsonObject = response.getJSONObject("obj");
                                stUpdateTime = jsonObject.getString("maxDataDate");
                                stUpLoadTime = jsonObject.getString("maxUploadDate");
                                updateTime.setText(updateTime.getText() + " " + stUpdateTime + " cập nhật lúc " + stUpLoadTime);

                                JSONArray jsonArray = jsonObject.getJSONArray("lstDoanhThuNgay");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    test = obj.getString("tenCT");
                                    soLuong   = obj.getInt("soLuong");
                                    slHoatDong = obj.getInt("slHoatDong");
                                    tlHoatDong = obj.getDouble("tlHoatDong");
                                    if (test.equals("Toàn hệ thống")){
                                        listTVVTHT.add(new BCKinhdoanh(soLuong,slHoatDong,tlHoatDong));
                                    }
                                    else {
                                        listTVV.add(new BCKinhdoanh(soLuong,slHoatDong,tlHoatDong));
                                    }

                                    }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
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

        requestQueue_dt.add(req_dt);
    }

    private void Anhxa() {

        toolbar = (Toolbar) findViewById(R.id.toolbarbaocaotuyendung);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayoutBCTD);

        HorizontalScrollViewB = findViewById(R.id.HorizontalScrollViewB);

//        HorizontalScrollViewD = findViewById(R.id.HorizontalScrollViewD);

        header = (TableLayout) findViewById(R.id.table_header);
        congty_table = (TableLayout) findViewById(R.id.table_Congty);
        fixedColumn = (TableLayout) findViewById(R.id.fixed_column);
        scrollablePart = (TableLayout) findViewById(R.id.scrollable_part);

        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        int fixedHeaderHeight = 120;

        row = new TableRow(this);
        row.setLayoutParams(wrapWrapTableRowParams);

        row.addView(makeTableRowWithText("Công ty", fixedColumnWidths[0], fixedHeaderHeight,100));
        congty_table.addView(row);

        row = new TableRow(this);
        row.addView(makeTableRowWithText("Số TD tháng", fixedColumnWidths[1], fixedHeaderHeight,1));
        row.addView(makeTableRowWithText("TL KH tháng", fixedColumnWidths[2], fixedHeaderHeight,2));
        row.addView(makeTableRowWithText("Số TD LK năm", fixedColumnWidths[3], fixedHeaderHeight,3));
        row.addView(makeTableRowWithText("CC BVLN LK tháng", fixedColumnWidths[4], fixedHeaderHeight,4));
        row.addView(makeTableRowWithText("CC BVLN LK năm", fixedColumnWidths[5], fixedHeaderHeight,5));
        row.addView(makeTableRowWithText("Số TD LK quý", fixedColumnWidths[6], fixedHeaderHeight,6));
        row.addView(makeTableRowWithText("Số TD KH quý", fixedColumnWidths[7], fixedHeaderHeight,7));
        row.addView(makeTableRowWithText("TL KH quý", fixedColumnWidths[8], fixedHeaderHeight,8));
        row.addView(makeTableRowWithText("Số TD KH năm", fixedColumnWidths[9], fixedHeaderHeight,9));
        row.addView(makeTableRowWithText("TL KH năm", fixedColumnWidths[10], fixedHeaderHeight,10));
        header.addView(row);

        ScrollViewC = findViewById(R.id.ScrollViewC);
        ScrollViewC.setScrollViewListener(this);
        ScrollViewD = findViewById(R.id.ScrollViewD);
        ScrollViewD.setScrollViewListener(this);

//        ListViewBc = (ListView) findViewById(R.id.listviewbaocaotuyendung);
//        ListViewDTL = findViewById(R.id.listviewTDL);

        listdataTDL = new ArrayList<>();
        listQuyTDL = new ArrayList<>();
        listnamTDL = new ArrayList<>();

        listdataHeader = new ArrayList<>();
        listQuy = new ArrayList<>();
        listnam = new ArrayList<>();
        listTVV = new ArrayList<>();
        listTVVTHT = new ArrayList<>();
        listdataChild = new HashMap<String, List<BCTuyendung>>();
        svBc = (SearchView) findViewById(R.id.searchviewbaocaotuyendung);
        giatri = (TextView) findViewById(R.id.child_bctd_giatri);
        updateTime = (TextView) findViewById(R.id.txtbctuyendungUpdatetime);
        //upLoadTime = (TextView) findViewById(R.id.txtbctuyendungUpload);
        imgMenu = (ImageView) findViewById(R.id.imgbctuyendungMenu);
        imgSearch = findViewById(R.id.imgSearch);

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                AddControlMenu();
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (svBc.getVisibility()==GONE){
                    svBc.setVisibility(VISIBLE);
                }
                else svBc.setVisibility(GONE);
            }
        });
    }

    private void AddControlMenu() {
        expandableListViewMenu = (ExpandableListView) findViewById(R.id.expandablelistviewMenu);

        listdataHeaderMenu = new ArrayList<>();
        listdataChildMenu = new HashMap<String,List<String>>();

        listdataHeaderMenu.add(new MenuBaoCao("Trang chủ", R.drawable.ic_homepage));
        listdataHeaderMenu.add(new MenuBaoCao(StringDefine.Baocaodoanhthu, R.drawable.report_icon));
        listdataHeaderMenu.add(new MenuBaoCao(StringDefine.Baocaotuyendung, R.drawable.ic_employee));
        listdataHeaderMenu.add(new MenuBaoCao("Tra cứu lịch sử", R.drawable.calendar));
        listdataHeaderMenu.add(new MenuBaoCao("Đăng xuất", R.mipmap.icon_logout));

        List<String> baocao = new ArrayList<String>();
        baocao.add(StringDefine.Baocaodoanhthu);
        baocao.add(StringDefine.Baocaotuyendung);

        menuExpendableAdapter = new MenuExpendableAdapter(getApplicationContext(),listdataHeaderMenu,listdataChildMenu);
        expandableListViewMenu.setAdapter(menuExpendableAdapter);

        CatchOnListViewMenu();
    }

    private void CatchOnListViewMenu() {
        expandableListViewMenu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Intent intent = getIntent();
                Bundle bundle = intent.getBundleExtra("token");
                if(listdataHeaderMenu.get(groupPosition).getTen().toString().equals("Trang chủ")){
                    Intent intentHomepage = new Intent(getApplicationContext(), ContentActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentHomepage.putExtra("token",bundle);
                    }
                    startActivity(intentHomepage);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(listdataHeaderMenu.get(groupPosition).getTen().toString().equals(StringDefine.Baocaodoanhthu)){
                    Intent intentBCKinhdoanh = new Intent(getApplicationContext(),BCKinhdoanhActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentBCKinhdoanh.putExtra("token",bundle);
                    }
                    startActivity(intentBCKinhdoanh);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(listdataHeaderMenu.get(groupPosition).getTen().toString().equals("Tra cứu lịch sử")){
                    Intent intentTimkiem = new Intent(BCTuyendungActivity.this,TimkiemActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentTimkiem.putExtra("token",bundle);
                    }
                    startActivity(intentTimkiem);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(listdataHeaderMenu.get(groupPosition).getTen().toString().equals(StringDefine.Baocaotuyendung)){
                    Intent intentBCTuyendung = new Intent(getApplicationContext(),BCTuyendungActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentBCTuyendung.putExtra("token",bundle);
                    }
                    startActivity(intentBCTuyendung);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if(listdataHeaderMenu.get(groupPosition).getTen().toString().equals("Đăng xuất")){
                    Intent intentLogout = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentLogout);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

        expandableListViewMenu.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expandableListViewMenu.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

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
