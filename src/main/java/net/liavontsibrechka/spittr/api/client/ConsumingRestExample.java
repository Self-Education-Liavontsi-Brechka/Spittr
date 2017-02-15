package net.liavontsibrechka.spittr.api.client;

import net.liavontsibrechka.spittr.Spitter;
import net.liavontsibrechka.spittr.Spittle;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Objects;

public class ConsumingRestExample {
    // GET
    public Spitter fetchSpitter(String spitterId) {
        RestTemplate template = new RestTemplate();
//        Map<String, String> variables = new HashMap<>();
//        variables.put("id", userId);
//        template.getForObject("http://graph.facebook.com/{id}", Spitter.class, variables);
        return template.getForObject("url", Spitter.class, spitterId);
    }

    public ResponseEntity<Spitter> fetchSpitterEntity(String spitterId) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Spitter> entity =
                template.getForEntity("url", Spitter.class, spitterId);
        System.out.println(new Date(entity.getHeaders().getLastModified()));
        return entity;
    }

    // PUT
    public void updateSpittle(Spittle spittle) {
        RestTemplate template = new RestTemplate();
        template.put("http://localhost:8080/spittr-api/spittles/{id}", spittle, spittle.getId());
    }

    // DELETE
    public void deleteSpittle(long id) {
        RestTemplate rest = new RestTemplate();
        rest.delete("http://localhost:8080/spittr-api/spittles/{id}", id);
    }

    // POST
    public Spitter postSpitterForObject(Spitter spitter) {
        RestTemplate rest = new RestTemplate();
        return rest.postForObject("http://localhost:8080/spittr-api/spitters", spitter, Spitter.class);
    }

    public String postSpitter(Spitter spitter) {
        RestTemplate rest = new RestTemplate();
        return rest.postForLocation("http://localhost:8080/spittr-api/spitters", spitter).toString();
    }

    // exchange
    public Spitter getSpitterByExchange(long id) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        HttpEntity<Objects> requestEntity = new HttpEntity<>(headers);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Spitter> response =
                template.exchange("http://localhost:8080/spittr-api/spitters/{spitter}",
                        HttpMethod.GET, requestEntity, Spitter.class, id);
        return response.getBody();
    }
}
