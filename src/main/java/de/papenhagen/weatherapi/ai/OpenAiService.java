package de.papenhagen.weatherapi.ai;

import de.papenhagen.weatherapi.weather.OpenWeatherResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    private final OpenAiChatModel chatModel;

    public OpenAiService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createWeatherSummary(OpenWeatherResponse weather, String city) {
        OpenWeatherResponse.Main main = weather.main();
        OpenWeatherResponse.Wind wind = weather.wind();
        String desc = (weather.weather() != null && weather.weather().length > 0) ? weather.weather()[0].description() : "N/A";

        String prompt = String.format(
                "You are a friendly weather assistant." +
                        "\nCity: %s" +
                        "\nTemperature: %.1f°C" +
                        "\nHumidity: %.0f%%" +
                        "\nWind speed: %.1f m/s" +
                        "\nDescription: %s" +
                        "\n\nProduce a short (1-2 sentences) human-friendly weather summary and one suggestion for what to bring.",
                city, main.temp(), main.humidity(), wind.speed(), desc);

        return chatModel.call(prompt);
    }
}
