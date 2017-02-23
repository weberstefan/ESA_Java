package de.weber.esa.utils;

import de.weber.esa.struct.EnhancedSuffixArray;

/**
 * Created by Stefan on 12.01.2017.
 */
public class ESA_Utils {

    /**
     * count unique characters in a string
     *
     * @param input input string
     * @return number of unique characters in input string
     */
    public static final int countUniqueCharactersInString(final String input) {
        final String unique = input.replaceAll("(.)(?=.*?\\1)", "");
        return unique.length();
    }

    /**
     * represents the byte array for the BWT OCC(*,*) Function
     *
     * @param a : OCC byte array
     * @return OCC(*,*) as a String
     */
    public static final String printArray(final byte[][] a) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i = i + 1) {
            for (int j = 0; j < a[0].length; j = j + 1) {
                sb.append(a[i][j] + "\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * represents the byte array for the RMQ Q
     *
     * @param a : int array Q
     * @return Q as a String
     */
    public static final String printArray(final int[][] a) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i = i + 1) {
            for (int j = 0; j < a[0].length; j = j + 1) {
                sb.append(a[i][j] + "\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * receive suffix from position i and length l
     *
     * @param esa : Enhanced Suffix Array for current sequence
     * @param i   : starting position in sequence
     * @param l   : length of suffix of interest
     * @return suffix from i with length l
     */
    public static final String getCurrentSuffix(final EnhancedSuffixArray esa,
                                                final int i,
                                                final int l) {
        StringBuilder sb = new StringBuilder();
//        final int p = esa.suffices[i];
        int k = 0;
        while (k < l) {
            sb.append(esa.sequence[i + k]);
            k = k + 1;
        }

        return sb.toString();
    }

    /**
     * receive array as string
     * @param a : current array
     * @return : a as string in form of Arrays.toString(a)
     */
    public static final String arrayToString(final int[] a) {
        StringBuilder sb = new StringBuilder(a.length * 3);

        sb.append("[");

        for (int i = 0; i < a.length - 1; i = i + 1) {
            if (i == a.length - 2) {
                sb.append(a[i] + "]");
            } else {
                sb.append(a[i] + ", ");
            }
        }

        return sb.toString();
    }

    public static int ld(int bits) {
        int log = 0;
        if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
        if( bits >= 256 ) { bits >>>= 8; log += 8; }
        if( bits >= 16  ) { bits >>>= 4; log += 4; }
        if( bits >= 4   ) { bits >>>= 2; log += 2; }
        return log + ( bits >>> 1 );
    }

    public static int pow(final int base, final int pow) {
        int tmp = 1;
        for (int i = 0; i < pow; i++) {
            tmp *= base;
        }
        return tmp;
    }

}
