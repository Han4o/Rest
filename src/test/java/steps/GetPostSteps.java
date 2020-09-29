package steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.hu.Ha;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import lombok.var;
import org.hamcrest.Matchers;
import pojo.Address;
import pojo.Location;
import pojo.Posts;
import utilities.RestAssuredExtension;

import java.util.HashMap;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPostSteps {

    public static ResponseOptions<Response> response;

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) {
        response = RestAssuredExtension.getOpsWithToken(url, response.getBody().jsonPath().get("access_token"));
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String authorName) {

        var posts = response.getBody().as(Posts.class);

        assertThat(posts.getAuthor(),equalTo(authorName));

        //assertThat(response.getBody().jsonPath().get("author"), equalTo(authorName));
    }

    @Then("I should see author names")
    public void iShouldSeeAuthorNames() {
    }

    @Then("I should see the path parameter")
    public void iShouldSeeThePathParameter() {
    }

    @Given("I perform POST operation for {string} with body")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table) {
        var data = table.cells();

        HashMap<String, String> body = new HashMap<>();
        body.put("name",data.get(1).get(0));

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("profileNo", data.get(1).get(1));

        response = RestAssuredExtension.postOpsWithBodyAndParameters(url,pathParams,body);
    }

    @Then("I should see the body has name as {string}")
    public void iShouldSeeTheBodyHasNameAs(String name) {
        assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
    }

    @Given("I perform authentication operation for {string} with body")
    public void iPerformAuthenticationOperationForWithBody(String url, DataTable dataTable) {

        var data = dataTable.cells();

        HashMap<String, String> body = new HashMap<>();
        body.put("email", data.get(1).get(0));
        body.put("password", data.get(1).get(1));

        response = RestAssuredExtension.postOpsWithBody(url,body);
    }

    @And("I perform GET operation with path parameter for address {string}")
    public void iPerformGETOperationWithPathParameterForAddress(String url, DataTable table) {

        var data = table.cells();

        HashMap<String, String> queryParams = new HashMap<>();

        queryParams.put("id", data.get(1).get(0));

        response = RestAssuredExtension.getOpsWithQueryParamsAndToken(url, queryParams, response.getBody().jsonPath().get("access_token"));
    }

    @Then("I should see the street name as {string}")
    public void iShouldSeeTheStreetNameAs(String streetName) {
        var location = response.getBody().as(Location[].class);
        Address address = location[0].getAddress().get(0);
        assertThat(address.getStreet(), equalTo(streetName));
    }

    @Then("I should see the author name as {string} with json validation")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String arg0) {
        var responseBody = response.getBody().asString();

        assertThat(responseBody, matchesJsonSchemaInClasspath("post.json"));
       
    }
}
