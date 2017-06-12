import java.io.*;
import java.net.Socket;

/**
 * Created by kushalkanavi on 6/9/17.
 */
public class SocServerThread implements Runnable {

    Socket cliSocket;
    String key;

    public SocServerThread(Socket cliSocket, String key) {
        this.cliSocket = cliSocket;
        this.key = key;
    }

    public void run() {

        System.out.println("Client Connected");

        try {
            DataInputStream din = new DataInputStream(cliSocket.getInputStream());
            DataOutputStream dout = new DataOutputStream(cliSocket.getOutputStream());

            ChatSendDataThread st = new ChatSendDataThread(din,key);
            Thread t = new Thread(st);
            t.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msgin = "", msgout;

            while (!(msgin.equals("end"))) {

                msgout = br.readLine();
                Encryption en = new Encryption(msgout,key);
                dout.writeUTF(en.encrypt());
                dout.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}