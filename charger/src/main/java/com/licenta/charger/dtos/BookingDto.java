package com.licenta.charger.dtos;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class BookingDto {
    @NonNull
    private Long id;

    @NonNull
    private LocalDateTime startDate;
    int duration;
    @NonNull
    private String namePerson;
    @NonNull
    private String licensePlate;
    @NonNull
    private Long stationId;

    private BookingDto(){

    };

    public BookingDto(Long id,LocalDateTime startDate, int duration, String namePerson, String licensePlate, Long stationId) {
        this.id = id;
        this.startDate = startDate;
        this.duration = duration;
        this.namePerson = namePerson;
        this.licensePlate = licensePlate;
        this.stationId = stationId;
    }

}
