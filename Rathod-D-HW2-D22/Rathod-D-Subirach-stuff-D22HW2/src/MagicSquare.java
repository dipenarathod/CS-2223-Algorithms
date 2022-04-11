import java.util.LinkedList;

public class MagicSquare {
    LinkedList<LinkedList<Integer>> powerSet;
    int MagicSquare[];

    public MagicSquare(){
        powerSet=new LinkedList<>();
        MagicSquare=new int[]{ 1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15 };
    }

    public static void main(String[] args){
        MagicSquare square=new MagicSquare();
        System.out.println("Subirach's square");
        square.displaySquare();
        square.allocatePowerSet();
        int sumOfMaxCombinations=0;
        int numberOfMaxCombinations=0;
        System.out.println("Count of subsets of length 4 whose sum is 33="+square.Length4SetsSum33());
        System.out.println();
        for(int i=0;i<16;i++){
            System.out.println("Count of subsets of length "+(i+1)+" whose sum is 33="+square.LengthNSetsSumM(i+1,33));
        }
        for(int j=0;j<=132;j++) {
            int subsetSum=j;
            int numberOfCombinations=square.SetsSumM(j);
            if(numberOfCombinations>numberOfMaxCombinations){
                sumOfMaxCombinations=subsetSum;
                numberOfMaxCombinations=numberOfCombinations;
            }
            System.out.println("Count of subsets whose sum is " + j + "=" + square.SetsSumM(j));
        }
        System.out.println("The sum "+sumOfMaxCombinations+" can be formed by the greatest number of combinations="+numberOfMaxCombinations);
        System.out.println("This is interesting because the number of combinations is symmetric about 66. \n" +
                "Number of combinations that sum to 0 = Number of combinations that sum to 132 = 1 and so on. \n" +
                "66 is also 132/2. For additional info, look at the text file.");
    }

    /**
     * Display the square
     */
    void displaySquare(){
        for(int i=0;i<16;i++){
            System.out.print(this.MagicSquare[i]+" ");
            if((i+1)%4==0)
                System.out.println();
        }
    }

    /**
     * Allocate the power set
     */
    void allocatePowerSet(){
        long powerSetSize= (long) Math.pow(2,16);
        for(long i=0;i<powerSetSize;i++){
            LinkedList<Integer> subSet=new LinkedList<>();
            for(int j=0;j<16;j++){
                if((i & (1<<j)) > 0){
                    subSet.add(this.MagicSquare[j]);
                }
            }
            this.powerSet.add(subSet);
        }
    }

    /**
     * count subsets of length 4 whose sum=33
     * @return count of subsets of length 4 whose sum is 33
     */
    int Length4SetsSum33(){
        int count=0;
        for(LinkedList<Integer> subSet:this.powerSet){
            int subSetSum=0;
            if(subSet.size()==4){
                for(int element:subSet){
                    subSetSum+=element;
                }
                if(subSetSum==33)
                    count++;
            }
        }
        return count;
    }

    /**
     * count subsets of length N whose sum=M
     * @return count subsets of length N whose sum=M
     */
    int LengthNSetsSumM(int N,int M){
        int count=0;
        for(LinkedList<Integer> subSet:this.powerSet){
            int subSetSum=0;
            if(subSet.size()==N){
                for(int element:subSet){
                    subSetSum+=element;
                }
                if(subSetSum==M)
                    count++;
            }
        }
        return count;
    }

    /**
     * count subsets whose sum=M
     * @return count subsets whose sum=M
     */
    int SetsSumM(int M){
        int count=0;
        for(LinkedList<Integer> subSet:this.powerSet) {
            int subSetSum = 0;
            for (int element : subSet) {
                subSetSum += element;
            }
            if (subSetSum == M)
                count++;
        }
        return count;
    }

}
