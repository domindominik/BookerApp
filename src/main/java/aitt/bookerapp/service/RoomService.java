package aitt.bookerapp.service;

import aitt.bookerapp.model.RoomModel;
import aitt.bookerapp.repository.RoomRepository;

import java.util.List;

public interface RoomService {
    List<RoomModel> getAllRooms();
    RoomModel getRoomById(Long id);
    RoomModel saveRoom(RoomModel room);
    void deleteRoomById(Long id);
    boolean existsRoomById(Long id);
}
