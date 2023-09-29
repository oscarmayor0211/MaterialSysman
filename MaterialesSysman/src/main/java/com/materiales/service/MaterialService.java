package com.materiales.service;

import com.materiales.models.Material;

import java.util.List;

public interface MaterialService {

    Material createMaterial(Material material);
    Material updateMaterial( Material material);
    List<Material> getAllMaterial();
    List<Material> findByCiudadNombre(String ciudadNombre);

    void deleteMaterial(long id) throws Exception;
}
