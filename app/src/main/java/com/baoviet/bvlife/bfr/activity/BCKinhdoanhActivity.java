package com.baoviet.bvlife.bfr.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
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
import com.baoviet.bvlife.bfr.adapter.BCKinhdoanhExpendAdapter;
import com.baoviet.bvlife.bfr.adapter.MenuExpendableAdapter;
import com.baoviet.bvlife.bfr.model.BCKinhdoanh;
import com.baoviet.bvlife.bfr.model.MenuBaoCao;
import com.baoviet.bvlife.bfr.util.KD_CongtyComparator;
import com.baoviet.bvlife.bfr.util.MTDComparator;
import com.baoviet.bvlife.bfr.util.SolieutrongngayComparator;
import com.baoviet.bvlife.bfr.util.StringDefine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.v7.widget.Toolbar.*;
import static com.baoviet.bvlife.bfr.activity.BCTuyendungActivity.orientationBCTD;

public class BCKinhdoanhActivity extends AppCompatActivity implements ScrollViewListener  {

    private HorizontalScrollView HorizontalScrollViewD,HorizontalScrollViewB;
    private ObservableScrollView ScrollViewC = null;
    private ObservableScrollView ScrollViewD = null;

    Request.Priority mPriority = Request.Priority.HIGH;
    Toolbar toolbar;
    SearchView svBcKinhdoanh;
//    ListView ListViewBaoCaoKinhDoanh,listviewTDL;
    HashMap<String, List<BCKinhdoanh>> listdataChild;

    ArrayList<BCKinhdoanh> listCongty;
    ArrayList<BCKinhdoanh> listdataHeader;
    ArrayList<BCKinhdoanh> listQuy;
    ArrayList<BCKinhdoanh> listNam;

    ArrayList<BCKinhdoanh> listdataTDL;
    ArrayList<BCKinhdoanh> listQuyTDL;
    ArrayList<BCKinhdoanh> listNamTDL;

    ArrayList<BCKinhdoanh> listDtphantram;

    BCKinhdoanhExpendAdapter bcKinhdoanhExpendAdapter,bcKinhdoanhExpendAdapterTDL;

    ExpandableListView expandableListViewMenu;
    HashMap<String,List<String>> listdataChildMenu;
    List<MenuBaoCao> listdataHeaderMenu;
    DrawerLayout drawerLayout;
    MenuExpendableAdapter menuExpendableAdapter;

    TextView giatri, updateTime;
    ImageView imgMenu,imgLKnam,imgSearch;
    List<BCKinhdoanh> Solieu;
    //Textview header
//    TextView txtSolieutrongngay, txtheaderMTD, txtheaderCongty;

    boolean unSortedCT=true;
    boolean unSorted=true;
    boolean unSortedMTD = true;

    String token, responseMessage, responseStatus;
    String congty, stUpdateTime, stUpLoadTime;
    double solieutrongngay, mtd, socungkynamtruoc, soluykethang,
           soluykethangCungkynamtruoc,tlLuyKeNam,khThang,tlThang,
           khQuy,lkQuy,tlKhQuy,khNam,lkNam,tlNam,tlHoatDong;
    int soLuong,slHoatDong;

    private TextView recyclableTextView;
    private TextView fixedView;

    public static int orientation;
    int i ;
    private TableLayout header,scrollablePart,congty_table,fixedColumn;
    private TableRow row;
    private ArrayList<BCKinhdoanh> arrBCkinhdoanh;
    int[] scrollableColumnWidths;
    int[] fixedColumnWidths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bckinhdoanh);
        orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            scrollableColumnWidths = new int[]{25, 15, 15, 15,15,15, 15, 15,15,15, 15, 15,15, 15,15, 15,15, 15};
            fixedColumnWidths = new int[]{25, 15, 15, 15,15,15, 15, 15,15,15, 15, 15,15, 15,15, 15,15, 15};
        }
        else{
            scrollableColumnWidths = new int[]{40, 25, 25, 25,25,25, 25, 25,25,25, 25, 25,25, 25,25, 25,25, 25};
            fixedColumnWidths = new int[]{40, 25, 25, 25,25,25, 25, 25,25,25, 25, 25,25, 25,25, 25,25, 25};
        }
        Anhxa();
//        setScrollViewAndHorizontalScrollViewTag();
        ActionToolbar();

    }

    private void test(ArrayList<BCKinhdoanh> arrayList) {


        arrBCkinhdoanh = new ArrayList<>();
        fixedColumn.removeAllViews();
        scrollablePart.removeAllViews();
        arrBCkinhdoanh = arrayList;

        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        int fixedRowHeight = 70;
        for( i = 0; i < arrBCkinhdoanh.size(); i++) {
            row = new TableRow(this);
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.CENTER);
            row.addView(makeTableRowWithText2(arrBCkinhdoanh.get(i).getCongty(), scrollableColumnWidths[0], fixedRowHeight));
            fixedColumn.addView(row);
            row.setClickable(true);
            row.setOnClickListener(new OnClickListener() {
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

            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getSolieutrongngay()), scrollableColumnWidths[1], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getMdtLuykethang()), scrollableColumnWidths[2], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getKehoachthang()), scrollableColumnWidths[3], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getTlthang()), scrollableColumnWidths[4], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getKhquy()), scrollableColumnWidths[5], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getLkquy()), scrollableColumnWidths[6], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getTlkhquy()), scrollableColumnWidths[7], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getKhNam()), scrollableColumnWidths[8], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getLknam()), scrollableColumnWidths[9], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getTlnam()), scrollableColumnWidths[10], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getCungkynamtruoc()), scrollableColumnWidths[11], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getSoluykethangCungkynamtruoc()), scrollableColumnWidths[12], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getLuykenamCungkynamtruoc()), scrollableColumnWidths[13], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getTlLuyKeNam()), scrollableColumnWidths[14], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getSoLuong()), scrollableColumnWidths[15], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getSlHoatDong()), scrollableColumnWidths[16], fixedRowHeight));
            row.addView(makeTableRowWithText2(Double.toString(arrBCkinhdoanh.get(i).getTlHoatDong()), scrollableColumnWidths[17], fixedRowHeight));
            scrollablePart.addView(row);
            fixedColumn.setClickable(true);
            fixedColumn.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    int rowIndex2 = fixedColumn.indexOfChild(view);
                    Toast toast2 = Toast.makeText(BCKinhdoanhActivity.this, arrBCkinhdoanh.get(rowIndex2).getCongty(), Toast.LENGTH_LONG);
                    toast2.show();
                }
            });
            row.setClickable(true);
            row.setOnClickListener(new OnClickListener() {
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
                                        Intent intent_bc = new Intent(BCKinhdoanhActivity.this,BCChiTietDoanhThuActivity.class);

                                        Bundle bundle_get = new Bundle();

                                        Double lkthang =listdataHeader.get(t).getMdtLuykethang();
                                        Double cknamtruoc =listdataHeader.get(t).getCungkynamtruoc();
                                        Double khthang =listdataHeader.get(t).getKehoachthang();
                                        String congty = listdataHeader.get(t).getCongty();
                                        Double dtngay = listdataHeader.get(t).getSolieutrongngay();
                                        Double tllkthang = listdataHeader.get(t).getSoluykethangCungkynamtruoc();
                                        Double tlthang = listdataHeader.get(t).getTlthang();

                                        Double lkquy = listdataHeader.get(t).getLkquy();
                                        Double khquy = listdataHeader.get(t).getKhquy();
                                        Double tlkhquy = listdataHeader.get(t).getTlkhquy();

                                        Double lkNam = listdataHeader.get(t).getLknam();
                                        Double khNam = listdataHeader.get(t).getKhNam();
                                        Double tlNam = listdataHeader.get(t).getTlnam();
                                        Double soluykethangCungkynamtruoc = listdataHeader.get(t).getLknamtruoc();
                                        Double tlLuyKeNam = listdataHeader.get(t).getTlLuyKeNam();
//                                    bundle_get.putInt("position",position-1);
                                        bundle_get.putString("token_login", token);
                                        bundle_get.putDouble("LKthang", lkthang);
                                        bundle_get.putDouble("CKnamtruoc", cknamtruoc);
                                        bundle_get.putDouble("Khthang", khthang);
                                        bundle_get.putString("Congty", congty);
                                        bundle_get.putDouble("Dtngay", dtngay);
                                        bundle_get.putDouble("Tllkthang", tllkthang);
                                        bundle_get.putDouble("TLthang", tlthang);

                                        bundle_get.putDouble("Lkquy", lkquy);
                                        bundle_get.putDouble("Khquy", khquy);
                                        bundle_get.putDouble("TLKHquy", tlkhquy);

                                        bundle_get.putDouble("LKnamtruoc", soluykethangCungkynamtruoc);
                                        bundle_get.putDouble("LKnam", lkNam);
                                        bundle_get.putDouble("KHNam", khNam);
                                        bundle_get.putDouble("TLNam", tlNam);
                                        bundle_get.putDouble("TLLuyKeNam", tlLuyKeNam);

                                        intent_bc.putExtra("GetDetail",bundle_get);
                                        startActivity(intent_bc);
                                    }

        }


    }

    public TextView makeTableRowWithText(final String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels, int position) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(this);
        recyclableTextView.setText(text);
        recyclableTextView.setTextColor(Color.WHITE);
        recyclableTextView.setTextSize(16);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        recyclableTextView.setTag(position);
        recyclableTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                    if (position==1){
                        sortDataSolieutrongngay();
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
                                    Collections.sort(listdataHeader, new MTDComparator());
                                    Collections.sort(listQuy, new MTDComparator());
                                    Collections.sort(listNam, new MTDComparator());
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
                        test(listdataHeader);
    }

    private void sortDataSolieutrongngay() {
                    if(unSortedCT) {
                                    Collections.sort(listdataHeader, new SolieutrongngayComparator());
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
                               test(listdataHeader);
    }

    public TextView makeTableRowWithText2(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(this);
        recyclableTextView.setText(text);
        recyclableTextView.setTextColor(Color.BLACK);
        recyclableTextView.setTextSize(16);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }


    private void sortDataCongty() {
        if(unSortedCT) {
            Collections.sort(listdataHeader, new KD_CongtyComparator());
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
//        bcKinhdoanhExpendAdapter = new BCKinhdoanhExpendAdapter(getApplicationContext(),listdataHeader);
//        ListViewBaoCaoKinhDoanh.setAdapter(bcKinhdoanhExpendAdapter);
    }

    private void ActionToolbar() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setNavigationIcon(R.drawable.ic_menu);
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                AddControlMenu();
            }
        });*/
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                AddControlMenu();
            }
        });
        imgSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (svBcKinhdoanh.getVisibility()==GONE){
                    svBcKinhdoanh.setVisibility(VISIBLE);
                }
                else svBcKinhdoanh.setVisibility(GONE);
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

        /*expandableListViewMenu.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expandableListViewMenu.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });*/

    }
//    private void setScrollViewAndHorizontalScrollViewTag(){
//
//        HorizontalScrollViewB.setTag("horizontal scroll view b");
//        HorizontalScrollViewD.setTag("horizontal scroll view d");
//        ScrollViewC.setTag("scroll view c");
//        ScrollViewD.setTag("scroll view d");
//    }
    private void Anhxa() {

        // Anh xa
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayoutBCKD);
//        toolbar = (Toolbar) findViewById(R.id.toolbarbaocaokinhdoanh);

        HorizontalScrollViewB = findViewById(R.id.HorizontalScrollViewB);

//        HorizontalScrollViewD = findViewById(R.id.HorizontalScrollViewD);

        header = (TableLayout) findViewById(R.id.table_header);
        congty_table = (TableLayout) findViewById(R.id.table_Congty);
        fixedColumn = (TableLayout) findViewById(R.id.fixed_column);
        scrollablePart = (TableLayout) findViewById(R.id.scrollable_part);

        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        int fixedHeaderHeight = 80;

        row = new TableRow(this);
        row.setLayoutParams(wrapWrapTableRowParams);

        row.addView(makeTableRowWithText("Công ty", fixedColumnWidths[0], fixedHeaderHeight,100));
        congty_table.addView(row);

        row = new TableRow(this);
        row.addView(makeTableRowWithText("DT ngày", fixedColumnWidths[1], fixedHeaderHeight,1));
        row.addView(makeTableRowWithText("MTD", fixedColumnWidths[2], fixedHeaderHeight,2));
        row.addView(makeTableRowWithText("KH tháng", fixedColumnWidths[3], fixedHeaderHeight,3));
        row.addView(makeTableRowWithText("% KH tháng", fixedColumnWidths[4], fixedHeaderHeight,4));
        row.addView(makeTableRowWithText("KH quý", fixedColumnWidths[5], fixedHeaderHeight,5));
        row.addView(makeTableRowWithText("LK quý", fixedColumnWidths[6], fixedHeaderHeight,6));
        row.addView(makeTableRowWithText("% KH quý", fixedColumnWidths[7], fixedHeaderHeight,7));
        row.addView(makeTableRowWithText("KH năm", fixedColumnWidths[8], fixedHeaderHeight,8));
        row.addView(makeTableRowWithText("LK năm", fixedColumnWidths[9], fixedHeaderHeight,9));
        row.addView(makeTableRowWithText("% KH năm", fixedColumnWidths[10], fixedHeaderHeight,10));
        row.addView(makeTableRowWithText("MTD CK", fixedColumnWidths[11], fixedHeaderHeight,11));
        row.addView(makeTableRowWithText("% MTD CK", fixedColumnWidths[12], fixedHeaderHeight,12));
        row.addView(makeTableRowWithText("YTD CK", fixedColumnWidths[13], fixedHeaderHeight,13));
        row.addView(makeTableRowWithText("% YTD CK", fixedColumnWidths[14], fixedHeaderHeight,14));
        row.addView(makeTableRowWithText("SL TVV 90d", fixedColumnWidths[15], fixedHeaderHeight,15));
        row.addView(makeTableRowWithText("SL hoạt động", fixedColumnWidths[16], fixedHeaderHeight,16));
        row.addView(makeTableRowWithText("% hoạt động", fixedColumnWidths[17], fixedHeaderHeight,17));
        header.addView(row);
//        row.setClickable(true);
//        row.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                    TableRow t = (TableRow) v;
//                    TextView firstTextView = (TextView) t.getChildAt(0);
//                    String firstText = firstTextView.getText().toString();
//                        Toast toast = Toast.makeText(BCKinhdoanhActivity.this, firstText, Toast.LENGTH_LONG);
//                        toast.show();
//
////                for (int j = 1;j<20;j++){
////                    if (j==1){
////                        TextView sample = (TextView) row.getChildAt(1);
////                        String result=sample.getText().toString();
//
//
//
//
////                    else if (j==2){
////                        TextView sample = (TextView) row.getChildAt(2);
////                        String result=sample.getText().toString();
////                        Toast toast = Toast.makeText(BCKinhdoanhActivity.this, result, Toast.LENGTH_LONG);
////                        toast.show();
////                    }
////                }
//
//
//            }
//        });
        ScrollViewC = findViewById(R.id.ScrollViewC);
        ScrollViewC.setScrollViewListener(this);
        ScrollViewD = findViewById(R.id.ScrollViewD);
        ScrollViewD.setScrollViewListener(this);

//        HorizontalScrollViewB = new MyHorizontalScrollView(getApplicationContext());
//        HorizontalScrollViewD = new MyHorizontalScrollView(getApplicationContext());

//        ScrollViewC = new MyScrollView(getApplicationContext());
//        ScrollViewD = new MyScrollView(getApplicationContext());

        imgMenu = findViewById(R.id.imgbckinhdoanhMenu);
        imgSearch = findViewById(R.id.imgSearch);

//        ListViewBaoCaoKinhDoanh = (ListView) findViewById(R.id.listviewbaocaokinhdoanh);
//        ListViewBaoCaoKinhDoanh.setFocusable(true);
//        listviewTDL = findViewById(R.id.listviewTDL);

        listdataHeader = new ArrayList<>();
        listQuy = new ArrayList<>();
        listNam = new ArrayList<>();
        listCongty = new ArrayList<>();

        listdataTDL  = new ArrayList<>();
        listQuyTDL  = new ArrayList<>();
        listNamTDL  = new ArrayList<>();

        listDtphantram = new ArrayList<>();

        listdataChild = new HashMap<String, List<BCKinhdoanh>>();
        svBcKinhdoanh = (SearchView) findViewById(R.id.searchviewbaocaokinhdoanh);
        giatri = (TextView) findViewById(R.id.child_bckinhhoanh_giatri);
        updateTime = (TextView) findViewById(R.id.txtbckinhdoanhUpdatetime);
//        imgMenu = (ImageView) findViewById(R.id.imgbckinhdoanhMenu);

        // Add a header to the ListView

//        txtSolieutrongngay = findViewById(R.id.txtheaderSolieutrongngay);
//        txtheaderMTD = findViewById(R.id.txtheaderMTD);
//        txtheaderCongty = findViewById(R.id.txtheaderCongty);

        // Nhan du lieu token tu Intent
        final Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("token");
        if(bundle != null){
            token = bundle.getString("token_login");
        }

        // Doc du lieu tu Services
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", token);

        JsonObjectRequest req = new JsonObjectRequest(StringDefine.URL_BCDOANHTHU, new JSONObject(params),
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
                            congty = obj.getString("tenCT");
                            solieutrongngay = Math.round(obj.getDouble("soLieu"));
                            DecimalFormat format = new DecimalFormat("0.#");
                            format.format(solieutrongngay);
                            mtd = Math.round(obj.getDouble("mtd"));
                            socungkynamtruoc = Math.round(obj.getDouble("ckNamTruoc"));
                            soluykethang = obj.getDouble("tlLkThang");
                            soluykethangCungkynamtruoc = Math.round(obj.getDouble("lkNamCungKy"));
                            tlLuyKeNam = obj.getDouble("tlLuyKeNam");
                            khThang = Math.round(obj.getDouble("khThang"));
                            tlThang = obj.getDouble("tlThang");
                            khQuy = Math.round(obj.getDouble("khQuy"));
                            lkQuy = Math.round(obj.getDouble("lkQuy"));
                            tlKhQuy = obj.getDouble("tlKhQuy");
                            khNam = Math.round(obj.getDouble("khNam"));
                            lkNam = Math.round(obj.getDouble("ytd"));
                            tlNam = obj.getDouble("tlNam");
                            soLuong = obj.getInt("soLuong");
                            slHoatDong = obj.getInt("slHoatDong");
                            tlHoatDong = obj.getDouble("tlHoatDong");

                            if (congty.equals("Toàn hệ thống")){
//                                listdataTDL.add(new BCKinhdoanh(congty,solieutrongngay,mtd,socungkynamtruoc,soluykethang,soluykethangCungkynamtruoc,khThang,tlThang,lkQuy,khQuy,tlKhQuy,lkNam,khNam,tlNam,soluykethangCungkynamtruoc,tlLuyKeNam));
//                                listQuyTDL.add(new BCKinhdoanh(lkQuy,khQuy,tlKhQuy));
//                                listNamTDL.add(new BCKinhdoanh(lkNam,khNam,tlNam,soluykethangCungkynamtruoc,tlLuyKeNam));
                                Log.e("THL","an THL");
                            }
                            else {

                                listdataHeader.add(new BCKinhdoanh(congty,solieutrongngay,mtd,socungkynamtruoc,soluykethang,soluykethangCungkynamtruoc,khThang,tlThang,lkQuy,khQuy,tlKhQuy,lkNam,khNam,tlNam,soluykethangCungkynamtruoc,tlLuyKeNam,soLuong,slHoatDong,tlHoatDong));
                                listQuy.add(new BCKinhdoanh(lkQuy,khQuy,tlKhQuy));
                                listNam.add(new BCKinhdoanh(lkNam,khNam,tlNam,soluykethangCungkynamtruoc,tlLuyKeNam));
                            }
                            Log.e("congty",congty+"");
                            Log.e("tllkthang",soluykethang+"");
                            Log.e("tlthang",tlThang+"");
                            Log.e("tlKhQuy",tlKhQuy+"");

                            Log.e("lkthangCungkynamtruoc",soluykethangCungkynamtruoc+"");
                            Log.e("khNam",khNam+"");
                            Log.e("tlLuyKeNam",tlLuyKeNam+"");



//
//                            Solieu = new ArrayList<>();
//                            Solieu.add(new BCKinhdoanh("DT ngày",solieutrongngay + ""));
//                            Solieu.add(new BCKinhdoanh("MTD",mtd+ ""));
//                            Solieu.add(new BCKinhdoanh("CK năm trước",socungkynamtruoc+ ""));
//                            Solieu.add(new BCKinhdoanh("TL LK tháng CK",soluykethang +"%"));
//                            Solieu.add(new BCKinhdoanh("LK năm CK",soluykethangCungkynamtruoc+ ""));
//                            Solieu.add(new BCKinhdoanh("TL LK năm CK",tlLuyKeNam +"%"));
//                            Solieu.add(new BCKinhdoanh("KH tháng",khThang+ ""));
//                            Solieu.add(new BCKinhdoanh("TL tháng",tlThang +"%"));
//                            Solieu.add(new BCKinhdoanh("KH Quý",khQuy+ ""));
//                            Solieu.add(new BCKinhdoanh("LK Quý",lkQuy+ ""));
//                            Solieu.add(new BCKinhdoanh("TL KH Quý",tlKhQuy +"%"));
//                            Solieu.add(new BCKinhdoanh("KH năm",khNam+ ""));
//                            Solieu.add(new BCKinhdoanh("YTD",ytd+ ""));
//                            Solieu.add(new  ("TL năm",tlNam+"%"));
//                            Solieu.add(new BCKinhdoanh("SL TVV 90 ngày",soLuong+ ""));
//                            Solieu.add(new BCKinhdoanh("SL hoạt động",slHoatDong+ ""));
//                            Solieu.add(new BCKinhdoanh("TL hoạt động",tlHoatDong+ "%"));

//                            listdataChild.put(listdataHeader.get(i).getCongty(),Solieu);
                        }
//                        bcKinhdoanhExpendAdapterTDL = new BCKinhdoanhExpendAdapter(getApplicationContext(),listdataTDL);
//                        listviewTDL.setAdapter(bcKinhdoanhExpendAdapterTDL);

                        //Search View
                        svBcKinhdoanh.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
//                              bcKinhdoanhExpendAdapter.getFilter().filter(newText);
                                ArrayList<BCKinhdoanh> filterList = new ArrayList<BCKinhdoanh>();
                                for (int i = 0; i < listdataHeader.size(); i++) {
                                    if ((listdataHeader.get(i).getCongty().toUpperCase()).contains(newText.toString().toUpperCase())) {
                                        BCKinhdoanh bc = new BCKinhdoanh(
                                                listdataHeader.get(i).getCongty(),
                                                listdataHeader.get(i).getSolieutrongngay(),
                                                listdataHeader.get(i).getMdtLuykethang(),
                                                listdataHeader.get(i).getCungkynamtruoc(),
                                                listdataHeader.get(i).getSoluykethangCungkynamtruoc(),
                                                listdataHeader.get(i).getLuykenamCungkynamtruoc(),
                                                listdataHeader.get(i).getKehoachthang(),
                                                listdataHeader.get(i).getTlthang(),
                                                listdataHeader.get(i).getLkquy(),
                                                listdataHeader.get(i).getKhquy(),
                                                listdataHeader.get(i).getTlkhquy(),
                                                listdataHeader.get(i).getLknam(),
                                                listdataHeader.get(i).getKhNam(),
                                                listdataHeader.get(i).getTlnam(),
                                                listdataHeader.get(i).getLknamtruoc(),
                                                listdataHeader.get(i).getTlLuyKeNam(),
                                                listdataHeader.get(i).getSoLuong(),
                                                listdataHeader.get(i).getSlHoatDong(),
                                                listdataHeader.get(i).getTlHoatDong()


                                        );
                                        filterList.add(bc);
                                    }
                                }
                                test(filterList);

                                return false;
                            }
                        });

//                        ListViewBaoCaoKinhDoanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                for (int i = 0;i<listdataHeader.size();i++){
//                                    if (bcKinhdoanhExpendAdapter.getItem(position).toString().equalsIgnoreCase(listdataHeader.get(i).getCongty())){
//                                        Intent intent_bc = new Intent(BCKinhdoanhActivity.this,BCChiTietDoanhThuActivity.class);
//
//
//                                    Bundle bundle_get = new Bundle();
//
//                                    Double lkthang =listdataHeader.get(i).getMdtLuykethang();
//                                    Double cknamtruoc =listdataHeader.get(i).getCungkynamtruoc();
//                                    Double khthang =listdataHeader.get(i).getKehoachthang();
//                                    String congty = listdataHeader.get(i).getCongty();
//                                    Double dtngay = listdataHeader.get(i).getSolieutrongngay();
//                                    Double tllkthang = listdataHeader.get(i).getSoluykethangCungkynamtruoc();
//                                    Double tlthang = listdataHeader.get(i).getTlthang();
//
//                                    Double lkquy = listdataHeader.get(i).getLkquy();
//                                    Double khquy = listdataHeader.get(i).getKhquy();
//                                    Double tlkhquy = listdataHeader.get(i).getTlkhquy();
//
//                                    Double lkNam = listdataHeader.get(i).getLknam();
//                                    Double khNam = listdataHeader.get(i).getKhNam();
//                                    Double tlNam = listdataHeader.get(i).getTlnam();
//                                    Double soluykethangCungkynamtruoc = listdataHeader.get(i).getLknamtruoc();
//                                    Double tlLuyKeNam = listdataHeader.get(i).getTlLuyKeNam();
////                                    bundle_get.putInt("position",position-1);
//                                    bundle_get.putString("token_login", token);
//                                    bundle_get.putDouble("LKthang", lkthang);
//                                    bundle_get.putDouble("CKnamtruoc", cknamtruoc);
//                                    bundle_get.putDouble("Khthang", khthang);
//                                    bundle_get.putString("Congty", congty);
//                                    bundle_get.putDouble("Dtngay", dtngay);
//                                    bundle_get.putDouble("Tllkthang", tllkthang);
//                                    bundle_get.putDouble("TLthang", tlthang);
//
//                                    bundle_get.putDouble("Lkquy", lkquy);
//                                    bundle_get.putDouble("Khquy", khquy);
//                                    bundle_get.putDouble("TLKHquy", tlkhquy);
//
//                                    bundle_get.putDouble("LKnamtruoc", soluykethangCungkynamtruoc);
//                                    bundle_get.putDouble("LKnam", lkNam);
//                                    bundle_get.putDouble("KHNam", khNam);
//                                    bundle_get.putDouble("TLNam", tlNam);
//                                    bundle_get.putDouble("TLLuyKeNam", tlLuyKeNam);
//
//                                    intent_bc.putExtra("GetDetail",bundle_get);
//                                    startActivity(intent_bc);
//                                    }
//                                }
//
//                                }
//                        });
//                        listviewTDL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                for (int i = 0;i<listdataTDL.size();i++){
//
//                                        Intent intent_bc = new Intent(BCKinhdoanhActivity.this,BCChiTietDoanhThuActivity.class);
//                                        Bundle bundle_getTDL = new Bundle();
//
//                                        Double lkthang =listdataTDL.get(i).getMdtLuykethang();
//                                        Double cknamtruoc =listdataTDL.get(i).getCungkynamtruoc();
//                                        Double khthang =listdataTDL.get(i).getKehoachthang();
//                                        String congty = listdataTDL.get(i).getCongty();
//                                        Double dtngay = listdataTDL.get(i).getSolieutrongngay();
//                                        Double tllkthang = listdataTDL.get(i).getSoluykethangCungkynamtruoc();
//                                        Double tlthang = listdataTDL.get(i).getTlthang();
//
//                                        Double lkquy = listQuyTDL.get(i).getLkquy();
//                                        Double khquy = listQuyTDL.get(i).getKhquy();
//                                        Double tlkhquy = listQuyTDL.get(i).getTlkhquy();
//
//                                        Double lkNam = listNamTDL.get(i).getLknam();
//                                        Double khNam = listNamTDL.get(i).getKhNam();
//                                        Double tlNam = listNamTDL.get(i).getTlnam();
//                                        Double soluykethangCungkynamtruoc = listNamTDL.get(i).getLknamtruoc();
//                                        Double tlLuyKeNam = listNamTDL.get(i).getTlLuyKeNam();
////                                    bundle_get.putInt("position",position-1);
//                                        bundle_getTDL.putString("token_login", token);
//                                        bundle_getTDL.putDouble("LKthangTDL", lkthang);
//                                        bundle_getTDL.putDouble("CKnamtruocTDL", cknamtruoc);
//                                        bundle_getTDL.putDouble("KhthangTDL", khthang);
//                                        bundle_getTDL.putString("CongtyTDL", congty);
//                                        bundle_getTDL.putDouble("DtngayTDL", dtngay);
//                                        bundle_getTDL.putDouble("TllkthangTDL", tllkthang);
//                                        bundle_getTDL.putDouble("TLthangTDL", tlthang);
//
//                                        bundle_getTDL.putDouble("LkquyTDL", lkquy);
//                                        bundle_getTDL.putDouble("KhquyTDL", khquy);
//                                        bundle_getTDL.putDouble("TLKHquyTDL", tlkhquy);
//
//                                        bundle_getTDL.putDouble("LKnamtruocTDL", soluykethangCungkynamtruoc);
//                                        bundle_getTDL.putDouble("LKnamTDL", lkNam);
//                                        bundle_getTDL.putDouble("KHNamTDL", khNam);
//                                        bundle_getTDL.putDouble("TLNamTDL", tlNam);
//                                        bundle_getTDL.putDouble("TLLuyKeNamTDL", tlLuyKeNam);
//
//                                        intent_bc.putExtra("GetDetailTDL",bundle_getTDL);
//                                        startActivity(intent_bc);
//                                }
//
//                            }
//                        });
                        sortDataCongty();
                        test(listdataHeader);
                         ///Sort
//                        txtheaderCongty.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                sortDataCongty();
//                            }
//                            private void sortDataCongty() {
//                                if(unSortedCT) {
//                                    Collections.sort(listdataHeader, new KD_CongtyComparator());
//                                    unSortedCT=false;
//                                    unSorted =true;
//                                    unSortedMTD = true;
//                                }
//                                else {
//                                    Collections.reverse(listdataHeader);
//                                    unSortedCT=true;
//                                    unSorted =true;
//                                    unSortedMTD = true;
//                                }
//                                bcKinhdoanhExpendAdapter = new BCKinhdoanhExpendAdapter(getApplicationContext(),listdataHeader);
//                                ListViewBcKinhdoanh.setAdapter(bcKinhdoanhExpendAdapter);
//                            }
//                        });

//                        txtSolieutrongngay.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                sortDataSolieutrongngay();
//                            }
//                            private void sortDataSolieutrongngay() {
//                                if(unSorted) {
//                                    Collections.sort(listdataHeader, new SolieutrongngayComparator());
//                                    unSorted=false;
//                                    unSortedCT = true;
//                                    unSortedMTD = true;
//                                }
//                                else {
//                                    Collections.reverse(listdataHeader);
//                                    unSorted=true;
//                                    unSortedCT = true;
//                                    unSortedMTD = true;
//                                }
//                                bcKinhdoanhExpendAdapter = new BCKinhdoanhExpendAdapter(getApplicationContext(),listdataHeader);
////                                ListViewBaoCaoKinhDoanh.setAdapter(bcKinhdoanhExpendAdapter);
//                            }
//
//                        });

//                        txtheaderMTD.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                sortDataMTD();
//                            }
//                            private void sortDataMTD() {
//                                if(unSortedMTD) {
//                                    Collections.sort(listdataHeader, new MTDComparator());
//                                    Collections.sort(listQuy, new MTDComparator());
//                                    Collections.sort(listNam, new MTDComparator());
//                                    unSortedMTD = false;
//                                    unSortedCT = true;
//                                    unSorted=true;
//                                }
//                                else{
//                                    Collections.reverse(listdataHeader);
//                                    unSortedMTD = true;
//                                    unSortedCT = true;
//                                    unSorted=true;
//                                }
//                                bcKinhdoanhExpendAdapter = new BCKinhdoanhExpendAdapter(getApplicationContext(),listdataHeader);
////                                ListViewBaoCaoKinhDoanh.setAdapter(bcKinhdoanhExpendAdapter);
//                            }
//                        });
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

        requestQueue.add(req);
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
