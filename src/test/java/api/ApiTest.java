package api;

import Common.api.Base;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class ApiTest extends Base {


    @Test
    public void addPlace() {
        // Given -> all the input details
        // When -> submit the API - resource , http method
        // Then -> validate the response
        baseURI = "https://rahulshettyacademy.com";
        RestAssured.given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Mazen\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"English\"\n" +
                        "}").when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .header("Server", "Apache/2.4.41 (Ubuntu)");
    }
}
