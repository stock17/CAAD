import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

import java.io.*;

public class FunctionalInterfaces {
    List<Product> technologystore;

    //Read from file products.txt the list of products
    //Format: Name Categorie Stock Price
    public void readProductsFile() {
        String filePath = "src\\products.txt";

        //Create the Product List
        // Classic approach
        BufferedReader br = null;
        this.technologystore = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(filePath));
            String str = "";
            while ((str = br.readLine()) != null){
                int id = 0;
                String[] words = str.split(" ");
                technologystore.add (new Product(id++, words[0], words[1], Float.parseFloat(words[3]), Integer.parseInt(words[2])));
            }
        } catch (IOException ex) { ex.printStackTrace();
        } finally {
            try { if (br != null) br.close(); }
            catch (IOException ex) { ex.printStackTrace();}
        }


        /*try (Stream <String> lines = Files.lines (filePath, StandardCharsets.UTF_8)) {
                lines.onClose()
                .forEach( s -> {
                    s.split


                })
            }

        */


    }

    //Print each of the products
    public void printProducts() {
        System.out.println( technologystore
                .stream()
                .map(s -> s.name)
                .collect(Collectors.joining(" "))
        );
    }

    //Filter and print from the list all the products that are Accessories
    public void filter2() {
        System.out.println(technologystore
                .stream()
                .filter(s -> s.category.equals("Accessories"))
                .map(s -> s.name)
                .collect(Collectors.joining(" "))
        );
    }

    //Filter and print from the list all the products that the stock is more or equal to 50
    public void filter3() {
        System.out.println (technologystore
                .stream()
                .filter(s -> s.stock >= 50)
                .map(s -> s.name)
                .collect(Collectors.joining(" "))
        );
    }

    //Filter and print from the list all the products that: are cheaper than $150.00 and has more than 25 in stock
    public void filter4() {
        System.out.println (technologystore
                .stream()
                .filter(s -> s.price < 150 && s.stock > 25)
                .map(s -> s.name)
                .collect(Collectors.joining(" "))
        );
    }

    public void filter5() {
        //Remove from the list (don't print) if product: the categorie is Office
        //Your Code here
        System.out.println (technologystore.stream()
                .filter(s -> !s.category.equals("Office"))

                //Remove from the list (don't print) if product: has greater than 30 in stock
                //Your Code here
                .filter(s -> s.stock < 30)

                //Remove from the list (don't print) if product: are cheaper than $250.00
                //Your Code here
                .filter(s -> s.price > 250)

                //Print each of the products that left
                //Your Code here
                .map(s -> s.name)
                .collect(Collectors.joining(" "))
        );
    }


    public static void main(String[] args) throws Exception {
        FunctionalInterfaces activity4 = new FunctionalInterfaces();
        activity4.readProductsFile();
        System.out.println("----------Filter 1---------");
        activity4.printProducts();
        System.out.println("----------Filter 2---------");
        activity4.filter2();
        System.out.println("----------Filter 3---------");
        activity4.filter3();
        System.out.println("----------Filter 4---------");
        activity4.filter4();
        System.out.println("----------Filter 5---------");
        activity4.filter5();


        /* ANSWER

        ----------Filter 1---------
        Laptop-12GB_RAM-HDD_1T Printer Headphones HDMI-VGA-Adapter Laptop-16GB_RAM-SSD_750GB Scanner Computer-Keyboard AUX_5ft Ink-Cartridge Mouse-Wireless Laptop-8GB_RAM-HDD_500GB USB-microUSB
        ----------Filter 2---------
        Headphones Computer-Keyboard Mouse-Wireless
        ----------Filter 3---------
        Printer Headphones AUX_5ft Ink-Cartridge Mouse-Wireless USB-microUSB
        ----------Filter 4---------
        Headphones Computer-Keyboard AUX_5ft Ink-Cartridge Mouse-Wireless USB-microUSB
        ----------Filter 5---------
        Laptop-12GB_RAM-HDD_1T Laptop-16GB_RAM-SSD_750GB Laptop-8GB_RAM-HDD_500GB
        */
    }
}