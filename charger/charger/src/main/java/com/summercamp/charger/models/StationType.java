package com.licenta.charger.models;
import lombok.Data;

import javax.persistence.*;

@Entity  //vrem sa fie tabel
@Data
public class StationType{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(name="power")
    int power;

    @Column(name="nume")
    private String name;

    @Column(name="plugType")
    private String plugType;
}
