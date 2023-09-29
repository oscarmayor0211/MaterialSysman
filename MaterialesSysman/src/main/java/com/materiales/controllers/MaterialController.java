package com.materiales.controllers;

import com.materiales.exception.FechaInvalida;
import com.materiales.models.Material;
import com.materiales.repository.MaterialRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.materiales.service.impl.MaterialServiceImpl;

import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Api(tags = "Material")

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/material")
public class MaterialController {
    @Autowired
    MaterialServiceImpl materialService;
    @Autowired
    MaterialRepository materialRepository;

    @ApiOperation("Obtener todos los materiales")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Material>> getAllMaterial(){
        return ResponseEntity.ok().body(materialService.getAllMaterial());
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Material> createMaterial(@RequestBody Material material) throws FechaInvalida {
        Date fechaCompra = material.getFechaCompra();
        Date fechaVenta = material.getFechaVenta();
        // Validar que fechaCompra sea anterior o igual a fechaVenta
        if (fechaCompra != null && fechaVenta != null && fechaCompra.after(fechaVenta)) {
             throw new FechaInvalida();
        }
        return ResponseEntity.ok().body(materialService.createMaterial(material));
    }

    @PutMapping(value = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Material> updateMaterial(@PathVariable Long id , @RequestBody Material material) throws FechaInvalida {
        Date fechaCompra = material.getFechaCompra();
        Date fechaVenta = material.getFechaVenta();
        // Validar que fechaCompra sea anterior o igual a fechaVenta
        if (fechaCompra != null && fechaVenta != null && fechaCompra.after(fechaVenta)) {
            throw new FechaInvalida();
        }
        material.setId(id);
        return ResponseEntity.ok().body(materialService.updateMaterial(material));
    }
    @ApiOperation("Obtener todos los materiales por tipo, fecha de compra y serial")
    // Buscar materiales por tipo, fecha de compra y serial
    @GetMapping("/buscar")
    public List<Material> buscarMateriales(
        @RequestParam(required = false,name = "tipo") String tipo,
        @RequestParam(required = false,name= "fechaCompra" )String fechaCompra,
        @RequestParam(required = false,name= "serial" )String serial) throws ParseException {
        System.out.println(tipo + fechaCompra );
        // Convierte la cadena de fecha de compra a un objeto Date si se proporciona
        Date fecha = null;
        if (fechaCompra != null && !fechaCompra.isEmpty()) {
            // Realiza la conversión de la cadena de fecha a Date aquí
            // Asume que la fecha está en el formato yyyy-MM-dd
            try {
                fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaCompra);
            } catch ( Exception  e) {
               throw new ParseException(e.getMessage());
                //  lanzar una excepción
            }
        }

        // Utiliza la consulta personalizada del repositorio para buscar materiales
        System.out.println(materialRepository.buscarMaterialesPorFiltros(tipo  ,fecha , serial));
        return materialRepository.buscarMaterialesPorFiltros(tipo , fecha , serial);
    }

    @ApiOperation("Obtener todos los materiales por ciudad")
    @GetMapping("/buscar/ciudad/{nombreCiudad}")
    public ResponseEntity< List<Material> >buscarMaterialesPorCiudad(
            @PathVariable  String nombreCiudad){
        // Utiliza la consulta personalizada del repositorio para buscar materiales
        System.out.println(nombreCiudad);
        return ResponseEntity.ok().body(materialService.findByCiudadNombre(nombreCiudad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCuenta(@PathVariable Long id) {
        try {
            materialService.deleteMaterial(id);
            return ResponseEntity.ok("El material con ID " + id + " ha sido eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al eliminar el material.");
        }
    }
}
