import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Game6 {
    static boolean p1Turn = true;
    static int p1Sum;
    static int p2Sum;

    static DiceUI ui;

    public static void main(String[] args) {

            initGame();
    }

    private static void initGame()
    {
        EventQueue.invokeLater(() -> {
            ui = new DiceUI();
            JFrame ex = ui;
            ex.setVisible(true);
            ui.DiceBoard.DrawGraphics(1, p1Sum,p2Sum, 1, 1);


        });


    }

    public static void playGame(int Player){
        Scanner input = new Scanner(System.in);
        //System.out.println("Spiller " + Player + " kast");
        ui.DiceBoard.UpdateMessage("Spiller " + Player + "'s tur! ");
        //input.nextLine();
        int[] dieResults = throwDice();
        snakeEyesCheck(Player, dieResults);

        ui.DiceBoard.DrawGraphics(Player == 1 ? 2 : 1, p1Sum,p2Sum, dieResults[0], dieResults[1]);
        checkWinner(Player);
    }

    private static void snakeEyesCheck(int Player, int[] dieResults) {
        if(dieResults[0]==1 && dieResults[1]==1){
            if(Player == 1) {
                ui.DiceBoard.UpdateMessage("Spiller 1 du kastede " + dieResults[0] + " og " + dieResults[1] + " Det er 2 enere du mister alle dine point. Øv! ");
                System.out.println();
                p1Sum = 0;
            }else{
                ui.DiceBoard.UpdateMessage("Spiller 2 du kastede " + dieResults[0] + " og " + dieResults[1] + " Det er 2 enere du mister alle dine point. Øv! ");
                p2Sum = 0;
            }
        }else {
            addScores(Player, dieResults);
        }
    }

    private static void addScores(int Player, int[] dieResults) {

        if (Player == 1) {
            p1Sum += dieResults[0] + dieResults[1];
            ui.DiceBoard.UpdateMessage("Spiller 1 du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p1Sum);
        } else {
            p2Sum += dieResults[0] + dieResults[1];
            ui.DiceBoard.UpdateMessage("Spiller 2 du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p2Sum);
        }
    }

    private static void checkWinner(int Player) {
        if (p1Sum >= 40) {
            ui.DiceBoard.UpdateMessage("Spiller 1 vandt!");
            p1Sum = 0;
            p2Sum = 0;
        } else if (p2Sum >= 40){
            ui.DiceBoard.UpdateMessage("Spiller 2 vandt!");
            p1Sum = 0;
            p2Sum = 0;
        } else {
            //playGame(Player ==1?2:1);
        }
    }

    public static int[] throwDice(){
       int die1 = (int) ((Math.random() * (6)) + 1);
       int die2 = (int) ((Math.random() * (6)) + 1);
       return new int[]{die1, die2};
    }
}