package de.weber.esa.struct.lcp;

import de.weber.esa.io.Reader;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.utils.MathUtils;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Stefan on 24.06.2017.
 */
public class PLcp {

    public static void main(String[] args) {
        final String s = Reader.readFile(new File("res/test/long_string.txt"));

//        final String s = Reader.readFile(new File("res/test/chr_test.txt"));

        System.out.println("READ IN " + s.length());

        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        System.out.println("FINISHED Plcp size:\t" + ObjectSizeCalculator.getObjectSize(esa.plcp));
        System.out.println("FINISHED Lcp size:\t" + ObjectSizeCalculator.getObjectSize(esa.lcp));
    }


//    private LcpException[] lcpExceptionArray;
    private TIntObjectMap<Integer> lcpExcMap;

    public final byte[] plcp;

    private final int length;

    public PLcp(final EnhancedSuffixArray esa,
                final int q) {
        this.length = esa.length - 1;

        int[] plcpAuxiliary = new int[this.length];

        // compute auxiliary
        for (int i = 1; i <= this.length; i = i + 1) {
            if (esa.suffices[i] % q == 0) {
                plcpAuxiliary[esa.suffices[i] / q] = esa.suffices[i - 1];
            }
        }

        // turn auxiliary into plcp q
        plcp = new byte[this.length + 1];

        int numberOfExceptions = 0;

        int l = 0;
        for (int i = 0; i < this.length / q; i = i + 1) {
            final int j = plcpAuxiliary[i];

            while (esa.sequence[i * q + l] == esa.sequence[j + l]) {
                l = l + 1;
            }
            if (l >= MathUtils.BORDER_FOR_EXCEPTION) {
                this.plcp[i] = (short) MathUtils.BORDER_FOR_EXCEPTION;
                numberOfExceptions = numberOfExceptions + 1;
            } else {
                plcp[i] = (byte) l;
            }

            l = Math.max(l - q, 0);
        }

        if (numberOfExceptions > 0) {
            this.lcpExcMap = new TIntObjectHashMap<>(numberOfExceptions, .75f);
//            this.lcpExceptionArray = new LcpException[numberOfExceptions];

            int pos = 0;

            l = 0;
            for (int i = 0; i < this.length / q; i = i + 1) {
                final int j = plcpAuxiliary[i];

                while (esa.sequence[i * q + l] == esa.sequence[j + l]) {
                    l = l + 1;
                }

                if (l >= MathUtils.BORDER_FOR_EXCEPTION) {
                    this.lcpExcMap.put(i, l);
//                    this.lcpExceptionArray[pos] = new LcpException(i, l);
//                    pos = pos + 1;
                }

                l = Math.max(l - q, 0);
            }
        }

        plcp[this.length] = - 1;

        // sort NOT necessary since we compute the LCP values not in lexicographic order but in text order
//        Arrays.sort(this.lcpExceptionArray, (o1, o2) -> Integer.compare(o1.lcpPosition, o2.lcpPosition));
    }

//    public final int getLcpBinary(final int[] sa,
//                                  final int pos) {
//        if (pos == this.length + 1) {
//            return - 1;
//        }
//
//        if (this.plcp[sa[pos]] < MathUtils.BORDER_FOR_EXCEPTION) {
//            return (int) this.plcp[sa[pos]];
//        }
//
//        int l = 0;
//        int r = this.lcpExceptionArray.length - 1;
//
//        final int p = sa[pos];
//
//        while (l <= r) {
//            int mid = l + (r - l) / 2;
//            if (this.lcpExceptionArray[mid].lcpPosition == p) {
//                return this.lcpExceptionArray[mid].lcpValue;
//            } else if (this.lcpExceptionArray[mid].lcpPosition < p) {
//                l = mid + 1;
//            } else if (this.lcpExceptionArray[mid].lcpPosition > p) {
//                r = mid - 1;
//            } else {
//                throw new RuntimeException("Should not reach here (Binary Search Lcp Exception Array)");
//            }
//        }
//
//        return - 1;
//    }

    public final int getLcp(final int[] sa,
                            final int j) {
        if (j == sa.length - 1) {
            return - 1;
        }

        if (this.plcp[sa[j]] >= MathUtils.BORDER_FOR_EXCEPTION) {
            return this.lcpExcMap.get(sa[j]);
        }

        return this.plcp[sa[j]];
    }

    @Override
    public String toString() {
        return "PLCP:\t" + Arrays.toString(this.plcp)

//                + "\nExcAry:\t" + Arrays.toString(this.lcpExceptionArray)

//                + "\nExcMap:\t" + this.lcpExcMap
                ;
    }

}
