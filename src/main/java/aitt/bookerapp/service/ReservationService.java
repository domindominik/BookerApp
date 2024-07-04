package aitt.bookerapp.service;

import aitt.bookerapp.model.ReservationModel;

import java.util.List;

public interface ReservationService {
    List<ReservationModel> getAllReservations();
    List<ReservationModel> getReservationsByUserId(Long userId);
    List<ReservationModel> getReservationsByRoomId(Long roomId);
    ReservationModel getReservationById(Long id);
    ReservationModel saveReservation(ReservationModel reservation);
    ReservationModel deleteReservation(Long id);
}
