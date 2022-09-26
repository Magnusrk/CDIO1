import java.util.Scanner;

public class Game6 {
    static int p1Sum;
    static int p2Sum;
    static boolean p1ThrowIs6;
    static boolean p1ThrowWas6;
    static boolean p2ThrowIs6;
    static boolean p2ThrowWas6;
    static boolean p1sumOver40;
    static boolean p2sumOver40;
    static boolean gameIsOver;


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
                System.out.print("Spiller 2 du kastede " + dieResults[0] + " og " + dieResults[1] + " Det er 2 enere du mister alle dine point. Øv!");
                p2Sum = 0;
            }
        } else if (dieResults[0]==6 && dieResults[1]==6){
            if(p1ThrowIs6 || p2ThrowIs6){
                if (Player == 1){
                    p1ThrowWas6 = true;
                }
                else {
                    p2ThrowWas6 = true;
                }
            }
            if(Player == 1){
                p1ThrowIs6 = true;
                addScores(Player, dieResults);
            }
            else{
                p2ThrowIs6 = true;
                addScores(Player, dieResults);
            }
        }
        else {
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
        if (p1sumOver40 && (dieResults[0]==dieResults[1] && Player == 1) || p1ThrowWas6) {
            System.out.println("Spiller 1 vandt");
            gameIsOver = true;
        } else if (p2sumOver40 && (dieResults[0]==dieResults[1] && Player == 2) || p2ThrowWas6){
            System.out.println("Spiller 2 vandt");
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
