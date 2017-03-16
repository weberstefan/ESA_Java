package de.weber.esa.struct.bwt;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.Arrays;

/**
 * Created by Stefan on 13.01.2017.
 * <p>
 * Calculate the Burrows-Wheeler-Transformation
 */
public class BWT {

    /**
     * Represents the BWT
     */
    public final char[] bwt;

    /**
     * length of Burrows Wheeler table
     */
    public final int length;

    /**
     * Represents the OCC(*,*) Function in byte arrays
     * <p>O(n * n)</p>
     */
    public final byte[][] OCC;

    /**
     * Calculating the BWT and the OCC table for a given enhanced suffix array
     *
     * @param esa : enhanced suffix array with LCP for given string
     */
    public BWT(final EnhancedSuffixArray esa) {
        this.length = esa.length;
        this.bwt = new char[this.length];
        this.OCC = new byte[this.length][esa.numberOfDistinctCharacters];

        for (int i = 0; i < this.length; i = i + 1) {
            final int p = esa.suffices[i];
            this.bwt[i] = (p - 1 < 0) ? esa.sequence[this.length - 1] : esa.sequence[p - 1];
            final int pos = esa.bwtCMap.get(this.bwt[i]).getPosMap();
            this.OCC[i][pos] = 1;
        }
    }

    @Override
    public String toString() {
        return "BWT:\t" + Arrays.toString(this.bwt); // + "\nOCC:\n" + ESA_Utils.printArray(this.OCC);
    }


}
