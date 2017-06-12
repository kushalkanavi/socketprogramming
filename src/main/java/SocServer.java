import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kushalkanavi on 6/9/17.
 */
public class SocServer{

    public static void main(String[] args){

        String key = "secretkey";

        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server Connected Waiting for Client Connection");

            while(true) {
                Socket cliSocket = serverSocket.accept();

                SocServerThread serverThread = new SocServerThread(cliSocket,key);
                Thread t = new Thread(serverThread);
                t.start();
            }
        }catch(Exception e){}
    }
}