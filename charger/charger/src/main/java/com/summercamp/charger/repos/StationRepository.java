package com.licenta.charger.repos;
import com.licenta.charger.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station,Long> {
}

