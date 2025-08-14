package com.bot.repos.interfaces;

import java.util.List;

import com.bot.dtos.ComandoPersonalizadoDTO;
import com.bot.dtos.RegistroHistorialDTO;
import com.bot.dtos.ResponseDTO;
import com.bot.model.Comando;
import com.bot.model.Historial;

public interface HistorialRepository {
	void registrar(RegistroHistorialDTO dto);
	ResponseDTO registrarPersonalizado(ComandoPersonalizadoDTO dto);
}
