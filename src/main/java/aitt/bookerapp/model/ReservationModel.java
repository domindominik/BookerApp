package aitt.bookerapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"roomId", "date", "hour"})
})
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long roomId;
    private int hour;
    private int quantity;
    private LocalDate date;

    @Column(nullable = false, updatable = false)
    private Timestamp timestamp;

    @PrePersist
    protected void onCreate() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}