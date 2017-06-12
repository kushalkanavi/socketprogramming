import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by kushalkanavi on 6/12/17.
 */
public class ChatSendDataThread implements Runnable {
    DataInputStream din;
    String key;

    public ChatSendDataThread(DataInputStream din, String key) {
        this.din = din;
        this.key = key;
    }

    public void run() {
        String msgin = "";
        try {
            while (!(msgin.equals("end"))) {

                msgin = din.readUTF();
                Decryption dc = new Decryption(msgin,key);

                System.out.println("Encrypted Client Message: " + msgin);
                System.out.println("Client Message: " + dc.decrypt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}