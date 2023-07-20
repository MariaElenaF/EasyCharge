package com.licenta.charger.controllers;
import com.licenta.charger.models.StationType;
import com.licenta.charger.repos.StationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins ="http://localhost:63342/")
@RestController
@RequestMapping("/api/stationTypes")
public class StationTypeController {

    @Autowired
    private StationTypeRepository stationTypeRepository;

    @GetMapping
    public List<StationType> getStationTypes(){
        return stationTypeRepository.findAllByOrderByPowerDesc();

    }

    @PostMapping
    public StationType saveStationType(@RequestBody StationType stationType){
        return stationTypeRepository.save(stationType);

    }

    @PutMapping
    public StationType updateStationType(@RequestBody StationType updateStationDto){

        StationType updateStationType = stationTypeRepository.findById(updateStationDto.getId())
                .orElseThrow(() -> new RuntimeException("Error while retrieving stationType."));

        updateStationType.setPower(updateStationDto.getPower());
        updateStationType.setName(updateStationDto.getName());
        updateStationType.setPlugType(updateStationDto.getPlugType());


        return stationTypeRepository.save(updateStationType);

    }

    @GetMapping("/{id}")
    public StationType getStationType(@PathVariable Long id){
        return stationTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("error"));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBooking( @PathVariable Long id){
        stationTypeRepository.deleteById(id);
    }

}