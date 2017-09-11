package HelloTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import  testJava.*;

public class test {

    @Test
    public void getRequest(){

        Assert.assertEquals(TestClass.Hello(), 4, "Not equal!");

    }

}
