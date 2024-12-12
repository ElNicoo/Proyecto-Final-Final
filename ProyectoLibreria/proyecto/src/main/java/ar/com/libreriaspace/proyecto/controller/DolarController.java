package ar.com.libreriaspace.proyecto.controller;

import org.springframework.web.bind.annotation.RestController;

import ar.com.libreriaspace.proyecto.model.Dolar;
import ar.com.libreriaspace.proyecto.service.DolarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class DolarController {

    @Autowired
    private DolarService dolarService;
    
    @GetMapping("/dolar")
    @ResponseBody
    public Dolar obtenerDolar() {
        return dolarService.obtenerDolar();
    }
    

}
