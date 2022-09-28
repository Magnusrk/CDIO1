public class DiceTest {
    public static void main(String[] args)
    {
        double sum1=0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0;
        int testCount = 1000000;                           //Antal kast
        int[] sums = new int[testCount];                //Initialiser et array med ligeså mange elementer som kast
        int ens = 0;

        System.out.println("Så mange slag slået "+ testCount);
        for(int i = 0; i < testCount; i++)
        {
            int[] diceThrow = DiceGame.throwDice();     //Kast terningerne og tilføj summen til listen
            sums[i] = diceThrow[0] + diceThrow[1];
            if(diceThrow[0]==1){
                sum1 = sum1+1;
            }
            if(diceThrow[0]==2){
                sum2 = sum2+1;
            }
            if(diceThrow[0]==3){
                sum3 = sum3+1;
            }
            if(diceThrow[0]==4){
                sum4 = sum4+1;
            }
            if(diceThrow[0]==5){
                sum5 = sum5+1;
            }
            if(diceThrow[0]==6){
                sum6 = sum6+1;
            }
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
        System.out.println();
        System.out.println("Chancen for at slå [1] " + (sum1/(testCount))*100 + " %");
        System.out.println("Chancen for at slå [2] " + (sum2/(testCount))*100 + " %");
        System.out.println("Chancen for at slå [3] " + (sum3/(testCount))*100 + " %");
        System.out.println("Chancen for at slå [4] " + (sum4/(testCount))*100 + " %");
        System.out.println("Chancen for at slå [5] " + (sum5/(testCount))*100 + " %");
        System.out.println("Chancen for at slå [6] " + (sum6/(testCount))*100 + " %");




    }
}
