package com.baoviet.bvlife.bfr.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.baoviet.bvlife.bfr.adapter.BCChungchiExpendAdapter;
import com.baoviet.bvlife.bfr.model.BCChungchi;
import com.baoviet.bvlife.bfr.util.StringDefine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BCChungchiActivity extends AppCompatActivity {

    Toolbar toolbar;
    SearchView svBc;
    ExpandableListView expandListViewBc;
    HashMap<String, List<BCChungchi>> listdataChild;

    ArrayList<BCChungchi> listdataHeader;
    BCChungchiExpendAdapter bcExpendAdapter;

    String token, responseMessage, responseStatus;
    String congty, mact;
    String stUpdateTime;

    public double tongsothamgia,tongsocc,tongsotruot,tongsokhoa,tylecapcc;
    TextView updateTime;

    //Textview header
    TextView txtTongsothamgia;

    boolean sortAscending=true;
    boolean unSorted=true;
    boolean unSortedMTD = true;
    boolean sortAscendingMTD=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcchungchi);

        Anhxa();
        ActionToolbar();
        getReportData();
    }

    private void getReportData() {
        // Add a header to the ListView
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.header_bcchungchi, expandListViewBc,false);
        expandListViewBc.addHeaderView(header);

        //header
        txtTongsothamgia = (TextView) header.findViewById(R.id.txtheaderBCCCTongthamgia);

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

        JsonObjectRequest req = new JsonObjectRequest(StringDefine.URL_BCCHUNGCHI, new JSONObject(params),
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
                                JSONArray jsonArray = jsonObject.getJSONArray("lstChungChi");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    congty = obj.getString("tenCT");
                                    mact = obj.getString("maCT");
                                    tongsothamgia = obj.getDouble("tsThamGia");
                                    tongsocc = obj.getDouble("tsCC");
                                    tongsotruot = obj.getDouble("tsTruot");
                                    tongsokhoa = obj.getDouble("tsKhoa");
                                    tylecapcc = obj.getDouble("tlCC");

                                    listdataHeader.add(new BCChungchi(congty,mact,tongsothamgia));

                                    List<BCChungchi> Solieu = new ArrayList<>();
                                    Solieu.add(new BCChungchi("MCT",mact));
                                    Solieu.add(new BCChungchi("Tổng số tham gia",tongsothamgia));
                                    Solieu.add(new BCChungchi("Tổng số CC",tongsocc));
                                    Solieu.add(new BCChungchi("Tổng số trượt",tongsotruot));
                                    Solieu.add(new BCChungchi("Tổng số khóa",tongsokhoa));
                                    Solieu.add(new BCChungchi("Tỷ lệ cấp CC (%)",tylecapcc));

                                    listdataChild.put(listdataHeader.get(i).getCongty(),Solieu);
                                }
                                bcExpendAdapter = new BCChungchiExpendAdapter(getApplicationContext(),listdataHeader,listdataChild);
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

                                /*txtTongsothamgia.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        sortData();
                                    }
                                    private void sortData() {
                                        if(unSorted) Collections.sort(listdataHeader, new CC_TSTG_Comparator());
                                        else Collections.reverse(listdataHeader);

                                        sortAscending=!sortAscending;
                                        unSorted=false;

                                        bcExpendAdapter = new BCChungchiExpendAdapter(getApplicationContext(),listdataHeader,listdataChild);
                                        expandListViewBc.setAdapter(bcExpendAdapter);
                                    }
                                });*/

                                /*
                                  txtheaderMTD.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //Toast.makeText(getApplicationContext(), "Sắp xếp theo MTD", Toast.LENGTH_SHORT).show();
                                        sortDataMTD();
                                    }
                                    private void sortDataMTD() {
                                        if(unSortedMTD) Collections.sort(listdataHeader, new MTDComparator());
                                        else Collections.reverse(listdataHeader);

                                        sortAscendingMTD=!sortAscendingMTD;
                                        unSortedMTD=false;
                                        bcKinhdoanhExpendAdapter = new BCKinhdoanhExpendAdapter(getApplicationContext(),listdataHeader,listdataChild);
                                        expandListViewBcKinhdoanh.setAdapter(bcKinhdoanhExpendAdapter);
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
            /*@Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                //headers.put("Authorization", token);
                headers.put("Content-Type", "application/json; charset=UTF-8");
                headers.put("token", token);
                return headers;
            }*/
        };

        requestQueue.add(req);
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarbaocaochungchi);
        expandListViewBc = (ExpandableListView) findViewById(R.id.expandlistviewbaocaochungchi);
        listdataHeader = new ArrayList<>();
        listdataChild = new HashMap<String, List<BCChungchi>>();
        svBc = (SearchView) findViewById(R.id.searchviewbaocaochungchi);
        //giatri = (TextView) findViewById(R.id.child_bctd_giatri);
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
