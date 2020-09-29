package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import lombok.var;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import utilities.RestAssuredExtension;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;

public class PutPostSteps {

    public static ResponseOptions<Response> response;

    @And("I Perform PUT operation for {string}")
    public void iPerformPUTOperationFor(String url, DataTable dataTable) {
        var data = dataTable.cells();

        HashMap<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        response = RestAssuredExtension.putOpsWithBodyAndParams(url,pathParams,body);
    }

    @Then("I {string} see the body with title as {string}")
    public void iSeeTheBodyWithTitleAs(String condition, String title) {
        if(condition.equalsIgnoreCase("should")){
            assertThat(response.getBody().jsonPath().get("title"), Is.is(title));
        }
        else assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
    }
}
