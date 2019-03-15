package com.baoviet.bvlife.bfr.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.model.SearchDay;
import com.baoviet.bvlife.bfr.adapter.SearchDayAdapter;
import com.baoviet.bvlife.bfr.util.Sapsepdoanhthungay;
import com.baoviet.bvlife.bfr.util.StringDefine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Timkiemtheongay extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private ListView lvday;
    private TextView txtStartday,txtEndday;
    private TextView txtTP,txtGet,txtNoti;
    private TextView txtInforStartDate,txtInforEndDate;
    private TextView txtCheck;
    private Dialog dialogday;
    private DatePickerDialog datePickerStartDay,datePickerEndday;
    private Spinner spin;
    private int year,month,day;
    private List<String> arrCty;
    private ArrayList<SearchDay> arrData;
    private Request.Priority mPriority = Request.Priority.HIGH;
    private String ngay,congty;
    private SearchDayAdapter customAdaper;
    private String token, responseMessage, responseStatus;
    private String cty;
    double solieutrongngay;
    private String s;
    private String startday,endday;
    private LinearLayout rlInfor ;
    private ImageButton btnBack;
    private ImageView btnSearchMore;
    private ArrayList<String> a ;
    private TextView txtSortngay,txtSortdoanhthu,txtSortBieudo;
    boolean unSortedCT=true;
    boolean unSorted=true;
    boolean unSortedMTD = true;
    private RelativeLayout rlContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timkiemtheongay_layout, container, false);

        innit(view);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()){
            getTenCty();
        }
    }

    private void getIntanceCal(){
        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day  = c.get(Calendar.DAY_OF_MONTH);

    }

    private void showDialog() {

        getIntanceCal();
        dialogday = new Dialog(getActivity());
        dialogday.setTitle("Thangcode.com");
        dialogday.setContentView(R.layout.dialog_theongay);
        dialogday.show();


        spin=(Spinner) dialogday.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrCty);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        ImageView btnstart = dialogday.findViewById(R.id.btnChooseStarDay);
        ImageView btnend = dialogday.findViewById(R.id.btnChooseEndDay);
        Button btnSearch = dialogday.findViewById(R.id.btnsearchngay);
        txtStartday=dialogday.findViewById(R.id.txtngaybatdau);
        txtEndday=dialogday.findViewById(R.id.txtngayketthuc);
        txtGet = dialogday.findViewById(R.id.txtText);
        txtCheck = dialogday.findViewById(R.id.txtCheck);

        txtStartday.setText("1/"+(month+1)+"/"+year);
        txtEndday.setText(day+"/"+(month+1)+"/"+year);
        btnstart.setOnClickListener(this);
        btnend.setOnClickListener(this);
        btnSearch.setOnClickListener(this);

    }

    private void getTenCty() {
        // Nhan du lieu token tu Intent
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("Đang tải dữ liệu...");
        progress.show();

        arrCty = new ArrayList<String>();


        // Doc du lieu tu Services
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        JsonObjectRequest req = new JsonObjectRequest(StringDefine.URL_BCDOANHTHU, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progress.cancel();
                        try {
                            responseStatus = response.getString("responseStatus").toString();
                            responseMessage = response.getString("responseMessage");
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
                        showDialog();
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

    private void getData() {

        arrData = new ArrayList<>();
        a = new ArrayList<>();

        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("Đang tải dữ liệu...");
        progress.show();
        // Nhan du lieu token tu Intent

        // Doc du lieu tu Services
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final JSONObject obj = new JSONObject();
        try {
            obj.put("strCompName", txtGet.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            obj.put("strFromDate", txtStartday.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            obj.put("strToDate", txtEndday.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,StringDefine.URL_REVENUE_DATA_DATE, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            responseStatus = response.getString("responseStatus").toString();
                            responseMessage = response.getString("responseMessage");
                            if(responseStatus.equals(StringDefine.responseStatusLoginSuccess)){
                                JSONObject jsonObject = response.getJSONObject("obj");
                                JSONArray jsonArray = jsonObject.getJSONArray("lstDoanhThu");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject obj = jsonArray.getJSONObject(i);
                                        s = obj.getString("dataDate");
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        try {
                                        Date date = dateFormat.parse(s);
                                        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                                        ngay = formatter.format(date);

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                        solieutrongngay = Math.round(obj.getDouble("soLieu"));
//                                      DecimalFormat format = new DecimalFormat("0.#");
//                                      format.format(solieutrongngay);
                                        int doanhthungay = (int) solieutrongngay;
                                        a.add(ngay);
                                        arrData.add(new SearchDay(ngay,doanhthungay));
                                        Log.e("test",""+ngay);
                                }

                                Collections.sort(arrData, new Comparator<SearchDay>() {
                                    @Override
                                    public int compare(SearchDay arg0, SearchDay arg1) {
                                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                        int compareResult = 0;
                                        try {
                                            Date arg0Date = format.parse(arg0.getNgay());
                                            Date arg1Date = format.parse(arg1.getNgay());
                                            compareResult = arg1Date.compareTo(arg0Date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
//                                    compareResult = arg0.compareTo(arg1);
                                        }
                                        return compareResult;
                                    }
                                });

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            txtNoti.setVisibility(View.VISIBLE);
                            txtNoti.setText("Không có dữ liệu trong khoảng bạn muốn tìm kiếm . Vui lòng nhập lại ngày tháng");
                        }
                        progress.cancel();
                        rlContent.setVisibility(View.VISIBLE);
                        rlInfor.setVisibility(View.VISIBLE);
                        customAdaper = new SearchDayAdapter(getContext(),R.layout.item_searchday,arrData);
                        lvday.setAdapter(customAdaper);
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

        req.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(req);
    }

    private void innit(View view) {
        final Intent intent = getActivity().getIntent();
        final Bundle bundle = intent.getBundleExtra("token");
        if(bundle != null){
            token = bundle.getString("token_login");
        }
        if (getUserVisibleHint()){
            getTenCty();

        }

        rlContent = view.findViewById(R.id.rlContent);
        txtSortdoanhthu = view.findViewById(R.id.txtSapxepDoanhthu);
        txtSortdoanhthu.setOnClickListener(this);
        txtSortBieudo= view.findViewById(R.id.txtSapxepBieuDo);
        txtSortBieudo.setOnClickListener(this);
        txtSortngay= view.findViewById(R.id.txtSapxepNgay);
        txtSortngay.setOnClickListener(this);

        txtInforEndDate = view.findViewById(R.id.txtInforEndDate);
        txtInforStartDate=view.findViewById(R.id.txtInforStartDate);
        txtNoti = view.findViewById(R.id.txtNoti);
        lvday = (ListView) view.findViewById(R.id.lvDay);
        txtTP = view.findViewById(R.id.txtTP);
        rlInfor =view.findViewById(R.id.lnInfor);
        btnBack = view.findViewById(R.id.btnBackday);
        btnBack.setOnClickListener(this);
        btnSearchMore = view.findViewById(R.id.btnSearchMorengay);
        btnSearchMore.setOnClickListener(this);

        getIntanceCal();
        datePickerStartDay = new DatePickerDialog(
                getActivity(), R.style.DialogTheme, dateStartDay, year, month, day);
        datePickerEndday = new DatePickerDialog(
                getActivity(), R.style.DialogTheme, dateEndday, year, month, day);
        txtInforStartDate.setText("1/"+(month+1)+"/"+year);
        txtInforEndDate.setText(day+"/"+(month+1)+"/"+year);
    }

    private DatePickerDialog.OnDateSetListener dateStartDay = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            startday =  view.getDayOfMonth()+ "/" + (view.getMonth()+1) + "/" + view.getYear();
            txtStartday.setText(startday);
            txtInforStartDate.setText(startday);

        }
    };

    private DatePickerDialog.OnDateSetListener dateEndday = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            endday =  view.getDayOfMonth()+ "/" + (view.getMonth()+1) + "/" + view.getYear();
            txtEndday.setText(endday);
            txtInforEndDate.setText(endday);
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

                if (cty.equalsIgnoreCase("Chọn công ty")){
                    txtCheck.setVisibility(View.VISIBLE);
                }
                else {
                    dialogday.dismiss();
                    getData();
                }
                break;
            case R.id.btnSearchMorengay:
                rlContent.setVisibility(View.GONE);
                rlInfor.setVisibility(View.GONE);
                showDialog();
                break;
            case R.id.btnBackday:
                getActivity().onBackPressed();
                break;
            case R.id.txtSapxepDoanhthu:
                sortDoanhthu();
                break;
            case R.id.txtSapxepBieuDo:
                sortDoanhthu();
                break;
            case R.id.txtSapxepNgay:
                Collections.sort(arrData, new Comparator<SearchDay>() {
                    @Override
                    public int compare(SearchDay arg0, SearchDay arg1) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        int compareResult = 0;
                        try {
                            Date arg0Date = format.parse(arg0.getNgay());
                            Date arg1Date = format.parse(arg1.getNgay());
                            compareResult = arg1Date.compareTo(arg0Date);
                        } catch (ParseException e) {
                            e.printStackTrace();
//                                    compareResult = arg0.compareTo(arg1);
                        }
                        return compareResult;
                    }
                });
                customAdaper.notifyDataSetChanged();
                break;
        }
    }

    private void sortDoanhthu() {
        if(unSortedMTD) {
            Collections.sort(arrData, new Sapsepdoanhthungay());
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
        customAdaper.notifyDataSetChanged();
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
}