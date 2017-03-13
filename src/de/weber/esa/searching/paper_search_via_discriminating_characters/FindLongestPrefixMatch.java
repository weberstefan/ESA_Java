package de.weber.esa.searching.paper_search_via_discriminating_characters;

import de.weber.esa.searching.wrapper.IntervalWrapper;
import de.weber.esa.searching.wrapper.PatternMatchingWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.discriminatingCharacters.DiscriminatingCharacters;

/**
 * Created by Stefan on 13.03.2017.
 * <p>
 * Paper: Bitpacking Techniques for indexing genomes II: ESA -> Algorithms 1, 3
 */
public class FindLongestPrefixMatch {

    public FindLongestPrefixMatch(final EnhancedSuffixArray esa,
                                  final String s) {
        System.out.println(this.find(esa, s));
    }

    private PatternMatchingWrapper find(final EnhancedSuffixArray esa,
                                        final String s) {
        final int n = esa.length;
        final int m = s.length();
        int c = 0;
        int i = 1;
        int j = n;

        while (c < m) {
            if (i == j) {
                // singleton lcp interval
                c = c + this.countMatches(esa, s, i, c, m);
                return new PatternMatchingWrapper(c, i, j);
            } else {
                // child interval
                int k1 = 1;
                try {
                    k1 = esa.child.UP[j + 1];
                } catch (ArrayIndexOutOfBoundsException eAIOOB) {
                }

                if (k1 <= i || k1 > j) {
                    k1 = esa.child.DOWN[i];
                }

                final int lcpIJ = esa.lcp.lcps[k1];

                if (lcpIJ > c) {
                    final int M = Math.min(lcpIJ, m);
                    c = c + this.countMatches(esa, s, i, c, M);

                    if (c < M || c == m) {
                        return new PatternMatchingWrapper(c, i, j);
                    }
                }

                final IntervalWrapper iw = this.getInterval(esa, i, j, lcpIJ, k1, s.charAt(c));
                if (iw == null) {
                    return new PatternMatchingWrapper(c, i, j);
                }
                c = c + 1;
                i = iw.i;
                j = iw.j;
            }
        }
        return new PatternMatchingWrapper(c, i, j);
    }

    private int countMatches(final EnhancedSuffixArray esa,
                             final String s,
                             final int i,
                             final int c,
                             final int m) {
        final int startSeq = esa.suffices[i] + c;
        final int endSeq = esa.suffices[i] + m - 1;

        final StringBuilder sbSeq = new StringBuilder();

        for (int x = startSeq; x <= endSeq; x = x + 1) {
            sbSeq.append(esa.sequence[x]);
        }

        final StringBuilder sbQuery = new StringBuilder();

        for (int x = c; x <= (m - 1); x = x + 1) {
            sbQuery.append(s.charAt(x));
        }

        int matches = 0;

        for (int x = 0; x < Math.max(sbQuery.length(), sbSeq.length()); x = x + 1) {
            matches = (sbSeq.charAt(x) == sbQuery.charAt(x)) ? matches + 1 : matches;
        }

        return matches;
    }

    private IntervalWrapper getInterval(final EnhancedSuffixArray esa,
                                        final int i,
                                        final int j,
                                        final int lcpIJ,
                                        final int k1,
                                        final char p) {
        DiscriminatingCharacters dc = esa.lcp.getDiscriminatingCharactersAtPosition(k1);

        char s1 = dc.first;
        char s2 = dc.second;

        if (p < s1) {
            return null;
        } else if (p == s1) {
            return new IntervalWrapper(i, (k1 - 1));
        } else if (p < s2) {
            return null;
        }

        int k = k1;
        while (k < j && esa.lcp.lcps[k] == lcpIJ) {
            int k2 = esa.child.NEXT[k];
            if (p == s2) {
                return new IntervalWrapper(k, (k2 - 1));
            } else {
                dc = esa.lcp.getDiscriminatingCharactersAtPosition(k2);
                // s1 not necessary anymore
                s2 = dc.second;

                if (p < s2) {
                    return null;
                }
                k = k2;
            }
        }

        return (p == s2) ? new IntervalWrapper(k, j) : null;
    }

}
