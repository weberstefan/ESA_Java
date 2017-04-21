package de.weber.esa.struct.lcp;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.discriminatingCharacters.DiscriminatingCharacters;
import de.weber.esa.utils.ESA_Utils;

import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by Stefan on 20.01.2017.
 * <p>
 * Class for calculating LCP values and corresponding first discriminating characters
 * for a given suffix array in O(n)
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
//    public final ArrayList<DiscriminatingCharacters> discriminatingCharacterses;
    public final DiscriminatingCharacters[] dc;
//    public HashMap<Integer, DiscriminatingCharacters> discriminatingCharactersHashMap;

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

        this.dc = new DiscriminatingCharacters[this.length - 1];
//        this.discriminatingCharacterses = new ArrayList<>(this.length - 1);
//        this.discriminatingCharactersHashMap = new HashMap<>(this.length - 1);

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

            k = Math.max(0, k - 1);
        }

        System.out.println(Calendar.getInstance().getTime() + "  now DC");

        // compute discriminating characters array in O(n)
        for (int i = 1; i < this.length; i = i + 1) {
            this.dc[i - 1] = new DiscriminatingCharacters(esa.sequence[esa.suffices[i - 1] + this.lcps[i]], esa.sequence[esa.suffices[i] + this.lcps[i]]);
//            this.discriminatingCharacterses.add(new DiscriminatingCharacters(esa.sequence[esa.suffices[i - 1] + this.lcps[i]], esa.sequence[esa.suffices[i] + this.lcps[i]]));
//            this.discriminatingCharactersHashMap.put(i - 1, new DiscriminatingCharacters(esa.sequence[esa.suffices[i - 1] + this.lcps[i]], esa.sequence[esa.suffices[i] + this.lcps[i]]));
        }

//        System.out.println("SIZE OF DC ARRAY[] : " + ObjectSizeCalculator.getObjectSize(this.dc));
//        System.out.println("SIZE OF DC LIST    : " + ObjectSizeCalculator.getObjectSize(this.discriminatingCharacterses));
//        System.out.println("SIZE OF HASHMAP DC : " + ObjectSizeCalculator.getObjectSize(this.discriminatingCharactersHashMap));

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
        return this.dc[pos - 1];
    }

    @Override
    public String toString() {
        return "LCP:\t" + ESA_Utils.arrayToString(this.lcps) + "\nDC: " + Arrays.toString(this.dc);
    }

}
