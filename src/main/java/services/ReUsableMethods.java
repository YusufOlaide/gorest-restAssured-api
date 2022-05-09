package services;

import io.restassured.path.json.JsonPath;

import java.sql.Timestamp;

public class ReUsableMethods {

    public static JsonPath rawToJSON(String response){
        JsonPath jP = new JsonPath(response);
        return jP;
    }

    public static String emailAddress(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long time = timestamp.getTime();
        String email = "qatest" + time + "@test.com";
        return email;
    }
}
