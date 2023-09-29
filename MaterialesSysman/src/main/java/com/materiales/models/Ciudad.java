package com.materiales.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ciudades")
public class Ciudad implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idciudad;
    private String nombre;
    private String departamento;

    
   
    
}
