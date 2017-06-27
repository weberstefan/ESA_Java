package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 26.06.2017.
 */
public class MaximalRepeats_Updated {

    public static void main(String[] args) {
        final String s = "ACAAACATAT";
//        final String s = "ABAABABA";
        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        MaximalRepeats_Updated mr = new MaximalRepeats_Updated();

        System.out.println(mr.computeMaximalRepeats(esa, 0));
    }

    public MaximalRepeats_Updated() {

    }

    public List<Repeats> maxRepeatList;

    public List<Repeats> computeMaximalRepeats(final EnhancedSuffixArray esa,
                                               final int minimumLength) {
        this.maxRepeatList = new ArrayList<>();

        int i = esa.length - 1;

        while (i > 0) {

            if (esa.plcp.getLcp(esa.suffices, i) >= minimumLength) {
                int j = i - 1;

                while (j > 0 && esa.plcp.getLcp(esa.suffices, i) >= esa.plcp.getLcp(esa.suffices, j)) {
                    j = j - 1;

                    if (j == 0 || esa.plcp.getLcp(esa.suffices, j) >= esa.plcp.getLcp(esa.suffices, j + 1)) {
                        j = j + 1;
                        break;
                    }
                }

                if (Repeats.isPairwiseDistinctBwt(esa, j, i)) {
                    Repeats.fillList(this.maxRepeatList, esa, j, i, esa.plcp.getLcp(esa.suffices, i));
                }

                i = j;
            }

            i = i - 1;
        }

        return this.maxRepeatList;
    }

}
