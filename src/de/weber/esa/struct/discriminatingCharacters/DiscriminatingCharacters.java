package de.weber.esa.struct.discriminatingCharacters;

import de.weber.esa.struct.EnhancedSuffixArray;

/**
 * Created by Stefan on 21.04.2017.
 */
public class DiscriminatingCharacters {

    /**
     * Represents the first discriminating character of Suffix[SA[k - 1]] with Suffix[SA[k]]
     */
    private final char[] first;

    /**
     * Represents the first discriminating character of Suffix[SA[k]] with Suffix[SA[k - 1]]
     */
    private final char[] second;

    public DiscriminatingCharacters(final EnhancedSuffixArray esa) {
        this.first = new char[esa.length - 1];
        this.second = new char[esa.length - 1];

        for (int i = 1; i < esa.lcp.length; i = i + 1) {
            first[i - 1] = esa.sequence[esa.suffices[i - 1] + esa.lcp.lcps[i]];
            second[i - 1] = esa.sequence[esa.suffices[i] + esa.lcp.lcps[i]];
        }
    }

    public char getFirst(final int p) {
        return this.first[p - 1];
    }

    public char getSecond(final int p) {
        return this.second[p - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < first.length; i = i + 1) {
            sb.append(first[i] + "" + second[i]);
            if (i < first.length - 1) {
                sb.append(", ");
            } else {
                sb.append("]");
            }
        }
        return sb.toString();
    }

}
