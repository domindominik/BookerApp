package aitt.bookerapp.controller;

import aitt.bookerapp.dto.IdRequest;
import aitt.bookerapp.model.RoomModel;
import aitt.bookerapp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody RoomModel room) {
        try {
            RoomModel savedRoom = roomService.saveRoom(room);
            return ResponseEntity.ok(savedRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRoomById(@RequestBody IdRequest idRequest) {
        RoomModel room = roomService.getRoomById(idRequest.getId());
        if (room != null){
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RoomModel>> getAllRooms() {
        List<RoomModel> roomModels = roomService.getAllRooms();
        return ResponseEntity.ok(roomModels);
    }

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestBody IdRequest idRequest) {
        roomService.deleteRoomById(idRequest.getId());
        return ResponseEntity.ok().build();
    }
}
