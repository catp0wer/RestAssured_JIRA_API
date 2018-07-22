package files;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ReusableMethods {
    private static String token;

    public ReusableMethods(String username, String password) {
        String pair = username + ":" + password;
        byte[] encodedBytes = Base64.encodeBase64(pair.getBytes());
        token = "Basic "+new String(encodedBytes);

    }
    public static String getProp(String key) throws IOException {
        Properties prop = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("env.properties");
        prop.load(in);
        return prop.getProperty(key);
    }

    public static Response AddIssue() throws IOException {
        RestAssured.baseURI = ReusableMethods.getProp("HOST");
        return given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                body(payLoad.getPostDataCreateIssue()).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201).extract().response();

    }

    public static String getSessionID() throws IOException {
        //Create a session
        RestAssured.baseURI = getProp("HOST");
        Response sessionResponse =
                given().header("Content-Type", "application/json").
                        body(getProp("POST_body_authentication")).
                        when().
                        post(getProp("POST_res")).
                        then().
                        statusCode(200).
                        extract().response();


        //extracting the session value
        return getValueFromJson(sessionResponse, getProp("Session_id"));
    }

    public static JsonPath responseToJson(String response) {
        JsonPath res = new JsonPath(response);
        return res;
    }

    public static String getValueFromJson(Response response, String valueToExtract) {
        String respInString = responseToString(response);
        JsonPath respInJson = responseToJson(respInString);
        String value = respInJson.get(valueToExtract);
        System.out.println(valueToExtract+ " is equal to : " + value);
        return value;
    }

    public static String responseToString(Response response) {
        String respInString = response.asString();
        System.out.println(respInString);
        return respInString;

    }

    public static Response addComment() throws IOException {
        RestAssured.baseURI = ReusableMethods.getProp("HOST");
        Response commentResponse = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionID()).
                body(payLoad.getPostDataAddComment()).
                when().
                post("/rest/api/2/issue/" + ReusableMethods.getValueFromJson(ReusableMethods.AddIssue(), "id") + "/comment").
                then().
                statusCode(201).extract().response();
        return commentResponse;

    }
}
