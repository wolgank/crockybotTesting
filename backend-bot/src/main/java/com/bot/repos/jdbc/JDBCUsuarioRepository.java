package com.bot.repos.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.dtos.ResponseDTO;
import com.bot.dtos.UsuarioDTO;
import com.bot.repos.crud.UsuarioCrudRepository;
import com.bot.repos.interfaces.UsuarioRepository;
import com.bot.tables.UsuarioTable;
import com.bot.utils.Utils;

@Component
public class JDBCUsuarioRepository implements UsuarioRepository{
	
	@Autowired
	UsuarioCrudRepository usuarioRepo;

	@Override
	public ResponseDTO registrarUsuarios(List<UsuarioDTO> dto, int idEspecialidad) {
		
		List<String> codigosRepetidos= new ArrayList<>();
		
		dto.forEach(d -> {
			UsuarioTable usuario = new UsuarioTable(d.getCodigo(),d.getCorreo(), idEspecialidad);
			try {
				usuarioRepo.save(usuario);
			}
			catch(Exception ex){
				codigosRepetidos.add(usuario.getCodigo());
			}
		});
		
		if(codigosRepetidos.size() != 0)
			return new ResponseDTO("success", Utils.crearRespuesta(codigosRepetidos));
		
		return new ResponseDTO("success", "");
	}

	@Override
	public List<String> consultarUsuarios(int idEspecialidad) {
		
		List<String> identificadores = usuarioRepo.listIdUsuarioPlataforma(idEspecialidad);

		return identificadores;
	}
	
}
