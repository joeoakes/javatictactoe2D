import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private char currentPlayer;
    private JLabel statusLabel;

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        panel = new JPanel();
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        statusLabel = new JLabel("Player X's turn");

        panel.setLayout(new GridLayout(3, 3));
        panel.setPreferredSize(new Dimension(300, 300));

        // Initialize buttons and add action listeners
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 50));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[row][col].getText().equals("") && currentPlayer != '-') {
                            buttons[row][col].setText(String.valueOf(currentPlayer));
                            if (checkWin()) {
                                statusLabel.setText("Player " + currentPlayer + " wins!");
                                currentPlayer = '-';
                            } else if (isBoardFull()) {
                                statusLabel.setText("It's a draw!");
                                currentPlayer = '-';
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                                statusLabel.setText("Player " + currentPlayer + "'s turn");
                            }
                        }
                    }
                });
                panel.add(buttons[i][j]);
            }
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Check if the game has been won
    private boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(buttons[i][0].getText().charAt(0), buttons[i][1].getText().charAt(0), buttons[i][2].getText().charAt(0))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(buttons[0][i].getText().charAt(0), buttons[1][i].getText().charAt(0), buttons[2][i].getText().charAt(0))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (checkRowCol(buttons[0][0].getText().charAt(0), buttons[1][1].getText().charAt(0), buttons[2][2].getText().charAt(0)) ||
                checkRowCol(buttons[0][2].getText().charAt(0), buttons[1][1].getText().charAt(0), buttons[2][0].getText().charAt(0)));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToe();
            }
        });
    }
}
