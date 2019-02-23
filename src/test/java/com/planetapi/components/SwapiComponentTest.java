package com.planetapi.components;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SwapiComponentTest {

    private SwapiComponent swapiComponent;

    @Before
    public void setUp(){

        swapiComponent = new SwapiComponent();
    }

    @Test
    public void shouldSetSwapiComponent(){

        String url = "url";

        swapiComponent.setUrl(url);

        assertThat(swapiComponent.getUrl(), is(url));
    }
}
