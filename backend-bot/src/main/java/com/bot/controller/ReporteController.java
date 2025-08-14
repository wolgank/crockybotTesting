package com.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bot.dtos.ResponseDTO;
import com.bot.services.ReporteService;


@RestController
@RequestMapping("/reportes")
public class ReporteController {
	
	@Autowired
	private ReporteService reporteService; 
	
	@GetMapping("/indicadores/{id-especialidad}")
	public ResponseEntity<ResponseDTO>reporteIndicadores(@PathVariable("id-especialidad") int idEspecialidad){
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(reporteService.generarReporteIndicadores(idEspecialidad))
				,HttpStatus.OK);
	}
	
	@GetMapping("/consultas-personalizadas/{id-especialidad}")
	public ResponseEntity<ResponseDTO>reporteConsultasPersonalizadas(@PathVariable("id-especialidad") int idEspecialidad){
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(reporteService.generarReporteConsultaPersonalizada(idEspecialidad))
				,HttpStatus.OK);
	}
	
	@GetMapping("/usuarios/{id-especialidad}")
	public ResponseEntity<ResponseDTO>reporteUsuarios(@PathVariable("id-especialidad") int idEspecialidad){
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(reporteService.generarReporteUsuarios(idEspecialidad))
				,HttpStatus.OK);
	}
}
