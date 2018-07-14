package files;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ReusableMethods {

    public static String getProp(String key) throws IOException {
        Properties prop = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("env.properties");
        prop.load(in);
        return prop.getProperty(key);
    }

    public static Response AddIssue() throws IOException {
        //creating issue
        RestAssured.baseURI = ReusableMethods.getProp("HOST");
        Response res = given().
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionID()).
                body(payLoad.getPostDataCreateIssue()).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201).extract().response();
        return res;
    }
    public static String getSessionID() throws IOException {
        //Create a session
        RestAssured.baseURI = getProp("HOST");
        Response sessionResponse =
                given().header("Content-Type","application/json").
                        body(getProp("POST_body_authentication")).
                        when().
                        post(getProp("POST_res")).
                        then().
                        statusCode(200).
                        extract().response();


        //extracting the session value
        return getValueFromJson(sessionResponse,getProp("Session_id"));
    }
    public static JsonPath responseToJson(String response){
        JsonPath res = new JsonPath(response);
        return res;
    }
   public static String getValueFromJson(Response response, String valueToExtract){
       String respInString = responseToString(response);
       JsonPath respInJson = responseToJson(respInString);
       String value = respInJson.get(valueToExtract);
       System.out.println(valueToExtract+ " is equal to : " + value);
       return value;
   }

   public static String responseToString(Response response){
       String respInString = response.asString();
       System.out.println(respInString);
       return respInString;

   }


}
