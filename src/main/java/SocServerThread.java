import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by kushalkanavi on 6/9/17.
 */
public class SocServerThread implements Runnable {
    private Socket cliSocket;
    private String keyString;
    SocServer socServer;

    public SocServerThread(Socket cliSocket,String keyString) {
        this.cliSocket = cliSocket;
        this.keyString = keyString;
    }

    public void run() {

        try {
            System.out.println("Client Connected");

            DataInputStream din = new DataInputStream(cliSocket.getInputStream());
            DataOutputStream dout = new DataOutputStream(cliSocket.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msgin = "", msgout;

            while (!(msgin.equals("end"))) {

                    msgin = din.readUTF();
                    String decryptedString = socServer.decrypt(msgin, keyString);
                    System.out.println("Encrypted Client Message: " + msgin);
                    System.out.println("Client Message: " + decryptedString);

                    msgout = br.readLine();
                    String encryptedString = socServer.encrypt(msgout, keyString);
                    dout.writeUTF(encryptedString);
                    dout.flush();
            }
            cliSocket.close();
        } catch (Exception e) {

        }
    }
}