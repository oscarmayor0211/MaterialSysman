package com.materiales.service.impl;

import com.materiales.models.Ciudad;
import com.materiales.models.Material;
import com.materiales.repository.MaterialRepository;
import com.materiales.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@Service
public class MaterialServiceImpl implements MaterialService {
    private static final Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);

    @Autowired
    MaterialRepository materialRepository;
    @Override
    public Material createMaterial(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Material updateMaterial(Material material) {
        Material material1 = materialRepository.findById(material.getId()).orElseThrow(() -> new ResourceAccessException("No se encontro el material con el id = " + material.getId()));
        material1.setId(material.getId());
        material1.setCiudad(material.getCiudad());
        material1.setDescripcion(material.getDescripcion());
        material1.setEstado(material.getEstado());
        material1.setFechaCompra(material.getFechaCompra());
        material1.setFechaVenta(material.getFechaVenta());
        material1.setNombre(material.getNombre());
        material1.setNumeroInterno(material.getNumeroInterno());
        material1.setPrecio(material.getPrecio());
        material1.setSerial(material.getSerial());
        material1.setTipo(material.getTipo());
        return materialRepository.save(material1);
    }

    @Override
    public List<Material> getAllMaterial() {
        return materialRepository.findAll();
    }

    @Override
    public List<Material> findByCiudadNombre(String ciudadNombre) {
        return materialRepository.findByCiudadNombre(ciudadNombre);
    }

    @Override
    public void deleteMaterial(long id) throws Exception {

        try {
            Material material = materialRepository.findById(id).orElseThrow(() -> new ResourceAccessException("No se encontro el material con el id = " + id));
            logger.info("Material Eliminada!");
            materialRepository.delete(material);

        } catch (Exception e) {
            logger.warn("Material no encontrada");
            throw new Exception(e.getMessage());
        }
    }
}
