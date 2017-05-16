package test;

import de.weber.esa.searching.binarysearch.BinarySearch;
import de.weber.esa.searching.fimindex.FMIndexSearch;
import de.weber.esa.searching.paper_search_via_discriminating_characters.FindLongestPrefixMatch;
import de.weber.esa.searching.scriptum_search_via_child_table.Find;
import de.weber.esa.searching.scriptum_search_via_child_table.Find_2;
import de.weber.esa.searching.wrapper.PatternMatchingWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created by Stefan on 02.05.2017.
 */
public class SearchingTests {

    @Test
    public void testSearching() {
        final String s = "ACAAACATAT";

        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);
        System.out.println("ESArray built: " + Calendar.getInstance().getTime());
        System.out.println("length: " + esa.length);

        final BinarySearch binarySearch = new BinarySearch();

        final FMIndexSearch fmIndexSearch = new FMIndexSearch();

        final FindLongestPrefixMatch findLongestPrefixMatch = new FindLongestPrefixMatch(esa);

        final Find findWithBool = new Find();

        final Find_2 findIntArrays = new Find_2(esa);

        testAllPrefix(s, esa, binarySearch, fmIndexSearch, findLongestPrefixMatch, findWithBool, findIntArrays);

    }

    private void testAllPrefix(String s, EnhancedSuffixArray esa,
                               BinarySearch binarySearch,
                               FMIndexSearch fmIndexSearch,
                               FindLongestPrefixMatch findLongestPrefixMatch,
                               Find findWithBool,
                               Find_2 findIntArrays) {
        for (int i = 0; i < s.length(); i = i + 1) {
            for (int c = 1; c <= s.length() - i; c = c + 1) {
                final PatternMatchingWrapper curBinary = binarySearch.search(esa, s.substring(i, i + c));
                final PatternMatchingWrapper curFmIndex = fmIndexSearch.backwardSearch(esa, s.substring(i, i + c));
                final PatternMatchingWrapper curFindLongestPrefixMatchDc = findLongestPrefixMatch.matching(esa, s.substring(i, i + c).toCharArray(), true);
                final PatternMatchingWrapper curFindLongestPrefixMatchNotDc = findLongestPrefixMatch.matching(esa, s.substring(i, i + c).toCharArray(), false);
                final PatternMatchingWrapper curFindWithBool = findWithBool.find(esa, s.substring(i, i + c).toCharArray());
                final PatternMatchingWrapper curFindIntArrays = findIntArrays.find(esa, s.substring(i, i + c).toCharArray());

                /* binary == fmIndex */
                Assert.assertEquals(curBinary, curFmIndex);

                /* binary == find longest prefix match DC  */
                Assert.assertEquals(curBinary, curFindLongestPrefixMatchDc);

                /* binary == find longest prefix match NO DC */
                Assert.assertEquals(curBinary, curFindLongestPrefixMatchNotDc);

                /* binary == find with bool table */
                Assert.assertEquals(curBinary, curFindWithBool);

                /* find with bool table == find with int table */
                Assert.assertEquals(curFindWithBool, curFindIntArrays);
            }
        }
    }

}
