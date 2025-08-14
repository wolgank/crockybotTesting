package com.bot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bot.dtos.RegistroHistorialDTO;
import com.bot.dtos.ResponseDTO;
import com.bot.dtos.TokenDTO;
import com.bot.dtos.VerificacionDTO;
import com.bot.model.Comando;
import com.bot.model.Historial;
import com.bot.repos.interfaces.ComandoRepository;
import com.bot.repos.interfaces.HistorialRepository;
import com.bot.repos.interfaces.TokenRepository;
import com.bot.repos.interfaces.UsuarioRepository;
import com.bot.utils.Utils;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	private TokenRepository tokenRepository; 
	
	@PostMapping("/registrar")
	public ResponseEntity<ResponseDTO>enviarVerificacion(@RequestBody TokenDTO dto){
		return new ResponseEntity<ResponseDTO>(tokenRepository.registrarToken(dto),HttpStatus.OK);
	}
	
	@PostMapping("/verificar")
	public ResponseEntity<ResponseDTO>verificarToken(@RequestBody VerificacionDTO dto){
		return new ResponseEntity<ResponseDTO>(tokenRepository.verificarToken(dto)
				,HttpStatus.OK);
	}
	
}
