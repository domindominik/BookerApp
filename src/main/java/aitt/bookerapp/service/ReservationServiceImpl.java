package aitt.bookerapp.service;

import aitt.bookerapp.model.OfficeModel;
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
    private final OfficeService officeService;

    @Override
    public List<ReservationModel> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<ReservationModel> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Override
    public List<ReservationModel> getReservationsByRoomId(Long roomId) {
        return reservationRepository.findByRoomId(roomId);
    }

    @Override
    public ReservationModel getReservationById(Long id){
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ReservationModel saveReservation(ReservationModel reservation){
        validateReservation(reservation);

        ReservationModel savedReservation = reservationRepository.save(reservation);
        httpService.sendReservation(savedReservation);
        return savedReservation;
    }

    private void validateReservation(ReservationModel reservation) {
        if (!userRepository.existsById(reservation.getUserId())) {
            throw new RuntimeException("User not found");
        }
        if (!roomRepository.existsById(reservation.getRoomId())) {
            throw new RuntimeException("Room not found");
        }
        if (reservation.getQuantity() < 1 || reservation.getQuantity() > 3) {
            throw new RuntimeException("Reservation duration must be between 1 and 3 hours");
        }
        boolean exists = reservationRepository.existsOverlappingReservation(
                reservation.getRoomId(), reservation.getDate(), reservation.getHour(), reservation.getQuantity());
        if (exists) {
            throw new RuntimeException("The room is already booked for the specified time.");
        }
        Integer totalHoursBooked = reservationRepository.getTotalBookingHoursForUser(
                reservation.getUserId(), reservation.getDate());
        if (totalHoursBooked != null && (totalHoursBooked + reservation.getQuantity()) > 3) {
            throw new RuntimeException("User has exceeded the daily booking limit of 3 hours");
        }
        validateOfficeHours(reservation);
    }

    private void validateOfficeHours(ReservationModel reservation) {
        OfficeModel officeHours = officeService.getHours();
        if (officeHours == null) {
            throw new RuntimeException("Office hours not set.");
        }
        int openHours = officeHours.getOpenTime().getHour();
        int closeHours = officeHours.getCloseTime().getHour();
        int reservationStartHour = reservation.getHour();
        int reservationEndHour = reservation.getHour() + reservation.getQuantity();
        if(reservationStartHour < openHours || reservationEndHour > closeHours) {
            throw new RuntimeException("Reservation is outside of office hours.");
        }
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
