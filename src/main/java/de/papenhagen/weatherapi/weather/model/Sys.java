package de.papenhagen.weatherapi.weather.model;

import io.github.giovannicaggianella.toon.annotation.ToonEntity;

@ToonEntity
public record Sys(int type, int id, String country, int sunrise, int sunset) {
}
