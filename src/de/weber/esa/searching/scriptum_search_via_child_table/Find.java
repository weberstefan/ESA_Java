package de.weber.esa.searching.scriptum_search_via_child_table;

import de.weber.esa.searching.paper_search_via_discriminating_characters.FindLongestPrefixMatch;
import de.weber.esa.searching.wrapper.IntervalWrapper;
import de.weber.esa.searching.wrapper.PatternMatchingWrapper;
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

        final char[] q = "AAAACACA".toCharArray();

        Find f = new Find();
        Find_2 f2 = new Find_2(esa);
        FindLongestPrefixMatch flpm = new FindLongestPrefixMatch(esa);

        System.out.println(f.find(esa, q));
        System.out.println(f2.find(esa, q));
        System.out.println(flpm.matching(esa, q, false, false, true));
    }

    private boolean isPatternSubstring = false;

    public PatternMatchingWrapper find(final EnhancedSuffixArray esa,
                                       final char[] s) {
        IntervalWrapper iw = new IntervalWrapper(esa.bwtCMap.get(s[0]).getPosSequence(), ESA_Utils.getCharEndPosSA(esa, s[0]));
        if (s.length == 1) {
            return new PatternMatchingWrapper(1, iw.i, iw.j);
        }
        final int n = esa.length;
        final int m = s.length;
        int p = 0;
        boolean prefix = true;
        isPatternSubstring = false;

        while ((iw.isNotNullInterval(n)) &&
                p < m &&
                prefix) {
            if (iw.i < iw.j) {
                // child interval
                int k = Math.min(this.LCP(esa, iw.i, iw.j), m);

                prefix = this.isPrefix(esa, s, esa.suffices[iw.i] + p, esa.suffices[iw.i] + k - 1, p + 1, k);

                if (k == m) {
                    break;
                }

                iw = this.getChildIntervalByChar(esa, iw.i, iw.j, s[k], k, prefix);

                if (isPatternSubstring) {
                    p = k;
                    break;
                }

                p = k;
            } else if (iw.i == iw.j) {
                prefix = this.isPrefix(esa, s, esa.suffices[iw.i] + p, esa.suffices[iw.i] + m - 1, p + 1, m);
                if (prefix) {
                    p = m;
                }
                break;
            } else {
                throw new RuntimeException("Should not reach here");
            }
        }

        if (isPatternSubstring) {
            return new PatternMatchingWrapper(p, iw.i, iw.j);
        } else if (iw.i == iw.j && ! prefix) {
            try {
                if (this.countMatches(esa, s, esa.suffices[iw.i] + p, esa.suffices[iw.i] + m - 1, p + 1, m) > 0) {
                    return new PatternMatchingWrapper(p + this.countMatches(esa, s, esa.suffices[iw.i] + p, esa.suffices[iw.i] + m - 1, p + 1, m), iw.i, iw.j);
                }
            }catch (ArrayIndexOutOfBoundsException eAIOOB) {
            }
        }
        return (prefix) ?
                new PatternMatchingWrapper(s.length, iw.i, iw.j) : new PatternMatchingWrapper(0, - 2, - 2);
    }

    private IntervalWrapper getChildIntervalByChar(final EnhancedSuffixArray esa,
                                                   final int i,
                                                   final int j,
                                                   final char c,
                                                   final int turn,
                                                   final boolean prefix) {

        if (esa.sequence[esa.suffices[i] + turn] == esa.sequence[esa.suffices[j] + turn] &&
                esa.sequence[esa.suffices[i] + turn] == c &&
                turn != 0) {
            return new IntervalWrapper(i, j);
        }
        IntervalWrapper iw = this.getNextChildInterval(esa, i, j, i);

        while ((esa.sequence[esa.suffices[iw.i] + turn] != c) &&
                iw.j < j) {
            iw = this.getNextChildInterval(esa, i, j, iw.j + 1);
        }

        if (esa.sequence[esa.suffices[iw.i] + turn] == c) {
            return iw;
        } else if (prefix) {
            isPatternSubstring = true;
            return new IntervalWrapper(i, j);
        }
        return new IntervalWrapper(- 1, - 1);
    }

    private IntervalWrapper getNextChildInterval(final EnhancedSuffixArray esa,
                                                 final int i,
                                                 final int j,
                                                 final int k) {
        if (k == i) {
            if (i == 0) {
                return new IntervalWrapper(0, 0);
            } else {
                return (this.isInterval(esa.child.cld[i], i, j)) ?
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
//        else if (j == esa.length - 1)
//            return 0;
        else
            return (this.isInterval(esa.child.cld[i], i, j)) ?
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

    private int countMatches(final EnhancedSuffixArray esa,
                             final char[] s,
                             int startSeq,
                             final int endSeq,
                             int startPattern,
                             final int endPattern) {
        int matches = 0;

        while (startSeq <= endSeq &&
                startPattern <= endPattern &&
                startPattern <= s.length) {
            if (esa.sequence[startSeq] != s[startPattern - 1]) {
                break;
            }
            startSeq = startSeq + 1;
            startPattern = startPattern + 1;
            matches = matches + 1;
        }

        return matches;
    }
}
