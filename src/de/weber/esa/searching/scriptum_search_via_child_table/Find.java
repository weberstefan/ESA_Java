package de.weber.esa.searching.scriptum_search_via_child_table;

import de.weber.esa.searching.paper_search_via_discriminating_characters.FindLongestPrefixMatch;
import de.weber.esa.searching.wrapper.IntervalWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.utils.ESA_Utils;

/**
 * Created by Stefan on 13.03.2017.
 * <p>
 * Find pattern p using child table with int[] child and bool[] next, down (up not needed)
 */
public class Find {

    public static void main(String[] args) {
        final String s = "ACAAACATAT";
        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);


        final String testQuery = "ACA";

        Find f = new Find();
        Find_2 f2 = new Find_2(esa);
        FindLongestPrefixMatch flpm = new FindLongestPrefixMatch(esa);

//        System.out.println(f.find(esa, testQuery.toCharArray()));

        for (int i = 0; i < s.length(); i = i + 1) {
            for (int c = 1; c <= s.length() - i; c = c + 1) {
                System.out.println("F : " + (s.substring(i, i + c)) + " : " + f.find(esa, s.substring(i, i + c).toCharArray()));
                System.out.println("F2: " + (s.substring(i, i + c)) + " : " + f2.find(esa, s.substring(i, i + c).toCharArray()));
                System.out.println("DC: " + (s.substring(i, i + c)) + " : " + flpm.matching(esa, s.substring(i, i + c).toCharArray(), true));
                System.out.println("NO: " + (s.substring(i, i + c)) + " : " + flpm.matching(esa, s.substring(i, i + c).toCharArray(), false));
            }
        }

    }

    public Find() {
    }

    public IntervalWrapper find(final EnhancedSuffixArray esa,
                                    final char[] s) {
        final int n = esa.length - 1;
        final int m = s.length;
//        IntervalWrapper iw = new IntervalWrapper(0, n);
        IntervalWrapper iw = new IntervalWrapper(esa.bwtCMap.get(s[0]).getPosSequence(), ESA_Utils.getCharEndPosSA(esa, s[0]));
        int p = 0;
        boolean prefix = true;
        int turn = 0;

        while ((iw.isNotNullInterval(n)) && p < m && prefix) {
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

                turn = curPosToCompare + 1;
            } else if (iw.i == iw.j) {
                prefix = this.isPrefix(esa, s, esa.suffices[iw.i] + p, esa.suffices[iw.i] + m - 1, p + 1, m);
                p = m;
            } else {
                throw new RuntimeException("Should not reach here");
            }
        }
        return (prefix) ?
                iw : new IntervalWrapper(- 1, - 1);
    }

    private IntervalWrapper getChildIntervalByChar(final EnhancedSuffixArray esa,
                                                   final int i,
                                                   final int j,
                                                   final char c,
                                                   final int turn) {

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
                return (esa.child.down[i] && this.isInterval(esa.child.cld[i], i, j)) ?
                        new IntervalWrapper(i, esa.child.cld[i] - 1) :
                        new IntervalWrapper(i, esa.child.cld[j] - 1);
            }
        } else {
            return (esa.child.next[k]) ?
                    new IntervalWrapper(k, esa.child.cld[k] - 1) : new IntervalWrapper(k, j);
        }
    }

    private int LCP(final EnhancedSuffixArray esa,
                    final int i,
                    final int j) {
        if (i == 0)
            return 0;
        else if (j == esa.length - 1)
            return 0;
        else
            return (esa.child.down[i] && this.isInterval(esa.child.cld[i], i, j)) ?
                    esa.lcp.getCurrentLcpValue(esa.child.cld[i]) : esa.lcp.getCurrentLcpValue(esa.child.cld[j]);
    }

    private boolean isInterval(final int k,
                               final int i,
                               final int j) {
        return (k > i && k <= j);
    }

    private boolean isPrefix(final EnhancedSuffixArray esa,
                             final char[] s,
                             int startSeq,
                             final int endSeq,
                             int startPattern,
                             final int endPattern) {
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
}
