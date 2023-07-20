package com.licenta.charger.controllers;
import com.licenta.charger.dtos.BookingDto;
import com.licenta.charger.models.Booking;
import com.licenta.charger.models.Station;
import com.licenta.charger.repos.BookingRepository;
import com.licenta.charger.repos.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private StationRepository stationRepository;

    @GetMapping
    public List<Booking> getBooking(){
        return bookingRepository.findAll();

    }
/*
    @PostMapping
    public Booking saveBooking(@RequestBody Booking booking){
        return bookingRepository.save(booking);

    }

 */
    @PostMapping
    public Booking saveBooking(@RequestBody BookingDto bookingDto){
        Station station = stationRepository.findById(bookingDto.getStationId())
                .orElseThrow(() -> new RuntimeException("Error while retrieving stationDto type."));
        Booking booking = new Booking();
        booking.setStationId(station);
        booking.setStartDate(bookingDto.getStartDate());
        booking.setDuration(bookingDto.getDuration());
        booking.setNamePerson(bookingDto.getNamePerson());
        booking.setLicensePlate(bookingDto.getLicensePlate());
        return bookingRepository.save(booking);
    }
}

