package com.collinson.stepDefinitions;

import com.collinson.resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.AllNetworks;
import pojo.Network;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class GetNetworksAPI extends Utils {

    AllNetworks networkDetails;
    private String cityName;
    private String latitude;
    private String longitude;

    @Given("base URI")
    public void base_uri() throws IOException {
        setBaseURI();

    }

    @When("user calls citybikeAPI with Get http request")
    public void user_calls_citybike_api_with_get_http_request() throws IOException {
        overrideRestAssuredArrayBehaviour();
        networkDetails = given().spec(requestSpecification()).when().get().as(AllNetworks.class);
    }

    @Then("verify city {string} is in {string}")
    public void verify_city_is_in(String city, String country) {

        for (Network network : networkDetails.getNetworks()) {

            if (network.getLocation().getCity().equalsIgnoreCase(city)) {

                assertEquals(country, getCountryForIsoCountryCode(network.getLocation().getCountry()));

                cityName = city + " " + country;
                latitude = network.getLocation().getLatitude().toString();
                longitude = network.getLocation().getLongitude().toString();
            }
        }
    }

    @Then("return corresponded latitude and longitude")
    public void return_corresponded_latitude_and_longitude() {
        System.out.println(cityName);
        System.out.println(latitude);
        System.out.println(longitude);
    }

    @Then("verify network {string} is in the city {string}")
    public void verify_network_is_in_the_city(String networkName, String city) {

        for (Network network : networkDetails.getNetworks()) {

            if (network.getName().equalsIgnoreCase(networkName)) {

                assertEquals(city, network.getLocation().getCity().toString());

            }
        }
    }

    @Then("verify network {string} exists in more than one city and return all city names")
    public void verify_network_exists_in_more_than_one_city_and_return_all_city_names(String networkName) {
        int cityCount = 0;
        for (Network network : networkDetails.getNetworks()) {

            if (network.getName().equalsIgnoreCase(networkName)) {

                cityCount = cityCount + 1;
                // System.out.println(networkName+ "exists in" +network.getLocation().getCity() +"\n");
                System.out.printf("%s exists in %s\n\n", networkName, network.getLocation().getCity());

            }
        }
        assertTrue(cityCount > 1);
    }

    @Then("verify network {string} exists in the given {string}")
    public void verify_network_exists_in_the_given(String networkName, String city) {
        List<String> networks = new ArrayList<>();
        for (Network network : networkDetails.getNetworks()) {
            if (network.getLocation().getCity().equalsIgnoreCase(city)) {
                networks.add(network.getName());
            }
        }
        assertTrue(networks.contains(networkName));
    }

}
