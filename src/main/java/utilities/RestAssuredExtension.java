package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import lombok.var;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RestAssuredExtension {

    public static RequestSpecification request;

    public RestAssuredExtension() {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000");
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
    }

    public static void getOpsWithParameter(String url, Map<String, String> pathParams) {
        request.pathParams(pathParams);
        try {
            request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static ResponseOptions<Response> getOps(String url) {
        try {
            return request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseOptions<Response> postOpsWithBodyAndParameters(String url, Map<String, String> pathParams, Map<String, String> body) {
        request.pathParams(pathParams);
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> postOpsWithBody(String url, Map<String, String> body) {
        request.body(body);
        return request.post(url);
    }


    public static ResponseOptions<Response> deleteOpsWithPathParams(String url, Map<String, String> pathParams){
        request.pathParams(pathParams);
        return request.delete(url);
    }

    public static ResponseOptions<Response> getOpsWithPathParams(String url, Map<String, String> pathParams){
        request.pathParams(pathParams);
        return request.get(url);
    }

    public static ResponseOptions<Response> putOpsWithBodyAndParams(String url, Map<String, String> pathParams, Map<String, String> body){
        request.pathParams(pathParams);
        request.body(body);
        return request.put(url);
    }

    public static ResponseOptions<Response> getOpsWithToken(String url, String token){
        request.header(new Header("Authorization", "Bearer " + token));
        try {
            return request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseOptions<Response> getOpsWithQueryParamsAndToken(String url, HashMap<String, String> queryParams, String token){
        request.header(new Header("Authorization", "Bearer " + token));
        request.queryParams(queryParams);
        return request.get(url);
    }
}

