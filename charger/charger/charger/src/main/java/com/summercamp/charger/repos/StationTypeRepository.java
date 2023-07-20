package com.licenta.charger.repos;

import com.licenta.charger.models.StationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationTypeRepository extends JpaRepository<StationType,Long> {
}
