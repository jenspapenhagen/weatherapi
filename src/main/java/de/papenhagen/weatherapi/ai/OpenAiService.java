package de.papenhagen.weatherapi.ai;

import de.papenhagen.weatherapi.weather.model.OpenWeatherResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    private final OpenAiChatModel chatModel;

    public OpenAiService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createWeatherSummary(OpenWeatherResponse weather, String city) {
        String desc = (weather.weather() != null && !weather.weather().isEmpty()) ? weather.weather().getFirst().description() : "N/A";

        String prompt = String.format(
                "You are a friendly weather assistant." +
                        "For City: %s" +
                        "\n %s" +
                        "\n %s" +
                        "\n\nProduce a short (2-3 sentences) human-friendly weather summary and one suggestion for what to wear.",
                city, weather, desc);

        return chatModel.call(prompt);
    }
}
