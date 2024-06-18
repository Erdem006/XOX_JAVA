import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class XOX
{
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("XOX GAME");
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] boardButtons = new JButton[3][3];
    String player1 = "X";
    String player2 = "O";
    String currentPlayer = player1;

    boolean gameOver = false;
    int turns = 0;

    XOX()
    {
        //Frame
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Label
        label.setText("X-O-X");
        label.setOpaque(true);
        label.setForeground(Color.white);
        label.setBackground(Color.darkGray);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 50));

        //Panel
        panel.setLayout(new BorderLayout());
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.gray);

        //ADD
        panel.add(label);
        frame.add(boardPanel);
        frame.add(panel, BorderLayout.NORTH);

        for(int r = 0; r < 3; r++)
        {
            for(int c = 0; c < 3; c++)
            {
                JButton tile = new JButton();
                boardButtons[r][c] = tile;
                boardPanel.add(tile);

                tile.setForeground(Color.white);
                tile.setBackground(Color.darkGray);
                tile.setFocusable(false);
                tile.setFont(new Font("Arial", Font.BOLD, 120));

                tile.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        if(gameOver) return;

                        JButton tile = (JButton)e.getSource();

                        if(tile.getText() == "")
                        {
                            tile.setText(currentPlayer);
                            turns++;
                            CheckWinner();

                            if(!gameOver)
                            {
                                currentPlayer = currentPlayer == player1 ? player2 : player1;
                                label.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }
    }

    void CheckWinner()
    {
        //HORIZONTAL WIN
        for(int r = 0; r < 3; r++)
        {
            if(boardButtons[r][0].getText() == "") continue;

            if(boardButtons[r][0].getText() == boardButtons[r][1].getText() &&
               boardButtons[r][1].getText() == boardButtons[r][2].getText())
            {
                for(int i = 0; i < 3; i++)
                {
                    SetWinner(boardButtons[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //VERTICAL WIN
        for(int c = 0; c < 3; c++)
        {
            if(boardButtons[0][c].getText() == "") continue;

            if(boardButtons[0][c].getText() == boardButtons[1][c].getText() &&
               boardButtons[1][c].getText() == boardButtons[2][c].getText())
            {
                for(int i = 0; i < 3; i++)
                {
                    SetWinner(boardButtons[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //DIAGONALLY
        if(boardButtons[0][0].getText() == boardButtons[1][1].getText() &&
           boardButtons[1][1].getText() == boardButtons[2][2].getText() &&
           boardButtons[0][0].getText() != "")
        {
            for(int i = 0; i < 3; i++)
            {
                SetWinner(boardButtons[i][i]);
            }
            gameOver = true;
            return;
        }

        //REVERSE DIAGNOALLY
        if(boardButtons[0][2].getText() == boardButtons[1][1].getText() &&
           boardButtons[1][1].getText() == boardButtons[2][0].getText() &&
           boardButtons[0][2].getText() != "")
        {
            SetWinner(boardButtons[0][2]);
            SetWinner(boardButtons[1][1]);
            SetWinner(boardButtons[2][0]);
            gameOver = true;
            return;
        }
        
        if(turns == 9)
        {
            for(int r = 0; r < 3; r++)
            {
                for(int c = 0; c < 3; c++)
                {
                    SetTie(boardButtons[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void SetWinner(JButton tile)
    {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        label.setText(currentPlayer + " is winner!");
    }

    void SetTie(JButton tile)
    {
        tile.setForeground(Color.red);
        tile.setBackground(Color.gray);
        label.setText("Tie!");
    }
}
