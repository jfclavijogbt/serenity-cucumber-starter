package com.poc.cl.api;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

import java.security.InvalidParameterException;
import java.util.Map;

public class GenericRestClient {

    public GenericRestClient() {
    }

    public GenericRestClient(String baseURI) {
        SerenityRest.setDefaultBasePath(baseURI);
    }

    public GenericRestClient(String baseURI, String token) {
        SerenityRest.setDefaultBasePath(baseURI);
        SerenityRest.setDefaultAuthentication(RestAssured.oauth2(token));
    }

    public GenericRestClient(String baseURI, String user, String pass) {
        SerenityRest.setDefaultBasePath(baseURI);
        SerenityRest.setDefaultAuthentication(RestAssured.preemptive().basic(user, pass));
    }

    private static RequestSpecification defaultRequestSpecification() {
       //return SerenityRest.given(RestAssured.given().baseUri("https://api-automation.soymach.com")).log().all();
        //return SerenityRest.given().baseUri("https://api-automation.soymach.com").log().all();
        return SerenityRest.setDefaultRequestSpecification(RestAssured.given()).given().baseUri("https://api-automation.soymach.com").log().all();
    }

    public ValidatableResponse runWebServiceWithBody(Method httpMethod, String resource, Object requestBody) {
        RequestSpecification requestSpec = defaultRequestSpecification()
                .contentType(ContentType.JSON).body(requestBody);
        return call(httpMethod, resource, requestSpec).then();
    }

    public ValidatableResponse runWebServiceWithBody(Method httpMethod, Map<String, ?> headers, String resource, Object requestBody) {
        RequestSpecification requestSpec = defaultRequestSpecification()
                .contentType(ContentType.JSON)
                .headers(headers)
                .body(requestBody);
        return call(httpMethod, resource, requestSpec).then();
    }

    public ValidatableResponse runSimpleWebService(Method httpMethod, String resource) {
        RequestSpecification requestSpec = defaultRequestSpecification();
        return call(httpMethod, resource, requestSpec).then();
    }

    public ValidatableResponse runSimpleWebService(Method httpMethod, Map<String, ?> headers, Map<String, ?> parameters, String resource) {
        RequestSpecification requestSpec = defaultRequestSpecification()
                .headers(headers)
                .queryParams(parameters);
        return call(httpMethod, resource, requestSpec).then();
    }

    public ValidatableResponse runSimpleWebService(Method httpMethod, Map<String, ?> headers, String resource) {
        RequestSpecification requestSpec = defaultRequestSpecification()
                .headers(headers);
        return call(httpMethod, resource, requestSpec).then();
    }

    public ValidatableResponse runSimpleWebService(Method httpMethod, Map<String, ?> headers, String resource, Map<String, ?> pathParameters) {
        RequestSpecification requestSpec = defaultRequestSpecification()
                .headers(headers)
                .pathParams(pathParameters);
        return call(httpMethod, resource, requestSpec).then();
    }

    private Response call(Method httpMethod, String resource, RequestSpecification requestSpecification) {
        Response response;
        switch (httpMethod) {
            case GET:
                response = requestSpecification.when().get(resource);
                break;
            case POST:
                response = requestSpecification.when().post(resource);
                break;
            case PUT:
                response = requestSpecification.when().put(resource);
                break;
            case DELETE:
                response = requestSpecification.when().delete(resource);
                break;
            case PATCH:
                response = requestSpecification.when().patch(resource);
                break;
            default:
                throw new InvalidParameterException("Invalid Http Method" + httpMethod);
        }
        return response;
    }
}
