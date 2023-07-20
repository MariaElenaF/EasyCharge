package com.licenta.charger.controllers;
import com.licenta.charger.models.Station;
import com.licenta.charger.dtos.StationDto;
import com.licenta.charger.models.StationType;
import com.licenta.charger.repos.StationRepository;
import com.licenta.charger.repos.StationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private StationTypeRepository stationTypeRepository;

    @GetMapping
    public List<Station> getStation() {
        return stationRepository.findAll();

    }

    /*
        @PostMapping
        public Station saveStation(@RequestBody StationDto stationDto){
            //return stationRepository.save(station);
            StationType stationType = stationTypeRepository.findById(stationDto.getStationType()).orElseThrow(() -> new RuntimeException("Error while retrieving stationDto type"));
            Station station = new Station();
            station.setStationType(stationType);
            station.setNameStation(stationDto.getNameStation());
            station.setLocation(stationDto.getLocation());
            station.setOpen(stationDto.getOpen());

            return stationRepository.save(station);



        }
     */
    /*
    @PostMapping
    public Station saveStation(@RequestBody Station station) {
        return stationRepository.save(station);
    }

     */

    @PostMapping
    public Station saveStation(@RequestBody StationDto stationDto){
        StationType stationType = stationTypeRepository.findById(stationDto.getStationTypeId())
                .orElseThrow(() -> new RuntimeException("Error while retrieving stationDto type."));
        Station station = new Station();
        station.setStationType(stationType);
        station.setNameStation(stationDto.getNameStation());
        station.setLocation(stationDto.getLocation());
        station.setOpen(stationDto.getOpen());
        return stationRepository.save(station);
    }

}

