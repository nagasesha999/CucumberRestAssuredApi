package co.seshu.praject.StepDef;



import co.seshu.praject.resources.APIResources;
import co.seshu.praject.resources.GlobalDataVariables;
import co.seshu.praject.resources.TestDataBuild;
import co.seshu.praject.resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class APIStepDef extends Utils {

//    public APIStepDef(SeleniumDriver driver) {
//        super(driver);
//    }
    JsonPath jsonPath;
    String responseString;
    RequestSpecification request;
    ResponseSpecification res;
    Response response;
    TestDataBuild data = new TestDataBuild();
    GlobalDataVariables global = new GlobalDataVariables();
    static String place_id;
    @Given("Add place PayLoad")
    public void addPlacePayLoad() throws FileNotFoundException {
        request = given().spec(requestSpecification()).body(data.addPlacePayLoad());
    }

    @When("User calls {string} Place API with {string} http request")
    public void userCallsPlaceAPIWithApi(String expResource, String httpRequest) {
//        res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        APIResources resourceApi = APIResources.valueOf(expResource);
        String expRes = resourceApi.getResource();
        System.out.println(expRes);
        if(httpRequest.equalsIgnoreCase("post")){
            response =  request.when().post(resourceApi.getResource());
            if (resourceApi.getResource().contains("add")){
                place_id = getJsonPath(response,"place_id");
                System.out.println("---------------------"+place_id+"------------------");
            }
        }else if(httpRequest.equalsIgnoreCase("get")) {
            response = request.when().get(resourceApi.getResource());
        }

    }

    @Then("the api call got success with status code {int}")
    public void theApiCallGotSuccessWithStatusCode(int expStatusCode) {
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String expKey, String expValue) {
//        responseString = response.asString();
//        jsonPath = new JsonPath(responseString);
//        Assert.assertEquals(jsonPath.get(expKey).toString(),expValue);
        Assert.assertEquals(getJsonPath(response,expKey),expValue);
    }


    @Given("Add place PayLoad with {string} {string} {string}")
    public void addPlacePayLoadWith(String name, String language, String address) throws FileNotFoundException {
        request = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
    }

    @Then("verify {string} argument with get call {string} response")
    public void verifyArgumentWithGetCallResponse(String value, String expResource) {
//        APIResources resourceApi = APIResources.valueOf(expResource);
//        response = request.when().get(resourceApi.getResource());
        Assert.assertEquals(getJsonPath(response,"name"),value);

    }

    @When("the user get a place using place_id")
    public void theUserGetAPlaceUsingPlace_id() throws FileNotFoundException {
        request = given().spec(requestSpecification()).queryParam("place_id",place_id);
    }

    @Given("Delete place api PayLoad")
    public void deletePlaceApiPayLoad() throws FileNotFoundException {
        request = given().spec(requestSpecification()).body(data.delete_PayLoad(place_id));
        place_id=null;
    }
}
