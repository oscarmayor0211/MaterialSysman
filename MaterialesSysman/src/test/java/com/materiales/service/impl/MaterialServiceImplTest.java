package com.materiales.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.materiales.models.Ciudad;
import com.materiales.models.Material;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest

class MaterialServiceImplTest {
    private final String URL = "/api/material";
    @Autowired
    MockMvc mvc;
    @Test
    void createMaterial() throws Exception {
        Ciudad ciudad1 = new Ciudad();
        ciudad1.setIdciudad(1L);
        ciudad1.setNombre("Cali");
        ciudad1.setDepartamento("Valle");
        Material material = new Material();

        material.setTipo("tipo");
        material.setSerial("wewe");
        material.setNombre("ladrillo");
        material.setPrecio(4000);
        material.setEstado("activo");
        material.setNumeroInterno("2332");
        material.setDescripcion("ladrillo limpio");
        material.setCiudad(ciudad1);
        mvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(material))).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getAllMaterial() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL)).andDo(print());

    }

    @Test
    void findByCiudadNombre() {
    }
    public static String toJson(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}