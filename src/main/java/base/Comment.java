package base;
import files.ReusableMethods;
import org.testng.annotations.Test;
import java.io.IOException;


public class Comment {

    @Test
    public void addComment() throws IOException {
        ReusableMethods.addComment();

    }
}
