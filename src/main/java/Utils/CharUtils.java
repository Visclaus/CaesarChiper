package Utils;

public class CharUtils {

    public static final char[] lowerCase = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static final char[] upperCase = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static boolean isLowerCase(char c) {
        return Character.getType(c) == Character.LOWERCASE_LETTER;
    }

    public static boolean isUpperCase(char c){
        return Character.getType(c) == Character.UPPERCASE_LETTER;
    }
}
