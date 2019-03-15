package com.baoviet.bvlife.bfr.util;

import com.baoviet.bvlife.bfr.model.SearchDay;
import com.baoviet.bvlife.bfr.model.SearchMonth;

import java.util.Comparator;

public class Sapxepdoanhthuthang implements Comparator<SearchMonth> {
    @Override
    public int compare(SearchMonth o1, SearchMonth o2) {
        if (o1.doanhthu == o2.doanhthu)
            return 0;
        else if (o1.doanhthu < o2.doanhthu)
            return 1;
        else
            return -1;
    }
}