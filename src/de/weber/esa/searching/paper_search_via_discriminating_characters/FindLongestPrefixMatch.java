package de.weber.esa.searching.paper_search_via_discriminating_characters;

import de.weber.esa.searching.wrapper.IntervalWrapper;
import de.weber.esa.searching.wrapper.PatternMatchingWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.discriminatingcharacters.DiscriminatingCharacters;
import de.weber.esa.utils.ESA_Utils;

/**
 * Created by Stefan on 01.04.2017.
 */
public class FindLongestPrefixMatch {

    public FindLongestPrefixMatch(final EnhancedSuffixArray esa) {
        this.dc = new DiscriminatingCharacters(esa);
    }

    private DiscriminatingCharacters dc;

    public PatternMatchingWrapper matching(final EnhancedSuffixArray esa,
                                           final char[] P,
                                           final boolean isDC) {
        int c = 0;
        final int n = esa.length - 1;
        final int m = P.length;
//        IntervalWrapper iw = new IntervalWrapper(1, n);
        IntervalWrapper iw = new IntervalWrapper(esa.bwtCMap.get(P[0]).getPosSequence(), ESA_Utils.getCharEndPosSA(esa, P[0]));


        while (c < m) {
            if (iw.i == iw.j) {
                c = c + this.countMatches(esa, P, esa.suffices[iw.i] + c, esa.suffices[iw.i] + m - 1, c, m - 1);
                return new PatternMatchingWrapper(c, iw.i, iw.j);
            } else {
                int k1 = 0;
                if (iw.j <= n) {
                    if (! esa.child.down[iw.j]) {
                        k1 = esa.child.cld[iw.j];
                    } else {
                        k1 = esa.child.cld[iw.j + 1];
                    }
                }

                if (k1 <= iw.i || k1 > iw.j) {
                    k1 = esa.child.cld[iw.i];
                }

                final int lcpIJ = esa.lcp.getCurrentLcpValue(k1);

                if (lcpIJ > c) {
                    final int M = Math.min(lcpIJ, m);
                    c = c + this.countMatches(esa, P, esa.suffices[iw.i] + c, esa.suffices[iw.i] + M - 1, c, M - 1);
                    if (c < M || c == m) {
                        return new PatternMatchingWrapper(c, iw.i, iw.j);
                    }
                }

                IntervalWrapper iw2 = (isDC) ? this.getIntervalDC(esa, iw, lcpIJ, k1, P[c]) : this.getInterval(esa, iw, lcpIJ, k1, P[c]);
                if (iw2 == null) {
                    return new PatternMatchingWrapper(c, iw.i, iw.j);
                }
                c = c + 1;
                iw = iw2;
            }
        }
        return new PatternMatchingWrapper(c, iw.i, iw.j);
    }

    /**
     * @param esa
     * @param iw
     * @param lcpIJ
     * @param k1
     * @param p
     * @return next child interval using Discriminating Characters
     */
    private IntervalWrapper getIntervalDC(final EnhancedSuffixArray esa,
                                          final IntervalWrapper iw,
                                          final int lcpIJ,
                                          int k1,
                                          final char p) {
        char s1 = this.dc.getFirst(k1);
        char s2 = this.dc.getSecond(k1);
        if (p < s1) {
            return null;
        } else if (p == s1) {
            return new IntervalWrapper(iw.i, k1 - 1);
        } else if (p < s2) {
            return null;
        }

        while (k1 <= iw.j && esa.lcp.getCurrentLcpValue(k1) == lcpIJ) {
            int k2 = (esa.child.next[k1]) ? esa.child.cld[k1] : (iw.j < esa.length) ? iw.j + 1 : iw.j;
            if (p == s2) {
                return new IntervalWrapper(k1, k2 - 1);
            } else {
                s1 = this.dc.getFirst(k2);
                s2 = this.dc.getSecond(k2);

                if (p < s2) {
                    return null;
                }
                k1 = k2;
            }
        }

        return (p == s2) ? new IntervalWrapper(k1, iw.j) : null;

    }

    /**
     * @param esa
     * @param iw
     * @param lcpIJ
     * @param k1
     * @param p
     * @return next child interval
     */
    private IntervalWrapper getInterval(final EnhancedSuffixArray esa,
                                        final IntervalWrapper iw,
                                        final int lcpIJ,
                                        int k1,
                                        final char p) {
        char s = esa.sequence[esa.suffices[iw.i] + lcpIJ];
        if (p < s) {
            return null;
        } else if (p == s) {
            return new IntervalWrapper(iw.i, k1 - 1);
        }

        while (k1 < iw.j && esa.lcp.getCurrentLcpValue(k1) == lcpIJ) {
            int k2 = (esa.child.next[k1]) ? esa.child.cld[k1] : (iw.j < esa.length) ? iw.j + 1 : iw.j;
            s = esa.sequence[esa.suffices[k1] + lcpIJ];
            if (p < s) {
                return null;
            } else if (p == s) {
                return new IntervalWrapper(k1, k2 - 1);
            } else {
                k1 = k2;
            }
        }

        s = esa.sequence[esa.suffices[k1] + lcpIJ];
        return (p == s) ? new IntervalWrapper(k1, iw.j) : null;
    }

    /**
     * @param esa
     * @param p
     * @param startSeq
     * @param endSeq
     * @param startPattern
     * @param endPattern
     * @return number of matching positions in sequence at specified position with pattern
     */
    private int countMatches(final EnhancedSuffixArray esa,
                             final char[] p,
                             int startSeq,
                             final int endSeq,
                             int startPattern,
                             final int endPattern) {
        int matches = 0;

        while (startSeq <= endSeq &&
                startPattern <= endPattern &&
                startPattern <= p.length) {
            if (esa.sequence[startSeq] == p[startPattern]) {
                matches = matches + 1;
            }
            startSeq = startSeq + 1;
            startPattern = startPattern + 1;
        }

        return matches;
    }

}
