package com.licenta.charger.models;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Booking{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endTime")
    private LocalDateTime endDate;

    @Column(name = "namePerson")
    private String namePerson;

    @Column(name = "licensePlate")
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "station_Id")
    private Station stationId;
}
