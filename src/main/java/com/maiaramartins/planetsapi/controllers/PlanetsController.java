package com.maiaramartins.planetsapi.controllers;

import com.maiaramartins.planetsapi.business.PlanetBO;
import com.maiaramartins.planetsapi.models.PlanetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class PlanetsController {

    @Autowired
    private PlanetBO planetBO;

    @GetMapping()
    public ResponseEntity<List<PlanetModel>> getAll(){
        return ResponseEntity.ok(planetBO.findAllPlanets());
    }

    @GetMapping("/{find_by}")
    public ResponseEntity<PlanetModel> findBy(@PathVariable("find_by") String findBy,
                                               @RequestParam(value = "by", defaultValue = "id") String by){
        PlanetModel byName;
        if(by.equals("name")) byName = planetBO.findByName(findBy);
        else byName = planetBO.findById(findBy);

        if(byName == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(byName);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetModel> deleteById(@PathVariable("id") long id){
        return ResponseEntity.ok(planetBO.deleteById(id));

    }


}