Feature: Validating City Bike Networks

  Background:
    Given base URI
    When user calls citybikeAPI with Get http request

  @GivenCityIsInGivenCountry
  Scenario Outline:As a user I want to verify that the city Frankfurt is in Germany and return their corresponded latitude and longitude

    Then verify city "<city>" is in "<country>"
    And return corresponded latitude and longitude

    Examples:
      | city      | country |
      | Frankfurt | Germany |
      | Antequera | Spain   |
      | Aranjuez  | Spain   |


  @GivenNetworkisInGivenCity
  Scenario: As a user I want to verify given network BikeSantiago is in the city Santiago
    Then verify network "BikeSantiago" is in the city "Santiago"

  @CheckNetworkExistsInMoreThanOneCity
  Scenario Outline: As a user I want to verify given network exists in more than one city and return all city names
    Then verify network "<network>" exists in more than one city and return all city names

    Examples:
      | network    |
      | Bysykkel   |
      | Onroll     |
      | Cyclopolis |

  @OnrollInGivenCity
  Scenario Outline: As a user I want to verify given city has Onroll bike network
    Then verify network "Onroll" exists in the given "<city>"

    Examples:
      | city      |
      | Albacete  |
      | Antequera |
      | Aranjuez  |






