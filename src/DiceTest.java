public class DiceTest {

    public static void main(String[] args)
    {
        int testCount = 1000;
        int[] sums = new int[testCount];
        for(int i = 0; i < testCount; i++)
        {
            int[] diceThrow = DiceGame.throwDice();
            sums[i] = diceThrow[0] + diceThrow[1];
        }

        int[] distribution = new int[11];

        for(int i = 0; i < sums.length; i++)
        {
            distribution[sums[i]-2] += 1;
        }
        System.out.println("Distribution of sums: ");
        for(int i = 0; i < distribution.length; i++)
        {
            System.out.println("["+(i+2)+"]: " +distribution[i]);
        }

    }
}
