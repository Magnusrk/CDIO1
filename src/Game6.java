import java.awt.*;
import javax.swing.*;

public class Game6 {
    static int p1Sum; //Player 1 sum
    static int p2Sum; //Player 2 sum
    static boolean p1ThrowIs6; //Player 1 throw is 6
    static boolean p1ThrowWas6; //Player 1 throw was 6
    static boolean p2ThrowIs6; //Player 2 throw is 6
    static boolean p2ThrowWas6; //Player 2 throw was 6
    static boolean p1sumOver40; //Player 1 point is over 40
    static boolean p2sumOver40; //Player 2 point is over 40
    static boolean gameIsOver;

    public static void main(String[] args) {

            initGame();
    }

    private static void initGame()
    {
        EventQueue.invokeLater(() -> {
            JFrame ex = new DiceUI();
            ex.setVisible(true);
            DiceUI.DiceBoard.DrawGraphics(1, p1Sum,p2Sum, 1, 1);


        });


    }

    public static void playGame(int Player){
        DiceUI.DiceBoard.UpdateMessage("Spiller " + Player + "'s tur! ");
        //input.nextLine();
        int[] dieResults = throwDice();
        snakeEyesCheck(Player, dieResults);
        DiceUI.DiceBoard.DrawGraphics(Player == 1 ? 2 : 1, p1Sum,p2Sum, dieResults[0], dieResults[1]);
        checkWinner(Player, dieResults);
    }

    private static void snakeEyesCheck(int Player, int[] dieResults) {
        if(dieResults[0]==1 && dieResults[1]==1){
            if(Player == 1) {
                DiceUI.DiceBoard.UpdateMessage("Spiller 1 du kastede " + dieResults[0] + " og " + dieResults[1] + " Det er 2 enere du mister alle dine point. Øv! ");
                p1Sum = 0;
            }else{
                DiceUI.DiceBoard.UpdateMessage("Spiller 2 du kastede " + dieResults[0] + " og " + dieResults[1] + " Det er 2 enere du mister alle dine point. Øv! ");
                p2Sum = 0;
            }
        } else if (dieResults[0]==6 && dieResults[1]==6){
            if(p1ThrowIs6 || p2ThrowIs6){
                if (Player == 1){
                    p1ThrowWas6 = true;
                } else {
                    p2ThrowWas6 = true;
                }
            }
            if(Player == 1){
                p1ThrowIs6 = true;
                addScores(Player, dieResults);
            } else{
                p2ThrowIs6 = true;
                addScores(Player, dieResults);
            }
        } else {
            addScores(Player, dieResults);
        }
    }

    private static void addScores(int Player, int[] dieResults) {

        if (Player == 1) {
            p1Sum += dieResults[0] + dieResults[1];
            DiceUI.DiceBoard.UpdateMessage("Spiller 1 du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p1Sum);
        } else {
            p2Sum += dieResults[0] + dieResults[1];
            DiceUI.DiceBoard.UpdateMessage("Spiller 2 du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p2Sum);
        }
    }

    private static void checkWinner(int Player, int[] dieResults) {
        if (p1sumOver40 && (dieResults[0] != 1 && dieResults[0]==dieResults[1] && Player == 1) || p1ThrowWas6) {
            DiceUI.DiceBoard.UpdateMessage("Spiller 1 vandt!");
            gameIsOver = true;
        } else if (p2sumOver40 && (dieResults[0] != 1 && dieResults[0]==dieResults[1] && Player == 2) || p2ThrowWas6){
            DiceUI.DiceBoard.UpdateMessage("Spiller 2 vandt!");
            gameIsOver = true;
        }
        if (p1Sum >= 40){
            p1sumOver40 = true;
        } else if (p2Sum >= 40) {
            p2sumOver40 = true;
        }
        if (!gameIsOver) {
            checkExtraTurn(Player, dieResults);
        }
        else
        {
            resetGame();
        }
    }

    private static void resetGame()
    {
        gameIsOver = false;
        p1Sum = 0;
        p2Sum = 0;
    }

    public static int[] throwDice(){
        int die1 = (int) ((Math.random() * (6)) + 1);
        int die2 = (int) ((Math.random() * (6)) + 1);
        return new int[]{die1, die2};
    }

    private static void checkExtraTurn(int Player, int[] dieResults) {
        if (dieResults[0] != 1 && dieResults[0] == dieResults[1]) {
            DiceUI.DiceBoard.DrawGraphics(Player, p1Sum,p2Sum, dieResults[0], dieResults[1]);
            DiceUI.DiceBoard.UpdateMessage("Spiller " + Player + " har tur igen");
        }
    }
}

