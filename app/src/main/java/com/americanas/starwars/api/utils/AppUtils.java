package com.americanas.starwars.api.utils;

import org.springframework.beans.BeanUtils;

import com.americanas.starwars.api.models.PlanetsModel;
import com.americanas.starwars.domain.models.Planets;

public class AppUtils {

    public static PlanetsModel entityToModel(Planets planets) {
    	PlanetsModel planetsModel = new PlanetsModel();
        BeanUtils.copyProperties(planets, planetsModel);
        return planetsModel;
    }

    public static Planets modelToEntity(PlanetsModel planetsModel) {
        Planets planets = new Planets();
        BeanUtils.copyProperties(planetsModel, planets);
        return planets;
    }
}