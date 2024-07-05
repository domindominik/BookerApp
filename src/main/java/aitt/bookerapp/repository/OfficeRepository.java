package aitt.bookerapp.repository;

import aitt.bookerapp.model.OfficeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<OfficeModel, Long> {
}
