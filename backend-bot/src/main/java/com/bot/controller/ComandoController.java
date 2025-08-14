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

import com.bot.dtos.ComandoPersonalizadoDTO;
import com.bot.dtos.RegistroComandoDTO;
import com.bot.dtos.ResponseDTO;
import com.bot.dtos.TokenDTO;
import com.bot.model.Comando;
import com.bot.repos.interfaces.ComandoRepository;
import com.bot.repos.interfaces.HistorialRepository;

@RestController
@RequestMapping("/comandos")
public class ComandoController {
	
	@Autowired
	private ComandoRepository comandoRepository; 
	
	@Autowired
	private HistorialRepository historialRepository; 
	
	@GetMapping("/listar/{id-especialidad}")
	public ResponseEntity<ResponseDTO>listarPorEspecialidad(@PathVariable("id-especialidad") int idEspecialidad){
		List<Comando>comandos = comandoRepository.listarPorIdEspecialidad(idEspecialidad);
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(comandos),HttpStatus.OK);
	}	
	
	@PostMapping("/registrar/{id-especialidad}")
	public ResponseEntity<ResponseDTO>registrarPorEspecialidad(@PathVariable("id-especialidad") int idEspecialidad,
																@RequestBody List<RegistroComandoDTO> comandos){
		return new ResponseEntity<ResponseDTO>(comandoRepository.registrarPorIdEspecialidad(comandos, idEspecialidad),HttpStatus.OK);
	}	
	
	@PostMapping("/personalizado")
	public ResponseEntity<ResponseDTO>registrarConsultaPersonalizadaPorEspecialidad(@RequestBody ComandoPersonalizadoDTO dto){
		return new ResponseEntity<ResponseDTO>(historialRepository.registrarPersonalizado(dto),HttpStatus.OK);
	}
}
