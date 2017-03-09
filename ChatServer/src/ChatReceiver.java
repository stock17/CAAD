import java.io.BufferedReader;
import java.io.IOException;

public class ChatReceiver extends Thread {

    private BufferedReader inStream;

    ChatReceiver(BufferedReader inStream) {

        this.inStream =inStream;
    }

    public void run() {

        //Read the welcome message from the server
        try {
            System.out.println(inStream.readLine());
            String input ="";
            while((input = inStream.readLine()) != null) {
                System.out.println(input);
            }
        }
        catch (IOException ex) {}



    }
}