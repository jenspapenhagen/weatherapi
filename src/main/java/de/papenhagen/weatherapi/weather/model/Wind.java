package de.papenhagen.weatherapi.weather.model;

import io.github.giovannicaggianella.toon.annotation.ToonEntity;

@ToonEntity
public record Wind(double speed, int deg, double gust) {
}
