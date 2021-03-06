package de.weber.esa.searching.binarysearch;

import de.weber.esa.searching.wrapper.PatternMatchingWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.utils.ESA_Utils;

/**
 * Created by Stefan on 13.03.2017.
 * <p>
 * Binary search for a pattern; retrieves suffix arrays start and end position of found pattern
 */
public class BinarySearch {

    /**
     * empty constructor for call
     */
    public BinarySearch() {
    }

    /**
     * binary search for pattern query
     *
     * @param esa   : current enhanced suffix array for sequence of interest
     * @param query : pattern to search for
     * @return (query, SA[i..j]) iff sequence of interest contains pattern
     */
    public PatternMatchingWrapper search(final EnhancedSuffixArray esa,
                                         final String query) {
        // queries first letter is not inside the suffix array string
        if (! esa.bwtCMap.containsKey(query.charAt(0))) {
            System.out.println(query.charAt(0) + " is not inside the suffix array");
            return new PatternMatchingWrapper(0, - 1, - 1);
        }
        final int m = query.length();
        final int n = esa.length;
        int l = esa.bwtCMap.get(query.charAt(0)).getPosSequence(); // O(1)
        // get r in O(|Alphabet(query)|)
        int r = ESA_Utils.getCharEndPosSA(esa, query.charAt(0));

        while (l <= r) {
            int mid = l + (r - l) / 2;

            final int p = esa.suffices[mid];
            final String currentSuffixSubstring = ESA_Utils.getCurrentSuffix(esa, p, m);

            int res = query.compareTo(currentSuffixSubstring);

            if (res == 0) {

                int up = mid + 1;
                boolean isUp = false;
                /*
                    as long as LCP[mid++] is greater than or equal to length of query
                    their SA entries also match the query
                */
                while (esa.lcp.getCurrentLcpValue(up) >= m) {
                    up = up + 1;
                    isUp = true;
                }

                int down = mid - 1;
                boolean isDown = false;
                int downP = esa.suffices[down];
                String suffixDown = ESA_Utils.getCurrentSuffix(esa, downP, m);
                /*
                    iff LCP[mid] greater than or equal to length of query
                    LCP[mid--] can match query --> iff LCP[mid--] greater than or equal to 0
                */
                while (query.compareTo(suffixDown) == 0) {
                    down = down - 1;
                    isDown = true;
                    downP = esa.suffices[down];
                    suffixDown = ESA_Utils.getCurrentSuffix(esa, downP, m);
                }

                return new PatternMatchingWrapper(query.length(), isDown ? (down + 1) : mid, isUp ? (up - 1) : mid);
            } else if (res < 0) {
                r = mid - 1;
            } else if (res > 0) {
                l = mid + 1;
            } else {
                throw new RuntimeException("Should never reach here");
            }
        }
        return new PatternMatchingWrapper(0, - 1, - 1);
    }


}
