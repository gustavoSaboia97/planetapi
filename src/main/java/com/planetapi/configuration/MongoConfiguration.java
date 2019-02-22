package com.planetapi.configuration;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {

    private String mongoHost;
    private String mongoPort;
    private String mongoDatabase;

    public MongoConfiguration(){

        mongoHost = System.getenv("MONGO_HOST");
        mongoPort = System.getenv("MONGO_PORT");
        mongoDatabase = System.getenv("MONGO_DATABASE");
    }

    @Bean
    public MongoClient mongo(){

        return new MongoClient(mongoHost+":"+mongoPort);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), mongoDatabase);
    }
}
