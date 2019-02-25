package com.baoviet.bvlife.bfr.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.adapter.BCKehoachAFYPExpendAdapter;
import com.baoviet.bvlife.bfr.model.BCKehoachAFYP;
import com.baoviet.bvlife.bfr.util.StringDefine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BCKehoachAFYPActivity extends AppCompatActivity {

    Toolbar toolbar;
    SearchView svBc;
    ExpandableListView expandListViewBc;
    HashMap<String, List<BCKehoachAFYP>> listdataChild;

    ArrayList<BCKehoachAFYP> listdataHeader;
    BCKehoachAFYPExpendAdapter bcExpendAdapter;

    String token, responseMessage, responseStatus;
    String congty, stUpdateTime;
    public double tongcongtygia;
    public double T1;
    public double T2;
    public double T3;
    public double Q1;
    public double T4;
    public double T5;
    public double T6;
    public double Q2;
    public double T7;
    public double T8;
    public double T9;
    public double Q3;
    public double T10;
    public double T11;
    public double T12;
    public double Q4;
    public double canam;
    TextView updateTime;

    //Textview header
    TextView txtTongcongtygiao;

    boolean sortAscending=true;
    boolean unSorted=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bckehoach_afyp);

        Anhxa();
        ActionToolbar();
        getReportData();
    }

    private void getReportData() {
        // Add a header to the ListView
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.header_bckehoach_afyp, expandListViewBc,false);
        expandListViewBc.addHeaderView(header);

        //header
        txtTongcongtygiao = (TextView) header.findViewById(R.id.txtheaderBCKHAFYPTongctygiao);

        // Nhan du lieu token tu Intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("token");
        if(bundle != null){
            token = bundle.getString("token_login");
            //System.out.println(token);
        }

        // Doc du lieu tu Services
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", token);

        JsonObjectRequest req = new JsonObjectRequest(StringDefine.URL_BCKHAFYP, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            responseStatus = response.getString("responseStatus").toString();
                            responseMessage = response.getString("responseMessage");
                            if(responseStatus.equals(StringDefine.responseStatusLoginSuccess)){
                                JSONObject jsonObject = response.getJSONObject("obj");
                                stUpdateTime = jsonObject.getString("maxDate");
                                updateTime.setText(updateTime.getText() + " " + stUpdateTime);
                                JSONArray jsonArray = jsonObject.getJSONArray("lstKHDoanhThu");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    congty = obj.getString("tenCT");
                                    tongcongtygia = obj.getDouble("tct");
                                    T1 = obj.getDouble("tdT1");
                                    T2 = obj.getDouble("tdT2");
                                    T3 = obj.getDouble("tdT3");
                                    Q1 = obj.getDouble("tdQ1");

                                    T4 = obj.getDouble("tdT4");
                                    T5 = obj.getDouble("tdT5");
                                    T6 = obj.getDouble("tdT6");
                                    Q1 = obj.getDouble("tdQ2");

                                    T7 = obj.getDouble("tdT7");
                                    T8 = obj.getDouble("tdT8");
                                    T9 = obj.getDouble("tdT9");
                                    Q1 = obj.getDouble("tdQ3");

                                    T10 = obj.getDouble("tdT11");
                                    T11 = obj.getDouble("tdT11");
                                    T12= obj.getDouble("tdT12");
                                    Q4 = obj.getDouble("tdQ4");
                                    canam = obj.getDouble("tdYear");

                                    listdataHeader.add(new BCKehoachAFYP(congty,tongcongtygia,T1));

                                    List<BCKehoachAFYP> Solieu = new ArrayList<>();
                                    Solieu.add(new BCKehoachAFYP("Tổng Công ty giao",tongcongtygia));
                                    Solieu.add(new BCKehoachAFYP("Tháng 1",T1));
                                    Solieu.add(new BCKehoachAFYP("Tháng 2",T2));
                                    Solieu.add(new BCKehoachAFYP("Tháng 3",T3));
                                    Solieu.add(new BCKehoachAFYP("Quý 1",Q1));

                                    Solieu.add(new BCKehoachAFYP("Tháng 4",T1));
                                    Solieu.add(new BCKehoachAFYP("Tháng 5",T2));
                                    Solieu.add(new BCKehoachAFYP("Tháng 6",T3));
                                    Solieu.add(new BCKehoachAFYP("Quý 2",Q2));

                                    Solieu.add(new BCKehoachAFYP("Tháng 7",T1));
                                    Solieu.add(new BCKehoachAFYP("Tháng 8",T2));
                                    Solieu.add(new BCKehoachAFYP("Tháng 9",T3));
                                    Solieu.add(new BCKehoachAFYP("Quý 3",Q3));

                                    Solieu.add(new BCKehoachAFYP("Tháng 10",T1));
                                    Solieu.add(new BCKehoachAFYP("Tháng 11",T2));
                                    Solieu.add(new BCKehoachAFYP("Tháng 12",T3));
                                    Solieu.add(new BCKehoachAFYP("Quý 4",Q4));

                                    listdataChild.put(listdataHeader.get(i).getCongty(),Solieu);
                                }
                                bcExpendAdapter = new BCKehoachAFYPExpendAdapter(getApplicationContext(),listdataHeader,listdataChild);
                                expandListViewBc.setAdapter(bcExpendAdapter);
                                //Search View
                                svBc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                    @Override
                                    public boolean onQueryTextSubmit(String query) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String newText) {
                                        bcExpendAdapter.getFilter().filter(newText);
                                        return false;
                                    }
                                });

                                /*txtTongcongtygiao.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //Toast.makeText(getApplicationContext(), "Sắp xếp theo Số liệu trong ngày", Toast.LENGTH_SHORT).show();
                                        sortDataSolieutrongngay();
                                    }
                                    private void sortDataSolieutrongngay() {
                                        if(unSorted) Collections.sort(listdataHeader, new KHAFYP_TCTG_Comparator());
                                        else Collections.reverse(listdataHeader);

                                        sortAscending=!sortAscending;
                                        unSorted=false;

                                        bcExpendAdapter = new BCKehoachAFYPExpendAdapter(getApplicationContext(),listdataHeader,listdataChild);
                                        expandListViewBc.setAdapter(bcExpendAdapter);
                                    }
                                });*/
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", token);
                return headers;
            }
        };

        requestQueue.add(req);
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarbaocaoKHAFYP);
        expandListViewBc = (ExpandableListView) findViewById(R.id.expandlistviewbaocaoKHAFYP);
        listdataHeader = new ArrayList<>();
        listdataChild = new HashMap<String, List<BCKehoachAFYP>>();
        svBc = (SearchView) findViewById(R.id.searchviewbaocaoKHAFYP);

        updateTime = (TextView) findViewById(R.id.txtbcUpdatetime);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
