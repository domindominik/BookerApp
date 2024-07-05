package aitt.bookerapp.controller;

import aitt.bookerapp.model.OfficeModel;
import aitt.bookerapp.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/office")
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeService officeService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<OfficeModel> getOfficeHours(){
        OfficeModel officeHours = officeService.getHours();
        if(officeHours == null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(officeHours);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<OfficeModel> saveOfficeHours(@RequestBody OfficeModel officeHours){
        OfficeModel savedHoursSaved = officeService.saveHours(officeHours);
        return ResponseEntity.ok(savedHoursSaved);
    }
}
