package de.papenhagen.weatherapi.ai;

import de.papenhagen.weatherapi.weather.model.Clouds;
import de.papenhagen.weatherapi.weather.model.Coord;
import de.papenhagen.weatherapi.weather.model.Main;
import de.papenhagen.weatherapi.weather.model.OpenWeatherResponse;
import de.papenhagen.weatherapi.weather.model.Rain;
import de.papenhagen.weatherapi.weather.model.Sys;
import de.papenhagen.weatherapi.weather.model.Weather;
import de.papenhagen.weatherapi.weather.model.Wind;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ai.openai.OpenAiChatModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class OpenAiServiceTest {

    @Test
    void testCreateWeatherSummary_callsModelAndReturns() {
        OpenAiChatModel model = Mockito.mock(OpenAiChatModel.class);
        when(model.call(anyString())).thenReturn("Sunny and warm, bring sunglasses.");

        OpenAiService service = new OpenAiService(model);
        OpenWeatherResponse resp = new OpenWeatherResponse(
                new Coord(11.0, 45.5),
                List.of(new Weather(12, "Rain", "moderate rain", "10d")),
                "stations",
                new Main(284.2,
                        282.93,
                        283.06,
                        286.82,
                        1021,
                        60,
                        1021,
                        910), 10000,
                new Wind(4.09, 121, 3.47),
                new Rain(2.73),
                new Clouds(83),
                1726660758,
                new Sys(1, 6736, "IT", 1726636384, 1726680975),
                7200, 3165523, "Province of Turin", 200);
        String out = service.createWeatherSummary(resp, "Berlin");
        assertEquals("Sunny and warm, bring sunglasses.", out);
    }
}
