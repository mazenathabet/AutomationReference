package Common.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jdk.jfr.ContentType;

public class Base {
    public static JsonPath rawToJson(String response){
        return new JsonPath(response);
    }
    public static RequestSpecification requestSpec (String baseUri, String queryParam1, String queryParam2, ContentType contentType){
        return  new RequestSpecBuilder().setBaseUri(baseUri).
                addQueryParam(queryParam1,queryParam2)
                .setContentType(String.valueOf(contentType)).build();
    }
    public static ResponseSpecification responseSpec(int statusCode, ContentType contentType){
        return new ResponseSpecBuilder().expectStatusCode(statusCode).expectContentType(String.valueOf(contentType)).build();
    }
}
