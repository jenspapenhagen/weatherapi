package de.papenhagen.weatherapi.ai;

import de.papenhagen.weatherapi.weather.OpenWeatherResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ai.openai.OpenAiChatModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class OpenAiServiceTest {

    @Test
    public void testCreateWeatherSummary_callsModelAndReturns() {
        OpenAiChatModel model = Mockito.mock(OpenAiChatModel.class);
        when(model.call(anyString())).thenReturn("Sunny and warm, bring sunglasses.");

        OpenAiService service = new OpenAiService(model);
        OpenWeatherResponse.Weather w = new OpenWeatherResponse.Weather("clear sky");
        OpenWeatherResponse resp = new OpenWeatherResponse(new OpenWeatherResponse.Main(25.0, 40.0),
                new OpenWeatherResponse.Wind(2.5), new OpenWeatherResponse.Weather[]{w});

        String out = service.createWeatherSummary(resp, "Berlin");
        assertEquals("Sunny and warm, bring sunglasses.", out);
    }
}
