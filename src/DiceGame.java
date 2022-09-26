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
                snakeEyesCheck(Player, dieResults);
                checkWinner(Player, dieResults);
    }

    private static void snakeEyesCheck(int Player, int[] dieResults) {
        if(dieResults[0]==1 && dieResults[1]==1){
            if(Player == 1) {
                System.out.println("Spiller 1 du kastede " + dieResults[0] + " og " + dieResults[1] + " Det er 2 enere du mister alle dine point. Øv! ");
                p1Sum = 0;
            }else{
                System.out.println("Spiller 2 du kastede " + dieResults[0] + " og " + dieResults[1] + " Det er 2 enere du mister alle dine point. Øv!");
                p2Sum = 0;
            }
        }else {
            addScores(Player, dieResults);
        }
    }

    private static void addScores(int Player, int[] dieResults) {

        if (Player == 1) {
            p1Sum += dieResults[0] + dieResults[1];
            System.out.println("Spiller 1 du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p1Sum);
        } else {
            p2Sum += dieResults[0] + dieResults[1];
            System.out.println("Spiller 2 du kastede " + dieResults[0] + " og " + dieResults[1] + " Summen: " + (dieResults[0] + dieResults[1]) + " Dine point: " + p2Sum);
        }
    }

    private static void checkWinner(int Player, int[] dieResults) {
        if (p1Sum >= 40 && dieResults[0]==dieResults[1] && Player == 1) {
            System.out.println("Spiller 1 vandt");
        } else if (p2Sum >= 40 && dieResults[0]==dieResults[1] && Player == 2){
            System.out.println("Spiller 2 vandt");
        } else checkExtraTurn(Player, dieResults);
    }

    public static int[] throwDice(){
       int die1 = (int) ((Math.random() * (6)) + 1);
       int die2 = (int) ((Math.random() * (6)) + 1);
       return new int[]{die1, die2};
    }

    private static void checkExtraTurn(int Player, int[] dieResults) {
        if (dieResults[0] == dieResults[1]) {
            System.out.println("Spiller " + Player + " har tur igen");
            playGame(Player);
        } else {
            playGame(Player == 1 ? 2 : 1);
        }
    }
}