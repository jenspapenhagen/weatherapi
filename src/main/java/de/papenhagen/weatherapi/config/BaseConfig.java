package de.papenhagen.weatherapi.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.TimeZone;

@Configuration
public class BaseConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.ALWAYS))
                .disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES) //not really care if all fields are filled
                // .disable(MapperFeature.DEFAULT_VIEW_INCLUSION) in Jackson 3 this is default disabled
                // .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) in Jackson 3 this is default disabled
                .defaultTimeZone(TimeZone.getTimeZone("UTC")) // set a default timezone for dates
                .build();
    }
}
