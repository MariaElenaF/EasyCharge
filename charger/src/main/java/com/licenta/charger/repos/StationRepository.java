package com.licenta.charger.repos;
import com.licenta.charger.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station,Long> {

    List <Station> findByNameStationContains(String nameStation);

}

