package Impl;

import Api.CaesarDecrypter;
import Utils.CharUtils;

import java.util.HashMap;
import java.util.Map;

public class CaesarVigenereDecrypter {

    private Map<Character, Integer> frequencyMap = new HashMap<>();

    public CaesarVigenereDecrypter() {
        for (Character character : CharUtils.lowerCase) {
            frequencyMap.put(character, 0);
        }
    }

    public void clean() {
        for (Character character : CharUtils.lowerCase) {
            frequencyMap.put(character, 0);
        }
    }

    private String decryptWithKey(String encryptedMessage, char[] keyWord) {
        StringBuilder decryptedMessage = new StringBuilder();
        int alpSize = 26;
        int j = 0;
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char c = encryptedMessage.charAt(i);
            int key = String.valueOf(CharUtils.lowerCase).indexOf(keyWord[j]);
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
            j = (j + 1) % keyWord.length;
        }
        return decryptedMessage.toString();
    }

    public String decrypt(String message, int keySize) {
        char[] charArray = message.toCharArray();
        char[] trw = new char[keySize];
        for (int k = 0; k < keySize; k++) {
            int j = k;
            while (j < charArray.length) {
                if (Character.isLetter(charArray[j])) {
                    char lc = Character.toLowerCase(charArray[j]);
                    frequencyMap.replace(lc, frequencyMap.get(lc) + 1);
                }
                j = j + keySize;
            }
            Map.Entry<Character, Integer> maxEntry = null;
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }
            char frChar = maxEntry.getKey();
            int frCharPos = String.valueOf(CharUtils.lowerCase).indexOf(frChar);
            trw[k] = CharUtils.lowerCase[frCharPos - 4];
            clean();
        }
        return decryptWithKey(message, trw);
    }
}
