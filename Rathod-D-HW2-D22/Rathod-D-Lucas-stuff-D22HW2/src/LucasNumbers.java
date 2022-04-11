import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class LucasNumbers {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        LucasNumbers obj=new LucasNumbers();
        boolean preferLucas=false;
        while(true) {
            System.out.println("If you want to see Lucas numbers, enter Y, else N for my custom sequence.");
            char c = sc.next().charAt(0);
            if (c == 'Y') {
                preferLucas = true;
                break;
            } else if (c == 'N') {
                preferLucas = false;
                break;
            }
            else
                System.out.println("You entered:" + c + ". Please enter Y or N");
        }
        if(preferLucas) {
            int numberOfLucasNumbers = obj.getNumberOfNumbers("Lucas");
            long LucasNumbers[] = new long[numberOfLucasNumbers + 1];
            double timeResults[] = new double[numberOfLucasNumbers + 1];
            for (int i = 0; i <= numberOfLucasNumbers; i++) {
                long start = System.nanoTime();
                LucasNumbers[i] = obj.LucasNumber(i);
                long end = System.nanoTime();
                timeResults[i] = (double) (end - start) / 1000000000.0;
                System.out.println(i + "th Lucas number=" + LucasNumbers[i]);
                System.out.println("Time required to calculate " + LucasNumbers[i] + "=" + timeResults[i]);
                if (i > 0) {
                    double numberRatio = (double) LucasNumbers[i] / LucasNumbers[i - 1];
                    System.out.println("Ratio of " + LucasNumbers[i] + " and " + LucasNumbers[i - 1] + "=" + numberRatio);
                    double timeRatio = timeResults[i] / timeResults[i - 1];
                    System.out.println("Time ratio in calculating " + LucasNumbers[i] + " and " + LucasNumbers[i - 1] + "=" + timeRatio);
                }
            }
        }
        else{
            int numberOfMyNumbers = obj.getNumberOfNumbers("My");
            long MyNumbers[] = new long[numberOfMyNumbers + 1];
            double timeResults[] = new double[numberOfMyNumbers + 1];
            for (int i = 0; i <= numberOfMyNumbers; i++) {
                long start = System.nanoTime();
                MyNumbers[i] = obj.MyNumbers(i);
                long end = System.nanoTime();
                timeResults[i] = (double) (end - start) / 1000000000.0;
                System.out.println(i + "th my number=" + MyNumbers[i]);
                System.out.println("Time required to calculate " + MyNumbers[i] + "=" + timeResults[i]);
                if (i > 0) {
                    double numberRatio = (double) MyNumbers[i] / MyNumbers[i-1];
                    System.out.println("Ratio of " + MyNumbers[i] + " and " + MyNumbers[i-1] + "=" + numberRatio);
                    double timeRatio = timeResults[i] / timeResults[i - 1];
                    System.out.println("Time ratio in calculating " + MyNumbers[i] + " and " + MyNumbers[i - 1] + "=" + timeRatio);
                }
            }
        }
        System.out.println("For additional info look at the text file");

    }

    /**
     * LucasNumber
     * @return the nth Lucas Number using naive recursion
     */
    long LucasNumber(long n){
        if(n==0)
            return 2;
        else if(n==1)
            return 1;
        else
            return LucasNumber(n-1) + LucasNumber(n-2);
    }

    /**
     * getNumberOfLucasNumbers()
     * @return number of lucas numbers to be generated
     */
    int getNumberOfNumbers(String type){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of "+type+" numbers to be generated(should be greater than or equal to 0)");
        int numberOfNumbers=0;
        try{
            numberOfNumbers=sc.nextInt();
            if(numberOfNumbers<0){
                System.out.println("Entered number should be greater than or equal to 0");
                throw new Exception("0 or negative number");
            }
        } catch (Exception e) {
            return getNumberOfNumbers(type);
        }
        return numberOfNumbers;
    }

    /**
     * MyNumbers
     * @return the nth number in my number sequence using naive recursion
     */
    long MyNumbers(long n){
        if(n==0)
            return 3;
        else if(n==1)
            return 2;
        else
            return MyNumbers(n-1) + MyNumbers(n-2);
    }

}
