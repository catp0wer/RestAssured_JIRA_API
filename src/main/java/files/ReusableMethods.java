package files;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

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

   public static void getValueFromResponse(String response, String valueToExtract){
       JsonPath resInJson = new JsonPath(response);
       String value = resInJson.get(valueToExtract);
       System.out.println(valueToExtract+ " is equal to : " + value);
   }

   public static String responseToString(Response response){
       String respInString = response.asString();
       System.out.println(respInString);
       return respInString;

   }
}
