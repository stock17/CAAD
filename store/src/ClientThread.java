import java.io.*;
import java.net.Socket;

/**
 * Created by Master on 13.03.2017.
 */
public class ClientThread extends Thread {
    private Socket socket;
    private int id;

    private static int nextId = 0;

    ClientThread (Socket socket) {
        this.socket = socket;
        id = nextId++;

    }

    public String receiveFile (BufferedReader reader){
        StringBuilder context = new StringBuilder();
        String buffer = "";
        try {
            while (!(buffer = reader.readLine()).equals("ENDFILE")) {
                System.out.println("Append " + buffer);
                context.append(buffer.trim());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return context.toString();
    }

    public boolean saveFile(String filename, String context){

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new StringReader(context));
            bw = new BufferedWriter(new FileWriter("./" + filename));
            String buffer = "";

            System.out.println("Saving file:" + filename);

            while ((buffer = br.readLine()) !=  null) {
                bw.write(buffer);
            }

            System.out.println("Saved.");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }

        }
        return true;
    }

    public void run() {
        String buffer;
        BufferedReader br = null;

        try {

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((buffer = br.readLine()) != null) {

                if (buffer.equals("NEWFILE")){
                    System.out.println("Start to receive a new file.");
                    String filename = br.readLine();
                    System.out.println("Receiving file: " + filename);
                    String filecontext = receiveFile(br);
                    System.out.println("Received");
                    System.out.println(filecontext);
                    saveFile(filename, filecontext);
                } else
                    System.out.println(buffer);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }







    }
}
