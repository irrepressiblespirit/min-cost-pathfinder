package com.skibnev.mincostpathfinder.comparator;

import com.skibnev.mincostpathfinder.logic.Path;

import java.math.BigDecimal;
import java.util.Comparator;

public class PathComparator implements Comparator<Path> {

    @Override
    public int compare(Path path1, Path path2) {
        BigDecimal bigDecimal = new BigDecimal(path1.getCost());
        BigDecimal bigdecimal2 = new BigDecimal(path2.getCost());
        if (bigDecimal.compareTo(bigdecimal2) < 0) {
            return -1;
        }
        return 1;
    }
}
