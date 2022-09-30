

public class DiceTest {
    public static void main(String[] args) {

        //Variabler til at holde styr på, hvilke tal de enkelte terninger har slået.
        double sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0;
        int testCount = 1000000;                           //Antal kast
        int[] sums = new int[testCount];                   //Initialiser et array med ligeså mange elementer som kast

        for (int i = 0; i < testCount; i++) {
            int[] diceThrow = DiceGame.throwDice();     //Kast terningerne og tilføj summen til listen
            sums[i] = diceThrow[0] + diceThrow[1];
            switch (diceThrow[0]) {
                case 1 -> sum1 += 1;
                case 2 -> sum2 += 1;
                case 3 -> sum3 += 1;
                case 4 -> sum4 += 1;
                case 5 -> sum5 += 1;
                case 6 -> sum6 += 1;
            }

        }
        System.out.println("Så mange slag slået " + testCount + "\n");

        int[] distribution = new int[11];               //Initialisér et array der skal holde fordelingen
                                                        //Længden er 11, da der er 11 muligheder. 2-12
        for (int sum : sums) {
            distribution[sum - 2] += 1;             //Optæl distributionen. Indeks er sum[i]-2,
        }                                               //da fx det laveste er 2, som skal tilhøre indeks 0.

        System.out.println("Distribution of sums: ");

        for (int i = 0; i < distribution.length; i++) {
            System.out.println("[" + (i + 2) + "]: " + distribution[i]);
        }

        //Beregning af den gennemsnitlige sum af de 2 terninger.
        double average = 0;
        for (int a = 0; a < 11; a++) {
            average += (a + 2) * distribution[a];
        }
        average = average / testCount;
        System.out.println("Gennemsnittet " + average + "\n");

        //Beregning af sandsynligheden for at slå de enkelte kast
        System.out.println("Chancen for at slå [1] " + (sum1 / (testCount)) * 100 + " %");
        System.out.println("Chancen for at slå [2] " + (sum2 / (testCount)) * 100 + " %");
        System.out.println("Chancen for at slå [3] " + (sum3 / (testCount)) * 100 + " %");
        System.out.println("Chancen for at slå [4] " + (sum4 / (testCount)) * 100 + " %");
        System.out.println("Chancen for at slå [5] " + (sum5 / (testCount)) * 100 + " %");
        System.out.println("Chancen for at slå [6] " + (sum6 / (testCount)) * 100 + " %");
    }
}
