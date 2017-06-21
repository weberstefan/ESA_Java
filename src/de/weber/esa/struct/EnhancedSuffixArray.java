package de.weber.esa.struct;

import de.weber.esa.struct.bwt.BWT;
import de.weber.esa.struct.bwt.BWTFeatures;
import de.weber.esa.struct.childtable.ChildTable;
import de.weber.esa.struct.lcp.LCP;
import de.weber.esa.utils.ESA_Utils;
import external.sais;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stefan on 12.01.2017.
 * <p>
 * Requires <p>String: Input Sequence</p>
 * <p>
 * Wrapper class for the enhanced suffix array for a given input string creating:
 * <p>Suffix Array will be created with sais implementation</p>
 * <p>LCP Table</p>
 * <p>Child Table</p>
 * <p>Burrows Wheeler Transformation</p>
 * <p>LCP Tree</p>
 * <p>Maximum Repeats</p>
 * <p>RMQ</p>
 * <p>Wavelet Tree</p>
 */
public class EnhancedSuffixArray {

    /**
     * The sequence with length N corresponding to the suffix array as a character array
     * <p>Required Space: 12 + (N*2 [Bytes per char]) -> Round up to next multiple of 8</p>
     */
    public final char[] sequence;

    /**
     * Termination symbol not in the alphabet of the sequence
     * <p>16 bit</p>
     */
    public final char terminationSymbol = '$';

    /**
     * The length of the sequence + termination symbol
     * <p>16 bit</p>
     */
    public final int length;

    /**
     * Represents the suffix array in O(N) with the sais implementation
     * <p>12 + (N * 4[bytes per int]) -> Round up to next multiple of 8</p>
     */
    public final int[] suffices;

    /**
     * The inverse of the suffix array length N, that is important only for calculating LCP values in O(N)
     * <p>12 + (N * 4[bytes per int]) -> Round up to next multiple of 8</p>
     */
    public int[] inverse;

    /**
     * Represents the LCP array
     */
    public final LCP lcp;

    /**
     * Represents the Child table
     */
    public final ChildTable child;

    /**
     * Number of distinct characters in the sequence
     * <p>16 bit</p>
     */
    public final int numberOfDistinctCharacters;

    /**
     * Represents the C(*) function for the BWT
     * <p>Keys: Distinct characters of the sequence</p><br />
     * <p></p>
     */
    public final Map<Character, BWTFeatures> bwtCMap;

    /**
     * Represents the BWT tables
     */
    public BWT bwt;

    /**
     * calculate the enhaced suffix array for a given input sequence
     *
     * @param s : input sequence of interest
     */
    public EnhancedSuffixArray(final String s) {
        // add the termination symbol to the input sequence s
        final String tmpSequence = s + this.terminationSymbol;
        // getting the number of distinct characters in the tmpSequence
        this.numberOfDistinctCharacters = ESA_Utils.countUniqueCharactersInString(tmpSequence);
        // create a char array for the temporary input sequence
        this.sequence = tmpSequence.toCharArray();
        // set the length N to number of characters in the sequence
        this.length = this.sequence.length;
        // initialize the suffices with length N
        this.suffices = new int[this.length + 1];

        // get the suffix array using sais implementation
        sais.suffixsort(this.sequence, this.suffices, this.length);
        // set last entry to - 1 --> sequence length + 1
        this.suffices[this.length] = - 1;

        this.bwtCMap = new HashMap<>(this.numberOfDistinctCharacters);
        int counter = 0;
        // create the inverse for the suffix array in O(N) which is used for a linear time construction of the LCP table
        // computing the BWT C(*) Map
        this.inverse = new int[this.length];
        for (int i = 0; i < this.length; i = i + 1) {
            this.inverse[this.suffices[i]] = i;

            if (! this.bwtCMap.containsKey(this.sequence[this.suffices[i]])) {
                BWTFeatures f = new BWTFeatures(i, counter);
                this.bwtCMap.put(this.sequence[this.suffices[i]], f);
                counter = counter + 1;
            }
        }

        // compute the LCP array
        this.lcp = new LCP(this);
//        System.out.println("DONE LCP esa: " + Calendar.getInstance().getTime());
        // deallocate the inverse table, since it is not of any use anymore
//        this.inverse = null;

        // compute the child table
        this.child = new ChildTable(this.lcp);
//        System.out.println("DONE Child: " + Calendar.getInstance().getTime());

        // compute the BWT tables
        this.bwt = null; //new BWT(this, true);
//        System.out.println("DONE Bwt: " + Calendar.getInstance().getTime());
    }

    @Override
    public String toString() {
        return "Sequence:\t" + Arrays.toString(this.sequence) + "\n" +
                "SA[Seq[i]]:\t" + Arrays.toString(this.suffices) + "\n" +
                "Inverse:\t" + Arrays.toString(this.inverse) + "\n" +
                this.lcp + "\n" +
                this.child + "\n" + this.bwt;
    }

}
