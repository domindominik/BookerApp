package aitt.bookerapp.repository;

import aitt.bookerapp.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository <ReservationModel, Long> {
    boolean existsByRoomIdAndDateAndHour(Long roomId, LocalDate date, int hour);
}
