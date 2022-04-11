import java.util.Scanner;

public class fastinversioncount {
    int[] arr;
    int inversionCount;
    public fastinversioncount(int[] arr){
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
        fastinversioncount eic=new fastinversioncount(arr);
        System.out.println("Your array:");
        for(int i=0;i<arrLength;i++){
            System.out.println(eic.arr[i]);
        }
        eic.MergeSortArray(eic.arr);
        System.out.println("Inversions="+eic.inversionCount);

    }

    void MergeSortedArrays(int arr1[],int arr2[],int arr3[]){
        int i=0,j=0,k=0;
        while(i<arr1.length && j<arr2.length){
            if(arr1[i]<=arr2[j]){
                arr3[k++]=arr1[i++];
            }
            else{
                arr3[k++]=arr2[j++];
                this.inversionCount+=arr1.length-i;
            }
        }
        while(j<arr2.length) {
            arr3[k++] = arr2[j++];
        }
        while(i<arr1.length) {
            arr3[k++] = arr1[i++];
        }
    }

    void MergeSortArray(int arr[]){
        if(arr.length>1){
            int middleIndex=arr.length/2;
            int arr2[]=new int[middleIndex];
            int arr3[]=new int[arr.length-middleIndex];
            for(int i=0;i<middleIndex;i++)
                arr2[i]=arr[i];
            for(int i=middleIndex;i<arr.length;i++)
                arr3[i-middleIndex]=arr[i];
            MergeSortArray(arr2);
            MergeSortArray(arr3);
            MergeSortedArrays(arr2,arr3,arr);
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
