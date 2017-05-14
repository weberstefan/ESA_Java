package test;

import de.weber.esa.struct.EnhancedSuffixArray;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.junit.Test;

/**
 * Created by Stefan on 22.01.2017.
 */
public class Sizes {

    @Test
    public void sizes() {
        String s = "ACAAACATAT";
        for (int i = 0; i < 10; i = i + 1) {
            s += "ACAAACATAT";
        }
        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        System.out.println("Length: " + esa.length);

        System.out.println(ObjectSizeCalculator.getObjectSize(esa.lcp));
    }

}
