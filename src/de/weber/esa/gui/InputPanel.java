package de.weber.esa.gui;

import de.weber.esa.struct.EnhancedSuffixArray;
import de.weber.esa.struct.bwt.BWT;
import de.weber.esa.struct.bwt.FMIndex;

import javax.swing.*;
import java.awt.*;

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

    private JButton fmIndexButton;
    private FMIndex fm;
    private boolean isFmIndex = false;

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
                fm = esa.bwt.backwardSearch(esa, query);
                if (BWT.charNotInSequence) {
                    JOptionPane.showMessageDialog(null, "There is a character in your query that is not in the sequence");
                    BWT.charNotInSequence = false;
                }
                isFmIndex = true;
                redo();
            }
        });
        add(this.fmIndexButton);
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
        }

        validate();
        repaint();
    }

}
