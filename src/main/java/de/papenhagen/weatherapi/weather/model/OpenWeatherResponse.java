package de.papenhagen.weatherapi.weather.model;

import io.github.giovannicaggianella.toon.annotation.ToonEntity;

import java.util.List;

@ToonEntity
public record OpenWeatherResponse(Coord coord,
                                  List<Weather> weather,
                                  String base,
                                  Main main,
                                  int visibility,
                                  Wind wind,
                                  Rain rain,
                                  Clouds clouds,
                                  int dt,
                                  Sys sys,
                                  int timezone,
                                  int id,
                                  String name,
                                  int cod) {
}

