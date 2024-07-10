package aitt.bookerapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${MAILGUN_API_KEY}")
    private String mailgunApiKey;

    @Value("${MAILGUN_DOMAIN}")
    private String mailgunDomain;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        String url = "https://api.mailgun.net/v3/" + mailgunDomain + "/messages";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String auth = "api:" + mailgunApiKey;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        headers.add("Authorization", "Basic " + encodedAuth);
        Map<String, String> body = new HashMap<>();
        body.put("from", "Mailgun Sandbox <postmaster@" + mailgunDomain + ">");
        body.put("to", to);
        body.put("subject", subject);
        body.put("text", text);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}