package com.baoviet.bvlife.bfr.util;

import com.baoviet.bvlife.bfr.model.BCTuyendung;

import java.util.Comparator;

/**
 * Created by Administrator on 4/12/2018.
 */

public class TD_CongtyComparator implements Comparator<BCTuyendung> {
    @Override
    public int compare(BCTuyendung o1, BCTuyendung o2) {
        SapxepTiengViet sx = new SapxepTiengViet();
        //return o1.congty.compareToIgnoreCase(o2.congty);
        return sx.generator(o1.congty).compareTo(sx.generator(o2.congty));
    }

}