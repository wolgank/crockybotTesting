package com.bot.repos.crud;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bot.dtos.ReporteConsultaPersonalizadaDTO;
import com.bot.tables.ComandoTable;
import com.bot.tables.HistorialTable;

@Repository
public interface HistorialCrudRepository extends CrudRepository<HistorialTable,Integer> {
	
	
	@Query ( "SELECT COUNT(*) "
			+ "FROM historial "
			+ "where "
			+ "personalizado = true "
			+ "and "
			+ "id_especialidad = :idEspecialidad"
			)
	Integer findNumeroDeConsultasPersonalizadas(@Param("idEspecialidad") int idEspecialidad);
	
	@Query ( "SELECT COUNT(*) "
			+ "FROM historial "
			+ "where "
			+ "personalizado = false "
			+ "and "
			+ "id_especialidad = :idEspecialidad"
			)
	Integer findNumeroDeConsultasAutomaticas(@Param("idEspecialidad") int idEspecialidad);
	
	@Query ( "SELECT COUNT(*) "
			+ "FROM historial "
			+ "where "
			+ "id_especialidad = :idEspecialidad"
			)
	Integer findNumeroDeConsultas(@Param("idEspecialidad") int idEspecialidad);
	
	@Query("SELECT h.id, "
			+ "mensaje_personalizado as consulta, "
			+ "fecha, "
			+ "usuario.codigo, "
			+ "usuario.nombre_usuario, "
			+ "usuario.correo "
			+ "from historial h "
			+ "inner join usuario on "
			+ "usuario.id = h.id_usuario "
			+ "where "
			+ "h.id_especialidad = :idEspecialidad "
			+ "and h.personalizado = true ")
	List<ReporteConsultaPersonalizadaDTO> findConsultasPersonalizadasByIdEspecialidad(@Param("idEspecialidad") int idEspecialidad);
}
