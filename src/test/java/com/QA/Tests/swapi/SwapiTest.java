package com.QA.Tests.swapi;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


public class SwapiTest {

    private Properties prop;

    @BeforeClass
    public void beforeClass() throws IOException {
        prop = new Properties();
        FileInputStream ip = new FileInputStream("src/test/java/config.properties");
        prop.load(ip);
    }


    @Test
    public void testPeopleEndpointResponseCode() {
        // verifies that the people endpoint responds with a successful response code
        String url = prop.getProperty("swapiPeopleEndpoint");
        int successfulResponseCode = 200;
        given().
                when().
                get(url).
                then().
                assertThat().
                statusCode(successfulResponseCode);
    }

    @Test
    public void testCountPeopleTallerThan200() throws JSONException {
        // get all people in a list
        String url = prop.getProperty("swapiPeopleEndpoint");
        int actualNumberOfPeopleTallerThan200 = 0;

        // iterate through all pages of the people endpoint
        while(!url.equals("null")) {
            Response response =
                    given().
                            headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                            when().
                            get(url).
                            then().
                            contentType(ContentType.JSON).
                            extract().
                            response();
            JSONObject res = new JSONObject(response.body().asString());
            JSONArray people = res.getJSONArray("results");

            // iterate through the results field of the response body
            for(int i = 0; i < people.length(); i++) {
                JSONObject person = people.getJSONObject(i);

                // handle the one "unknown" height value
                try {
                    if(Integer.parseInt(person.getString("height")) > 200) {
                        actualNumberOfPeopleTallerThan200 += 1;
                    }
                } catch (NumberFormatException ignored) {}
            }

            url = res.getString("next");
        }

        int expectedNumberOfPeopleTallerThan200 = 10;


        Assert.assertEquals(actualNumberOfPeopleTallerThan200, expectedNumberOfPeopleTallerThan200);


    }

    @Test
    public void verifyNamesOfPeopleTallerThan200() throws JSONException {
        // get all people in a list
        String url = prop.getProperty("swapiPeopleEndpoint");

        ArrayList<String> expectedNames = new ArrayList<String>() {
            {
                //Darth Vader. Chewbacca, Roos Tarpals, Rugor Nass, Yarael Poof, Lama Su, Tuan Wu, Grievous, Tarfful, Tion Medon
                add("Darth Vader");
                add("Chewbacca");
                add("Roos Tarpals");
                add("Rugor Nass");
                add("Yarael Poof");
                add("Lama Su");
                add("Taun We");
                add("Grievous");
                add("Tarfful");
                add("Tion Medon");
            }
        };

        ArrayList<String> actualNames = new ArrayList<String>();

        // iterate through all pages of the people endpoint
        while(!url.equals("null")) {
            Response response =
                    given().
                            headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                            when().
                            get(url).
                            then().
                            contentType(ContentType.JSON).
                            extract().
                            response();
            JSONObject res = new JSONObject(response.body().asString());
            JSONArray people = res.getJSONArray("results");

            // iterate through the results field of the response body
            for(int i = 0; i < people.length(); i++) {
                JSONObject person = people.getJSONObject(i);

                // handle the one "unknown" height value
                try {
                    if(Integer.parseInt(person.getString("height")) > 200) {
                        actualNames.add(person.getString("name"));
                    }
                } catch (NumberFormatException ignored) {}
            }

            url = res.getString("next");
        }

// check every expected name and see that it exists in the actual names list
        for(String expectedName : expectedNames) {
            Assert.assertTrue(actualNames.contains(expectedName));
        }
    }

    @Test
    public void verifyTotalNumberOfPeople() throws JSONException {
        // get all people in a list
        String url = prop.getProperty("swapiPeopleEndpoint");

        int actualCount = 0;

        // we should check if the count field = 82 and also if the total number of people iterated through sum up to 82
        int expectedCount = 82;


        // iterate through all pages of the people endpoint
        while(!url.equals("null")) {
            Response response =
                    given().
                            headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                            when().
                            get(url).
                            then().
                            contentType(ContentType.JSON).
                            extract().
                            response();
            JSONObject res = new JSONObject(response.body().asString());
            JSONArray people = res.getJSONArray("results");

            // just add the length of the people array because each index is a person
            actualCount += people.length();

            // check that the count field = 82
            Assert.assertEquals(res.getInt("count"), expectedCount);

            url = res.getString("next");
        }

        // Check the actual count with the expected count
        Assert.assertEquals(actualCount, expectedCount);
    }

}

