package com.americanas.starwars.api.utils;

import org.springframework.beans.BeanUtils;

import com.americanas.starwars.api.models.PlanetsModel;
import com.americanas.starwars.api.models.PlanetsResumeModel;
import com.americanas.starwars.domain.models.Planets;

public class AppUtils {

    public static PlanetsModel entityToModel(Planets planets) {
    	PlanetsModel planetsModel = new PlanetsModel();
        BeanUtils.copyProperties(planets, planetsModel);
        return planetsModel;
    }
    
    public static PlanetsResumeModel entityToResumeModel(Planets planets) {
    	PlanetsResumeModel planetsResumeModel = new PlanetsResumeModel();
        BeanUtils.copyProperties(planets, planetsResumeModel);
        return planetsResumeModel;
    }

    public static Planets resumeModelToEntity(PlanetsResumeModel planetsResumeModel) {
        Planets planets = new Planets();
        BeanUtils.copyProperties(planetsResumeModel, planets);
        return planets;
    }
    
    public static Planets modelToEntity(PlanetsModel planetsModel) {
        Planets planets = new Planets();
        BeanUtils.copyProperties(planetsModel, planets);
        return planets;
    }
}