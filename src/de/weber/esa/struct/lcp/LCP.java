package de.weber.esa.struct.lcp;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.discriminatingCharacters.DiscriminatingCharacters;
import de.weber.esa.utils.ESA_Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stefan on 20.01.2017.
 * <p>
 * Class for calculating LCP values for a given suffix array in O(n)
 * <p>Theorem 5.24 in Scriptum: Algorithmns and Sequences by Prof. Heun</p>
 */
public class LCP {

    /**
     * The length of the LCP table
     * <p>16 bit</p>
     */
    public final int length;

    /**
     * Represents the LCP table
     * <p>12 + (N * 4[bytes per int]) -> Round up to next multiple of 8</p>
     */
    public final int[] lcps; // TODO REPRESENT AS BIT ARRAY

    /**
     * Represents the first discriminating characters
     */
    public final Map<Integer, DiscriminatingCharacters> discriminatingCharactersMap;

    /**
     * calculate the LCP table for the suffix array
     *
     * @param esa : suffix array for a given string
     */
    public LCP(final EnhancedSuffixArray esa) {
        // set the length N
        this.length = esa.length;
        /**
         * initialize the lcp array
         * the last value is set to -1 for correctly computing the child tables
         */
        this.lcps = new int[this.length + 1];

        this.discriminatingCharactersMap = new HashMap<>(this.length - 1);

        //lcps[0] = -1 by definition
        this.lcps[0] = - 1;
        // calculating the lcp array
        int k = 0;
        for (int i = 0; i < this.length - 1; i = i + 1) {
            final int a = esa.inverse[i];
            final int b = esa.suffices[a - 1];

            while ((i + k) < this.length &&
                    (b + k) < this.length &&
                    esa.sequence[i + k] == esa.sequence[b + k]) {
                k = k + 1;
            }

            this.lcps[a] = k;

            /*
                TODO Space efficiently compute discriminating characters

                this.discriminatingCharactersMap.put(i, new DiscriminatingCharacters(esa.sequence[b + k], esa.sequence[i + k]));
            */

            k = Math.max(0, k - 1);
        }

        // for getting correct child properties
        // set lcp[length + 1] = -1
        this.lcps[this.length] = - 1;
    }

    /**
     * receive first discriminating characters at position
     *
     * @param pos : position of interest
     * @return Discriminating Characters at position
     */
    public DiscriminatingCharacters getDiscriminatingCharactersAtPosition(final int pos) {
        return this.discriminatingCharactersMap.get(pos);
    }

    @Override
    public String toString() {
        return "LCP:\t" + ESA_Utils.arrayToString(this.lcps) + "\nDC: " + this.discriminatingCharactersMap;
    }

}
