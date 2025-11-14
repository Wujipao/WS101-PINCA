import java.util.Arrays;
import java.util.Scanner;

public class StoreArray {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int[] array = new int[5];
        float sum = 0;

        System.out.print("Enter five Numbers: ");
        for (int i=0; i<5; i++){
            int num = scn.nextInt();
            array[i] = num;
            sum += array[i];
        }
        float average = sum / array.length;
        System.out.print("Your numbers: ");
        System.out.println(Arrays.toString(array));

        System.out.println("The sum of your array: " + sum);
        System.out.println("The average of your array: " + average);

    }
}
