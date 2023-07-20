package com.licenta.charger.controllers;
import com.licenta.charger.models.StationType;
import com.licenta.charger.repos.StationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stationType")
public class StationTypeController {

    @Autowired
    private StationTypeRepository stationTypeRepository;

    @GetMapping
    public List<StationType> getStationTypes(){
        return stationTypeRepository.findAll();

    }

    @PostMapping
    public StationType saveStationType(@RequestBody StationType stationType){
        return stationTypeRepository.save(stationType);

    }
}
