package com.baoviet.bvlife.bfr.activity;


import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.baoviet.bvlife.bfr.adapter.TimkiemFragmentAdapter;
import com.baoviet.bvlife.bfr.fragment.Timkiemtheongay;
import com.baoviet.bvlife.bfr.fragment.Timkiemtheothang;
import com.baoviet.bvlife.bfr.model.BCKinhdoanh;
import com.baoviet.bvlife.bfr.model.Company;
import com.baoviet.bvlife.bfr.model.MenuBaoCao;
import com.baoviet.bvlife.bfr.util.StringDefine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimkiemActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] navLabels = {"Tra cứu theo ngày","Tra cứu theo tháng" };
    Request.Priority mPriority = Request.Priority.HIGH;

    String token, responseMessage, responseStatus;
    String congty;
    ArrayList<Company> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkiem);
//        Anhxa();

        viewPager = (ViewPager) findViewById(R.id.pager);
        createViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        innit();
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Timkiemtheongay(), "Tab 1");
        adapter.addFrag(new Timkiemtheothang(), "Tab 2");
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
    }

    private void innit() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Tra cứu theo ngày");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tracuu, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Tra cứu theo tháng");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tracuu, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);



//        viewPager.setOffscreenPageLimit(2);
//        //Adding onTabSelectedListener to swipe views
//        tabLayout.setOnTabSelectedListener(this);
//        tabLayout.setSelectedTabIndicatorHeight(0);

        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.bk_bckinhdoanh_left));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
