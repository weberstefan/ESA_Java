package de.weber.esa.gui;

import de.weber.esa.searching.binarysearch.BinarySearch;
import de.weber.esa.searching.fimindex.FMIndexSearch;
import de.weber.esa.searching.paper_search_via_discriminating_characters.FindLongestPrefixMatch;
import de.weber.esa.searching.scriptum_search_via_child_table.Find;
import de.weber.esa.searching.wrapper.PatternMatchingWrapper;
import de.weber.esa.struct.EnhancedSuffixArray;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by Stefan on 11.02.2017.
 */
public class InputPanel extends JPanel {

    private JLabel inputSequenceLabel;
    private JButton inputSequenceButton;
    private JButton executeESA;
    private JTextArea inputSequenceArea;
    private String sequence;
    private String query;

    private JTextArea resultsTextArea;
    private JLabel resultLabel;

    private PatternMatchingWrapper fm;

    private JButton fmIndexButton;
    private boolean isFmIndex = false;
    private JButton binarySearchButton;
    private boolean isBinarySearch = false;
    private JButton findLPM;
    private boolean isFindLPM;
    private JButton find;
    private boolean isFind;

    private EnhancedSuffixArray esa;

    public InputPanel() {
        super(new BorderLayout());
        setLayout(null);

        this.populateInputs();

        this.populateButton();

        this.populateResults();

        setPreferredSize(new Dimension(860, 860));
    }

    private void populateInputs() {
        this.inputSequenceLabel = new JLabel("Enter a raw sequence or select a raw sequence from a file");
        this.inputSequenceLabel.setBounds(0, 0, 500, 40);
        add(inputSequenceLabel);
        this.inputSequenceArea = new JTextArea();
        this.inputSequenceArea.setBounds(3, 40, 500, 300);
        add(this.inputSequenceArea);

        this.inputSequenceButton = new JButton("Select sequence from file");
        this.inputSequenceButton.setBounds(520, 40, 200, 75);
        this.inputSequenceButton.addActionListener(e -> {
            sequence = FileChooser.openFile();
            inputSequenceArea.setText(sequence);
            redo();
        });
        add(this.inputSequenceButton);

        this.executeESA = new JButton("Submit ESA");
        this.executeESA.setBounds(520, 150, 150, 75);
        this.executeESA.addActionListener(e -> {
            sequence = inputSequenceArea.getText();
            if (! sequence.chars().allMatch(Character::isLetter)) {
                JOptionPane.showMessageDialog(null, "Sequence must only contain letters!");
            } else {
                esa = new EnhancedSuffixArray(sequence);
                redo();
            }
        });
        add(this.executeESA);
    }

    private void populateButton() {
        this.fmIndexButton = new JButton("FM-Index");
        this.fmIndexButton.setBounds(520, 250, 150, 75);
        this.fmIndexButton.addActionListener(e -> {
            query = JOptionPane.showInputDialog("Enter a query");
            if (! query.chars().allMatch(Character::isLetter)) {
                JOptionPane.showMessageDialog(null, "Query must only contain letters!");
            } else {
                FMIndexSearch fmIndexSearch = new FMIndexSearch();
                fm = fmIndexSearch.backwardSearch(esa, query);
                isFmIndex = true;
                redo();
            }
        });
        add(this.fmIndexButton);

        this.binarySearchButton = new JButton("BinarySearch");
        this.binarySearchButton.setBounds(520, 350, 150, 75);
        this.binarySearchButton.addActionListener(e -> {
            query = JOptionPane.showInputDialog("Enter a query");
            if (! query.chars().allMatch(Character::isLetter)) {
                JOptionPane.showMessageDialog(null, "Query must only contain Letters!");
            } else {
                BinarySearch binarySearch = new BinarySearch();
                fm = binarySearch.search(esa, query);
                isBinarySearch = true;
                redo();
            }
        });
        add(this.binarySearchButton);

        this.findLPM = new JButton("findLPM");
        this.findLPM.setBounds(675, 250, 150, 75);
        this.findLPM.addActionListener(e -> {
            query = JOptionPane.showInputDialog("Enter a query");
            if (! query.chars().allMatch(Character::isLetter)) {
                JOptionPane.showMessageDialog(null, "Query must only contain Letters!");
            } else {
                FindLongestPrefixMatch flpm = new FindLongestPrefixMatch(esa);
                JList list = new JList(new String[]{"DC", "No DC"});
                JOptionPane.showMessageDialog(null, list, "Choose your searching properties", JOptionPane.PLAIN_MESSAGE);
                fm = flpm.matching(esa, query.toCharArray(), Arrays.toString(list.getSelectedIndices()).equals("[0]"));
                isFindLPM = true;
                redo();
            }
        });
        add(this.findLPM);

        this.find = new JButton("Find Query");
        this.find.setBounds(675, 350, 150, 75);
        this.find.addActionListener(e -> {
            query = JOptionPane.showInputDialog("Enter a query");
            if (! query.chars().allMatch(Character::isLetter)) {
                JOptionPane.showMessageDialog(null, "Query must only contain Letters!");
            } else {
                Find find = new Find();
                fm = find.find(esa, query.toCharArray());
                isFind = true;
                redo();
            }
        });
        add(this.find);
    }

    private void populateResults() {
        this.resultLabel = new JLabel("Results");
        this.resultLabel.setForeground(Color.RED);
        this.resultLabel.setBounds(10, 440, 200, 50);
        add(this.resultLabel);
        this.resultsTextArea = new JTextArea();
        this.resultsTextArea.setBounds(3, 480, 860, 400);
        add(this.resultsTextArea);
    }

    public void redo() {
        if (esa != null) {
            if (ESA_Gui.isDisplayESA()) {
                this.resultsTextArea.setText("Enhanced Suffix Array for: " + sequence + "$\n" + esa.toString());
            }

            if (isFmIndex) {
                this.resultsTextArea.append("\n\n" + query + " found at suffix array positions: " + fm.toString());
                isFmIndex = false;
            }

            if (isBinarySearch) {
                this.resultsTextArea.append("\n\n" + query + " found at suffix array positions: " + fm.toString());
                isBinarySearch = false;
            }

            if (isFindLPM) {
                this.resultsTextArea.append("\n\n" + query + " found at suffix array positions: " + fm.toString());
                isFindLPM = false;
            }

            if (isFind) {
                this.resultsTextArea.append("\n\n" + query + " found at suffix array positions: " + fm.toString());
                isFind = false;
            }
        }

        validate();
        repaint();
    }

}
