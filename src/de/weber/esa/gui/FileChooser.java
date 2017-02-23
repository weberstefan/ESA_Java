package de.weber.esa.gui;

import de.weber.esa.io.Reader;

import javax.swing.*;
import java.io.File;

/**
 * Created by Stefan on 10.02.2017.
 */
public class FileChooser {

    private static JFileChooser fileChooser = new JFileChooser();

    public static final String openFile() {
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return Reader.readFile(file);
        }
        JOptionPane.showMessageDialog(null, "No file was selected");
        return "";
    }

}
