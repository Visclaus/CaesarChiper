import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyCaesarDecrypter implements CaesarDecrypter {

    private Map<Character, Integer> frequencyMap = new HashMap<>();

    public FrequencyCaesarDecrypter() {
        for (Character character : CharUtils.lowerCase) {
            frequencyMap.put(character, 0);
        }
    }

    private String decryptWithKey(String encryptedMessage, int key) {
        StringBuilder decryptedMessage = new StringBuilder();
        int alpSize = 26;
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char c = encryptedMessage.charAt(i);
            if (CharUtils.isLowerCase(c)) {
                int tmp = (String.valueOf(CharUtils.lowerCase).indexOf(c) - key + alpSize) % alpSize;
                decryptedMessage.append(CharUtils.lowerCase[tmp]);
            } else {
                if (CharUtils.isUpperCase(c)) {
                    int tmp = (String.valueOf(CharUtils.upperCase).indexOf(c) - key + alpSize) % alpSize;
                    decryptedMessage.append(CharUtils.upperCase[tmp]);
                } else {
                    decryptedMessage.append(c);
                }
            }
        }
        return decryptedMessage.toString();
    }

    @Override
    public String decrypt(String message) {
        char[] charArray = message.toCharArray();
        for (char c : charArray) {
            if (Character.isLetter(c)) {
                char lc = Character.toLowerCase(c);
                frequencyMap.replace(lc, frequencyMap.get(lc) + 1);
            }
        }
        Map.Entry<Character, Integer> maxEntry = null;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        char frChar = maxEntry.getKey();
        int frCharPos = String.valueOf(CharUtils.lowerCase).indexOf(frChar);
        int key = frCharPos - 4;
        return decryptWithKey(message, key);
    }
}
