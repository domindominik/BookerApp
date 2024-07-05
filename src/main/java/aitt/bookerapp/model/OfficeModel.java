package aitt.bookerapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class OfficeModel {
    @Id
    private Long id;
    private String name;
    private LocalTime openTime;
    private LocalTime closeTime;
}
