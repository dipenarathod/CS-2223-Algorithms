import java.util.ArrayList;
import java.util.Scanner;

public class BinaryReflectedGrayCodes {
    int lengthOfBRGC;
    ArrayList<String> BRGC;
    String names[];
    public BinaryReflectedGrayCodes(int lengthOfBRGC){
        this.lengthOfBRGC=lengthOfBRGC;
        this.BRGC=new ArrayList<>();
        //this.names= new String[]{"Axel", "Boxo", "Crunchy", "Doofus", "Enzo","Fitz","Giggles"};
        this.names= new String[]{"Giggles", "Fitz", "Enzo", "Doofus", "Crunchy","Boxo","Axel"};
    }
    public static void main(String[] args){
        BinaryReflectedGrayCodes brgc=new BinaryReflectedGrayCodes(7);
        brgc.BRGC=brgc.generateBRGC(brgc.lengthOfBRGC);
        //brgc.displayBRGC();
        System.out.println("Order of characters coming on the tricycle:");
        for(int i=brgc.names.length-1;i>=0;i--){
            System.out.print(brgc.names[i]+" ");
        }
        System.out.println();
        System.out.println("Index|Gray code|Klutzomaniacs Riding|Action");
        for(int i=0;i<brgc.BRGC.size();i++){
            System.out.print(i+"|");
            System.out.print(brgc.BRGC.get(i)+"|");
            if(i==0){
                System.out.print("Empty Tricycle|");
                System.out.print("Spotlight");
            }
            else{
                String prevGrayCode=brgc.BRGC.get(i-1);
                String currGrayCode=brgc.BRGC.get(i);
                String peopleOnStage="";
                for(int j=0;j<currGrayCode.length();j++){
                    if(currGrayCode.charAt(j)=='1')
                        peopleOnStage+=brgc.names[j]+" ";
                }
                peopleOnStage=peopleOnStage.trim();
                System.out.print(peopleOnStage+"|");
                for(int j=0;j<currGrayCode.length();j++){
                    if(prevGrayCode.charAt(j)!=currGrayCode.charAt(j)){
                        if(currGrayCode.charAt(j)=='1'){
                            System.out.print(brgc.names[j]+" enters");
                        }
                        else{
                            System.out.print(brgc.names[j]+" leaves");
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    public ArrayList<String> generateBRGC(int n){
        ArrayList<String> BRGC=new ArrayList<>();
        if(n==1){
            BRGC.add("0");
            BRGC.add("1");
            return BRGC;
        }
        else{
            ArrayList<String> L1=new ArrayList<>();
            L1=generateBRGC(n-1);
            ArrayList<String> L2=new ArrayList<>();
            for(int i=L1.size()-1;i>=0;i--){
                L2.add(L1.get(i));
            }
            for(int i=0;i<L1.size();i++){
                L1.set(i,"0"+L1.get(i));
            }
            for(int i=0;i<L2.size();i++){
                L2.set(i,"1"+L2.get(i));
            }
            L1.addAll(L2);
            return L1;
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

    public void displayBRGC(){
        for(String code:this.BRGC){
            System.out.println(code);
        }
    }
}
