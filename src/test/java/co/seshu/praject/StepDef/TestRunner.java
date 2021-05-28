package co.seshu.praject.StepDef;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/FeatureFiles",
        glue = "co/seshu/praject/StepDef", tags = "@MapsAPI",
        plugin = { "pretty", "html:target/cucumber-reports"},
//                "junit:target/cucumber-reports/Cucumber.xml"  ,"json:target/cucumber-reports/Cucumber.json"},
        monochrome = true)
public class TestRunner {

}
