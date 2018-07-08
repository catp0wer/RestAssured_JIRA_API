import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import files.ReusableMethods;
import java.io.IOException;
import static io.restassured.RestAssured.given;


public class base {
    @Test
    public void JiraAPI() throws IOException {

        //Create a session
        RestAssured.baseURI = ReusableMethods.getProp("HOST");
        Response sessionResponse =
                given().header("Content-Type","application/json").
                body("{\"username\": \"XXX\", \"password\": \"YYY\"}").
                when().
                post("/rest/auth/1/session").
                then().
                statusCode(200).
                extract().response();

        String respInString = sessionResponse.asString();
        System.out.println(respInString);

        //extracting the session value
        JsonPath resInJson = new JsonPath(respInString);
        String sessionValue = resInJson.get("session.value");
        System.out.println("Session value is: " + sessionValue);

    }

}