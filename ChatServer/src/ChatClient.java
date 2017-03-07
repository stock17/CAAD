import java.io.*;
import java.net.*;

public class ChatClient {
    private String hostName;
    private int portNumber;
    private Socket clientSocket;

    public ChatClient(String hostName,int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        try {
            clientSocket = new Socket(hostName,portNumber);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch(IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName + ":" + portNumber + ". Check that the server is running.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            //start Receiver
            ChatReceiver cr = new ChatReceiver(in);
            cr.start();


            String userInput = null;
            while((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
            }
        } catch(IOException e) {
            System.err.println("I/O Exception");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String args[]) {
        String hostName = "192.168.1.7"; // Set IP Address manually
        int port = 2222;                 // Set port manually
        ChatClient chatClient = new ChatClient(hostName,port);
        chatClient.start();



    }
}



