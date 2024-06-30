package aitt.bookerapp.controller;

import aitt.bookerapp.dto.IdRequest;
import aitt.bookerapp.model.ReservationModel;
import aitt.bookerapp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationModel> save(@RequestBody ReservationModel reservation) {
        ReservationModel savedReservation = reservationService.saveReservation(reservation);
        return ResponseEntity.ok(savedReservation);
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationModel> getReservation(@RequestBody IdRequest idRequest) {
        ReservationModel reservation = reservationService.getReservationById(idRequest.getId());
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<ReservationModel>> getAllReservations() {
        List<ReservationModel> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationModel> delete(@RequestBody IdRequest idRequest) {
        reservationService.deleteReservation(idRequest.getId());
        return ResponseEntity.ok().build();
    }
}
