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

import com.bot.dtos.ResponseDTO;
import com.bot.dtos.UsuarioDTO;
import com.bot.repos.interfaces.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/registrar/{id-especialidad}")
	public ResponseEntity<ResponseDTO> registrar(@RequestBody List<UsuarioDTO> dto, @PathVariable("id-especialidad") int idEspecialidad){
		return new ResponseEntity<ResponseDTO>(usuarioRepository.registrarUsuarios(dto, idEspecialidad),HttpStatus.OK);
	}
	
	@GetMapping("/listar/{id-especialidad}")
	public ResponseEntity<ResponseDTO> consultar(@PathVariable("id-especialidad") int idEspecialidad){
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(usuarioRepository.consultarUsuarios(idEspecialidad))
				,HttpStatus.OK);
	}
}
