package com.planetapi.business;

import com.planetapi.exception.CannotBeBlankException;
import com.planetapi.model.Planet;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ValidationImplTest {

    private ValidationImpl validation;

    @Before
    public void setUp(){

        validation = spy(new ValidationImpl());
    }

    @Test(expected = CannotBeBlankException.class)
    public void shouldNotPassIntoValidationFieldsWithNullPlanet(){

        validation.validateFields(null);
    }

    @Test(expected = CannotBeBlankException.class)
    public void shouldNotPassIntoValidationFieldsWithPlanetWithNullName(){

        Planet planet = new Planet();

        planet.setName(null);
        planet.setTerrain("terrain");
        planet.setClimate("climate");

        validation.validateFields(planet);
    }

    @Test(expected = CannotBeBlankException.class)
    public void shouldNotPassIntoValidationFieldsWithPlanetWithNullTerrain(){

        Planet planet = new Planet();

        planet.setName("name");
        planet.setTerrain(null);
        planet.setClimate("climate");

        validation.validateFields(planet);
    }

    @Test(expected = CannotBeBlankException.class)
    public void shouldNotPassIntoValidationFieldsWithPlanetWithNullClimate(){

        Planet planet = new Planet();

        planet.setName("name");
        planet.setTerrain("terrain");
        planet.setClimate(null);

        validation.validateFields(planet);
    }

    @Test(expected = CannotBeBlankException.class)
    public void shouldNotPassIntoValidationFieldsWithPlanetWithBlankName(){

        Planet planet = new Planet();

        planet.setName("");
        planet.setTerrain("terrain");
        planet.setClimate("climate");

        validation.validateFields(planet);
    }

    @Test(expected = CannotBeBlankException.class)
    public void shouldNotPassIntoValidationFieldsWithPlanetWithBlankTerrain(){

        Planet planet = new Planet();

        planet.setName("name");
        planet.setTerrain("");
        planet.setClimate("climate");

        validation.validateFields(planet);
    }

    @Test(expected = CannotBeBlankException.class)
    public void shouldNotPassIntoValidationFieldsWithPlanetWithBlankClimate(){

        Planet planet = new Planet();

        planet.setName("name");
        planet.setTerrain("terrain");
        planet.setClimate("");

        validation.validateFields(planet);
    }

    @Test(expected = CannotBeBlankException.class)
    public void shouldNotPassIntoValidationFieldWithNullField(){

        validation.validateField("field", null);
    }

    @Test(expected = CannotBeBlankException.class)
    public void shouldNotPassIntoValidationFieldWithBlankField(){

        validation.validateField("field", "");
    }

    @Test
    public void shouldPassIntoValidation(){

        Planet planet = new Planet();

        planet.setName("name");
        planet.setTerrain("terrain");
        planet.setClimate("climate");

        validation.validateFields(planet);

        verify(validation).validateField("name", planet.getName());
        verify(validation).validateField("terrain", planet.getTerrain());
        verify(validation).validateField("climate", planet.getClimate());
    }
}
