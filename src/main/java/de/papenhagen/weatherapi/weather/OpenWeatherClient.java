package de.papenhagen.weatherapi.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class OpenWeatherClient {

    private final RestClient restClient;
    private final String apiKey;

    public OpenWeatherClient(@Value("${openweather.base-url}") String baseUrl,
                             @Value("${openweather.api-key}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public OpenWeatherResponse getWeather(String city) {
        try {
            return restClient.get()
                    .uri("/weather?q={city}&appid={key}&units=metric", city, apiKey)
                    .retrieve()
                    .body(OpenWeatherResponse.class);
        } catch (RestClientException e) {
            throw new IllegalCallerException("Failed to fetch weather for " + city, e);
        }
    }
}
