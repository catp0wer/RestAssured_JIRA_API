package base;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import files.ReusableMethods;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import files.payLoad;


public class Issue {
    @Test
    public void AddIssue() throws IOException {

        //creating issue
        RestAssured.baseURI = ReusableMethods.getProp("HOST");
        Response res = given().header("Content-Type","application/json").
                header("Cookie","JSESSIONID="+ReusableMethods.getSessionID()).
                body(payLoad.getPostDataCreateIssue()).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201).extract().response();
        //convert response into String
        String respString = ReusableMethods.responseToString(res);
        //convert response from String to Json to extract bug's id from json response
        JsonPath respJson = ReusableMethods.responseToJson(respString);
        System.out.println(ReusableMethods.getValueFromResponse(respJson,"id"));

    }

}
