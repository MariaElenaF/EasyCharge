package com.licenta.charger.dtos;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingDto {
    private Long id;

    private LocalDateTime startDate;

    int duration;

    private String namePerson;

    private String licensePlate;

    private Long stationId;
}
