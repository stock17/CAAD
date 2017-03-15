import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Yury on 15.03.2017.
 */
public class ServerTerminal extends Thread {

    private CentralStore server;

    ServerTerminal (CentralStore server){
        this.server = server;
    }


    public void run() {
        System.out.println("Terminal start");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = "";

        while (true){

            try {

                command = br.readLine().toUpperCase();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("echo: " + command);
            switch (command){

                case "CLOSE" :  server.stopServer();
                                break;
                case "REPORT":  server.report();
                                break;
            }

        }

    }
}


