import java.sql.SQLOutput;
import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter a word or a phrase: ");
        String str = scn.nextLine();

        if (isPalindrome(str)){
            System.out.println("Your word is a palindrome!");
        }
        else{
            System.out.println("Your word is NOT a palindrome.");
        }
    }
    public static boolean isPalindrome(String str){
        String lowered = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        StringBuilder sb = new StringBuilder(lowered);
        String reversed = sb.reverse().toString();
        return lowered.equals(reversed);
    }
}
