package co.seshu.praject.StepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class MyStepdefs {
    @Given("lunch the application")
    public void lunchTheApplication() {
        System.out.println("seshu");
    }

    @When("login the application")
    public void loginTheApplication() {
        System.out.println("seshu");
    }
}
