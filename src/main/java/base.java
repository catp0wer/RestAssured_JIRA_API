import io.restassured.RestAssured;
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
                body(ReusableMethods.getProp("POST_body_authentication")).
                when().
                post(ReusableMethods.getProp("POST_res")).
                then().
                statusCode(200).
                extract().response();

        String respInString = ReusableMethods.responseToString(sessionResponse);

        //extracting the session value
        ReusableMethods.getValueFromResponse(respInString,ReusableMethods.getProp("Session_id"));

    }

}
