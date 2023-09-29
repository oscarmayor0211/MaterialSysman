package com.materiales.service;

import com.materiales.models.Ciudad;

import java.util.List;

public interface CiudadService {
    Ciudad createCiudad(Ciudad ciudad);
    Ciudad updateCiudad( Ciudad ciudad);
    List<Ciudad> getAllCiudad();
    void deleteCiudad(long id) throws Exception;

}
