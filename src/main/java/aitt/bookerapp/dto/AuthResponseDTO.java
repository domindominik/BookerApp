package aitt.bookerapp.dto;

public class AuthResponseDTO {
    private String token;
    public AuthResponseDTO(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}
