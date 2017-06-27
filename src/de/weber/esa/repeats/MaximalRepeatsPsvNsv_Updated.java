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
                    this.isPairwiseDistinctBwt(esa, j, i)) {
                this.fillList(this.maxRepeatList, esa, j, i, esa.plcp.getLcp(esa.suffices, i));
            }

            i = j - 1;
        }

        return this.maxRepeatList;
    }

    private List<Repeats> fillList(List<Repeats> list,
                                   final EnhancedSuffixArray esa,
                                   int i,
                                   final int j,
                                   final int l) {
        if (i + 1 == j) {
            list.add(new Repeats(Math.min(esa.suffices[i], esa.suffices[j]), Math.max(esa.suffices[i], esa.suffices[j]), l));
            return list;
        }

        while (i <= j) {
            int x = i + 1;

            while (x <= j) {
                list.add(new Repeats(Math.min(esa.suffices[i], esa.suffices[x]), Math.max(esa.suffices[i], esa.suffices[x]), l));

                x = x + 1;
            }
            i = i + 1;
        }

        return list;
    }

    private final boolean isPairwiseDistinctBwt(final EnhancedSuffixArray esa,
                                                int i,
                                                final int j) {
        if (i == j)
            return false;

        while (i <= j) {
            int x = i + 1;

            while (x <= j) {
                if (esa.bwt.bwt[i] == esa.bwt.bwt[x]) {
                    return false;
                }
                x = x + 1;
            }
            i = i + 1;
        }
        return true;
    }

}
