import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is a simple tic-tac-toe project that uses JFrame, JPanel, JButton and ActionListener
 * Project is a tic-tac-toe game where two players play and game ends once a winner is determined or if it is a tie
 */
public class TicTacToe {

    // Variables
    int bordWidth = 600;
    int bordHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;
    public TicTacToe() {
        // Frame properties
        frame.setVisible(true);
        frame.setSize(bordWidth, bordHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Label properties
        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        // Panel properties
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Board properties
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn");
                            }

                        }
                    }
                });
            }
        }
    }

    // Functions that checks if any of the players won
    public void checkWinner() {
        // Check horizontally for winner
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText() == "") {
                continue;
            }
            if (board[i][0].getText() == board[i][1].getText()
                    && board[i][1].getText() == board[i][2].getText()) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                gameOver = true;
                return;
            }
        }
        // Checks vertically for winner
        for (int i = 0; i < 3; i++) {
            if (board[0][i].getText() == "") {
                continue;
            }
            if (board[0][i].getText() == board[1][i].getText()
                    && board[1][i].getText() == board[2][i].getText()) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[j][i]);
                }
                gameOver = true;
                return;
            }
        }
        // Checks diagonally for winner
        if (board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText()
                && board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        // Checks anti diagonally for winner
        if (board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText()
                && board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }
        // Checks if it is a tie
        if (turns == 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
        }
    }

    // Function that sets different color for the winner player
    public void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    // Function that sets different color if it is a tie
    public void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
