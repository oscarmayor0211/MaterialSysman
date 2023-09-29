package com.materiales.repository;

import com.materiales.models.Material;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MaterialRepository extends JpaRepository<Material, Long> {
	@Query("SELECT m FROM Material m WHERE " +
			"(:tipoParam IS NULL OR m.tipo = :tipoParam) AND " +
			"(:fechaCompraParam IS NULL OR m.fechaCompra = :fechaCompraParam) AND " +
			"(:serialParam IS NULL OR m.serial = :serialParam)")
	List<Material> buscarMaterialesPorFiltros(
			@Param("tipoParam") String tipo,
			@Param("fechaCompraParam") Date fechaCompra,
			@Param("serialParam") String serial
	);


	List<Material> findByCiudadNombre(String ciudadNombre);

}
