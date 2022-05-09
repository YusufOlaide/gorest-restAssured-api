package services;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.CreateUserModel;
import models.UpdateUserModel;


public class GoRestService extends BaseService {
    public static Response createUser(final CreateUserModel createUserModel){
        return defaultRequestSpecification()
                .body(createUserModel).log().all()
                .when()
                .post("/public/v1/users");
    }

    public static String createUserId(){
        final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", "Male", ReUsableMethods.emailAddress(), "Active");
        String response = GoRestService.createUser(createUserModel)
                .then()
                .extract().response().asString();

        JsonPath jPId = ReUsableMethods.rawToJSON(response);
        return jPId.getString("data.id");
    }

    public static Response updateUser(final UpdateUserModel updateUserModel){
        String id = GoRestService.createUserId();
        return defaultRequestSpecification()
                .body(updateUserModel).log().all()
                .when()
                .put("/public/v1/users/"+id);
    }
}
