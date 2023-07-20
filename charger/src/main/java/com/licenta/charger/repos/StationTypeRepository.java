package com.licenta.charger.repos;

import com.licenta.charger.models.StationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationTypeRepository extends JpaRepository<StationType,Long> {
    List <StationType> findAllByOrderByPowerDesc();

}

