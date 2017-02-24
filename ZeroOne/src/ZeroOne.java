/**
 * Created by Yury on 17.02.2017.
 */
public class ZeroOne {

    public static double x = 0.2;
    public static double y = 0.7;


    public static  void main(String[] args){
        if ((x > 0 && x < 1) && (y > 0 && y < 1))
            System.out.println("True");
        else
            System.out.println("False");
    }
}
