import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import javax.swing.Timer;


public class DiceUI extends JFrame {
    public static diceBoard DiceBoard;
    public DiceUI() {

        initUI();
    }

    private void initUI() {
        DiceBoard = new diceBoard();
        add(DiceBoard);
        setResizable(false);
        pack();
        setTitle("Terningespil!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class diceBoard extends JPanel implements ActionListener
{
    final int B_WIDTH = 700;
    final int B_HEIGHT = 700;
    final int DieSize = 100;
    public static int turn = 1;
    private int p1Score = 0;
    private int p2Score = 0;
    private int input_delay = 10;

    private Timer timer;

    public static boolean rollingDies = false;

    private static String statusMessage = "Velkommen til terningespillet!";

    private int d1 = 1;
    private int d2 = 1;

    public diceBoard() {
        initDiceBoard();

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

    public void UpdateMessage(String message)
    {
        statusMessage = message;
        repaint();
    }


    private void initDiceBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.getHSBColor(0.5f, 0.96f, 0.1f));
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        timer = new Timer(input_delay, this);
        timer.start();
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
        g.setFont(g.getFont().deriveFont(20f));
        g.setColor(Color.white);

        g.drawString("Tryk 'enter' for at rulle terningerne!", B_WIDTH/2-160,B_HEIGHT-100);
        g.setFont(g.getFont().deriveFont(15f));
        g.drawString(statusMessage, (B_WIDTH/2) - (int)((float)statusMessage.length()*3.5f),B_HEIGHT-400);
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
            g.drawString("Spiller 1", 30,30);
            g.setColor(Color.white);
            g.drawString("Spiller 2", B_WIDTH - 150,30);
        }
        else {
            g.setColor(Color.white);
            g.drawString("Spiller 1", 30,30);
            g.setColor(Color.green);
            g.drawString("Spiller 2", B_WIDTH - 150,30);
        }
    }

    public void actionPerformed(ActionEvent e) {
        rollingDies = false;

    }
}
class TAdapter extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ENTER && !DiceUI.DiceBoard.rollingDies)
        {

            Game6.playGame(diceBoard.turn);
            DiceUI.DiceBoard.rollingDies = true;
        }

    }

}
