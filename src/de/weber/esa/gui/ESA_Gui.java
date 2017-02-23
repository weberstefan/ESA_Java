package de.weber.esa.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stefan on 10.02.2017.
 */
public class ESA_Gui {

    public static void main(String[] args) {
        new ESA_Gui();
    }

    private final JFrame esaFrame;

    private final InputPanel inputPanel;

    private static boolean displayESA = true;

    public ESA_Gui() {
        this.esaFrame = new JFrame("Enhanced Suffix Arrays");

        final JMenuBar menuBar = new JMenuBar();
        populateMenuBar(menuBar);
        this.esaFrame.setJMenuBar(menuBar);

        this.esaFrame.setLayout(new BorderLayout());

        this.inputPanel = new InputPanel();

        this.esaFrame.add(this.inputPanel);
        this.esaFrame.setSize(860, 860);
        this.esaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.esaFrame.setVisible(true);
    }

    private void populateMenuBar(JMenuBar menuBar) {
        menuBar.add(createFileMenu());
        menuBar.add(preferencesMenu());
    }

    private JMenu preferencesMenu() {
        final JMenu preferencesMenu = new JMenu("Preferences");

        final JCheckBoxMenuItem cbDisplayESAItem = new JCheckBoxMenuItem("Display ESA", true);
        cbDisplayESAItem.addActionListener(e -> {
            displayESA = true;
            inputPanel.redo();
        });
        preferencesMenu.add(cbDisplayESAItem);

        return preferencesMenu;
    }

    private JMenu createFileMenu() {
        final JMenu filesMenu = new JMenu("File");
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        filesMenu.add(exitMenuItem);

        return filesMenu;
    }

    public static boolean isDisplayESA() { return displayESA; }

}
