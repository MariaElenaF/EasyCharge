package com.licenta.charger.dtos;

import lombok.Data;

@Data
public class StationDto {
    private Long id;

    private String nameStation;

    private String location;

    private boolean open;

    private Long stationTypeId;

    public boolean getOpen() {
        return open;
    }

}
