package com.licenta.charger.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Station{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JoinColumn(name="id",nullable = false)
    private Long id;


    @Column(name="location")
    private String location;

    @Column(name="open")
    private boolean open;
    public boolean getOpen() {
        return open;
    }

    @Column(name="nameStation")
    private String nameStation;

    @ManyToOne
    @JoinColumn(name="station_type_id")
    private StationType stationType;
}

