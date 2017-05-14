package test;

import de.weber.esa.searching.fimindex.FMIndexSearch;
import de.weber.esa.searching.wrapper.PatternMatchingWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Stefan on 20.01.2017.
 *
 * Check BWT with a given sequence and its FM index
 */
public class BwtProperties {

    @Test
    public void testBWT() {
        final String s = "ABANANAISANANANAS";

        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        final String query = "NANA";

        // l = 12; r = 14
        final FMIndexSearch fmIndexSearch = new FMIndexSearch();
        final PatternMatchingWrapper fm = fmIndexSearch.backwardSearch(esa, query);
        final int l = fm.i;
        final int r = fm.j;
        Assert.assertEquals(l, 12);
        Assert.assertEquals(r, 14);
    }

}
