package br.dev.madu.airports.controllers;

import br.dev.madu.airports.entities.Airport;
import br.dev.madu.airports.services.AirportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sesidevb
 */
@RestController
public class AirportController {
    
    @Autowired
    private AirportService airportService;
    
    /**
     * Endpoint /airports/airport
     * Retorna TODOS os aeroportos da base de dados.
     * @return 
     */
    @GetMapping("/airport")
    public List<Airport> findAll() {
        List<Airport> result = airportService.findAll();
        return result;
    }
     
    
    /**
      * Endpoint /airports/city/{cityName}
      * preparando para devolve código de status conforme
      * padronização REST.
      * @param cityName
      * @return
      */
    @GetMapping("/city/{cityName}")
    public ResponseEntity<List<Airport>> findCityIgnoreCase(@PathVariable String cityName){
        List<Airport> result = airportService.findByCity(cityName);
        
        if (result.isEmpty()){
            //Ops..lista vazia...
            //notFound devolve 404
            return ResponseEntity.notFound().build();
            
        } else {
            //Eba! Tem Dados!
            //ok devolve 200
            return ResponseEntity.ok(result);
            
        }
    }
}