package de.papenhagen.weatherapi.weather.model;

import io.github.giovannicaggianella.toon.annotation.ToonEntity;

@ToonEntity
public record Main(double temp,
            double feelsLike,
            double tempMin,
            double tempMax,
            int pressure,
            int humidity,
            int seaLevel,
            int grndLevel) {
}
