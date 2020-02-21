import Api.CaesarDecrypter;
import Api.CaesarEncipher;
import Impl.CaesarEncipherImpl;
import Impl.FrequencyCaesarDecrypter;
import Utils.ResourcesUtils;

import java.io.*;
import java.util.Arrays;

public class Application {

    public static void main(String[] args) throws IOException {
        File initialText = ResourcesUtils.resolveResFile("text.txt");
        File encryptedText = ResourcesUtils.resolveResFile("encryptedText.txt");
        File decryptedText = ResourcesUtils.resolveResFile("decryptedText.txt");
        StringBuilder resultBuilder = new StringBuilder();
        char[] readBuf = new char[256];
        if (initialText != null && encryptedText != null && decryptedText != null) {
            try (Reader reader = new BufferedReader(new FileReader(initialText))) {
                int c;
                while ((c = reader.read(readBuf)) > 0) {
                    if (c < 256) {
                        readBuf = Arrays.copyOf(readBuf, c);
                    }
                    resultBuilder.append(readBuf);
                }
            }
            try (Writer writer = new BufferedWriter(new FileWriter(encryptedText));
                 Writer writer1 = new BufferedWriter(new FileWriter(decryptedText))) {
                CaesarEncipher encipher = new CaesarEncipherImpl();
                CaesarDecrypter decrypter = new FrequencyCaesarDecrypter();
                String encrypted = encipher.encrypt(resultBuilder.toString(), 1);
                writer.write(encrypted);
                writer1.write(decrypter.decrypt(encrypted));
            }
        }
    }
}
