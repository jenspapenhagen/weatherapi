package de.papenhagen.weatherapi.weather;

import de.papenhagen.weatherapi.weather.model.OpenWeatherResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class OpenWeatherClientTest {

    @Test
     void testGetWeather_returnsParsedRecord() throws Exception {
        MockWebServer server = new MockWebServer();
        String body = "{\n" +
                "   \"coord\": {\n" +
                "      \"lon\": 7.367,\n" +
                "      \"lat\": 45.133\n" +
                "   },\n" +
                "   \"weather\": [\n" +
                "      {\n" +
                "         \"id\": 501,\n" +
                "         \"main\": \"Rain\",\n" +
                "         \"description\": \"moderate rain\",\n" +
                "         \"icon\": \"10d\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"base\": \"stations\",\n" +
                "   \"main\": {\n" +
                "      \"temp\": 284.2,\n" +
                "      \"feels_like\": 282.93,\n" +
                "      \"temp_min\": 283.06,\n" +
                "      \"temp_max\": 286.82,\n" +
                "      \"pressure\": 1021,\n" +
                "      \"humidity\": 60,\n" +
                "      \"sea_level\": 1021,\n" +
                "      \"grnd_level\": 910\n" +
                "   },\n" +
                "   \"visibility\": 10000,\n" +
                "   \"wind\": {\n" +
                "      \"speed\": 4.09,\n" +
                "      \"deg\": 121,\n" +
                "      \"gust\": 3.47\n" +
                "   },\n" +
                "   \"rain\": {\n" +
                "      \"1h\": 2.73\n" +
                "   },\n" +
                "   \"clouds\": {\n" +
                "      \"all\": 83\n" +
                "   },\n" +
                "   \"dt\": 1726660758,\n" +
                "   \"sys\": {\n" +
                "      \"type\": 1,\n" +
                "      \"id\": 6736,\n" +
                "      \"country\": \"IT\",\n" +
                "      \"sunrise\": 1726636384,\n" +
                "      \"sunset\": 1726680975\n" +
                "   },\n" +
                "   \"timezone\": 7200,\n" +
                "   \"id\": 3165523,\n" +
                "   \"name\": \"Province of Turin\",\n" +
                "   \"cod\": 200\n" +
                "} ";
        server.enqueue(new MockResponse().setBody(body).setHeader("Content-Type", "application/json"));
        server.start();

        String baseUrl = server.url("/").toString();
        OpenWeatherClient client = new OpenWeatherClient(baseUrl, "DUMMY_KEY");
        OpenWeatherResponse resp = client.getWeather("Berlin");

        assertEquals(284.2, resp.main().temp());
        assertEquals(60, resp.main().humidity());
        assertEquals(4.09, resp.wind().speed());

        server.shutdown();
    }
}
