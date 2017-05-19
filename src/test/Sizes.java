package test;

import de.weber.esa.searching.paper_search_via_discriminating_characters.FindLongestPrefixMatch;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.discriminatingcharacters.DcPositionAlphabet;
import de.weber.esa.struct.discriminatingcharacters.DcPositionAminoAcidDna;
import de.weber.esa.struct.discriminatingcharacters.DiscriminatingCharacters;
import org.junit.Test;

/**
 * Created by Stefan on 22.01.2017.
 */
public class Sizes {

    @Test
    public void sizes() {
        String s = "ACAAACATAT";
//        for (int i = 0; i < 10; i = i + 1) {
//            s += "ACAAACATAT";
//        }
        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        System.out.println("Length: " + esa.length);

        final DiscriminatingCharacters dc = new DiscriminatingCharacters(esa);
//        System.out.println(dc.toString());

        final DcPositionAminoAcidDna dcPositionAminoAcidDna = new DcPositionAminoAcidDna(esa);
//        System.out.println(dcPositionAminoAcidDna.toString());

        final DcPositionAlphabet dcPositionAlphabet = new DcPositionAlphabet(esa);
//        System.out.println(dcPositionAlphabet.toString());


//        System.out.println(ObjectSizeCalculator.getObjectSize(dc));
//        System.out.println(ObjectSizeCalculator.getObjectSize(dcPositionAminoAcidDna));
//        System.out.println(ObjectSizeCalculator.getObjectSize(dcPositionAlphabet));

        for (int i = 0; i < esa.length - 1; i = i + 1) {
            int lcp = esa.lcp.getCurrentLcpValue(i + 1);
            char s1 = esa.sequence[esa.suffices[i] + lcp];
            char s2 = esa.sequence[esa.suffices[i + 1] + lcp];

//            System.out.println(i + "\t" + s1 + ":" + s2 + " - " + lcp);
        }


        FindLongestPrefixMatch f = new FindLongestPrefixMatch(esa);
//        System.out.println(f.matching(esa, "AC".toCharArray(), false, true, false));
        System.out.println(f.matching(esa, "AC".toCharArray(), false, false, true));

//        System.out.println(ObjectSizeCalculator.getObjectSize(esa.lcp));
    }

}
