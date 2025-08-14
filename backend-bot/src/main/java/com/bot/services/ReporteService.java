package com.bot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.dtos.ReporteConsultaPersonalizadaDTO;
import com.bot.dtos.ReporteIndicadoresDTO;
import com.bot.dtos.UsuarioReporteDTO;
import com.bot.repos.crud.HistorialCrudRepository;
import com.bot.repos.crud.UsuarioCrudRepository;
@Service
public class ReporteService {

	@Autowired
	private HistorialCrudRepository historialRepo; 
	
	@Autowired
	private UsuarioCrudRepository usuarioRepo; 
	
	public List<ReporteIndicadoresDTO> generarReporteIndicadores(int idEspecialidad) {
		List<ReporteIndicadoresDTO> listaIndicadores = new ArrayList<ReporteIndicadoresDTO>(); 
		listaIndicadores.add(new ReporteIndicadoresDTO("Numero de alumnos verificados", 
				String.valueOf(usuarioRepo.findNumeroDeVerificados(idEspecialidad))));
		
		listaIndicadores.add(new ReporteIndicadoresDTO("Numero de consultas hechas", 
				String.valueOf(historialRepo.findNumeroDeConsultas(idEspecialidad))));
		
		listaIndicadores.add(new ReporteIndicadoresDTO("Numero de consultas personalizadas hechas", 
				String.valueOf(historialRepo.findNumeroDeConsultasPersonalizadas(idEspecialidad))));
		
		listaIndicadores.add(new ReporteIndicadoresDTO("Numero de consultas automáticas hechas", 
				String.valueOf(historialRepo.findNumeroDeConsultasAutomaticas(idEspecialidad))));
		
		return listaIndicadores; 
	}
	
	public List<ReporteConsultaPersonalizadaDTO> generarReporteConsultaPersonalizada(int idEspecialidad){
		return historialRepo.findConsultasPersonalizadasByIdEspecialidad(idEspecialidad); 
	}
	
	public List<UsuarioReporteDTO> generarReporteUsuarios(int idEspecialidad){
		return usuarioRepo.findByIdEspecialidad(idEspecialidad).stream()
				.map(u -> u.toReport()).collect(Collectors.toList()); 
	}
}
