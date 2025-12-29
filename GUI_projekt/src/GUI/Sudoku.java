package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

public class Sudoku {

    // Σκορ
    static int score = 0;

   
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mini Sudoku 3x3");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 5, 5));
        frame.add(panel, BorderLayout.CENTER);

        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(scoreLabel, BorderLayout.NORTH);

        // Formatter για αριθμούς 1-3
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(3);
        formatter.setAllowsInvalid(false);

        //
        JFormattedTextField[][] cells = new JFormattedTextField[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new JFormattedTextField(formatter);
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 30)); // Μεγαλύτεροι αριθμοί
                panel.add(cells[i][j]);
            }
        }

        // Κουμπί Check
        JButton checkButton = new JButton("Check");
        frame.add(checkButton, BorderLayout.SOUTH);

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean valid = true;

                // Έλεγχος γραμμών
                for (int i = 0; i < 3; i++) {
                    boolean[] seen = new boolean[4]; // 1-3
                    for (int j = 0; j < 3; j++) {
                        Object valObj = cells[i][j].getValue();
                        if (valObj == null) { valid = false; break; }
                        int val = (Integer) valObj;
                        if (seen[val]) { valid = false; break; }
                        seen[val] = true;
                    }
                }

                // Έλεγχος στηλών
                for (int j = 0; j < 3; j++) {
                    boolean[] seen = new boolean[4]; // 1-3
                    for (int i = 0; i < 3; i++) {
                        Object valObj = cells[i][j].getValue();
                        if (valObj == null) { valid = false; break; }
                        int val = (Integer) valObj;
                        if (seen[val]) { valid = false; break; }
                        seen[val] = true;
                    }
                }

                // Εμφάνιση μηνύματος και νέο παιχνίδι αν σωστό
                if (valid) {
                    score++;
                    JOptionPane.showMessageDialog(frame, "Σωστό! Ξεκινάει νέο παιχνίδι.");
                    scoreLabel.setText("Score: " + score);
                    // Καθαρίζουμε τα κελιά
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            cells[i][j].setValue(null);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Λάθος! Κάποιος αριθμός επαναλαμβάνεται ή κενό.");
                }
            }
        });

        frame.setVisible(true);
    }
}
