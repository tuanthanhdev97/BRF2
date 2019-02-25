package com.baoviet.bvlife.bfr.util;

import com.baoviet.bvlife.bfr.model.BCKinhdoanh;

import java.util.Comparator;

/**
 * Created by Administrator on 3/6/2018.
 */

public class MTDComparator implements Comparator<BCKinhdoanh> {
    @Override
    public int compare(BCKinhdoanh o1, BCKinhdoanh o2) {
        if (o1.mdtLuykethang == o2.mdtLuykethang)
            return 0;
        else if (o1.mdtLuykethang > o2.mdtLuykethang)
            return 1;
        else
            return -1;
    }

}
