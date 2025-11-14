import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Products {
    private String name;
    private double price;

    public Products(String name, double price){
        this.name = name;
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
    @Override
    public String toString(){
        return "Product: name = '" + name  + "', price = " + price;
    }
}
