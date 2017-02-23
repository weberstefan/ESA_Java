package de.weber.esa.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Stefan on 20.01.2017.
 *
 * Read in a file and return the files content as one string containing only characters from [A-Za-z]
 */
public class Reader {

    /**
     * @param file : file to be read in
     * @return string from file with only characters from A-Z and in Uppercase letters
     */
    public static String readFile(final File file) {
        try {
            return new String(Files.readAllBytes(file.toPath())).replaceAll("\\s*", "").replaceAll("[^A-Za-z]", "").toUpperCase();
        } catch (IOException eIO) {
            eIO.printStackTrace();
            System.err.println(eIO.toString());
        }
        return null;
    }

}
