public class DiceGame {
    public static void main(String[] args) {
       int p1Sum, p2Sum;

       int [] dieResults = throwDice();


    }

    public static int[] throwDice(){
       int die1 = (int) ((Math.random() * (6-1)) + 1);
       int die2 = (int) ((Math.random() * (6-1)) + 1);
       return new int[]{die1, die2};
    }
}