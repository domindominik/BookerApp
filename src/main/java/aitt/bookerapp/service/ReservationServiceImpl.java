package aitt.bookerapp.service;

import aitt.bookerapp.model.ReservationModel;
import aitt.bookerapp.repository.ReservationRepository;
import aitt.bookerapp.repository.RoomRepository;
import aitt.bookerapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final HttpService httpService;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<ReservationModel> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public ReservationModel getReservationById(Long id){
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ReservationModel saveReservation(ReservationModel reservation){
        if (!userRepository.existsById(reservation.getUserId())){
            throw new RuntimeException("User not found");
        }
        if (!roomRepository.existsById(reservation.getRoomId())){
            throw new RuntimeException("Room not found");
        }
        boolean exists = reservationRepository.existsByRoomIdAndDateAndHour(
                reservation.getRoomId(), reservation.getDate(), reservation.getHour()
        );
        if (exists) {
            throw new RuntimeException("The room is already booked for the specified time.");
        }
        ReservationModel savedReservation = reservationRepository.save(reservation);
        httpService.sendReservation(savedReservation);
        return savedReservation;
    }

    @Override
    public ReservationModel deleteReservation(Long id) {
        ReservationModel reservationToDelete = reservationRepository.findById(id).orElse(null);
        if (reservationToDelete != null) {
            reservationRepository.deleteById(id);
            httpService.sendReservation(reservationToDelete);
        }
        return reservationToDelete;
    }
}
