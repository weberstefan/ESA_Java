package de.weber.esa.repeats;

import de.weber.esa.struct.EnhancedSuffixArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Stefan on 09.06.2017.
 */
public class SupermaximalRepeats_Updated {


    private List<Maxima> list = new ArrayList<>();

    public SupermaximalRepeats_Updated(final EnhancedSuffixArray esa) {
        int i = 1; // 0 -> - 1
        final int n = esa.lcp.length;
        int j = i + 1;
        boolean isNext = false;
        while (i < n) {
            j = i + 1;
            isNext = false;
            while (i < n && j < n &&
                    esa.lcp.getCurrentLcpValue(i) < esa.lcp.getCurrentLcpValue(j)) {
                isNext = true;

                if (esa.lcp.getCurrentLcpValue(j) > esa.lcp.getCurrentLcpValue(j + 1)) {
                    break;
                }

                if (esa.lcp.getCurrentLcpValue(j) < esa.lcp.getCurrentLcpValue(j + 1)) {
                    i = j;
                }

                j = j + 1;
            }

            if (esa.lcp.getCurrentLcpValue(j) > 0) {
                if (this.isPairwiseDistinctBwt(esa, i, j)) {
//                    this.list.add(new Maxima(esa.suffices[i], esa.suffices[j], esa.lcp.getCurrentLcpValue(j)));
                    this.fillList(esa, i, j, esa.lcp.getCurrentLcpValue(j));
                }
            }

            i = (isNext) ? j + 1 : i + 1;
        }
        System.out.println(this.list);
    }

    private List<Maxima> fillList(final EnhancedSuffixArray esa,
                                  int i,
                                  final int j,
                                  final int l) {

        while (i <= j) {
            int x = i + 1;

            while (x <= j) {
                final int minPos = Math.min(esa.suffices[x], esa.suffices[i]);
                final int maxPos = Math.max(esa.suffices[x], esa.suffices[i]);
                this.list.add(new Maxima(minPos, maxPos, l));
                x = x + 1;
            }
            i = i + 1;
        }
        return this.list;
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


    public static final class Maxima {
        final int i;
        final int j;
        final int l;

        public Maxima(final int i,
                      final int j,
                      final int l) {
            this.l = l;
            this.j = j;
            this.i = i;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (! (o instanceof Maxima)) {
                return false;
            }
            final Maxima m = (Maxima) o;
            return (this.i == m.i &&
                    this.j == m.j &&
                    this.l == m.l);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.i, this.j, this.l);
        }

        @Override
        public String toString() {
            return "(" + this.l + "," + this.i + "," + this.j + ")";
        }
    }


}
