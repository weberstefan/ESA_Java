package de.weber.esa.utils;

/**
 * Created by Stefan on 02.03.2017.
 */
public class MathUtils {

    public static final int SHORT_MAXIMUM_LCP = 32767;
    public static final int BYTE_MAXIMUM_LCP = 127;

    /*
        http://stackoverflow.com/a/3305710
     */
    public static int ld(int bits) {
        if( bits == 0 )
            return 0; // or throw exception
        return 31 - Integer.numberOfLeadingZeros( bits );
    }

    public static int pow(final int base,
                          final int pow) {
        int tmp = 1;
        for (int i = 0; i < pow; i++) {
            tmp = tmp * base;
        }
        return tmp;
    }

}
