package com.collinson.cucumber.options;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/com/collinson/features/networkValidations.feature"},glue={"com/collinson/stepDefinitions"},tags = "")
public class TestRunner {
}
