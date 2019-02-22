package com.planetapi.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Planet {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
    private String climate;
    private String terrain;

    @Transient
    private int apparitionCounter;
}
