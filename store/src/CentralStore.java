import java.net.*;
import java.io.*;
import java.util.*;


public class CentralStore{

    ServerSocket serverSocket;
    List<ClientThread> listOfClients;



    public void start (int port) throws IOException {

        serverSocket = new ServerSocket(port);
        listOfClients = new ArrayList<>();


        try {
            System.out.println("IP: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Port: " + port);
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException: " + e);
        }

        Socket socket = null;
        System.out.println("Waiting for clients to connect...");

        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }

            ClientThread client = new ClientThread(socket);
            client.start();
            listOfClients.add(client);
            client = null;
        }
    }

    public static void main(String args[]) throws IOException, InterruptedException{

       CentralStore cs = new CentralStore();
       cs.start(1024);




    }

}