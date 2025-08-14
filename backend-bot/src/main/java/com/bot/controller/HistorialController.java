package com.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bot.dtos.RegistroHistorialDTO;
import com.bot.dtos.ResponseDTO;
import com.bot.repos.interfaces.HistorialRepository;

@RestController
@RequestMapping("/historial")
public class HistorialController {
	
	@Autowired
	private HistorialRepository historialRepository; 
	
	@PostMapping("/registrar")
	public ResponseEntity<ResponseDTO>registrar(@RequestBody RegistroHistorialDTO dto){
		historialRepository.registrar(dto);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(true),HttpStatus.OK);
	}	
}
