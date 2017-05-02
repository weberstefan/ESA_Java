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


    public static final int getCharEndPosSA(final EnhancedSuffixArray esa,
                                            final char charOfInterest) {
        for (final char c : esa.bwtCMap.keySet()) {
            if (esa.bwtCMap.get(c).getPosMap() == esa.bwtCMap.get(charOfInterest).getPosMap() + 1) {
                return (esa.bwtCMap.get(c).getPosSequence() - 1);
            }
        }

        // it is the last character
        if (esa.bwtCMap.containsKey(charOfInterest)) {
            return esa.length - 1;
        } else
            throw new RuntimeException("Yout Character of interest is not inside your sequence, hence, not inside the Enhanced Suffix Array.");
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
                if (a[i][j] < 0) {
                    sb.append("-\t");
                } else {
                    sb.append(a[i][j] + "\t");
                }
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
        while (k < l &&
                (i + k) < esa.length) {
            sb.append(esa.sequence[i + k]);
            k = k + 1;
        }

        return sb.toString();
    }

}
