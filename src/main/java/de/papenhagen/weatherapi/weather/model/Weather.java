package de.papenhagen.weatherapi.weather.model;

import io.github.giovannicaggianella.toon.annotation.ToonEntity;

@ToonEntity
public record Weather(int id, String main, String description, String icon) {}
