import java.io.*;
import java.util.Arrays;

public class Application {

    public static void main(String[] args) throws IOException {
        File text = new File(Application.class.getClassLoader().getResource("text.txt").getFile());
        File encryptedText = new File(Application.class.getClassLoader().getResource("encryptedText.txt").getFile());
        File decryptedText = new File(Application.class.getClassLoader().getResource("decryptedText.txt").getFile());

        StringBuilder builder = new StringBuilder();
        char[] rBuf = new char[256];
        char[] wBuf = new char[256];
        try (Reader reader = new BufferedReader(new FileReader(text))) {
            int c;
            while ((c = reader.read(rBuf)) > 0) {
                if (c < 256) {
                    rBuf = Arrays.copyOf(rBuf, c);
                }
                builder.append(rBuf);
            }
        }
        try (Writer writer = new BufferedWriter(new FileWriter(encryptedText)); Writer writer1 = new BufferedWriter(new FileWriter(decryptedText))) {
            CaesarEncipher encipher = new CaesarEncipherImpl();
            CaesarDecrypter decrypter = new FrequencyCaesarDecrypter();
            String encrypted = encipher.encrypt(builder.toString(), 1);
            writer.write(encrypted);
            writer1.write(decrypter.decrypt(encrypted));
        }
    }
}
