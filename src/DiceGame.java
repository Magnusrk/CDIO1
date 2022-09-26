import java.util.Scanner;

public class DiceGame {
    static boolean p1Turn = true;
    static int p1Sum;
    static int p2Sum;
    static boolean p1wasSix = false;
    static boolean p2wasSix = false;
    static boolean isP1wasSix = false;
    static  boolean isP2wasSix = false;


    public static void main(String[] args) {

            playGame(1);

    }

    public static void playGame(int Player){
                Scanner input = new Scanner(System.in);
                System.out.println("Spiller " + Player + " kast");
                input.nextLine();
                int[] dieResults = throwDice();
                dieResults[0] = 6;
                dieResults[1] = 6;
                addScores(Player, dieResults);
                checkWinner(Player);
    }

    private static void addScores(int Player, int[] dieResults) {
        if (Player == 1) {
            isP1wasSix = p1wasSix && dieResults[0] == 6 && dieResults[1] == 6;
            p1Sum += dieResults[0] + dieResults[1];
            p1wasSix = dieResults[0] == 6 && dieResults[1] == 6;
            System.out.println("Du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p1Sum);
            System.out.println(p1wasSix);
        } else {
            isP2wasSix = p2wasSix && dieResults[0] == 6 && dieResults[1] == 6;
            p2Sum += dieResults[0] + dieResults[1];
            p2wasSix = dieResults[0] == 6 && dieResults[1] == 6;
            System.out.println("Du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p2Sum);
            System.out.println(p2wasSix);
        }
    }

    private static void checkWinner(int Player) {
        if (p1Sum >= 40 || isP1wasSix) {
            System.out.println("Spiller 1 vandt");
        } else if (p2Sum >= 40 || isP2wasSix){
            System.out.println("Spiller 2 vandt");
        } else {
            playGame(Player ==1?2:1);
        }
    }

    public static int[] throwDice(){
       int die1 = (int) ((Math.random() * (6)) + 1);
       int die2 = (int) ((Math.random() * (6)) + 1);
       return new int[]{die1, die2};
    }
}