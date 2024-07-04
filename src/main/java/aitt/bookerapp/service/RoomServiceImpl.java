package aitt.bookerapp.service;

import aitt.bookerapp.model.RoomModel;
import aitt.bookerapp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<RoomModel> getAllRooms(){
        return roomRepository.findAll();
    }

    @Override
    public RoomModel getRoomById(Long id) {
        return roomRepository.getReferenceById(id);
    }

    @Override
    public RoomModel saveRoom(RoomModel room){
        try {
            return roomRepository.save(room);
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException("Room already exists", e);
        }
    }

    @Override
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public boolean existsRoomById(Long id) {
        return roomRepository.existsById(id);
    }
}
