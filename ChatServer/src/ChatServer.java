import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {
    public int port;
    private int IDs;
    private List<ClientThread> clients;
    private ServerSocket serverSocket = null;

    public ChatServer(int port) {
        this.port = port;
        IDs = 1;
        clients = new ArrayList<ClientThread>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Socket socket = null;
        ClientThread newClient = null;
        System.out.println("Waiting for clients to connect...");
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            newClient = new ClientThread(socket, this, IDs++);
            newClient.start();
            clients.add(newClient);
            newClient = null;
        }
    }

    public void sendAll(String message){
        for (ClientThread client: clients){
            client.out.println(message);
            client.out.flush();
        }
    }

    public void removeClient(ClientThread clientClosed) {
        clients.remove(clientClosed);
        System.out.println("");
    }

    public static void main(String args[]) {
        try {
            System.out.println("Server IP: " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException: " + e);
        }
        int port = 2222;
        ChatServer chatServer = new ChatServer(port);
        chatServer.start();

    }


}