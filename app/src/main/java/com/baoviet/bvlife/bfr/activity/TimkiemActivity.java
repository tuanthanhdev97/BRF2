package com.baoviet.bvlife.bfr.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.baoviet.bvlife.bfr.adapter.TimkiemFragmentAdapter;
import com.baoviet.bvlife.bfr.fragment.Timkiemtheongay;
import com.baoviet.bvlife.bfr.fragment.Timkiemtheothang;
import com.baoviet.bvlife.bfr.model.BCTuyendung;
import com.baoviet.bvlife.bfr.util.StringDefine;
import com.whiteelephant.monthpicker.MonthPickerDialog;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TimkiemActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,View.OnClickListener {
    //This is our tablayout
    private TabLayout tabLayout;
    DatePickerDialog datePickerStartDay,datePickerEndday;
    //This is our viewPager
    private ViewPager viewPager;
    TextView txtStartday,txtEndday;
    TextView txtStartmonth,txtEndmonth;
    Dialog dialogday;

    private int currentYear;
    private int yearSelected;
    private int monthSelected;
    private Spinner spin1,spin2;
    String arr[]={
            "Thanh Hóa",
            "Hà Nội",
            "Bắc Ninh",
            "Hà Nội",
            "Nghệ An",
            "Hồ Chí Minh",
            "Cần Thơ",};
    String token, responseMessage, responseStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkiem);
        //Adding toolbar to the activity

        getData();
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Tìm theo ngày"));
        tabLayout.addTab(tabLayout.newTab().setText("Tìm theo tháng"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        TimkiemFragmentAdapter adapter = new TimkiemFragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
        tabLayout.setSelectedTabIndicatorHeight(0);


        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.bk_bckinhdoanh_left));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        datePickerStartDay = new DatePickerDialog(
                this, dateStartDay, year, month, day);
        datePickerEndday = new DatePickerDialog(
               this, dateEndday, year, month, day);

    }

    private void getData() {
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
        String url = "https://esb.baoviet.com.vn/eposwsTest/api/quickReport/getRevenueByCompNameAndMonth";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            responseStatus = response.getString("responseStatus").toString();
                            responseMessage = response.getString("responseMessage");
                            Log.e("thanhdeptrai",responseMessage);
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
                HashMap<String , String> body = new HashMap<String, String>();
                body.put("strCompName", "Đồng Tháp");
                body.put("strFromDate", "01/01/2018");
                body.put("strCompName", "20/12/2019");
                return body;

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


    private void showDialogSearchDay() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dialogday = new Dialog(this);
        dialogday.setTitle("Thangcode.com");
        dialogday.setContentView(R.layout.dialog_theongay);
        dialogday.show();
        spin1=(Spinner) dialogday.findViewById(R.id.spinner1);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arr);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin1.setAdapter(adapter);
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

    private void showDialogSearchMonth() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        Dialog dialog = new Dialog(this);
        dialog.setTitle("Thangcode.com");
        dialog.setContentView(R.layout.dialog_theothang);
        dialog.show();
        spin2=(Spinner) dialog.findViewById(R.id.spinner1);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, arr);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin2.setAdapter(adapter);
        ImageView btnstart = dialog.findViewById(R.id.btnChooseStarMonth);
        ImageView btnend = dialog.findViewById(R.id.btnChooseEndMonth);
        Button btnSearch = dialog.findViewById(R.id.btnsearchthang);
        txtStartmonth=dialog.findViewById(R.id.txtthangbatdau);
        txtEndmonth=dialog.findViewById(R.id.txtthangketthuc);
        txtStartmonth.setText("1/"+year);
        txtEndmonth.setText((month+1)+"/"+year);
        btnstart.setOnClickListener(this);
        btnend.setOnClickListener(this);
        btnSearch.setOnClickListener(this);


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        if (tab.getPosition() == 0) {
            showDialogSearchDay();
        } else if (tab.getPosition() == 1) {
            showDialogSearchMonth();
        }
//        tabLayout.setTabTextColors(Color.BLACK,Color.WHITE);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
                switch (v.getId()){
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
                    case R.id.btnsearchngay:

                    break;
                    case R.id.btnsearchthang:
                        Intent i2 = new Intent(TimkiemActivity.this, Timkiemtheothang.class);
                        startActivity(i2);
                        break;

        }
    }

    private void showDialogPickMonth(final boolean check) {
        final Calendar today = Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(TimkiemActivity.this, new MonthPickerDialog.OnDateSetListener() {
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
