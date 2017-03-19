package de.weber.esa.searching.binarySearch;

import de.weber.esa.searching.wrapper.BinarySearchWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;

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
     * @param esa : current enhanced suffix array for sequence of interest
     * @param query : pattern to search for
     * @return if sequence of interest contains pattern
     */
    private BinarySearchWrapper binarySearch(final EnhancedSuffixArray esa,
                                             final String query) {
        // queries first letter is not inside the suffix array string
        if (! esa.bwtCMap.containsKey(query.charAt(0))) {
            System.out.println(query.charAt(0) + " is not inside the suffix array");
            return new BinarySearchWrapper(query, - 1, - 1);
        }

        int mid;
        int left = esa.bwtCMap.get(query.charAt(0)).getPosSequence();
        int right = esa.length - 1;
        int posChar = 0;

        while (left <= right && posChar < query.length()) {
            mid = left + ((right - left) / 2);
            final int p = esa.suffices[mid];

            if (query.charAt(posChar) == esa.sequence[p + posChar]) {
                if (posChar != query.length()) {
                    posChar = posChar + 1;
                    if (posChar == query.length()) {
                        int up = mid + 1;
                        boolean isUp = false;

                        /*
                            as long as LCP[mid++] greater than or equal to number of already matching characters
                            their SA entries also match the query
                         */
                        while (esa.lcp.lcps[up] >= posChar) {
                            up = up + 1;
                            isUp = true;
                        }

                        int down = mid - 1;
                        boolean isDown = false;

                        /*
                            iff LCP[mid] greater than or equal to number of already matching characters
                            LCP[mid--] can match query --> iff LCP[mid--] greater than or equal to 0
                         */
                        if (esa.lcp.lcps[mid] >= posChar) {
                            while (esa.lcp.lcps[down] >= 0) {
                                isDown = true;
                                if (esa.lcp.lcps[down] <= 0) {
                                    break;
                                }
                                down = down - 1;
                            }
                        }

                        return new BinarySearchWrapper(query, isDown ? down : mid, isUp ? (up - 1) : mid);
                    }
                }
            } else {
                if (! esa.bwtCMap.containsKey(query.charAt(posChar))) {
                    // query contains letter, that is not inside the suffix array string
                    System.out.println(query.charAt(posChar) + " is not inside the suffix array");
                    return new BinarySearchWrapper(query, - 1, - 1);
                } else if (query.charAt(posChar) < esa.sequence[p + posChar]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return new BinarySearchWrapper(query, - 1, - 1);
    }


}
