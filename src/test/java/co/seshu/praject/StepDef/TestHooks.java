package co.seshu.praject.StepDef;

import co.seshu.praject.util.Debugger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.FileNotFoundException;

public class TestHooks { // extends Pages {


    public static String currentTagName = "";
    public static String currentTags = "";
    public static String currentFeature = "";
    public static String temptagname = "";
    public static boolean new_scenario_feature = false;

    APIStepDef steps = new APIStepDef();
//    public TestHooks(SeleniumDriver driver) {
//        super(driver);
//        seleniumLib = new SeleniumLib(driver);
//    }

    @Before(order = 0)
    public void beginingOfTest(Scenario scenario) {
        Debugger.println(java.time.LocalDate.now()+" "+ java.time.LocalTime.now());
        currentTagName = scenario.getSourceTagNames().toString();
        currentTags = scenario.getSourceTagNames().toString();
        currentFeature = scenario.getId().split(";")[0];
        if (temptagname.isEmpty() || !(temptagname.equalsIgnoreCase(currentTagName))) {
            Debugger.println("\n" + scenario.getSourceTagNames() + " STARTED");
            temptagname = currentTagName;
            new_scenario_feature = true;
            Debugger.println("DATE TIME: "+java.time.LocalDate.now()+" "+ java.time.LocalTime.now());
            Debugger.println("FEATURE: " + currentFeature.replaceAll("-", " "));
            Debugger.println("SCENARIO: " + scenario.getName());
        } else {
            new_scenario_feature = false;
            Debugger.println("DATE TIME: "+java.time.LocalDate.now()+" "+ java.time.LocalTime.now());
            Debugger.println("SCENARIO: " + scenario.getName());
        }
    }

    @Before("@DeleteAPI")
    public void beforeScenario() throws FileNotFoundException {
        if (steps.place_id==null) {
            steps.addPlacePayLoad();
            steps.userCallsPlaceAPIWithApi("AddPlaceAPI", "POST");
        }
    }

    @Before("@GetPlaceAPI")
    public void beforeGetCall() throws FileNotFoundException {
        beforeScenario();
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        String scenarioStatus = scenario.getStatus().toString();
//        if (!scenarioStatus.equalsIgnoreCase("PASSED")) {
//            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
//        }
        Debugger.println("STATUS: " + scenarioStatus.toUpperCase());
    }

    @After(order = 1)
    public void afterScenario() {
        //login_page.logoutFromMI();
    }
}//end class
