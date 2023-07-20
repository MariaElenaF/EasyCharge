package com.licenta.charger.controllers;
import com.licenta.charger.dtos.BookingDto;
import com.licenta.charger.models.Booking;
import com.licenta.charger.models.Station;
import com.licenta.charger.repos.BookingRepository;
import com.licenta.charger.repos.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@CrossOrigin(origins ="http://localhost:63342/")

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private StationRepository stationRepository;

    @GetMapping
    public List<Booking> getBooking(){

            return bookingRepository.findAllByOrderByNamePersonAsc();
    }


    @PostMapping
    public Booking saveBooking(@Validated @RequestBody BookingDto bookingDto){
        Station station = stationRepository.findById(bookingDto.getStationId())
                .orElseThrow(() -> new RuntimeException("Error while retrieving stationDto type."));
        Booking booking = new Booking();
        booking.setStationId(station);
        booking.setStartDate(bookingDto.getStartDate());
        booking.setEndDate( bookingDto.getStartDate().plusMinutes(bookingDto.getDuration()) );
        booking.setNamePerson(bookingDto.getNamePerson());
        booking.setLicensePlate(bookingDto.getLicensePlate());

        List <Booking> bookingList=bookingRepository.findByEndDateAfterAndStartDateBeforeAndStationId(bookingDto.getStartDate(),bookingDto.getStartDate().plusMinutes(bookingDto.getDuration()),station);

        if(bookingList.size()>0)
            throw new RuntimeException("Error: mai incearca");

        if(booking.getStationId().getOpen()){
            return bookingRepository.save(booking);
        }else{
            throw new RuntimeException("Error: incearca la statiile bune");
        }
    }


    @PutMapping
    public Booking updateBooking(@Validated @RequestBody BookingDto bookingDto){
        Station station=stationRepository.findById(bookingDto.getStationId())
                .orElseThrow(() -> new RuntimeException("Error while retrieving stationDto type."));
        Booking booking=bookingRepository.findById(bookingDto.getId())
                .orElseThrow(() -> new RuntimeException("Error while retrieving bookingDto type."));
        booking.setStationId(station);
        booking.setStartDate(bookingDto.getStartDate());
        booking.setEndDate(bookingDto.getStartDate().plusMinutes(bookingDto.getDuration()) );
        booking.setNamePerson(bookingDto.getNamePerson());
        booking.setLicensePlate(bookingDto.getLicensePlate());

        List <Booking> bookingList=bookingRepository.findByEndDateAfterAndStartDateBeforeAndStationId(bookingDto.getStartDate(),bookingDto.getStartDate().plusMinutes(bookingDto.getDuration()),station);

        if(bookingList.size()>0)
            throw new RuntimeException("Error: mai incearca");

        if(booking.getStationId().getOpen()){
            return bookingRepository.save(booking);
        }else{
            throw new RuntimeException("Error: incearca la statiile bune");
        }

    }


    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id){
        return bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("error"));
    }


    @DeleteMapping(path = "/{id}")
    public void deleteBooking( @PathVariable Long id){
        bookingRepository.deleteById(id);
    }


}


