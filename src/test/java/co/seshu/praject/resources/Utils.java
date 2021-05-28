package co.seshu.praject.resources;

import co.seshu.praject.util.Debugger;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static PrintStream log;
    ResponseSpecification res;

    public RequestSpecification requestSpecification() throws FileNotFoundException {
        if (req == null) {
            log = new PrintStream(new FileOutputStream("Report_" + sdf.format(new Date()) + ".txt"));
            req = new RequestSpecBuilder().setBaseUri(getData("baseURI"))
                    .addQueryParam("key", "qaclick123")
//                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public ResponseSpecification responseSpecification() {
        res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        return res;
    }

    public static String getData(String key) {
        try {
            Properties prob = new Properties();
            FileInputStream input = new FileInputStream("testdata/EndPoints.properties");
            prob.load(input);
            return prob.getProperty(key);
        } catch (Exception exp) {
            Debugger.println("Exception From : getData : utils : " + exp);
            Debugger.println("Properties file is not present ");
        }
        return key;
    }

    public String getJsonPath(Response response, String key){
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.get(key).toString();
    }

}
