package de.papenhagen.weatherapi.api;


import de.papenhagen.weatherapi.ai.OpenAiService;
import de.papenhagen.weatherapi.weather.OpenWeatherClient;
import de.papenhagen.weatherapi.weather.OpenWeatherResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather-ai")
public class WeatherAiController {

    private final OpenWeatherClient weatherClient;
    private final OpenAiService openAiService;

    public WeatherAiController(OpenWeatherClient weatherClient, OpenAiService openAiService) {
        this.weatherClient = weatherClient;
        this.openAiService = openAiService;
    }

    @GetMapping("/{city}")
    @Operation(summary = "Get a short weather summary for city using OpenWeather + OpenAI")
    public String getWeatherSummary(@PathVariable String city) {
        OpenWeatherResponse weather = weatherClient.getWeather(city);
        return openAiService.createWeatherSummary(weather, city);
    }
}
