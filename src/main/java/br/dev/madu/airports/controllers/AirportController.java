package br.dev.madu.airports.controllers;

import DTO.AirportMinDTO;
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
    public List<Airport> findCityIgnoreCase(@PathVariable String cityName){
        List<Airport> result = airportService.findByCity(cityName);
        return result;
    }
    /**
      * Endpoint /airports/country/{countryName}
      * preparando para devolve código de status conforme
      * padronização REST.
      * @param countryName
      * @return
      */
    
    @GetMapping("/country/{countryName}")
    public ResponseEntity<List<AirportMinDTO>> findCountryIgnoreCase(@PathVariable String countryName){
        List<AirportMinDTO> result = airportService.findByCountry(countryName);
        
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
    public AirportService getAirportService() {
        return airportService;
}
     @GetMapping("/iatacode/{iataCode}")
    public ResponseEntity<Airport> findByIataCode(@PathVariable String iataCode){
        Airport result = airportService.findByIataCode(iataCode);
        
        if (result == null){
                return ResponseEntity.notFound().build();
                
            }else {
                return ResponseEntity.ok(result);
            }
        }
    }
