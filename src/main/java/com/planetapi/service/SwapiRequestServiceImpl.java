package com.planetapi.service;

import com.planetapi.components.SwapiComponent;

import com.planetapi.model.swapi.PlanetsResults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.planetapi.configuration.CacheConfiguration.PLANET_NAME;

import static java.util.Arrays.asList;

import static org.springframework.http.HttpHeaders.USER_AGENT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class SwapiRequestServiceImpl implements SwapiRequestService{

    @Autowired
    private SwapiComponent swapiComponent;

    private RestTemplate restTemplate;

    public SwapiRequestServiceImpl(){

        restTemplate = new RestTemplate();
    }

    @Override
    @Cacheable(PLANET_NAME)
    public int getApparisons(String name) {

        log.info("Requesting data to {} to search planet {}", swapiComponent.getUrl(), name);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(asList(APPLICATION_JSON));
        httpHeaders.put(USER_AGENT, asList("planetapi"));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(swapiComponent.getUrl());
        builder.queryParam("search", name);

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);

        log.info("Final request URI {}", builder.toUriString());

        ResponseEntity<PlanetsResults> responseEntity = restTemplate.exchange(builder.toUriString(), GET, entity, PlanetsResults.class);

        if(responseEntity.getBody().getResults().isEmpty())
            return 0;

        return responseEntity.getBody().getResults().get(0).getFilms().size();
    }
}
