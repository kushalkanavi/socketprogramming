import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;

/**
 * Created by kushalkanavi on 6/12/17.
 */
public class Encryption {

    String value;
    String keyString;

    public Encryption(String value, String keyString) {
        this.value = value;
        this.keyString = keyString;
    }

    public String encrypt() throws Exception {
        BlowfishEngine engine = new BlowfishEngine();
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(engine);
        KeyParameter key = new KeyParameter(keyString.getBytes());
        cipher.init(true, key);
        byte in[] = value.getBytes();
        byte out[] = new byte[cipher.getOutputSize(in.length)];
        int len1 = cipher.processBytes(in, 0, in.length, out, 0);
        try {
            cipher.doFinal(out, len1);
        } catch (CryptoException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        String s = new String(Base64.encode(out));
        return s;
    }
}
