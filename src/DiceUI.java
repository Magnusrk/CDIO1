import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;

public class DiceUI extends JFrame {

    public diceBoard DiceBoard;
    public DiceUI() {

        initUI();
    }

    private void initUI() {
        DiceBoard = new diceBoard();
        add(DiceBoard);

        setResizable(false);
        pack();

        setTitle("Dice game!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new DiceUI();
            ex.setVisible(true);
        });
    }
}

class diceBoard extends JPanel
{


    final int B_WIDTH = 700;
    final int B_HEIGHT = 700;

    final int DieSize = 100;

    private int turn = 1;
    private int p1Score = 0;
    private int p2Score = 0;

    private int d1 = 1;
    private int d2 = 1;
    public diceBoard() {
        initDiceBoard();

    }

    public void DrawGraphics(int playerTurn, int Player1Score, int Player2Score)
    {
        turn = playerTurn;
        p1Score = Player1Score;
        p2Score = Player2Score;
        repaint();
    }

    public void DrawGraphics(int playerTurn, int Player1Score, int Player2Score,int diceValue1, int diceValue2 )
    {
        turn = playerTurn;
        p1Score = Player1Score;
        p2Score = Player2Score;
        d1 = diceValue1;
        d2 = diceValue2;
        repaint();
    }



    private void initDiceBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.getHSBColor(0.5f, 0.96f, 0.1f));
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateGraphics(g);
    }

    private void updateGraphics(Graphics g) {

        drawPlayerNames(g);
        drawPlayerScores(g);
        drawDies(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawDies(Graphics g)
    {
        float dieFont = 40f;
        g.setFont(g.getFont().deriveFont(dieFont));
        g.setColor(Color.white);
        g.fillRect((B_WIDTH/4)-DieSize/2, (B_HEIGHT/5)*3, DieSize, DieSize );
        g.fillRect(((B_WIDTH/4)*3)-DieSize/2, (B_HEIGHT/5)*3, DieSize, DieSize );

        g.setColor(Color.black);
        g.drawString(""+d1, (B_WIDTH/4)-((int)dieFont/4),((B_HEIGHT/5)*3)+DieSize/4+((int)dieFont));
        g.drawString(""+d2, (B_WIDTH/4)*3-((int)dieFont/4),((B_HEIGHT/5)*3)+DieSize/4+((int)dieFont));

    }
    private void drawPlayerScores(Graphics g) {
        g.setFont(g.getFont().deriveFont(20f));
        g.setColor(Color.white);
        g.drawString(""+p1Score, 30,60);
        g.drawString(""+p2Score, B_WIDTH - 150,60);
    }

    private void drawPlayerNames(Graphics g) {
        g.setFont(g.getFont().deriveFont(20f));
        if(turn == 1) {
            g.setColor(Color.green);
            g.drawString("Player 1", 30,30);
            g.setColor(Color.white);
            g.drawString("Player 2", B_WIDTH - 150,30);
        }
        else {
            g.setColor(Color.white);
            g.drawString("Player 1", 30,30);
            g.setColor(Color.green);
            g.drawString("Player 2", B_WIDTH - 150,30);
        }
    }


}
class TAdapter extends KeyAdapter {


    @Override
    public void keyPressed(KeyEvent e)
    {

    }


}
