package com.bot.repos.interfaces;

import java.util.List;

import com.bot.dtos.RegistroHistorialDTO;
import com.bot.dtos.ResponseDTO;
import com.bot.dtos.TokenDTO;
import com.bot.dtos.VerificacionDTO;
import com.bot.model.Comando;
import com.bot.model.Historial;

public interface TokenRepository {
	ResponseDTO registrarToken(TokenDTO dto);
	ResponseDTO verificarToken(VerificacionDTO dto);
}
