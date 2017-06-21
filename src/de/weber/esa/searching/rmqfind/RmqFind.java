package de.weber.esa.searching.rmqfind;

import de.weber.esa.searching.wrapper.IntervalWrapper;
import de.weber.esa.searching.wrapper.PatternMatchingWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.rmq.RMQ;
import de.weber.esa.utils.ESA_Utils;

/**
 * Created by Stefan on 21.05.2017.
 */
public class RmqFind {

    public static void main(String[] args) {
        final String s = "ACAAACATAT";
        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        RmqFind rmqFind = new RmqFind(esa);

        System.out.println(rmqFind.rmq.toString());

        final char[] p = "ACAA".toCharArray();
        System.out.println(rmqFind.find(esa, p));
    }

    public RMQ rmq;

    public RmqFind(final EnhancedSuffixArray esa) {
        this.rmq = new RMQ(esa.lcp.lcps);
    }

    public PatternMatchingWrapper find(final EnhancedSuffixArray esa,
                                       final char[] s) {
        final int n = esa.length - 1;
        final int m = s.length;
        IntervalWrapper iw = new IntervalWrapper(esa.bwtCMap.get(s[0]).getPosSequence(), ESA_Utils.getCharEndPosSA(esa, s[0]));
        int p = 0;
        boolean prefix = true;

        int k = - 1;

        while ((iw.isNotNullInterval(n)) &&
                p < m &&
                prefix) {
            if (iw.i < iw.j) {
                k = Math.min(esa.lcp.getCurrentLcpValue(this.rmq.query(iw.i + 1, iw.j)), m);
            } else if (iw.i == iw.j) {
                k = m;
            } else {
                throw new RuntimeException("Should not reach here. Interval[i:j] MUST NOT be: " + iw);
            }

            prefix = this.isPrefix(esa, s, esa.suffices[iw.i] + p, esa.suffices[iw.i] + k - 1, p + 1, k);
            p = k;

            if (p < m) {
                iw = this.getChildIntervalByChar(esa, iw.i, iw.j, s[p]);
            }
        }

        return (prefix) ? new PatternMatchingWrapper(m, iw.i, iw.j) : new PatternMatchingWrapper(0, iw.i, iw.j);
    }

    private IntervalWrapper getChildIntervalByChar(final EnhancedSuffixArray esa,
                                                   final int i,
                                                   final int j,
                                                   final char c) {
        final int l = esa.lcp.getCurrentLcpValue(this.rmq.query(i + 1, j));

        IntervalWrapper iw = this.getNextChildInterval(esa, i, j, i);

        while ((esa.sequence[esa.suffices[iw.i] + l] != c) &&
                iw.j < j) {
            iw = this.getNextChildInterval(esa, i, j, iw.j + 1);
        }

        return (esa.sequence[esa.suffices[iw.i] + l] == c) ? iw : new IntervalWrapper(- 2, - 2);
    }

    private IntervalWrapper getNextChildInterval(final EnhancedSuffixArray esa,
                                                 final int i,
                                                 final int j,
                                                 final int k) {
        final int l = esa.lcp.getCurrentLcpValue(this.rmq.query(i + 1, j));

        int jStroke = 0;

        if (k < j) {
            jStroke = this.rmq.query(k + 1, j);
        }

        return (k == j || esa.lcp.getCurrentLcpValue(jStroke) > l) ? new IntervalWrapper(k, j) : new IntervalWrapper(k, jStroke - 1);
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
