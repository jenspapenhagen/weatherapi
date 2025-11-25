package de.papenhagen.weatherapi.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Rain(@JsonProperty("1h") double firstHour) {
}
