package com.baoviet.bvlife.bfr.util;

import com.baoviet.bvlife.bfr.model.BCKinhdoanh;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 4/12/2018. BCKinhdoanh
 */

public class KD_CongtyComparator implements Comparator<BCKinhdoanh> {
    private static List<String> important = Arrays.asList("Toàn hệ thống");
    @Override
    public int compare(BCKinhdoanh o1, BCKinhdoanh o2) {
        if(important.contains(o1.congty)) { return -1; }
        if(important.contains(o2.congty)) { return 1; }
        SapxepTiengViet sx = new SapxepTiengViet();
        //return o1.congty.compareToIgnoreCase(o2.congty);
        return sx.generator(o1.congty).compareTo(sx.generator(o2.congty));
    }

}