package de.weber.esa.struct.lcp;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.utils.MathUtils;

import java.util.Arrays;

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
     * Represents the border for which Lcp Values will be stored in the exception array
     * <p>Either Byte or Short border</p>
     * <p>16 bit</p>
     */
    public final int BORDER_FOR_EXCEPTION = MathUtils.SHORT_MAXIMUM_LCP;

    /**
     * Represents the LCP table
     * <p>12 + (N * 4[bytes per int]) -> Round up to next multiple of 8</p>
     */
    public final short[] lcps; // TODO REPRESENT AS BIT ARRAY

    public LcpException[] lcpExceptionArray;
//    public THashMap<Integer, Integer> lcpExceptionMap;

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
        this.lcps = new short[this.length + 1];

//        System.out.println("Starting Lcp: " + Calendar.getInstance().getTime());


        int numberOfExceptions = 0;

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

            if (k >= this.BORDER_FOR_EXCEPTION) {
                this.lcps[a] = (short) this.BORDER_FOR_EXCEPTION;
                numberOfExceptions = numberOfExceptions + 1;
            } else {
                this.lcps[a] = (short) k;
            }

            k = Math.max(0, k - 1);
        }

//        System.out.println("Now fill Exception Array: " + Calendar.getInstance().getTime() + "\tnumberOfExceptions = " + numberOfExceptions);

        if (numberOfExceptions > 0) {
//        this.lcpExceptionMap = new THashMap<>(numberOfExceptions, .75f);
            this.lcpExceptionArray = new LcpException[numberOfExceptions];
            int pos = 0;

            for (int i = 0; i < this.length - 1; i = i + 1) {
                final int a = esa.inverse[i];
                final int b = esa.suffices[a - 1];

                while ((i + k) < this.length &&
                        (b + k) < this.length &&
                        esa.sequence[i + k] == esa.sequence[b + k]) {
                    k = k + 1;
                }

                if (k >= this.BORDER_FOR_EXCEPTION) {
                    this.lcpExceptionArray[pos] = new LcpException(a, k);
                    pos = pos + 1;
                }

                k = Math.max(0, k - 1);
            }


            // sort lcp exception array for binary search in ascending order of their positions in the lcp array
            Arrays.sort(this.lcpExceptionArray, (o1, o2) -> Integer.compare(o1.lcpPosition, o2.lcpPosition));
        }

//        System.out.println("DONE Lcp: " + Calendar.getInstance().getTime());

        // for getting correct child properties
        // set lcp[length + 1] = -1
        this.lcps[this.length] = - 1;
    }

    /**
     * get the current Lcp value as integer from either lcp array or exception array
     *
     * @param pos: position in Lcp array
     * @return current Lcp value
     */
    public int getCurrentLcpValue(final int pos) {
        if (this.lcps[pos] < this.BORDER_FOR_EXCEPTION) {
            return (int) this.lcps[pos];
        }
        int l = 0;
        int r = this.lcpExceptionArray.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (this.lcpExceptionArray[mid].lcpPosition == pos) {
                return this.lcpExceptionArray[mid].lcpValue;
            } else if (this.lcpExceptionArray[mid].lcpPosition < pos) {
                l = mid + 1;
            } else if (this.lcpExceptionArray[mid].lcpPosition > pos) {
                r = mid - 1;
            } else {
                throw new RuntimeException("Should not reach here (Binary Search Lcp Exception Array)");
            }
        }
        return - 1;
    }


    @Override
    public String toString() {
        return "LCP:\t" + Arrays.toString(this.lcps) +
                ((this.lcpExceptionArray != null) ? "\nEcArray:\t" + Arrays.toString(this.lcpExceptionArray) : "");
    }

}
