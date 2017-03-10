import java.io.*;
import java.net.*;

public class InternetContent {
    public static String get(String url) {

        StringBuilder sb = new StringBuilder();
        BufferedReader buff = null;

        try {
            URL myUrl = new URL(url);
            buff = new BufferedReader(new InputStreamReader(myUrl.openStream()));
            String str = new String();

            while ((str = buff.readLine()) != null)
                sb.append(str.trim());



        } catch (IOException ez) {

        } finally {
            if (buff != null)
                try {buff.close();}
                catch (IOException ex) {}

        }


        return sb.toString();
    }
}
