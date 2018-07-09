package files;

public class payLoad {
    public static String getPostDataCreateIssue() {
        String postDataCreateIssue = "{" +
                "\"fields\": {" +
                "\"project\": {" +
                "\"key\": \"RES\"" +
                "}," +
                "\"summary\": \"Second bug\"," +
                "\"description\":\"Test\"," +
                "\"issuetype\": {" +
                "\"name\": \"Bug\"" +
                "}" +
                "}" +
                "}";
        return postDataCreateIssue;
    }
}



