package aitt.bookerapp.repository;

import aitt.bookerapp.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository <RoomModel, Long> {
    boolean existsById(Long id);
}
