package com.baoviet.bvlife.bfr.util;

import com.baoviet.bvlife.bfr.model.BCKinhdoanh;
import com.baoviet.bvlife.bfr.model.SearchDay;

import java.util.Comparator;

public class Sapsepdoanhthungay implements Comparator<SearchDay> {
    @Override
    public int compare(SearchDay o1, SearchDay o2) {
        if (o1.doanhthu == o2.doanhthu)
            return 0;
        else if (o1.doanhthu < o2.doanhthu)
            return 1;
        else
            return -1;
    }
}
