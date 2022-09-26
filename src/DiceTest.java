public class DiceTest {
    public static void main(String[] args)
    {
        int testCount = 1000;                           //Antal kast
        int[] sums = new int[testCount];                //Initialiser et array med ligeså mange elementer som kast
        int ens = 0;

        for(int i = 0; i < testCount; i++)
        {
            int[] diceThrow = DiceGame.throwDice();     //Kast terningerne og tilføj summen til listen
            sums[i] = diceThrow[0] + diceThrow[1];
            if (diceThrow[0]==diceThrow[1]){
                ens +=1;
            }
        }

        int[] distribution = new int[11];               //Initialisér et array der skal holde fordelingen
                                                        //Længden er 11, da der er 11 muligheder. 2-12
        for(int i = 0; i < sums.length; i++)
        {
            distribution[sums[i]-2] += 1;               //Optæl distributionen. Indeks er sum[i]-2,
        }                                               //da fx det laveste er 2, som skal tilhøre indeks 0.

        System.out.println("Distribution of sums: ");
        for(int i = 0; i < distribution.length; i++)
        {
            System.out.println("["+(i+2)+"]: " +distribution[i]);
        }
        System.out.println("Så mange slag var ens " + ens);

    }
}
