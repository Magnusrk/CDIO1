import java.util.Scanner;

public class DiceGame {
    static boolean p1Turn = true;
    static int p1Sum;
    static int p2Sum;


    public static void main(String[] args) {

            playGame(1);

    }

    public static void playGame(int Player){
                Scanner input = new Scanner(System.in);
                System.out.println("Spiller " + Player + " kast");
                input.nextLine();
                int[] dieResults = throwDice();
                if (Player == 1) {
                    p1Sum += dieResults[0] + dieResults[1];
                    System.out.println("Du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p1Sum);
                } else {
                    p2Sum += dieResults[0] + dieResults[1];
                    System.out.println("Du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p2Sum);
                }
                if (p1Sum >= 40) {
                    System.out.println("Spiller 1 vandt");
                } else if (p2Sum >= 40){
                    System.out.println("Spiller 2 vandt");
                } else {
                    playGame(Player==1?2:1);
                }
    }

    public static int[] throwDice(){
       int die1 = (int) ((Math.random() * (6)) + 1);
       int die2 = (int) ((Math.random() * (6)) + 1);
       return new int[]{die1, die2};
    }
}