package com.bot.repos.interfaces;

import java.util.List;

import com.bot.dtos.RegistroComandoDTO;
import com.bot.dtos.ResponseDTO;
import com.bot.model.Comando;

public interface ComandoRepository {
	
	List<Comando>listarPorIdEspecialidad(int idEspecialidad);
	
	ResponseDTO registrarPorIdEspecialidad(List<RegistroComandoDTO>comandos, int idEspecialidad);
	
	void registrarPorIdEspecialidadIdComandoPadre(List<RegistroComandoDTO> comandos, int idEspecialidad, Integer idComandoPadre);
}
