package com.materiales.repository;

import com.materiales.models.Material;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MaterialRepository extends JpaRepository<Material, Long> {
	@Query("SELECT m FROM Material m WHERE " +
			"(:tipo IS NULL OR m.tipo = :tipo  OR m.fechaCompra = :fechaCompra OR m.serial = :serial ) ")
	List<Material> buscarMaterialesPorFiltros(
			@Param("tipo") String tipo,
			@Param("fechaCompra") Date fechaCompra,
			@Param("serial") String serial

			);


	List<Material> findByCiudadNombre(String ciudadNombre);

}
