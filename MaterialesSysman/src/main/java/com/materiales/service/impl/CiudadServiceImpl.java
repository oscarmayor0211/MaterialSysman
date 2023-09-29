package com.materiales.service.impl;

import com.materiales.models.Ciudad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.materiales.repository.CiudadRepository;
import com.materiales.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@Service
public class CiudadServiceImpl implements CiudadService {
    private static final Logger logger = LoggerFactory.getLogger(CiudadServiceImpl.class);

    @Autowired
    CiudadRepository ciudadRepository;
    @Override
    public Ciudad createCiudad(Ciudad ciudad) {
        logger.info("Cuenta creada!");
        return ciudadRepository.save(ciudad);

    }

    @Override
    public Ciudad updateCiudad(Ciudad ciudad) {
        Ciudad ciudad1 = ciudadRepository.findById(ciudad.getIdciudad()).orElseThrow(() -> new ResourceAccessException("No se encontro la ciudad con el id = " + ciudad.getIdciudad()));
        ciudad1.setIdciudad(ciudad.getIdciudad());
        ciudad1.setNombre(ciudad.getNombre());
        ciudad1.setDepartamento(ciudad.getDepartamento());
        logger.info("Cuenta modificada!");
        return ciudadRepository.save(ciudad1);
    }

    @Override
    public List<Ciudad> getAllCiudad() {
        return ciudadRepository.findAll();
    }

    @Override
    public void deleteCiudad(long id) throws Exception {

        try {
            Ciudad ciudad = ciudadRepository.findById(id).orElseThrow(() -> new ResourceAccessException("No se encontro la ciudad con el id = " + id));
            logger.info("Ciudad Eliminada!");
            ciudadRepository.delete(ciudad);
        } catch (Exception e) {
            logger.warn("Ciudad no encontrada");
            throw new Exception(e.getMessage());
        }
    }
}
