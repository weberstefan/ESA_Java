package de.weber.esa;

import de.weber.esa.io.Reader;
import de.weber.esa.searching.paper_search_via_discriminating_characters.FindLongestPrefixMatch;
import de.weber.esa.searching.scriptum_search_via_child_table.Find;
import de.weber.esa.searching.scriptum_search_via_child_table.Find_2;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.utils.ESA_Utils;

import java.io.File;
import java.util.Calendar;

/**
 * Created by Stefan on 20.01.2017.
 */
public class MainAppl {

    public static void main(String[] args) {
//        final String s = "ACAAACATATACTAGCACTAGACTAGCAGCGACTAGCACAGCACTACGACGAGCATCAGCATCTACTAGCAGCATCGGGCTAGCGAT";
//        final String s = "MISSISSIPPI";
//        final String s = "BANANA";
//        final String s = "AAABAABAB";
//        final String s = "XABCYABCWABCYZ";

//        String s = "A";
//        for (int i = 0; i < 1300; i = i + 1) {
//            s += "A";
//        }


//        System.out.println("Start: " + Calendar.getInstance().getTime());

        final File file = new File("res/test/english.50MB");
//        final File file = new File("res/test/long_string.txt");
        final String s = Reader.readFile(file);
        System.out.println("Sequence read in: " + Calendar.getInstance().getTime());

        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);
        System.out.println("ESArray built: " + Calendar.getInstance().getTime());
        System.out.println(esa.length);

//        System.out.println(esa.toString());
//
//        System.out.println("LCP int array: " + ObjectSizeCalculator.getObjectSize(esa.lcp.lcps));

        final String q = "INCONSIDERINGTHERISEOFTHEBOLSHEVIKIITISNECESSARYTOUNDERSTANDTHATRUSSIA" +
                "NECONOMICLIFEANDTHERUSSIANARMYWERENOTDISORGANISEDONNOVEMBERTHBUTMANYMONTHSBEFOREASTHELOG" +
                "ICALRESULTOFAPROCESSWHICHBEGANASFARBACKASTHECORRUPTREACTIONARIESINCONTROLOFTHETSARSCOURT" +
                "DELIBERATELYUNDERTOOKTOWRECKRUSSIAINORDERTOMAKEASEPARATEPEACEWITHGERMANYTHELACKOFARMSONT" +
                "HEFRONTWHICHHADCAUSEDTHEGREATRETREATOFTHESUMMEROFTHELACKOFFOODINTHEARMYANDINTHEGREATCITI" +
                "ESTHEBREAKDOWNOFMANUFACTURESANDTRANSPORTATIONINALLTHESEWEKNOWNOWWEREPARTOFAGIGANTICCAMPA" +
                "IGNOFSABOTAGETHISWASHALTEDJUSTINTIMEBYTHEMARCHREVOLUTION";
//        final String q = "NORDERTOMAKEASEPARATEPEACEWIT";

        searchProperties(esa, q);


//        DiscriminatingCharacters dc = new DiscriminatingCharacters(esa);
//        DcPositionAlphabet dcPositionAlphabet = new DcPositionAlphabet(esa);
//        System.out.println(ObjectSizeCalculator.getObjectSize(dc) + " Dc []");
//        System.out.println(ObjectSizeCalculator.getObjectSize(dcPositionAlphabet) + " DcPositionAlhabet");

//        System.out.println(dcPosition.toString().equals(dc.toString()));

//        System.out.println(esa.toString());
//        DiscriminatingCharacters dc = new DiscriminatingCharacters(esa);
//        System.out.println(dc.toString());
//        System.out.println(ObjectSizeCalculator.getObjectSize(esa) + "\tESA SIZE");
//        System.out.println(ObjectSizeCalculator.getObjectSize(esa.lcp) + "\tLcp SIZE");
//        System.out.println(ObjectSizeCalculator.getObjectSize(esa.child) + "\tCT SIZE");
    }

    private static void searchProperties(EnhancedSuffixArray esa, String q) {
        System.out.println("Start init searching : " + Calendar.getInstance().getTime());
        FindLongestPrefixMatch flpm = new FindLongestPrefixMatch(esa);

        Find find = new Find();
        Find_2 find2 = new Find_2(esa);

        System.out.println("Start searching : " + Calendar.getInstance().getTime());


        long start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, q.toCharArray(), false, false, false) + " flpm NOT DC");
        long end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());


        start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, q.toCharArray(), true, false, false) + " flpm DC Array");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());

        start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, q.toCharArray(), false, true, false) + " flpm DC Nibbles");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());

        start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, q.toCharArray(), false, false, true) + " flpm DC On The Fly");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());

        start = System.currentTimeMillis();
        System.out.println(find.find(esa, q.toCharArray()) + " find");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FIND done: " + Calendar.getInstance().getTime());

        start = System.currentTimeMillis();
        System.out.println(find2.find(esa, q.toCharArray()) + " find 2");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FIND done: " + Calendar.getInstance().getTime());

        final String qq = ESA_Utils.getCurrentSuffix(esa, 17391043, esa.length);
        System.out.println("Start searching 2 : " + Calendar.getInstance().getTime());

        start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, qq.toCharArray(), false, false, false) + " flpm NOT DC");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());


        start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, qq.toCharArray(), true, false, false) + " flpm DC Array");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());

        start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, qq.toCharArray(), false, true, false) + " flpm DC Nibbles");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());

        start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, qq.toCharArray(), false, false, true) + " flpm DC On The Fly");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());

        start = System.currentTimeMillis();
        System.out.println(find.find(esa, qq.toCharArray()) + " find");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FIND done: " + Calendar.getInstance().getTime());

        start = System.currentTimeMillis();
        System.out.println(find2.find(esa, qq.toCharArray()) + " find 2");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FIND done: " + Calendar.getInstance().getTime());

//        System.out.println(ObjectSizeCalculator.getObjectSize(flpm.dc) + " DcPositions");
    }

}
