# CityBikeNetwork
API Testing
API automation exercise
========================
Question - PRACTICAL EXERCISE
-----------------------------
As a biker I would like to know the exact location of city bikes around the world in a given application.

•             Endpoint: http://api.citybik.es/v2/networks
•             Auth: No
•             HTTPS: No
•             Understands how the API works.
•             Create some BDD scenarios and automate them using Java to test the API
•             Test this particular scenario: “As a user I want to verify that the city Frankfurt is in Germany and return their corresponded latitude and longitude”
*****************************

Answer
-----------------------------

How to test/build/run
---------------------
This is Java Maven Project using cucumber BDD Framework. I have used IntelliJ IDE to build, Gherkin to define
test cases, Cucumber-Junit test runner.

To run the program, run com.collinson.cucumber.options.TestRunner class.

If you want to run an individual test you can do that by giving tag in CucumberOptions in TestRunner class.
You can also change the inputs or give additional inputs from com.collinson.features.networkValidations file.

A little bit about the program
------------------------------
Generated POJO classes using jsonschema2pojo-maven-plugin. Used these classes for deserialization.
Defined test scenarios in networkValidations.feature file.
Mapped all test cases to com.collinson.stepDefinitions.GetNetworksAPI.java.
Made 'Get API' call using the given endpoint and mapped the response JSON to POJO classes for deserialization.
Then verified the test cases using POJO classes.

----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----*----
