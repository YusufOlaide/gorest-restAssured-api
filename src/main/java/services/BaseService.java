package services;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseService {

    protected static RequestSpecification defaultRequestSpecification(){
        return restAssured()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer fa80cece96297cd1ee8f66607d62a94723a4ddd79769451e5a6ef9efba66ca61");
    }

    protected static RequestSpecification restAssured() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.urlEncodingEnabled = false;

        return given()
                .config(RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
    }
}
