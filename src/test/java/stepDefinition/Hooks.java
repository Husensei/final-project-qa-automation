package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static helper.Utility.quitDriver;
import static helper.Utility.startDriver;

public class Hooks {

    @Before(value = "@web")
    public void setUp() {
        startDriver();
    }

    @After(value = "@web")
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        quitDriver();
    }
}
