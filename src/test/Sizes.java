package test;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.junit.Test;

/**
 * Created by Stefan on 22.01.2017.
 */
public class Sizes {

    @Test
    public void sizes() {
        final int[] x = new int[12];

        System.out.println(ObjectSizeCalculator.getObjectSize(x));


        // ((
        final int[][] xx = new int[8][15];

        System.out.println(ObjectSizeCalculator.getObjectSize(xx));
    }

}
