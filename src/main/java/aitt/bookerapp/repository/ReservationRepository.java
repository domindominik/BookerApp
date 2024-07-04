package aitt.bookerapp.repository;

import aitt.bookerapp.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository <ReservationModel, Long> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM ReservationModel r " +
            "WHERE r.roomId = :roomId " +
            "AND r.date = :date " +
            "AND (:hour BETWEEN r.hour AND (r.hour + r.quantity - 1) " +
            "OR (r.hour BETWEEN :hour AND (:hour + :quantity - 1)))")
    boolean existsOverlappingReservation(Long roomId, LocalDate date, int hour, int quantity);

    @Query("SELECT SUM(r.quantity) FROM ReservationModel r " +
            "WHERE r.userId = :userId " +
            "AND r.date = :date")
    Integer getTotalBookingHoursForUser(Long userId, LocalDate date);

    List<ReservationModel> findByUserId(Long userId);
    List<ReservationModel> findByRoomId(Long roomId);
}
