package com.materiales.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@Entity
@Table(name="materiales")
public class Material implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String serial;
    private String numeroInterno;
    private double precio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaCompra;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaVenta;
    private String estado; // activo, disponible, asignado

    @ManyToOne
    @JoinColumn(name = "idciudad")
    private Ciudad ciudad;

}
