package de.papenhagen.weatherapi.weather;

import de.papenhagen.weatherapi.weather.model.OpenWeatherResponse;
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

    /**
     * Calling the openWeather API
     * JSON format API response example
     * <pre>
     *{
     *    "coord": {
     *       "lon": 7.367,
     *       "lat": 45.133
     *    },
     *    "weather": [
     *       {
     *          "id": 501,
     *          "main": "Rain",
     *          "description": "moderate rain",
     *          "icon": "10d"
     *       }
     *    ],
     *    "base": "stations",
     *    "main": {
     *       "temp": 284.2,
     *       "feels_like": 282.93,
     *       "temp_min": 283.06,
     *       "temp_max": 286.82,
     *       "pressure": 1021,
     *       "humidity": 60,
     *       "sea_level": 1021,
     *       "grnd_level": 910
     *    },
     *    "visibility": 10000,
     *    "wind": {
     *       "speed": 4.09,
     *       "deg": 121,
     *       "gust": 3.47
     *    },
     *    "rain": {
     *       "1h": 2.73
     *    },
     *    "clouds": {
     *       "all": 83
     *    },
     *    "dt": 1726660758,
     *    "sys": {
     *       "type": 1,
     *       "id": 6736,
     *       "country": "IT",
     *       "sunrise": 1726636384,
     *       "sunset": 1726680975
     *    },
     *    "timezone": 7200,
     *    "id": 3165523,
     *    "name": "Province of Turin",
     *    "cod": 200
     * }
     * </pre>
     *
     *  Doc: <a href="https://openweathermap.org/current">Online API OpenWeather</a>
     *
     * @param city from the weather are requested
     * @return the weather
     */
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
