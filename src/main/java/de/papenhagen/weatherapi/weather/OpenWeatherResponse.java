package de.papenhagen.weatherapi.weather;

public record OpenWeatherResponse(Main main, Wind wind, Weather[] weather) {
    public record Main(double temp, double humidity) {}
    public record Wind(double speed) {}
    public record Weather(String description) {}
}
