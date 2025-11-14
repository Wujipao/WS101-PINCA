import java.util.Scanner;

public class FibonacciSequence {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the number of of terms you want in Fibonacci Sequence: ");
        int num = scn.nextInt();

        fibonacci(num);
    }
    public static void fibonacci(int n) {
        int firstNum = 0;
        int secondNum = 1;

        System.out.println("Fibonacci Result: ");
        for (int i = 0; i < n; i++) {
            System.out.print(firstNum + " ");
            int nextNum = firstNum + secondNum;
            firstNum = secondNum;
            secondNum = nextNum;
        }
    }
}
