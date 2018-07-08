package files;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReusableMethods {
    public static String getProp(String key) throws IOException {
        Properties prop = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("env.properties");
        prop.load(in);
        return prop.getProperty(key);
    }
}
