package GUI;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Sudoku2 {

    private static final int SIZE = 3;
    private JFormattedTextField[][] cells = new JFormattedTextField[SIZE][SIZE];
    private JFrame frame;

    public Sudoku2() {
        frame = new JFrame("Mini Sudoku 3x3");
        frame.setSize(300, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(SIZE, SIZE, 5, 5));
        frame.add(panel, BorderLayout.CENTER);

        // Δημιουργία κελιών
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new JFormattedTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 24)); // μεγαλύτεροι αριθμοί
                panel.add(cells[i][j]);
            }
        }

        // Κουμπί Check
        JButton checkButton = new JButton("Check");
        frame.add(checkButton, BorderLayout.SOUTH);

        checkButton.addActionListener(e -> {
            if (isCorrect()) {
                JOptionPane.showMessageDialog(frame, "Σωστό! Κανένας αριθμός δεν επαναλαμβάνεται");
                generatePuzzle(); // νέο puzzle
            } else {
                JOptionPane.showMessageDialog(frame, "Λάθος ή κενά κελιά!");
            }
        });

        generatePuzzle(); // αρχικό puzzle
        frame.setVisible(true);
    }

    private void generatePuzzle() {
        int[][] solution = new int[SIZE][SIZE];
        Random rand = new Random();

        // 1. Τυχαία πρώτη σειρά
        List<Integer> firstRow = new ArrayList<>(Arrays.asList(1, 2, 3));
        Collections.shuffle(firstRow);
        for (int j = 0; j < SIZE; j++) {
            solution[0][j] = firstRow.get(j);
        }

        // 2. Δεύτερη σειρά
        // Επιλέγουμε τυχαία τον πρώτο αριθμό
        List<Integer> options = new ArrayList<>(Arrays.asList(1,2,3));
        options.remove(Integer.valueOf(solution[0][0]));
        solution[1][0] = options.get(rand.nextInt(options.size()));

        // Υπολογισμός υπολοίπων για μοναδική λύση
        for (int j = 1; j < SIZE; j++) {
            for (int val = 1; val <= SIZE; val++) {
                boolean usedInRow = false;
                boolean usedInCol = false;
                for (int k = 0; k < j; k++) if (solution[1][k] == val) usedInRow = true;
                for (int i = 0; i < 1; i++) if (solution[i][j] == val) usedInCol = true;
                if (!usedInRow && !usedInCol) {
                    solution[1][j] = val;
                    break;
                }
            }
        }

        // 3. Τρίτη σειρά
        for (int j = 0; j < SIZE; j++) {
            for (int val = 1; val <= SIZE; val++) {
                if (solution[0][j] != val && solution[1][j] != val) {
                    solution[2][j] = val;
                    break;
                }
            }
        }

        // 4. Γεμίζουμε το grid και κρύβουμε μερικά για puzzle
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (rand.nextBoolean()) { // κρύβουμε περίπου τα μισά
                    cells[i][j].setText("");
                    cells[i][j].setEditable(true);
                } else {
                    cells[i][j].setText(String.valueOf(solution[i][j]));
                    cells[i][j].setEditable(false);
                }
            }
        }
    }

    private boolean isCorrect() {
        // Έλεγχος γραμμών
        for (int i = 0; i < SIZE; i++) {
            boolean[] seen = new boolean[SIZE + 1];
            for (int j = 0; j < SIZE; j++) {
                String text = cells[i][j].getText();
                if (text.isEmpty()) return false;
                int val;
                try { val = Integer.parseInt(text); } 
                catch (NumberFormatException e) { return false; }
                if (val < 1 || val > SIZE || seen[val]) return false;
                seen[val] = true;
            }
        }

        // Έλεγχος στηλών
        for (int j = 0; j < SIZE; j++) {
            boolean[] seen = new boolean[SIZE + 1];
            for (int i = 0; i < SIZE; i++) {
                String text = cells[i][j].getText();
                int val;
                try { val = Integer.parseInt(text); } 
                catch (NumberFormatException e) { return false; }
                if (val < 1 || val > SIZE || seen[val]) return false;
                seen[val] = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        new Sudoku2();
    }
}
