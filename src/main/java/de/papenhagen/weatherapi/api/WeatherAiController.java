package de.papenhagen.weatherapi.api;


import de.papenhagen.weatherapi.ai.OpenAiService;
import de.papenhagen.weatherapi.weather.OpenWeatherClient;
import de.papenhagen.weatherapi.weather.model.OpenWeatherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather-ai")
@Tag(name = "Weather AI", description = "API that combines OpenWeather and OpenAI to produce summaries")
public class WeatherAiController {

    private final OpenWeatherClient weatherClient;
    private final OpenAiService openAiService;

    public WeatherAiController(OpenWeatherClient weatherClient, OpenAiService openAiService) {
        this.weatherClient = weatherClient;
        this.openAiService = openAiService;
    }

    @GetMapping("/{city}")
    @Operation(
            summary = "Get a short weather summary for a city",
            description = "Fetches live weather from OpenWeather and generates a short natural-language summary using OpenAI.",
            parameters = {
                    @Parameter(
                            name = "city",
                            description = "Name of the city to get the weather summary for",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Weather summary successfully generated",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(type = "string", example = "It's sunny in Paris with temperatures around 18°C.")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "City not found",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error fetching weather or generating summary",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public String getWeatherSummary(@PathVariable String city) {
        OpenWeatherResponse weather = weatherClient.getWeather(city);
        return openAiService.createWeatherSummary(weather, city);
    }
}
