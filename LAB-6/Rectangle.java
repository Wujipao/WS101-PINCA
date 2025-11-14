import java.util.Scanner;

public class Rectangle {
    public static void main(String[] args){
        float length, width;
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter length: ");
        length = scn.nextFloat();
        System.out.print("Enter width: ");
        width = scn.nextFloat();
        float area = calculateArea(length,width);

        System.out.println("The area of the rectangle is: " + area);
    }
    public static float calculateArea(float length, float width){
        return length * width;
    }
}
