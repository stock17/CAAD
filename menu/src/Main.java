/**
 * Created by Yury on 27.02.2017.
 */

import sun.security.krb5.internal.crypto.Des;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String dishfile = "src\\main_dish.txt";
        String drinkfile = "src\\drinks.txt";
        String desfile = "src\\dessert.txt";

        // read maindishes;
        List<MainDish> maindishes = new ArrayList<>();
        Scanner scan = null;
        try {
            scan = new Scanner(new BufferedReader(new FileReader(dishfile)));

            while (scan.hasNextLine()){
                String name = scan.nextLine();
                String mainmeal = scan.nextLine();
                String acc = scan.nextLine();
                double price = Double.parseDouble(scan.nextLine().substring(1));
                maindishes.add(new MainDish(name, price, mainmeal, acc));
                if (scan.hasNextLine())
                    scan.nextLine();
            }

        } finally { if (scan != null) scan.close(); }




        // read drinks;
        List<Drink> drinks = new ArrayList<>();
        try {
            scan = new Scanner(new BufferedReader(new FileReader(drinkfile)));

            while (scan.hasNextLine()){
                String name = scan.nextLine();
                double price = Double.parseDouble(scan.nextLine().substring(1));
                drinks.add(new Drink(name, price));
                if (scan.hasNextLine())
                    scan.nextLine();
            }

        } finally {
            if (scan != null) scan.close();

        }



        // 3.  Read dessert;
        List<Dessert> desserts = new ArrayList<>();

        try {
            scan = new Scanner(new BufferedReader(new FileReader(desfile)));

            while (scan.hasNextLine()){
                String name = scan.nextLine();
                String description = scan.nextLine();
                double price = Double.parseDouble(scan.nextLine().substring(1));
                desserts.add(new Dessert(name, price, description));
                if (scan.hasNextLine())
                    scan.nextLine();
            }

        } finally {  if (scan != null) scan.close(); }

        // Print out Menu

        System.out.println("Menu");
        System.out.println("\tMain Dishes");
        maindishes.forEach(System.out::println);
        System.out.println("\tDrinks");
        drinks.forEach(System.out::println);
        System.out.println("\tDessert");
        desserts.forEach(System.out::println);

        System.out.println("\n\nCombos with Special Discount:\n");

        for (int i = 0; i < 20; i++){
            Random generator = new Random();
            MainDish md = maindishes.get(generator.nextInt(maindishes.size()));
            Drink dr = drinks.get(generator.nextInt(drinks.size()));
            Dessert ds = desserts.get(generator.nextInt(desserts.size()));
            Combo cmb = new Combo(md, dr, ds);
            System.out.println(cmb);
        }



    }
}



abstract class Food {
    private String name;
    public String getName() { return this.name; }
    private double price;
    public double getPrice() { return this.price; }
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract String toString();
}

class MainDish extends Food {
    private String mainmeal, accompaniments;
    public MainDish(String name, double price, String mainmeal, String acc) {
        super(name, price);
        this.mainmeal = mainmeal;
        this.accompaniments = acc;
    }

    public String toString(){
        return "\t\t" + super.getName() + "\n\t\t\t" + this.mainmeal +
                "\n\t\t\t" + this.accompaniments + "\n\t\t\t$" + super.getPrice();
    };
}

class Drink extends Food {
    public Drink(String name, double price) {
        super(name, price);
    }

    public String toString(){
        return "\t\t" + super.getName() + "\n\t\t\t$"
                + super.getPrice();
    };
}

class Dessert extends Food {
    private String description;
    public Dessert(String name, double price, String description ) {
        super(name, price);
        this.description = description;
    }

    public String toString(){
        return "\t\t" + super.getName() + "\n\t\t\t" + this.description +
                "\n\t\t\t$" + super.getPrice();
    }
}


class Combo {
    //Combine Main Dish, Drink, Dessert
    private double ComboPrice;

    private MainDish md;
    private Drink dr;
    private Dessert ds;

    public Combo( MainDish md, Drink dr, Dessert ds) {
        this.md = md;
        this.dr = dr;
        this.ds = ds;
    }

    private double calculateComboPrice(){

        double price = md.getPrice() + dr.getPrice() + ds.getPrice();
        double discount = calculateComboDiscount();
        double endprice = price - discount;
        return Math.round(endprice * 100) / 100.00;

    }

    private double calculateComboDiscount(){
        Random generator = new Random();
        return generator.nextDouble() * 5;

    }

    public String toString() {

        return "\t" + md.getName() + " + " + dr.getName() + " + " + ds.getName() + "\n\t\t$" + calculateComboPrice();
    }
}
