import models.CreateUserModel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import services.GoRestService;
import services.ReUsableMethods;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.containsString;
public class CreateUserTests {

    @Test
    public void Users_CreateUsers_Success(){
        File schema = new File(System.getProperty("user.dir")+"\\schema.json");
        final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", "Male", ReUsableMethods.emailAddress(), "Active");
       String response = GoRestService.createUser(createUserModel)
                .then()
                .statusCode(SC_CREATED)
                .header("Content-Type",containsString("application/json"))
                .body("data.id", notNullValue())
                .body("data.name", equalTo(createUserModel.getName()))
                .body("data.email", notNullValue())
                .time(Matchers.lessThanOrEqualTo(2000L))
                .body(matchesJsonSchema(schema))
                .extract().response().asString();

        ReUsableMethods.rawToJSON(response);
        System.out.println(response);

    }

    @Test
    public void Users_CreateAlreadyExistingUser_Failure(){
        final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", "Male", "qatest@test.com", "Active");
        String response = GoRestService.createUser(createUserModel)
                .then()
                .statusCode(SC_UNPROCESSABLE_ENTITY)
                .header("Content-Type",containsString("application/json"))
                .body("data.field", notNullValue())
                .body("data.message", notNullValue())
                .extract().response().asString();

        ReUsableMethods.rawToJSON(response);
        System.out.println(response);

    }

    @Test
    public void Users_CreateUserInvalidEmail_Failure(){
        final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", "Male", "qatesttest.com", "Active");
        String response = GoRestService.createUser(createUserModel)
                .then()
                .statusCode(SC_UNPROCESSABLE_ENTITY)
                .header("Content-Type",containsString("application/json"))
                .body("data.field", notNullValue())
                .body("data.message", notNullValue())
                .extract().response().asString();

        ReUsableMethods.rawToJSON(response);
        System.out.println(response);

    }
}

