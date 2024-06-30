package aitt.bookerapp.service;

import aitt.bookerapp.model.ReservationModel;

public interface HttpService {
    void sendReservation(ReservationModel reservation);
}
