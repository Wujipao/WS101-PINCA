import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreamProduct {
        public static void main(String[] args) {
            List<Products> product = new ArrayList<>();
            product.add(new Products("Nikon D5200", 20000.00));
            product.add(new Products("Sony A6400", 45000.00));
            product.add(new Products("Canon M50", 65000.99));
            product.add(new Products("Canon 600D", 50000.75));
            product.add(new Products("FujiFilm XT-50", 80000.99));
            product.add(new Products("Nikon Z5", 120000.00));

            Scanner scn = new Scanner(System.in);
            int choice;
            System.out.println("Welcome to your Product ArrayList!");

            do {
                System.out.println("\n--Please Select---");
                System.out.println("1. Print All Products");
                System.out.println("2. Filter & Count Expensive Products");
                System.out.println("3. Exit Program");

                if (scn.hasNextInt()){
                    choice = scn.nextInt();
                }else{
                    System.out.println("Invalid Input.");
                    scn.next();
                    choice = 0;
                    continue;
                }
                switch(choice){
                    case 1:
                        System.out.println("Product List: ");
                        System.out.println("Name        | Price");
                        System.out.println("-------------------");
                        for (Products products : product){
                            System.out.println(products);
                        }
                        break;

                    case 2:
                        System.out.print("Enter price threshold: ");
                        double priceThreshold;
                        if (scn.hasNextDouble()){
                            priceThreshold = scn.nextDouble();
                        } else {
                            System.out.println("Invalid Input.");
                            scn.next();
                            break;
                        }

                        long expensiveProductCount = product.stream().filter(p -> p.getPrice() > priceThreshold).count();
                        System.out.printf("Price Threshold: $%.2f", priceThreshold);
                        System.out.printf("\nTotal number of products found above threshold:  %d \n", expensiveProductCount);
                        break;

                    case 3:
                        System.out.println("Exiting Program.");
                        break;

                    default:
                        System.out.println("Error Input.");
                }
            } while (choice != 3);
        }
    }
