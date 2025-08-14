package com.bot.repos.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bot.dtos.RegistroHistorialDTO;
import com.bot.dtos.ResponseDTO;
import com.bot.dtos.TokenDTO;
import com.bot.dtos.VerificacionDTO;
import com.bot.model.Comando;
import com.bot.model.Historial;
import com.bot.repos.crud.ComandoCrudRepository;
import com.bot.repos.crud.HistorialCrudRepository;
import com.bot.repos.crud.TokenCrudRepository;
import com.bot.repos.crud.UsuarioCrudRepository;
import com.bot.repos.interfaces.ComandoRepository;
import com.bot.repos.interfaces.HistorialRepository;
import com.bot.repos.interfaces.TokenRepository;
import com.bot.services.MailService;
import com.bot.tables.ComandoTable;
import com.bot.tables.HistorialTable;
import com.bot.tables.TokenTable;
import com.bot.tables.UsuarioTable;
import com.bot.utils.Utils;

@Component
public class JDBCTokenRepository implements TokenRepository{

	@Autowired
	UsuarioCrudRepository usuarioRepo;

	@Autowired
	TokenCrudRepository tokenRepo;
	
	@Autowired
	private MailService mailService;
	
	@Value("${correo.remitente}")
	private String remitente;
	
	@Value("${correo.asunto}")
	private String asunto;
	
	@Value("${correo.contenido}")
	private String contenido;
	
	@Value("${numero-intentos-token}")
	private Integer numeroIntentos;
	
	@Override
	public ResponseDTO registrarToken(TokenDTO dto) {
		UsuarioTable u = usuarioRepo.findUsuarioByCodigo(dto.getCodigoUsuario());
		if(u==null) {
			return new ResponseDTO("error", "No existe un usuario con ese código.");
		} else if(u.getVerificado()) {
			return new ResponseDTO("error", "El usuario ya esta verificado."); 
		}
		String codigoVerificacion = Utils.generarCodigoAleatorio(4);
		TokenTable t = tokenRepo.findById(dto.getIdAutor()).orElse(null); 
		if(t == null) {
			tokenRepo.save(new TokenTable(dto.getIdAutor(),dto.getNombreAutor(),u.getId(),numeroIntentos,codigoVerificacion,true));
		} else {
			UsuarioTable us = usuarioRepo.findUsuarioByIdUsuarioPlataforma(t.getIdAutor());
			if(us != null && us.getVerificado()) {
				return new ResponseDTO("error", "Usted ya verificó su usuario."); 
			}
			tokenRepo.save(new TokenTable(dto.getIdAutor(),dto.getNombreAutor(),u.getId(),numeroIntentos,codigoVerificacion,false));
		}
		mailService.enviarMail(remitente,u.getCorreo(), asunto, contenido.replace("{token}"
				, codigoVerificacion));
		return new ResponseDTO("success","Se ha enviado un token de verificación"
				+ " al correo registrado " + Utils.enmascararCorreo(u.getCorreo()));
	}

	@Override
	public ResponseDTO verificarToken(VerificacionDTO dto) {
		TokenTable t = tokenRepo.findById(dto.getIdAutor()).orElse(null); 
		if(t == null || t.getNumeroIntentos().equals(0)) {
			return new ResponseDTO("error", "No ha generado un token aún.");
		}
		UsuarioTable u = usuarioRepo.findById(t.getIdUsuarioAVerificar()).orElse(null);
		if(u == null) {
			return new ResponseDTO("error", "No existe el usuario a verificar.");
		} else if(u.getVerificado()) {
			return new ResponseDTO("error", "El usuario ya esta verificado."); 
		} else if (!dto.getCodigoVerificacion().equals(t.getCodigoVerificacion())) {
			t.disminuirNumeroIntentos();
			tokenRepo.save(t);
			if(t.getNumeroIntentos().equals(0)) {
				return new ResponseDTO("error", "El token es incorrecto. Número de intentos fallidos superado. Debe generar un nuevo token."); 
			}
			return new ResponseDTO("error", "El token es incorrecto."); 
		}
		u.setIdUsuarioPlataforma(dto.getIdAutor());
		u.setNombreUsuario(dto.getNombreAutor());
		u.setVerificado(true);
		usuarioRepo.save(u);
		return new ResponseDTO("success", "¡Verificación exitosa!");
	}
	

}
