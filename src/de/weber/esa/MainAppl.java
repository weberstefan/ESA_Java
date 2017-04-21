package de.weber.esa;

import de.weber.esa.io.Reader;
import de.weber.esa.searching.paper_search_via_discriminating_characters.FindLongestPrefixMatch;
import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.utils.ESA_Utils;

import java.io.File;
import java.util.Calendar;

/**
 * Created by Stefan on 20.01.2017.
 */
public class MainAppl {

    public static void main(String[] args) {
//        final String s = "ACAAACATAT";
//        final String s = "MISSISSIPPI";
//        final String s = "BANANA";
//        final String s = "AAABAABAB";
//        final String s = "XABCYABCWABCYZ";

        System.out.println("Start: " + Calendar.getInstance().getTime());

        final File file = new File("res/test/english.50MB");
//        final File file = new File("res/test/long_string.txt");
        final String s = Reader.readFile(file);
        System.out.println("Sequence read in: " + Calendar.getInstance().getTime());

        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);
        System.out.println("ESArray built: " + Calendar.getInstance().getTime());
        System.out.println(esa.length);

//        System.out.println(esa.toString());

        final String q = "INCONSIDERINGTHERISEOFTHEBOLSHEVIKIITISNECESSARYTOUNDERSTANDTHATRUSSIA" +
                "NECONOMICLIFEANDTHERUSSIANARMYWERENOTDISORGANISEDONNOVEMBERTHBUTMANYMONTHSBEFOREASTHELOG" +
                "ICALRESULTOFAPROCESSWHICHBEGANASFARBACKASTHECORRUPTREACTIONARIESINCONTROLOFTHETSARSCOURT" +
                "DELIBERATELYUNDERTOOKTOWRECKRUSSIAINORDERTOMAKEASEPARATEPEACEWITHGERMANYTHELACKOFARMSONT" +
                "HEFRONTWHICHHADCAUSEDTHEGREATRETREATOFTHESUMMEROFTHELACKOFFOODINTHEARMYANDINTHEGREATCITI" +
                "ESTHEBREAKDOWNOFMANUFACTURESANDTRANSPORTATIONINALLTHESEWEKNOWNOWWEREPARTOFAGIGANTICCAMPA" +
                "IGNOFSABOTAGETHISWASHALTEDJUSTINTIMEBYTHEMARCHREVOLUTION";
//        final String q = "NORDERTOMAKEASEPARATEPEACEWIT";

        FindLongestPrefixMatch flpm = new FindLongestPrefixMatch(esa);


        long start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, q.toCharArray(), false) + " flpm NOT DC");
        long end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());


        long startFour = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, q.toCharArray(), true) + " flpm DC");
        long endFur = System.currentTimeMillis();
        System.out.println("DONE in " + (endFur - startFour));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());

        final String qq = ESA_Utils.getCurrentSuffix(esa, 17391043, esa.length);

        start = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, qq.toCharArray(), false) + " flpm NOT DC");
        end = System.currentTimeMillis();
        System.out.println("DONE in " + (end - start));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());


        startFour = System.currentTimeMillis();
        System.out.println(flpm.matching(esa, qq.toCharArray(), true) + " flpm DC");
        endFur = System.currentTimeMillis();
        System.out.println("DONE in " + (endFur - startFour));
        System.out.println("FindLPrefixMath done: " + Calendar.getInstance().getTime());

        int max = 0;
        for (int i = 0; i < esa.lcp.length; i = i + 1) {

        }


//        System.out.println(esa.toString());
//        System.out.println(ObjectSizeCalculator.getObjectSize(esa) + "\tESA SIZE");
//        System.out.println(ObjectSizeCalculator.getObjectSize(esa.lcp.dc) + "\tDC SIZE");
//        System.out.println(ObjectSizeCalculator.getObjectSize(esa.child) + "\tCT SIZE");
    }

}
