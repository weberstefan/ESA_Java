package test;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.bwt.BWTFeatures;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Stefan on 20.01.2017.
 * <p>
 * Check the Enhanced Suffix Array Properties
 */
public class EsaPropreties {


    @Test
    public void testESAProperties() {
//         test ESArray with sequence "MISSISSIPPI$"
        final String s = "ACAAACATAT";
//        final String s = "MISSISSIPPI";


//        final File file = new File("res/test/english.50MB");
//        final String s = Reader.readFile(file);
        System.out.println(s.length());

        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        // length of esa must be equal to |ACAAACATAT + 1($)|
        Assert.assertEquals(esa.length, s.length() + 1);

        // each suffix's 1st character i must be less than or equal to suffix i + 1
        for (int i = 0; i < esa.length - 1; i = i + 1) {
            assert (esa.sequence[esa.suffices[i]] <= esa.sequence[esa.suffices[i + 1]]);
        }

//        final Tree tree = new Tree(esa.lcp);
//        System.out.println("LCP Tree:\n" + tree.toString());

//        ChildTable ct = new ChildTable(esa);
//        System.out.println(ct.toString());

        // TEST WILL ONLY PASS FOR "ACAAACATAT" !!
        this.esaPropertiesGivenString(esa, s);
    }

    /**
     * @param esa : enhanced suffix array for sequence s
     * @param s   : ACAAACATAT
     */
    private void esaPropertiesGivenString(final EnhancedSuffixArray esa,
                                          final String s) {
        // test C(*) function
        //$=0; A=1; B=9; I=10; N=11; S=16
        final List<Character> cList = new ArrayList<>(Arrays.asList('$', 'A', 'C', 'T'));
        final List<Integer> cListPosSequence = new ArrayList<>(Arrays.asList(0, 1, 7, 9));
        for (int i = 0; i < esa.bwtCMap.size(); i = i + 1) {
            final char p = cList.get(i);
            final BWTFeatures f = esa.bwtCMap.get(p);
            assert (f.getPosMap() == i);
            assert (f.getPosSequence() == cListPosSequence.get(i));
        }

        // test correct suffix array, LCP, BWT properties
        final char[] correctFirstCharacters = {
                '$',
                'A', 'A', 'A', 'A', 'A', 'A',
                'C', 'C',
                'T', 'T' };
        final int[] correctSAEntries = {
                10, 2, 3, 0, 4, 8, 6, 1, 5, 9, 7
        };
        final String[] correctSuffixesSA_I = {
                "$",
                "AAACATAT$",
                "AACATAT$",
                "ACAAACATAT$",
                "ACATAT$",
                "AT$",
                "ATAT$",
                "CAAACATAT$",
                "CATAT$",
                "T$",
                "TAT$"
        };
        final int[] correctLCPs = {
                - 1, 0, 2, 1, 3, 1, 2, 0, 2, 0, 1
        };
        final char[] correctBWT = {
                'T', 'C', 'A', '$', 'A', 'T', 'C', 'A', 'A', 'A', 'A'
        };
        final int[] correctBWTOCC = {
                3, 2, 1, 0, 1, 3, 2, 1, 1, 1, 1
        };
        for (int i = 0; i < esa.length; i = i + 1) {
            // test SA[i]
            assert (esa.suffices[i] == correctSAEntries[i]);
            // test correct first characters
            assert (esa.sequence[esa.suffices[i]] == correctFirstCharacters[i]);

            // test for correct suffix string
            StringBuilder sb = new StringBuilder();
            final int p = esa.suffices[i];
            int k = p;
            while (k != esa.length) {
                sb.append(esa.sequence[k]);
                k = k + 1;
            }

            System.out.println(sb.toString());

            assert (correctSuffixesSA_I[i].equals(sb.toString()));

            // test for correct LCP
            assert (esa.lcp.lcps[i] == correctLCPs[i]);

            // test for correct BWT
            assert (esa.bwt.bwt[i] == correctBWT[i]);
            assert (esa.bwt.OCC[i][correctBWTOCC[i]] == 1);
        }
    }

}
