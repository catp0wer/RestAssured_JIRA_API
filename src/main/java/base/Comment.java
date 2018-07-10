package base;

import files.ReusableMethods;
import files.payLoad;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class Comment {
    @Test
    public void addComment() throws IOException{
        RestAssured.baseURI = ReusableMethods.getProp("HOST");
        Response res = given().header("Content-Type","application/json").
                header("Cookie","JSESSIONID="+ReusableMethods.getSessionID()).
                body(payLoad.getPostDataAddComment()).
                when().
                post("/rest/api/2/issue/10004/comment").
                then().
                statusCode(201).extract().response();
    }
}
