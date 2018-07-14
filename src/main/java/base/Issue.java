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
       ReusableMethods.AddIssue();
    }

    @Test
    public void EditIssue() throws IOException {
        Response addIssueResponse = ReusableMethods.AddIssue();
        String issueID = ReusableMethods.getValueFromJson(addIssueResponse,"id");

        RestAssured.baseURI = ReusableMethods.getProp("HOST");
         given().
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionID()).
                body(payLoad.getPutDataUpdateIssue()).
                when().
                put("/rest/api/2/issue/"+issueID).
                then().
                statusCode(204);

    }
}
