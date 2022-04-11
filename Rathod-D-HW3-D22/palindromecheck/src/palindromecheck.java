import java.util.Scanner;

public class palindromecheck {
    String input;
    public palindromecheck(String input){
        //this.input=input.replaceAll("[^A-Za-z]+","");//comment next two lines if using this
        this.input=input.replaceAll("\\p{Punct}","");
        this.input=this.input.replaceAll(" ","");
        this.input=this.input.toLowerCase();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter input string:");
        String input=sc.nextLine();
        palindromecheck pc=new palindromecheck(input);
        System.out.println("Modified input string="+pc.input);
        boolean isStringPalindrome=pc.isPalindrome(pc.input);
        if(isStringPalindrome)
            System.out.println(pc.input+" is a palindrome");
        else
            System.out.println(pc.input+" is not a palindrome");
    }
    boolean isPalindrome(String input){
        if(input.length() == 0 || input.length() == 1)
            return true;
        if(input.charAt(0) == input.charAt(input.length()-1))
            return isPalindrome(input.substring(1, input.length()-1));
        return false;
    }
}
