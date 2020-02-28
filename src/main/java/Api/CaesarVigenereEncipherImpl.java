package Api;

import Api.CaesarEncipher;
import Utils.CharUtils;

public class CaesarVigenereEncipherImpl {

    public String encrypt(String inputMessage, char[] keyWord) {
        StringBuilder outputMessage = new StringBuilder();
        int alpSize = 26;
        int j = 0;
        for (int i = 0; i < inputMessage.length(); i++) {
            char c = inputMessage.charAt(i);
            int key = String.valueOf(CharUtils.lowerCase).indexOf(keyWord[j]);
            if (CharUtils.isLowerCase(c)) {
                int tmp = (String.valueOf(CharUtils.lowerCase).indexOf(c) + key) % alpSize;
                outputMessage.append(CharUtils.lowerCase[tmp]);
            } else {
                if (CharUtils.isUpperCase(c)) {
                    int tmp = (String.valueOf(CharUtils.upperCase).indexOf(c) + key) % alpSize;
                    outputMessage.append(CharUtils.upperCase[tmp]);
                } else {
                    outputMessage.append(c);
                }
            }
            j = (j + 1) % keyWord.length;
        }
        return outputMessage.toString();
    }
}
