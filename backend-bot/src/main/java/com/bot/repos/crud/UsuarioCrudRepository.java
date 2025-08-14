package com.bot.repos.crud;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bot.tables.UsuarioTable;

@Repository
public interface UsuarioCrudRepository extends CrudRepository<UsuarioTable,Integer> {
	
	@Query(   "SELECT id FROM "
			+ "usuario where "
			+ "id_usuario_plataforma = :idUsuarioPlataforma"
			)
	Integer findIdByIdUsuarioPlataforma(@Param("idUsuarioPlataforma") String idUsuarioPlataforma);
	
	@Query(   "SELECT * FROM "
			+ "usuario where "
			+ "id_usuario_plataforma = :idUsuarioPlataforma"
			)
	UsuarioTable findUsuarioByIdUsuarioPlataforma(@Param("idUsuarioPlataforma") String idUsuarioPlataforma);
	
	@Query(   "SELECT * FROM "
			+ "usuario where "
			+ "codigo = :codigoUsuario"
			)
	UsuarioTable findUsuarioByCodigo(@Param("codigoUsuario") String codigoUsuario);
	
	@Query(   "SELECT "
			+ "id_usuario_plataforma "
			+ "FROM usuario "
			+ "where "
			+ "verificado = true "
			+ "and "
			+ "id_especialidad = :idEspecialidad"
			)
	List<String> listIdUsuarioPlataforma(@Param("idEspecialidad") Integer idEspecialidad);
	
	@Query ( "SELECT COUNT(*) "
			+ "FROM usuario "
			+ "where "
			+ "verificado = true "
			+ "and "
			+ "id_especialidad = :idEspecialidad"
			)
	Integer findNumeroDeVerificados(@Param("idEspecialidad") Integer idEspecialidad);
	
	List<UsuarioTable> findByIdEspecialidad(Integer idEspecialidad);
}
