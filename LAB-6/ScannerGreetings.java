import java.util.Scanner;

public class ScannerGreetings {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = scn.nextLine();
        System.out.print("Please enter your age: ");
        int age = scn.nextInt();

        if (age < 18) {
            System.out.println("Hello, " + name + "! You are " + age + " years old and at a minor age!");
        }
        else {
            System.out.println("Hello, " + name + "! You are " + age + " years old and at a legal age!");
        }
    }
}
