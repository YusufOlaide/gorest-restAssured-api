import models.UpdateUserModel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import services.GoRestService;
import services.ReUsableMethods;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.containsString;

public class UpdateUserTests {

    @Test
    public void Users_UpdateUsers_Success(){
        final UpdateUserModel updateUserModel = new UpdateUserModel("Gino Paloma", "Female", ReUsableMethods.emailAddress(), "Active");
        String response = GoRestService.updateUser(updateUserModel)
                .then()
                .statusCode(SC_OK)
                .header("Content-Type",containsString("application/json"))
                .body("data.id", notNullValue())
                .body("data.name", equalTo(updateUserModel.getName()))
                .body("data.email", notNullValue())
                .time(Matchers.lessThanOrEqualTo(2000L))
                .extract().response().asString();

        ReUsableMethods.rawToJSON(response);
        System.out.println(response);
    }
}
