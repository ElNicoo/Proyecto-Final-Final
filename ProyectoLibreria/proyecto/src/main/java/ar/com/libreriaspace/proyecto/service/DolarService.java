package ar.com.libreriaspace.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.com.libreriaspace.proyecto.model.Dolar;

@Service
public class DolarService {

    @Value("${dolar.api.url}")
    private String dolarApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Dolar obtenerDolar() {
        ResponseEntity<Dolar> response = restTemplate.getForEntity(dolarApiUrl, Dolar.class);
        if (response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else{
            throw new RuntimeException("Error al obtener el tipo de cambio del dólar");
        }
    }

}
