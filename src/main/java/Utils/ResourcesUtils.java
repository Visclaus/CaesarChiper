package Utils;

import java.io.File;
import java.net.URL;

public class ResourcesUtils {
    public static File resolveResFile(String fileName) {
        URL url = ResourcesUtils.class.getClassLoader().getResource(fileName);
        if (url == null) {
            return null;
        } else {
            return new File(url.getFile());
        }
    }
}
