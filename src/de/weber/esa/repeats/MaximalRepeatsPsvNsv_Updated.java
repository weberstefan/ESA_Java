package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.psvnsv.PsvNsv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 27.06.2017.
 */
public class MaximalRepeatsPsvNsv_Updated {

    public static void main(String[] args) {
        final String s = "ACAAACATAT";
//        final String s = "ABAABABA";
        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        MaximalRepeatsPsvNsv_Updated mrPsv = new MaximalRepeatsPsvNsv_Updated();

        System.out.println(mrPsv.computeMaximalRepeats(esa, 0));

        MaximalRepeats_Updated mr = new MaximalRepeats_Updated();

        System.out.println(mr.computeMaximalRepeats(esa, 0));

        MaximalRepeats mrOne = new MaximalRepeats();

        System.out.println(mrOne.computeMaximalRepeats(esa, 0));
    }

    private List<Repeats> maxRepeatList;

    public MaximalRepeatsPsvNsv_Updated() {}

    public List<Repeats> computeMaximalRepeats(final EnhancedSuffixArray esa,
                                               final int minimumLength) {
        final PsvNsv psvNsv = new PsvNsv(esa);

        this.maxRepeatList = new ArrayList<>();

        int i = esa.length - 1;

        while (i > 0) {

            final int curPsv = psvNsv.psv[i];

            int j = i - 1;

            while (j >= 0 && curPsv <= psvNsv.psv[j]) {
                j = j - 1;
            }

            if (esa.plcp.getLcp(esa.suffices, i) > minimumLength &&
                    Repeats.isPairwiseDistinctBwt(esa, j, i)) {
                Repeats.fillList(this.maxRepeatList, esa, j, i, esa.plcp.getLcp(esa.suffices, i));
            }

            i = j - 1;
        }

        return this.maxRepeatList;
    }

}
