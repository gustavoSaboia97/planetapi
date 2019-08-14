package com.planetapi.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Planet {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "Name of planet should not be null")
    private String name;

    @NotBlank(message = "Climate of planet should not be null")
    private String climate;

    @NotBlank(message = "Terrain of planet should not be null")
    private String terrain;

    @Transient
    private int apparitionCounter;
}
