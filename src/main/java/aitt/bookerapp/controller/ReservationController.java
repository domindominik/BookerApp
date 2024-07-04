package aitt.bookerapp.controller;

import aitt.bookerapp.model.ReservationModel;
import aitt.bookerapp.service.ReservationService;
import aitt.bookerapp.service.RoomService;
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
    private final RoomService roomService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationModel> save(@RequestBody ReservationModel reservation) {
        ReservationModel savedReservation = reservationService.saveReservation(reservation);
        return ResponseEntity.ok(savedReservation);
    }

    @GetMapping(value = "/get", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationModel> getReservation(@RequestParam Long id) {
        ReservationModel reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReservationModel>> getAllReservations() {
        List<ReservationModel> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationModel> delete(@RequestBody Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservationsByUserId(@RequestParam Long userId) {
        if (!roomService.existsRoomById(userId)) {  // Assuming this should check against a UserService
            return ResponseEntity.status(404).body("User not found");
        }
        List<ReservationModel> reservations = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping(value = "/room", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReservationsByRoom(@RequestParam Long roomId) {
        if (!roomService.existsRoomById(roomId)) {
            return ResponseEntity.status(404).body("Room not found");
        }
        List<ReservationModel> reservations = reservationService.getReservationsByRoomId(roomId);
        return ResponseEntity.ok(reservations);
    }
}
