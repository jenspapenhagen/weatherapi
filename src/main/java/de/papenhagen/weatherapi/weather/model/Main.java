package de.papenhagen.weatherapi.weather.model;

import io.github.giovannicaggianella.toon.annotation.ToonEntity;

@ToonEntity
public record Main(Double temp,
                   Double feelsLike,
                   Double tempMin,
                   Double tempMax,
                   Integer pressure,
                   Integer humidity,
                   Integer seaLevel,
                   Integer grndLevel) {
}
