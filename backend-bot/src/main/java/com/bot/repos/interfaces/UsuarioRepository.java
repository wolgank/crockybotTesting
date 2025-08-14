package com.bot.repos.interfaces;

import java.util.List;

import com.bot.dtos.ResponseDTO;
import com.bot.dtos.UsuarioDTO;

public interface UsuarioRepository {
	
	ResponseDTO registrarUsuarios(List<UsuarioDTO> dto, int idEspecialidad);
	
	List<String> consultarUsuarios(int idEspecialidad);
}
