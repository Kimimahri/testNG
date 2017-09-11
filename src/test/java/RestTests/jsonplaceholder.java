package RestTests;

import com.jayway.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.*;
import static com.jayway.restassured.RestAssured.get;

import com.jayway.restassured.response.Response;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;




import static org.hamcrest.CoreMatchers.*;

import static com.jayway.restassured.RestAssured.*;



public class jsonplaceholder {

    @BeforeClass
    public static void setup() {


        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "https://jsonplaceholder.typicode.com";
        }
        RestAssured.baseURI = baseHost;


    }


    @Test
    public void getRequest(){


        given().when().get("/posts/1").then()
                .body("userId",equalTo(1))
                .body("id", equalTo(1))
                .body("title", containsString("facere repellat"))
                .body("body", containsString("rem eveniet architecto"))
                .statusCode(200);
    }

    @Test
    public  void postRequest() throws JSONException{
        final Random random = new Random();
        String randId = String.valueOf(random.nextInt(1000) + 1);

        JSONObject json = new JSONObject();

        json.put("userId", randId);

        Response resp =
        given().contentType("application/json")
                .body(json.toString()).when().post("/posts").then()
                .body("userId",equalTo(Integer.valueOf(randId)))
                .body("id", equalTo(101))
                .statusCode(201).extract().response();


        JSONObject jsonObject = new JSONObject(resp.asString());
        System.out.print(jsonObject);


    }
}