package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import files.ReusableMethods;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import files.payLoad;

public class Issue {

    public Issue() throws IOException {
    }
    private ReusableMethods tokenAuth = new ReusableMethods(ReusableMethods.getProp("Username"),
                                ReusableMethods.getProp("Password"));
    private Response res;
    @Test(testName = "Add an issue")
    public void AddIssue() throws IOException {
        res = tokenAuth.AddIssue();
    }

    @Test(testName = "Edit an issue")
    public void EditIssue() throws IOException {
        String issueID = ReusableMethods.getValueFromJson(res, "id");

        RestAssured.baseURI = ReusableMethods.getProp("HOST");
        given().
                header("Content-Type", "application/json").
                header("Authorization", tokenAuth.getToken()).
                body(payLoad.getPutDataUpdateIssue()).
                when().
                put("/rest/api/2/issue/" + issueID).
                then().
                statusCode(204);
    }

    @Test(testName = "Delete an issue")
    public void DeleteIssue() throws IOException {
        String issueID = ReusableMethods.getValueFromJson(res, "id");

        RestAssured.baseURI = ReusableMethods.getProp("HOST");
        given().
                header("Content-Type", "application/json").
                header("Authorization", tokenAuth.getToken()).
                when().
                delete("/rest/api/2/issue/" + issueID).
                then().
                statusCode(204);
    }

    @Test(testName = "Get an issue")
    public void GetIssue() throws IOException {
        String issueID = ReusableMethods.getValueFromJson(res, "id");
        RestAssured.baseURI = ReusableMethods.getProp("HOST");
        given().
                header("Content-Type", "application/json").
                header("Authorization", tokenAuth.getToken()).
                when().
                get("/rest/api/2/issue/"+issueID).
                then().
                statusCode(200);
    }
}
