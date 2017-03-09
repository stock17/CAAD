/**
 * Created by Yury on 03.03.2017.
 */
public class Product {
    public int id, stock;
    public String name, category;
    public float price;

    public Product(int id, String name, String category, float price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String toString(){
        return "";
    }
}
