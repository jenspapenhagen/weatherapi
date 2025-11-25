package de.papenhagen.weatherapi.weather;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenWeatherClientTest {

    @Test
    public void testGetWeather_returnsParsedRecord() throws Exception {
        MockWebServer server = new MockWebServer();
        // simple JSON matching our record shape
        String body = "{\n" +
                "  \"main\": { \"temp\": 12.3, \"humidity\": 80 },\n" +
                "  \"wind\": { \"speed\": 5.5 },\n" +
                "  \"weather\": [ { \"description\": \"light rain\" } ]\n" +
                "}";
        server.enqueue(new MockResponse().setBody(body).setHeader("Content-Type", "application/json"));
        server.start();

        String baseUrl = server.url("/").toString();
        OpenWeatherClient client = new OpenWeatherClient(baseUrl, "DUMMY_KEY");
        OpenWeatherResponse resp = client.getWeather("Berlin");

        assertEquals(12.3, resp.main().temp());
        assertEquals(80.0, resp.main().humidity());
        assertEquals(5.5, resp.wind().speed());
        assertEquals("light rain", resp.weather()[0].description());

        server.shutdown();
    }
}
