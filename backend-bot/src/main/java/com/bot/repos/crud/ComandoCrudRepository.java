package com.bot.repos.crud;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bot.tables.ComandoTable;

@Repository
public interface ComandoCrudRepository extends CrudRepository<ComandoTable,Integer> {
	
	
	@Query(   "SELECT "
			+ "id, "
			+ "titulo, "
			+ "respuesta, "
			+ "nombre, "
			+ "id_especialidad, "
			+ "id_comando_padre, "
			+ "imagen_url "
			+ "FROM "
			+ "comando where "
			+ "id_especialidad = :idEspecialidad "
			+ "and activo = 1 "
			+ "ORDER BY nombre asc"
			)
	List<ComandoTable>listComandosActivosByIdEspecialidad(@Param("idEspecialidad") int idEspecialidad); 
	
	@Query(   "SELECT * FROM "
			+ "comando where "
			+ "id_comando_padre = :idComandoPadre "
			+ "and activo = 1 "
			+ "ORDER BY nombre asc"
			)
	List<ComandoTable>listComandosActivosByComandoPadre(@Param("idComandoPadre") int idComandoPadre); 
	
	
}
