package com.materiales.controllers;

import com.materiales.models.Ciudad;
import com.materiales.service.impl.CiudadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/ciudad")
public class CiudadController {
    @Autowired
    CiudadServiceImpl ciudadService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Ciudad>> getAllCiudad(){
        return ResponseEntity.ok().body(ciudadService.getAllCiudad());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Ciudad> createMaterial(@RequestBody Ciudad ciudad){
        return ResponseEntity.ok().body(ciudadService.createCiudad(ciudad));
    }

    @PutMapping(value = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Ciudad> updateCiudad(@PathVariable Long id , @RequestBody Ciudad ciudad){
        ciudad.setIdciudad(id);
        return ResponseEntity.ok().body(ciudadService.updateCiudad(ciudad));
    }
}
