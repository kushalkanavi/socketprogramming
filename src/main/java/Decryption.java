import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;

/**
 * Created by kushalkanavi on 6/12/17.
 */
public class Decryption {
    String name;
    String keyString;

    public Decryption(String name, String keyString) {
        this.name = name;
        this.keyString = keyString;
    }

    public String decrypt() throws Exception {
        BlowfishEngine engine = new BlowfishEngine();
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(engine);
        StringBuffer result = new StringBuffer();
        KeyParameter key = new KeyParameter(keyString.getBytes());
        cipher.init(false, key);
        byte out[] = Base64.decode(name);
        byte out2[] = new byte[cipher.getOutputSize(out.length)];
        int len2 = cipher.processBytes(out, 0, out.length, out2, 0);
        cipher.doFinal(out2, len2);
        String s2 = new String(out2);
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            if (c != 0) {
                result.append(c);
            }
        }
        return result.toString();
    }
}
