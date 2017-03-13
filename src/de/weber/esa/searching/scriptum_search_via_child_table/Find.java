package de.weber.esa.searching.scriptum_search_via_child_table;

import de.weber.esa.searching.wrapper.IntervalWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;

/**
 * Created by Stefan on 13.03.2017.
 * <p>
 * Scriptum Prof Heun: 5.5
 */
public class Find {

    public Find(final EnhancedSuffixArray esa,
                final String s) {
        this.find(esa, s);
    }

    private void find(final EnhancedSuffixArray esa,
                      final String s) {
        final int n = esa.length;
        final int m = s.length();
        IntervalWrapper iw = new IntervalWrapper(1, n - 1);
        int p = 0;
        boolean prefix = true;

        while ((iw.isNotNullInterval(n)) &&
                p < m &&
                prefix) {
            if (iw.i < iw.j) {
                // child interval
                int k = Math.min(this.getLCP(esa, iw.i, iw.j), m);
                prefix = this.isPrefix(esa, s, iw.i, p, k);
                p = k;
            } else if (iw.i == iw.j) {
                // singleton
                prefix = this.isPrefix(esa, s, iw.i, p, m);
                p = m;
            }
            iw = this.getChildIntervalByChar(esa, iw.i, iw.j, s.charAt(p + 1));
        }

        System.out.println(prefix + " : " + iw.toString());
    }

    private boolean isPrefix(final EnhancedSuffixArray esa,
                             final String s,
                             final int i,
                             final int p,
                             final int km) {
        final StringBuilder sbSubSeq = new StringBuilder();
        final int startSeq = esa.suffices[i] + p;
        final int endSeq = esa.suffices[i] + km - 1;

        for (int x = startSeq; x <= endSeq; x = x + 1) {
            sbSubSeq.append(esa.sequence[x]);
        }

        final StringBuilder sbPattern = new StringBuilder();
        for (int x = (p + 1); x <= km; x = x + 1) {
            sbPattern.append(s.charAt(x));
        }

        return (sbPattern.toString().equals(sbSubSeq.toString()));
    }

    private IntervalWrapper getChildIntervalByChar(final EnhancedSuffixArray esa,
                                                   final int i,
                                                   final int j,
                                                   final char c) {
        IntervalWrapper iw = this.getNextChildInterval(esa, i, j, i);

        int iStroke = iw.i;
        int jStroke = iw.j;

        while ((esa.sequence[esa.suffices[iStroke]] != c) &&
                (jStroke < j)) {
            iw = this.getNextChildInterval(esa, i, j, jStroke + 1);
            iStroke = iw.i;
            jStroke = iw.j;
        }

        return (esa.sequence[esa.suffices[iStroke]] == c) ? new IntervalWrapper(iStroke, jStroke) : new IntervalWrapper(- 2, - 2);
    }

    private IntervalWrapper getNextChildInterval(final EnhancedSuffixArray esa,
                                                 final int i,
                                                 final int j,
                                                 final int k) {
        if (k == i) {
            try {
                return (this.isInsideInterval(esa.child.UP[j + 1], i, j)) ? new IntervalWrapper(i, esa.child.UP[j + 1] - 1) : new IntervalWrapper(i, esa.child.DOWN[i] - 1);
            } catch (ArrayIndexOutOfBoundsException eAIOOB) {
                return new IntervalWrapper(i, esa.child.DOWN[i] - 1);
            }
        } else {
            return (esa.child.NEXT[k] != 0) ? new IntervalWrapper(k, esa.child.NEXT[k] - 1) : new IntervalWrapper(k, j);
        }
    }

    private int getLCP(final EnhancedSuffixArray esa,
                       final int i,
                       final int j) {
        try {
            return (this.isInsideInterval(esa.child.UP[j + 1], i, j)) ? esa.lcp.lcps[esa.child.UP[j + 1]] : esa.lcp.lcps[esa.child.DOWN[i]];
        } catch (ArrayIndexOutOfBoundsException eAIOOB) {
            return esa.lcp.lcps[esa.child.DOWN[i]];
        }
    }

    /**
     * check whether current value is inside an interval of i and j
     *
     * @param k : current value
     * @param i : left position of interval
     * @param j : right position of interval
     * @return true if k in [i + 1 : j]
     */
    private boolean isInsideInterval(final int k,
                                     final int i,
                                     final int j) {
        return (k > i &&
                k <= j);
    }

}
