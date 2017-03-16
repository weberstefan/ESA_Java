package de.weber.esa.searching.binarySearch;

import de.weber.esa.io.Reader;
import de.weber.esa.searching.wrapper.BinarySearchWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;

import java.io.File;
import java.util.Calendar;

/**
 * Created by Stefan on 13.03.2017.
 *
 * //TODO VALIDATE
 */
public class BinarySearch {

    public static void main(String[] args) {
//        final String s = "ACAAACATAT";
        System.out.println("Start: " + Calendar.getInstance().getTime());
        final File file = new File("res/test/english.50MB");
        final String s = Reader.readFile(file);
        System.out.println("Sequence read in: " + Calendar.getInstance().getTime());

        final EnhancedSuffixArray esa = new EnhancedSuffixArray(s);

        System.out.println("ESA built: " + Calendar.getInstance().getTime());

        final String query = "AAA";

        final String testQuery = "INCONSIDERINGTHERISEOFTHEBOLSHEVIKIITISNECESSARYTOUNDERSTANDTHATRUSSIA" +
                "NECONOMICLIFEANDTHERUSSIANARMYWERENOTDISORGANISEDONNOVEMBERTHBUTMANYMONTHSBEFOREASTHELOG" +
                "ICALRESULTOFAPROCESSWHICHBEGANASFARBACKASTHECORRUPTREACTIONARIESINCONTROLOFTHETSARSCOURT" +
                "DELIBERATELYUNDERTOOKTOWRECKRUSSIAINORDERTOMAKEASEPARATEPEACEWITHGERMANYTHELACKOFARMSONT" +
                "HEFRONTWHICHHADCAUSEDTHEGREATRETREATOFTHESUMMEROFTHELACKOFFOODINTHEARMYANDINTHEGREATCITI" +
                "ESTHEBREAKDOWNOFMANUFACTURESANDTRANSPORTATIONINALLTHESEWEKNOWNOWWEREPARTOFAGIGANTICCAMPA" +
                "IGNOFSABOTAGETHISWASHALTEDJUSTINTIMEBYTHEMARCHREVOLUTION";

//        System.out.println(esa.toString());

//        final int left = esa.bwtCMap.get(query.charAt(0)).getPosMap();

        new BinarySearch(esa, testQuery);

        System.out.println("Query binary search: " + Calendar.getInstance().getTime());
    }

    public BinarySearch(final EnhancedSuffixArray esa,
                        final String query) {
        System.out.println(this.binarySearch(esa, query).toString());
    }

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
        int posChar = 1;

        try {
            while (left <= right && posChar < query.length()) {
                mid = left + ((right - left) / 2);
                final int p = esa.suffices[mid];

                if (query.charAt(posChar) == esa.sequence[p + posChar]) {
                    if (posChar == query.length()) {
                        return new BinarySearchWrapper(query, mid, esa.suffices[mid]);
                    } else {
                        posChar = posChar + 1;
                        if (posChar == query.length()) {
                            return new BinarySearchWrapper(query, mid, esa.suffices[mid]);
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
        } catch (ArrayIndexOutOfBoundsException e) {
            return new BinarySearchWrapper(query, - 1, - 1);
        }
        return new BinarySearchWrapper(query, - 1, - 1);
    }


}
