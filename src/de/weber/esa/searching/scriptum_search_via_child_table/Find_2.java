package de.weber.esa.searching.scriptum_search_via_child_table;

import de.weber.esa.searching.wrapper.IntervalWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.child_table.ChildTable2;

/**
 * Created by Stefan on 20.03.2017.
 * <p>
 * Find pattern p in enhanced suffix array using child table UP, NEXT, DOWN and CHILD
 */
public class Find_2 {

    public Find_2(final EnhancedSuffixArray esa) {

        this.c = new ChildTable2(esa.lcp);
    }

    private ChildTable2 c;

    public IntervalWrapper find(final EnhancedSuffixArray esa,
                                    final char[] s) {
        final int n = esa.length - 1;
        final int m = s.length;
        IntervalWrapper iw = new IntervalWrapper(0, n);
        int p = 0;
        boolean prefix = true;
        int turn = 0;

        while ((iw.isNotNullInterval(n)) &&
                p < m &&
                prefix) {
            if (iw.i < iw.j) {
                // child interval
                int k = Math.min(this.LCP(esa, iw.i, iw.j), m);

                prefix = this.isPrefix(esa, s, esa.suffices[iw.i] + p, esa.suffices[iw.i] + k - 1, p + 1, k);

                final int curPosToCompare = Math.max(turn, k);

                if (turn == m || curPosToCompare == m) {
                    break;
                }

                iw = this.getChildIntervalByChar(esa, iw.i, iw.j, s[curPosToCompare], curPosToCompare);

                p = k;

                turn = turn + 1;
            } else if (iw.i == iw.j) {
                prefix = this.isPrefix(esa, s, esa.suffices[iw.i] + p, esa.suffices[iw.i] + m - 1, p + 1, m);
                p = m;
            } else {
                throw new RuntimeException("Should not reach here");
            }
        }
        return (prefix) ?
                new IntervalWrapper(iw.i, iw.j) : new IntervalWrapper(- 1, - 1);
    }

    private boolean isPrefix(EnhancedSuffixArray esa, char[] s, int startSeq, int endSeq, int startPattern, int endPattern) {
        if (startSeq > endSeq || startPattern > endPattern) {
            return true; // empty strings
        }

        while (startSeq <= endSeq &&
                startPattern <= endPattern &&
                startPattern <= s.length) {
            if (esa.sequence[startSeq] != s[startPattern - 1]) {
                return false;
            }
            startSeq = startSeq + 1;
            startPattern = startPattern + 1;
        }
        return true;
    }

    private IntervalWrapper getChildIntervalByChar(EnhancedSuffixArray esa, int i, int j, char c, int turn) {
        if (esa.sequence[esa.suffices[i] + turn] == esa.sequence[esa.suffices[j] + turn] &&
                esa.sequence[esa.suffices[i] + turn] == c) {
            return new IntervalWrapper(i, j);
        }
        IntervalWrapper iw = this.getNextChildInterval(esa, i, j, i);

        while ((esa.sequence[esa.suffices[iw.i] + turn] != c) &&
                iw.j < j) {
            iw = this.getNextChildInterval(esa, i, j, iw.j + 1);
        }

        return (esa.sequence[esa.suffices[iw.i] + turn] == c) ?
                iw : new IntervalWrapper(- 1, - 1);
    }

    private IntervalWrapper getNextChildInterval(final EnhancedSuffixArray esa,
                                                 final int i,
                                                 final int j,
                                                 final int k) {
        if (k == i) {
            if (i == 0) {
                return new IntervalWrapper(0, 0);
            } else {
                return (this.isInInterval(this.c.DOWN[i], i, j)) ?
                        new IntervalWrapper(i, this.c.DOWN[i] - 1) :
                        new IntervalWrapper(i, this.c.UP[j + 1] - 1);
            }
        } else {
            return (this.c.NEXT[k] != 0) ?
                    new IntervalWrapper(k, this.c.NEXT[k] - 1) : new IntervalWrapper(k, j);
        }
    }

    private int LCP(EnhancedSuffixArray esa, int i, int j) {
        return (i == 0 || j == esa.length - 1) ?
                0 : (this.isInInterval(this.c.DOWN[i], i, j)) ?
                esa.lcp.getCurrentLcpValue(this.c.DOWN[i]) : esa.lcp.getCurrentLcpValue(this.c.UP[j + 1]);
    }

    private boolean isInInterval(final int k,
                                 final int i,
                                 final int j) {
        return (k > i &&
                k <= j);
    }

}
