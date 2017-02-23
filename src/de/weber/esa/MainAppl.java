package de.weber.esa;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.bwt.FMIndex;
import de.weber.esa.struct.repeats.SupermaximalRepeats;
import de.weber.esa.struct.rmq.RMQ;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.util.Calendar;

/**
 * Created by Stefan on 20.01.2017.
 */
public class MainAppl {

    public static void main(String[] args) {
        final String s = "ACAAACATAT";
//        final String s = "MISSISSIPPI";
//        final String s = "BANANA";

        System.out.println("Start: " + Calendar.getInstance().getTime());

//        final File file = new File("res/test/english.50MB");
//        final String s = Reader.readFile(file);
//        System.out.println("Sequence read in: " + Calendar.getInstance().getTime());

        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);
        System.out.println("ESArray built: " + Calendar.getInstance().getTime());
        System.out.println(s.length());

        System.out.println(esa.toString());

//        final String testQuery = "INCONSIDERINGTHERISEOFTHEBOLSHEVIKIITISNECESSARYTOUNDERSTANDTHATRUSSIA" +
//                "NECONOMICLIFEANDTHERUSSIANARMYWERENOTDISORGANISEDONNOVEMBERTHBUTMANYMONTHSBEFOREASTHELOG" +
//                "ICALRESULTOFAPROCESSWHICHBEGANASFARBACKASTHECORRUPTREACTIONARIESINCONTROLOFTHETSARSCOURT" +
//                "DELIBERATELYUNDERTOOKTOWRECKRUSSIAINORDERTOMAKEASEPARATEPEACEWITHGERMANYTHELACKOFARMSONT" +
//                "HEFRONTWHICHHADCAUSEDTHEGREATRETREATOFTHESUMMEROFTHELACKOFFOODINTHEARMYANDINTHEGREATCITI" +
//                "ESTHEBREAKDOWNOFMANUFACTURESANDTRANSPORTATIONINALLTHESEWEKNOWNOWWEREPARTOFAGIGANTICCAMPA" +
//                "IGNOFSABOTAGETHISWASHALTEDJUSTINTIMEBYTHEMARCHREVOLUTION";
        final String testQuery = "AA";

        FMIndex fm = esa.bwt.backwardSearch(esa, testQuery);
        System.out.println(testQuery + ": " + fm.toString());
        System.out.println("FM-backward search done: " + Calendar.getInstance().getTime());

        SupermaximalRepeats sr = new SupermaximalRepeats(esa);
        System.out.println("Supermaximal repeats: " + sr.getSupermaximalRepeats());
        System.out.println("Supermaximal Repeats built: " + Calendar.getInstance().getTime());

        RMQ rmq = new RMQ(esa.lcp.lcps, 3);
        System.out.println(rmq.toString());


//        System.out.println(esa.toString());
        System.out.println(ObjectSizeCalculator.getObjectSize(esa) + "\tESA SIZE");
    }

}
