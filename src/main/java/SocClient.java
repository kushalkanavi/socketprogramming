import java.io.*;
import java.net.Socket;

/**
 * Created by kushalkanavi on 6/9/17.
 */
public class SocClient{


    public static void main(String[] args) throws IOException {

        String ip = "localhost";
        int port = 9999;
        String key = "secretkey";

        try {
            Socket clientSocket = new Socket(ip,port);

            DataInputStream din = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

            ChatSendDataThread st = new ChatSendDataThread(din,key);
            Thread t = new Thread(st);
            t.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msgin="", msgout;

            while (!(msgin.equals("end"))) {

                msgout = br.readLine();
                Encryption en = new Encryption(msgout,key);
                dout.writeUTF(en.encrypt());
                dout.flush();
            }
            clientSocket.close();
        }catch (Exception e){}
    }
}