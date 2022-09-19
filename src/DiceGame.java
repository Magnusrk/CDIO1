import java.util.Scanner;

public class DiceGame {
    boolean p1Turn = true;
    static int p1Sum;
    int p2Sum;

    public static void main(String[] args) {
        playGame();

    }

    public static void playGame(){
        Scanner input = new Scanner(System.in);
        System.out.println("Spiller 1 kast");
        input.nextLine();
        int [] dieResults = throwDice();
        p1Sum = p1Sum+ dieResults[0]+ dieResults[1];
        System.out.println("Du kastede "+ dieResults[0] + " og " + dieResults[1] + " Dine point " + (dieResults[0]+dieResults[1])+ " summen " + p1Sum);
        playGame();
    }

    public static int[] throwDice(){
       int die1 = (int) ((Math.random() * (6-1)) + 1);
       int die2 = (int) ((Math.random() * (6-1)) + 1);
       return new int[]{die1, die2};
    }
}