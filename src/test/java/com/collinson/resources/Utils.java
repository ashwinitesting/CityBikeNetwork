package com.collinson.resources;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Properties;

public class Utils {
    RequestSpecification req;

    //Sets the base url
    public void setBaseURI() throws IOException {
        RestAssured.baseURI=getPropertyValue("baseURI");
    }

    //Builds request specification
    public RequestSpecification requestSpecification() throws IOException {

        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

            req = new RequestSpecBuilder()
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log)).
                            build();
            return req;
        }

        return req;
    }

    // This method reads the global property value from the given key
    public String getPropertyValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/com/collinson/resources/global.properties");
        prop.load(fileInputStream);
        return prop.getProperty(key);
    }

    // This method returns the country name for given country code
    public String getCountryForIsoCountryCode(String isoCode)
    {
        Locale l = new Locale("", isoCode);
        return l.getDisplayCountry();
    }


    // This method override the RestAssured config behaviour from Jackson2ObjectMapper to help deserialization and accept the single value as array
    public void overrideRestAssuredArrayBehaviour() {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                new Jackson2ObjectMapperFactory() {
                    @Override
                    public ObjectMapper create(Type type, String s) {
                        ObjectMapper om = new ObjectMapper().findAndRegisterModules();
                        om.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                        return om;
                    }
                }
                ));
    }

}
