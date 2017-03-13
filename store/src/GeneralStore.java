import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GeneralStore  {

    private Socket socket;
    private PrintWriter outWriter;
    private String foldername = "./src/files/";


    //public GeneralStore() {
    //    folder = new String ("./src/files/");
    //}

    public void listFiles(){
        File folder = new File (foldername);
        String[] files = folder.list();
        System.out.println("Files to sending:");
        for (String file : files) {
            System.out.println(file);
        }
    }

    public boolean isConnected () {
        if (socket == null || !socket.isConnected()) return false;
        else return true;
    }

    public boolean connect (String address) throws IOException
    {
        String[] params = address.split(":");
        if (params.length < 2) return false;
        String host; int port;
        host = params[0];
        port = Integer.parseInt(params[1]);

        try {
            socket = new Socket (host, port);

            outWriter = new PrintWriter(socket.getOutputStream(),true);

            return true;

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            e.printStackTrace();
            return false;
        } catch(IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host + ":" + port + ". Check that the server is running.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendFile (String filename){
        if (!isConnected()) {
            System.out.println("Not connected");
            return false;
        }

        if (filename == null || filename.equals("")) {
            System.out.println("Bad filename");
            return false;
        }

        StringBuilder localPath = new StringBuilder(foldername);
        localPath.append(filename);
        File file = new File(localPath.toString());

        if (!file.exists()) {
            System.out.println("Bad filename");
            return false;
        }

                // Sending...
        BufferedReader br = null;
        outWriter.println("NEWFILE");
        outWriter.println(filename);

        try {
            br = new BufferedReader(new FileReader(file));
            String buffer = "";
            while ((buffer = br.readLine()) != null) {
                outWriter.println(buffer);
            }
            outWriter.println("ENDFILE");
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try { br.close(); } catch (IOException ex) {ex.printStackTrace();}
        }


        return true;
    }



    public void start ()throws IOException {

        // for testing
        connect ("192.168.1.2:1024");
        ////////////////

        String cmdString = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while ((cmdString = br.readLine()) != null){
            String[] cmds = cmdString.split(" ");
            String cmd = "";
            String params = "";

            cmd = cmds[0].toUpperCase();
            if (cmds.length > 1) params = cmds [1];

            switch (cmd) {

                case "TEST" :   System.out.println("System is working");
                                if (socket == null || !socket.isConnected()) System.out.println("Not connected");
                                else System.out.println("Connected to Server. IP:" + socket.getInetAddress() + " Port: " + socket.getPort());
                                break;

                case "STOP":    System.out.println("System is terminated");
                                return;

                case "LIST":    listFiles();
                                break;

                case "CONNECT": if (connect(params)) System.out.println("Successful connection");
                                else System.out.println("Error connection");
                                break;

                case "MESSAGE": if (socket == null || !socket.isConnected()) System.out.println("Not connected");
                                else { outWriter.println(params); }
                                break;

                case "SEND":    if (sendFile(params))
                                    System.out.println("Successful sending.");
                                else
                                    System.out.println("Error sending");
                                break;

                default:        System.out.println("Bad command!");
                                break;
            }

        }

    }

    public static void main (String[] args)throws IOException {

        GeneralStore gs = new GeneralStore();
        gs.start();
    }
}