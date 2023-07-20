package com.licenta.charger.repos;
import com.licenta.charger.models.Booking;
import com.licenta.charger.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByEndDateAfterAndStartDateBeforeAndStationId(LocalDateTime startTime, LocalDateTime endDate, Station station);

    List<Booking> findAllByOrderByNamePersonAsc();

}

