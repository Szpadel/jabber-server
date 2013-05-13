import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.support.SQLErrorCodes;

public class Main {

    @Qualifier("H2")
    @Autowired
    private SQLErrorCodes h2;


    public static void main(String[] args) {
	// write your code here
    }


}
