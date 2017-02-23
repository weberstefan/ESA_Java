package test;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.bwt.FMIndex;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Stefan on 20.01.2017.
 *
 * Test BWT with a given sequence and its FM index
 */
public class BWTTest {

    @Test
    public void testBWT() {
        final String s = "ABANANAISANANANAS";

        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        final String query = "NANA";

        // l = 12; r = 14
        final FMIndex fm = esa.bwt.backwardSearch(esa, query);
        final int l = fm.getL();
        final int r = fm.getR();
        Assert.assertEquals(l, 12);
        Assert.assertEquals(r, 14);
    }

}
