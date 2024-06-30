package aitt.bookerapp.service;

import aitt.bookerapp.model.ReservationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService {

    private final RestTemplate restTemplate;

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Override
    public void sendReservation(ReservationModel reservation) {
        try {
            restTemplate.postForObject(externalApiUrl, reservation, ReservationModel.class);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
        }
    }
}
