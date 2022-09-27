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

    private void initUI() {     //Lav DiceBoard arver JPanel, og sæt tilføj det til DiceUI som arver JFrame
        DiceBoard = new diceBoard();
        add(DiceBoard);
        setResizable(false);
        pack();
        setTitle("Terningespil!");          //Titel på vindue
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class diceBoard extends JPanel implements ActionListener {
    final int B_WIDTH = 700;        //Bredde på vindue
    final int B_HEIGHT = 700;       //Højde på vindue
    final int DieSize = 100;        //Terningernes sidelængde
    public static int turn = 1;     //Player tur
    private int p1Score = 0;        //Scores
    private int p2Score = 0;        //Scores
    final int input_delay = 10;   //Man kan kun rulle 1 gang hvert 10ms. (For at undgå at man holde knappen inde)



    public static boolean rollingDies = false;      //Blive brugt til input delay

    private static String statusMessage = "Velkommen til terningespillet!"; //Besked der står i midten af brættet

    private int d1 = 1;     //Dice 1 value
    private int d2 = 1;      //Dice 2 value

    public diceBoard() {
        initDiceBoard();

    }

    //Public metode, opdaterer variabler og opdaterer grafikken.
    public void DrawGraphics(int playerTurn, int Player1Score, int Player2Score, int diceValue1, int diceValue2) {
        turn = playerTurn;
        p1Score = Player1Score;
        p2Score = Player2Score;
        d1 = diceValue1;
        d2 = diceValue2;
        repaint();
    }


    //Public metode, opdaterer statusMessage og grafikken.
    public void UpdateMessage(String message) {
        statusMessage = message;
        repaint();
    }


    private void initDiceBoard() {
        addKeyListener(new TAdapter());                                    //Key listener
        setBackground(Color.getHSBColor(0.5f, 0.96f, 0.1f));      //Background farve, i HSB
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        Timer timer = new Timer(input_delay, this);
        timer.start();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateGraphics(g);
    }

    //Tegner grafikken
    private void updateGraphics(Graphics g) {

        drawPlayerNames(g);     //Spiller 1 og spiller 2 i toppen
        drawPlayerScores(g);    //Deres scores
        drawDies(g);            //Terninger og deres værdi
        drawMessages(g);        //Andre beskeder
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawMessages(Graphics g) {
        g.setFont(g.getFont().deriveFont(20f));         //Sæt font
        g.setColor(Color.white);                             //Sæt farve
        g.drawString("Tryk 'enter' for at rulle terningerne!", B_WIDTH / 2 - 160, B_HEIGHT - 100); //String og pos
        g.setFont(g.getFont().deriveFont(15f));
        g.drawString(statusMessage, (B_WIDTH / 2) - (int) ((float) statusMessage.length() * 3.5f), B_HEIGHT - 400);
    }

    private void drawDies(Graphics g) {           //Tegn terninger
        float dieFont = 40f;                 //Font størrelse
        g.setFont(g.getFont().deriveFont(dieFont));
        g.setColor(Color.white);            //Farve på rektangel
        g.fillRect((B_WIDTH / 4) - DieSize / 2, (B_HEIGHT / 5) * 3, DieSize, DieSize); //Pos (x,y) og sidelængder
        g.fillRect(((B_WIDTH / 4) * 3) - DieSize / 2, (B_HEIGHT / 5) * 3, DieSize, DieSize);
        g.setColor(Color.black);                                //Sæt farve til sort og tegn tekst
        g.drawString("" + d1, (B_WIDTH / 4) - ((int) dieFont / 4), ((B_HEIGHT / 5) * 3) + DieSize / 4 + ((int) dieFont));
        g.drawString("" + d2, (B_WIDTH / 4) * 3 - ((int) dieFont / 4), ((B_HEIGHT / 5) * 3) + DieSize / 4 + ((int) dieFont));

    }

    private void drawPlayerScores(Graphics g) {     //Skriv spillernes scores
        g.setFont(g.getFont().deriveFont(20f));
        g.setColor(Color.white);
        g.drawString("" + p1Score, 30, 60);
        g.drawString("" + p2Score, B_WIDTH - 150, 60);
    }

    private void drawPlayerNames(Graphics g) {  //Skriv spillernes 'navne' (Spille 1/Spiller2)
        g.setFont(g.getFont().deriveFont(20f));
        if (turn == 1) {                                // Hvis det er spiller 1's tur, skriv spiller 1 med grønt.
            g.setColor(Color.green);                    // Ellers skriv spiller 2 med grønt.
            g.drawString("Spiller 1", 30, 30);
            g.setColor(Color.white);
            g.drawString("Spiller 2", B_WIDTH - 150, 30);
        } else {
            g.setColor(Color.white);
            g.drawString("Spiller 1", 30, 30);
            g.setColor(Color.green);
            g.drawString("Spiller 2", B_WIDTH - 150, 30);
        }
    }

    public void actionPerformed(ActionEvent e) {
        rollingDies = false;            //Hver gang timeren der holder styr på input delay udløber
    }                                   //Gør det muligt at rulle terningerne igen.
}

class TAdapter extends KeyAdapter {  //KeyAdapter der lytter til key input

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();                                           //Hvis der trykket på enter
        if (key == KeyEvent.VK_ENTER && !diceBoard.rollingDies) {           //Hvis der ikke allerede bliver rullet
                                                                            //terninger, spil en tur.
            Game6.playGame(diceBoard.turn);
            diceBoard.rollingDies = true;
        }

    }

}
