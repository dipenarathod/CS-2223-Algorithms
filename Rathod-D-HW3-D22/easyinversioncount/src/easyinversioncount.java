import java.util.Scanner;

public class easyinversioncount {
    int[] arr;
    int inversionCount;
    public easyinversioncount(int[] arr){
        this.arr=arr;
        this.inversionCount=0;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int arrLength=getInteger("Enter length of array:",true);
        int arr[]=new int[arrLength];
        for(int i=0;i<arrLength;i++){
            arr[i]=getInteger("Enter "+(i+1)+"th element of array",false);
        }
        easyinversioncount eic=new easyinversioncount(arr);
        System.out.println("Your array:");
        for(int i=0;i<arrLength;i++){
            System.out.println(eic.arr[i]);
        }
        eic.countInversion();
        System.out.println("Inversions="+eic.inversionCount);

    }

    void countInversion(){
        for(int i=0;i<this.arr.length;i++){
            for(int j=i+1;j<this.arr.length;j++){
                if(this.arr[i]>this.arr[j])
                    this.inversionCount++;
            }
        }
    }

    /**
     * get integer
     * @param inputMessage prompt for input
     * @param positiveFlag should input be positive
     * @return input integer
     */
    static int getInteger(String inputMessage,boolean positiveFlag) {
        Scanner sc = new Scanner(System.in);
        int num = -1;
        while (true) {
            try {
                System.out.println(inputMessage);
                num = sc.nextInt();
                if (num <= 0 && positiveFlag) {
                    System.out.println("Enter a positive number");
                    throw new Exception("Negative Number");
                } else
                    break;

            } catch (Exception e) {
                return getInteger(inputMessage, positiveFlag);
            }
        }
        return num;
    }
}
