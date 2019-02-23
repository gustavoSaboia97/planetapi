package com.planetapi.components;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "swapi.planet")
public class SwapiComponent {

    @Getter @Setter
    private String url;
}
