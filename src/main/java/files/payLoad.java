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

    public static String getPostDataAddComment(){
        String postDataAddComment = "{"+
                "\"body\":\"adding first comment\","+
                "\"visibility\":{"+
            "\"type\":\"role\","+
                    "\"value\":\"Administrators\""+
            "}"+
        "}";
       return postDataAddComment;
    }
}



