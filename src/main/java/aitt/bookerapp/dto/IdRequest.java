package aitt.bookerapp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class IdRequest {
    private Long id;
    private Long userId;

}
