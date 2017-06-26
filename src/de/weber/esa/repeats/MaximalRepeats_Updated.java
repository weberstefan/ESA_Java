package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 26.06.2017.
 */
public class MaximalRepeats_Updated {

    public static void main(String[] args) {
//        final String s = "ACAAACATAT";
        final String s = "ABAABABA";
        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        MaximalRepeats_Updated mr = new MaximalRepeats_Updated();

        System.out.println(mr.computeMaximalRepeats(esa, 3));
    }

    public MaximalRepeats_Updated(){

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

                    if (esa.plcp.getLcp(esa.suffices, j) >= esa.plcp.getLcp(esa.suffices, j + 1)) {
                        j = j + 1;
                        break;
                    }
                }

                if (this.isPairwiseDistinctBwt(esa, j, i)) {
                    this.fillList(this.maxRepeatList, esa, j, i, esa.plcp.getLcp(esa.suffices, i));
                }

                i = j;
            }

            i = i - 1;
        }

        return this.maxRepeatList;
    }

    private List<Repeats> fillList(List<Repeats> list,
                                   final EnhancedSuffixArray esa,
                                   final int i,
                                   final int j,
                                   final int l) {
        int seqPosI = Math.min(esa.suffices[i], esa.suffices[j]);
        int seqPosJ = Math.max(esa.suffices[i], esa.suffices[j]);

        while (seqPosI <= seqPosJ) {
            int x = seqPosI + 1;

            while (x <= seqPosJ) {
                list.add(new Repeats(seqPosI, x, l));
                x = x + 1;
            }
            seqPosI = seqPosI + 1;
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
