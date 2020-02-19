

public class CaesarEncipherImpl implements CaesarEncipher {

    @Override
    public String encrypt(String inputMessage, int key) {
        StringBuilder outputMessage = new StringBuilder();
        int alpSize = 26;
        for (int i = 0; i < inputMessage.length(); i++) {
            char c = inputMessage.charAt(i);
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
        }
        return outputMessage.toString();
    }
}
