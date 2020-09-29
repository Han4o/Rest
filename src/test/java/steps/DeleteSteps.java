package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.hu.Ha;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import lombok.Data;
import lombok.var;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import utilities.RestAssuredExtension;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteSteps {

    public static ResponseOptions<Response> response;

    @Given("I ensure to Perform POST operation for {string} with body as")
    public void iEnsureToPerformPOSTOperationForWithBodyAs(String url, DataTable dataTable) {
        var data = dataTable.cells();

        HashMap<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        response = RestAssuredExtension.postOpsWithBody(url, body);
    }

    @And("I perform DELETE operation for {string}")
    public void iPerformDELETEOperationFor(String url, DataTable dataTable) {

        var data = dataTable.cells();
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        response = RestAssuredExtension.deleteOpsWithPathParams(url, pathParams);
    }

    @And("I perform GET operation with path parameter for {string}")
    public void iPerformGETOperationWithPathParameterFor(String url, DataTable dataTable) {

        var data = dataTable.cells();
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        response = RestAssuredExtension.getOpsWithPathParams(url, pathParams);
    }

    @Then("I should not see the body with title as {string}")
    public void iShouldNotSeeTheBodyWithTitleAs(String name) {
        assertThat(response.getBody().jsonPath().get("author"), IsNot.not(name));
    }
}
