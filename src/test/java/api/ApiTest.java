package api;

import Common.api.Base;
import Common.api.Playloads.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest extends Base {

    String placeId;
    String URI = RestAssured.baseURI = "https://rahulshettyacademy.com";
    String newAddress = "223 - El Maadi ,Cairo ,Egypt";
    RequestSpecification request  = given().log().all().queryParam("key","qaclick123")
            .header("Content-Type","application/json");

    @Test
    public void addPlace(){
        String response = request.body(PayLoad.addPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath jsonPath = Base.rawToJson(response); // for parsing Json
        placeId = jsonPath.getString("place_id");
    }
    @Test(priority = 1)
    public void getPlace(){
                request.queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
    }
    @Test(priority = 2)
    public void updateAddress(){
        request.body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).
                body("msg",equalTo("Address successfully updated"));
    }

    @Test(priority = 3)
    public void getPlaceAfterUpdate(){
        String getPlaceResponse = request.queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
        JsonPath jsonPath = Base.rawToJson(getPlaceResponse);
        String addressAfterUpdate = jsonPath.getString("address");
        Assert.assertEquals(newAddress,addressAfterUpdate);
    }
}
