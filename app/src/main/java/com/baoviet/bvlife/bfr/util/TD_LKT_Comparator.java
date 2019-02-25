package com.baoviet.bvlife.bfr.util;

import com.baoviet.bvlife.bfr.model.BCTuyendung;

import java.util.Comparator;

/**
 * Created by Administrator on 3/14/2018.
 */

public class TD_LKT_Comparator implements Comparator<BCTuyendung> {
    @Override
    public int compare(BCTuyendung o1, BCTuyendung o2) {
        if (o1.thuctuyen_luykethang == o2.thuctuyen_luykethang)
            return 0;
        else if (o1.thuctuyen_luykethang > o2.thuctuyen_luykethang)
            return 1;
        else
            return -1;
    }
}