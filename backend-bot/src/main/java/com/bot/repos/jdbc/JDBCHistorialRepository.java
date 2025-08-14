package com.bot.repos.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bot.dtos.ComandoPersonalizadoDTO;
import com.bot.dtos.RegistroHistorialDTO;
import com.bot.dtos.ResponseDTO;
import com.bot.model.Comando;
import com.bot.model.Historial;
import com.bot.model.Usuario;
import com.bot.repos.crud.ComandoCrudRepository;
import com.bot.repos.crud.EspecialidadCrudRepository;
import com.bot.repos.crud.HistorialCrudRepository;
import com.bot.repos.crud.UsuarioCrudRepository;
import com.bot.repos.interfaces.ComandoRepository;
import com.bot.repos.interfaces.HistorialRepository;
import com.bot.services.MailService;
import com.bot.tables.ComandoTable;
import com.bot.tables.EspecialidadTable;
import com.bot.tables.HistorialTable;
import com.bot.tables.UsuarioTable;
import com.bot.utils.Utils;

@Component
public class JDBCHistorialRepository implements HistorialRepository{
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	HistorialCrudRepository historialRepo;
	
	@Autowired
	UsuarioCrudRepository usuarioRepo;
	
	@Autowired
	EspecialidadCrudRepository especialidadRepo; 
	
	@Value("${consulta-personalizada.remitente}")
	private String remitente;
	
	@Value("${consulta-personalizada.asunto}")
	private String asunto;
	
	@Value("${consulta-personalizada.contenido}")
	private String contenido;
	
	
	@Override
	public void registrar(RegistroHistorialDTO dto) {
		UsuarioTable usuario = usuarioRepo.findUsuarioByIdUsuarioPlataforma(dto.getIdUsuario());
		historialRepo.save(new HistorialTable(usuario.getId(),Utils.obtenerFechaHoraActual(), 
				dto.getIdComando(), usuario.getIdEspecialidad()));
	}

	@Override
	public ResponseDTO registrarPersonalizado(ComandoPersonalizadoDTO dto) {
		UsuarioTable usuario = usuarioRepo.findUsuarioByIdUsuarioPlataforma(dto.getIdAutor());
		if(usuario == null) {
			return new ResponseDTO("error","El usuario no se encuentra en el sistema.");
		} else if(!usuario.getVerificado()) {
			return new ResponseDTO("error","El usuario no está verificado.");
		} else {
			EspecialidadTable especialidad = especialidadRepo.findById(usuario.getIdEspecialidad()).orElse(null);
			mailService.enviarMailCC(remitente,especialidad.getCorreo(), usuario.getCorreo(),asunto, contenido.replace("{codigo}"
					, usuario.getCodigo()).replace("{correo}", usuario.getCorreo())
					.replace("{consulta}", dto.getConsulta()));
			
			historialRepo.save(new HistorialTable(usuario.getId(),Utils.obtenerFechaHoraActual(), 
				dto.getConsulta(), usuario.getIdEspecialidad()));
			return new ResponseDTO("success","Se ha enviado su consulta al correo " + especialidad.getCorreo() +
					", recibirá una respuesta en el correo " + usuario.getCorreo() +" en los proximos días.");
		}
	}

}
