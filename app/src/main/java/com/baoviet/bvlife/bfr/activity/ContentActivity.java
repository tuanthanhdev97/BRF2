package com.baoviet.bvlife.bfr.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.baoviet.bvlife.bfr.R;
import com.baoviet.bvlife.bfr.adapter.MenuAdapter;
import com.baoviet.bvlife.bfr.adapter.MenuExpendableAdapter;
import com.baoviet.bvlife.bfr.model.MenuBaoCao;
import com.baoviet.bvlife.bfr.util.StringDefine;
import com.bumptech.glide.Glide;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipperTitle, viewFlipperBody;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    ListView listViewMenu;

    ExpandableListView expandableListViewMenu;
    //List<String> listdataHeader;
    HashMap<String,List<String>> listdataChild;

    DrawerLayout drawerLayout;

    // Chi tiet cua Menu
    ArrayList<MenuBaoCao> arrayMenu;
    ArrayList<MenuBaoCao> arrayListHeader;
    List<MenuBaoCao> listdataHeader;

    MenuAdapter adapter;
    MenuExpendableAdapter menuExpendableAdapter;

    TextView fullName, userName;
    ImageView btnDropArrow;
    ImageView imgMenu, imgTitle, imgBody;
    WebView webViewTitle;
    String token;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Anhxa();
        ActionBar();
        try {
            ActionViewFlipper();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void ActionViewFlipper() throws JSONException {
        //viewFlipperTitle = (ViewFlipper) findViewById(R.id.viewflipperTitle);
        //viewFlipperBody = (ViewFlipper) findViewById(R.id.viewflipperBody);
        //ArrayList<String> manghinhanh = new ArrayList<>();

        /*manghinhanh.add("https://baomoi-photo-2-td.zadn.vn/w700_r1m/17/08/30/100/23155270/1_83929.jpg");
        manghinhanh.add("https://thuonggiaonline.vn/upload/thu-hang/2018/thang-1/ngay-5/Bao-Viet-doanh-thu-15-ty-USD.jpg");
        manghinhanh.add("http://www.baoviet.com.vn/insurance/Uploads/Library/Images/GAL3884_Anh%202%20Gio%20vang%20gia%20soc%20664%20web(1).jpg");

        for(int i =0; i<manghinhanh.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext())
                    .load(manghinhanh.get(i))
                    .placeholder(R.drawable.logo_baoviet)
                    .error(R.drawable.logo_baoviet)
                    .into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);*/

        imageView = new ImageView(getApplicationContext());

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("token");
        if(bundle != null){
            token = bundle.getString("token_login");
            // Doc du lieu tu Services
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            ImageRequest imageRequest = new ImageRequest(StringDefine.URL_TITLE_IMAGE,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            /*//imgTitle.setImageBitmap(response);*/
                            ImageView imageView = new ImageView(getApplicationContext());
                            Glide.with(getApplicationContext())
                                    .load(StringDefine.URL_TITLE_IMAGE)
                                    .placeholder(R.drawable.logo_baoviet)
                                    .error(R.drawable.logo_baoviet)
                                    .into(imageView);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            viewFlipperTitle.addView(imageView);
                        }
                    }, 0, 0, null, null

            ){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", token);
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            ImageRequest imageRequestBody = new ImageRequest(StringDefine.URL_BODY_IMAGE,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            imgBody.setImageBitmap(response);
                        }
                    }, 0, 0, null, null

            ){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", token);
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            requestQueue.add(imageRequest);
            requestQueue.add(imageRequestBody);
        }
        /*ImageView imageViewBody = new ImageView(getApplicationContext());
        Picasso.with(getApplicationContext())
                .load(R.drawable.ic_mainbody)
                .placeholder(R.drawable.logo_baoviet)
                .error(R.drawable.logo_baoviet)
                .into(imageViewBody);
        imageViewBody.setScaleType(ImageView.ScaleType.FIT_XY);
        viewFlipperBody.addView(imageViewBody);*/
    }

    private void AddControlMenu() {
        expandableListViewMenu = (ExpandableListView) findViewById(R.id.expandablelistviewMenu);
        //listdataHeader = new ArrayList<>();
        listdataHeader = new ArrayList<>();
        listdataChild = new HashMap<String,List<String>>();

        listdataHeader.add(new MenuBaoCao("Trang chủ", R.drawable.ic_homepage));
        //listdataHeader.add(new MenuBaoCao(StringDefine.Baocaonhanh, R.drawable.report_icon));
        listdataHeader.add(new MenuBaoCao(StringDefine.Baocaodoanhthu, R.drawable.report_icon));
        listdataHeader.add(new MenuBaoCao(StringDefine.Baocaotuyendung, R.drawable.ic_employee));
        listdataHeader.add(new MenuBaoCao("Tra cứu lịch sử", R.drawable.ic_employee));
        listdataHeader.add(new MenuBaoCao("Đăng xuất", R.mipmap.icon_logout));

        List<String> baocao = new ArrayList<String>();
        baocao.add(StringDefine.Baocaodoanhthu);
        baocao.add(StringDefine.Baocaotuyendung);

        menuExpendableAdapter = new MenuExpendableAdapter(ContentActivity.this,listdataHeader,listdataChild);
        expandableListViewMenu.setAdapter(menuExpendableAdapter);

        CatchOnListViewMenu();
    }

    private void CatchOnListViewMenu() {
        /*expandableListViewMenu.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = getIntent();
                Bundle bundle = intent.getBundleExtra("token");
                if(listdataChild.get(listdataHeader.get(groupPosition).getTen()).get(childPosition).toString().equals(StringDefine.Baocaodoanhthu) ){
                    Intent intentBCKinhdoanh = new Intent(ContentActivity.this,BCKinhdoanhActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);

                        intentBCKinhdoanh.putExtra("token",bundle);
                        //System.out.println(token);
                    }
                    startActivity(intentBCKinhdoanh);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(listdataChild.get(listdataHeader.get(groupPosition).getTen()).get(childPosition).toString().equals(StringDefine.Baocaotuyendung) ){
                    Intent intentBCTuyendung = new Intent(ContentActivity.this,BCTuyendungActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentBCTuyendung.putExtra("token",bundle);
                    }
                    startActivity(intentBCTuyendung);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                *//*else if(listdataChild.get(listdataHeader.get(groupPosition).getTen()).get(childPosition).toString().equals(StringDefine.Baocaochungchi) ) {
                    Intent intentBCChungchi = new Intent(ContentActivity.this, BCChungchiActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentBCChungchi.putExtra("token",bundle);
                    }
                    startActivity(intentBCChungchi);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(listdataChild.get(listdataHeader.get(groupPosition).getTen()).get(childPosition).toString().equals(StringDefine.Baocaokehoachtuyendung) ) {
                    Intent intentBCKHTuyendung = new Intent(ContentActivity.this, BCKehoachTuyendungActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentBCKHTuyendung.putExtra("token",bundle);
                    }
                    startActivity(intentBCKHTuyendung);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(listdataChild.get(listdataHeader.get(groupPosition).getTen()).get(childPosition).toString().equals(StringDefine.BaocaokehoachAFYP) ) {
                    Intent intentBCKHAFYP = new Intent(ContentActivity.this, BCKehoachAFYPActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentBCKHAFYP.putExtra("token",bundle);
                    }
                    startActivity(intentBCKHAFYP);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }*//*
                return false;
            }
        });*/

        expandableListViewMenu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Intent intent = getIntent();
                Bundle bundle = intent.getBundleExtra("token");
                if(listdataHeader.get(groupPosition).getTen().toString().equals("Trang chủ")){
                    Intent intentHomepage = new Intent(ContentActivity.this, ContentActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentHomepage.putExtra("token",bundle);
                    }
                    startActivity(intentHomepage);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(listdataHeader.get(groupPosition).getTen().toString().equals(StringDefine.Baocaodoanhthu)){
                    Intent intentBCKinhdoanh = new Intent(ContentActivity.this,BCKinhdoanhActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentBCKinhdoanh.putExtra("token",bundle);
                    }
                    startActivity(intentBCKinhdoanh);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(listdataHeader.get(groupPosition).getTen().toString().equals("Tra cứu lịch sử")){
                    Intent intentTimkiem = new Intent(ContentActivity.this,TimkiemActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentTimkiem.putExtra("token",bundle);
                    }
                    startActivity(intentTimkiem);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if(listdataHeader.get(groupPosition).getTen().toString().equals(StringDefine.Baocaotuyendung)){
                    Intent intentBCTuyendung = new Intent(ContentActivity.this,BCTuyendungActivity.class);
                    if(bundle != null){
                        String token = bundle.getString("token_login");
                        bundle.putString("token_login", token);
                        intentBCTuyendung.putExtra("token",bundle);
                    }
                    startActivity(intentBCTuyendung);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if(listdataHeader.get(groupPosition).getTen().toString().equals("Đăng xuất")){
                    Intent intentLogout = new Intent(ContentActivity.this, LoginActivity.class);
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

    private void ActionBar() {//ic_menu_sort_by_size
        setSupportActionBar(toolbar);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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

    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarMenu);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        imgMenu = (ImageView) findViewById(R.id.imgmainMenu);
        //imgTitle = (ImageView) findViewById(R.id.imgTitle);
        imgBody = (ImageView) findViewById(R.id.imgMainBody);
        viewFlipperTitle = (ViewFlipper) findViewById(R.id.viewflipperTitle);

        // Nhan du lieu token tu Intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("token");

        /*btnDropArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khởi tạo 1 popupmenu
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), btnDropArrow);
                //đẩy layout của mình vừa tạo ở trên vào ứng dụng
                popupMenu.getMenuInflater().inflate(R.menu.context_menu_accout_info, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menu_item) {
                        switch (menu_item.getItemId()) {
                            case R.id.menuAccountSetting:
                                Toast.makeText(ContentActivity.this, "Chức năng đang trong quá trình xây dựng", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menuChangePassword:
                                Toast.makeText(ContentActivity.this, "Chức năng đang trong quá trình xây dựng", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menuLogout:
                                Intent intentLogout = new Intent(ContentActivity.this, LoginActivity.class);
                                startActivity(intentLogout);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                        }
                        return true;
                    }
                });

                try {
                    Field field = popupMenu.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                    Method method = cls.getDeclaredMethod("setForceShowIcon", new Class[]{boolean.class});
                    method.setAccessible(true);
                    method.invoke(menuPopupHelper, new Object[]{true});
                } catch (Exception e) {
                    e.printStackTrace();
                }
                popupMenu.show();
            }

        });*/
    }
}
