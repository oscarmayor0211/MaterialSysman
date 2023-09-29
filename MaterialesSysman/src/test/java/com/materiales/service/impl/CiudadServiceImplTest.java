package com.materiales.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.materiales.models.Ciudad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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

import java.util.Arrays;
import java.util.List;
@AutoConfigureMockMvc
@SpringBootTest
class CiudadServiceImplTest {
    private final String URL = "/api/ciudad";
    @Autowired
    MockMvc mvc;
    @Test
    void createCiudad() throws Exception {
        // Simulamos el comportamiento del repositorio
        Ciudad ciudad1 = new Ciudad();
        ciudad1.setIdciudad(1L);
        ciudad1.setNombre("Cali");
        ciudad1.setDepartamento("Valle");

        mvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(ciudad1))).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getAllCiudad() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL)).andDo(print());    }

    public static String toJson(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}