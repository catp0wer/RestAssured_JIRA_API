package files;

public class payLoad {
    public static String getPostDataCreateIssue() {
        String postDataCreateIssue = "{" +
                "\"fields\": {" +
                "\"project\": {" +
                "\"key\": \"RES\"" +
                "}," +
                "\"summary\": \"Bug X is created\"," +
                "\"description\":\"Test\"," +
                "\"issuetype\": {" +
                "\"name\": \"Bug\"" +
                "}" +
                "}" +
                "}";
        return postDataCreateIssue;
    }
    public static String getPutDataUpdateIssue() {
        String putDataUpdateIssue = "{" +
                "\"update\":{" +
                "\"summary\": [{"+
                "\"set\":\"Bug X is updated\""+
                "}"+
                "]"+
                "}"+
                "}";
        return putDataUpdateIssue;
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

    public static String getPostDataUpdateComment() {
        String postDataUpdateComment = "{" +
                "\"body\":\"updating the comment\"," +
                "\"visibility\":{" +
                "\"type\":\"role\"," +
                "\"value\":\"Administrators\"" +
                "}" +
                "}";
        return postDataUpdateComment;
    }


}



