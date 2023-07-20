package com.licenta.charger.controllers;
import com.licenta.charger.dtos.StationDto;
import com.licenta.charger.models.Station;
import com.licenta.charger.models.StationType;
import com.licenta.charger.repos.StationRepository;
import com.licenta.charger.repos.BookingRepository;
import com.licenta.charger.repos.StationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins ="http://localhost:63342/")

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private StationTypeRepository stationTypeRepository;
    @Autowired
    private BookingRepository bookingRepository;


    @GetMapping
    public List<Station> getStation(){
        return stationRepository.findAll();

    }


    @GetMapping(path = "/sorted/{field}/{direction}")
    public List<Station> getSortStation(@PathVariable String field, @PathVariable Sort.Direction direction) {
      //  return stationRepository.findAllByOrderByOpenDesc();
        if (direction.isAscending()) {
            return stationRepository.findAll(Sort.by(Sort.Direction.ASC, field));
        }else {
            return stationRepository.findAll(Sort.by(Sort.Direction.DESC, field));
        }

    }


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

    @PutMapping
    public Station updateStation(@RequestBody StationDto stationDto){
        StationType stationType = stationTypeRepository.findById(stationDto.getStationTypeId())
                .orElseThrow(() -> new RuntimeException("Error while retrieving stationDto type."));
        Station station=stationRepository.findById(stationDto.getId())
                .orElseThrow(() -> new RuntimeException("Error while retrieving bookingDto type."));
        station.setStationType(stationType);
        station.setNameStation(stationDto.getNameStation());
        station.setLocation(stationDto.getLocation());
        station.setOpen(stationDto.getOpen());
        return stationRepository.save(station);

    }

    @GetMapping("/{id}")
    public Station getStation(@PathVariable Long id){
        return stationRepository.findById(id).orElseThrow(() -> new RuntimeException("error"));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStation( @PathVariable Long id){
        stationRepository.deleteById(id);

    }
    //Search
    @GetMapping(path = "/nameStation/{nameStation}")
    public List<Station> getStationsByName(@PathVariable String nameStation){
        return stationRepository.findByNameStationContains(nameStation);
    }

}

