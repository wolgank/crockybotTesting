package com.bot.repos.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.dtos.RegistroComandoDTO;
import com.bot.dtos.ResponseDTO;
import com.bot.model.Comando;
import com.bot.repos.crud.ComandoCrudRepository;
import com.bot.repos.interfaces.ComandoRepository;
import com.bot.tables.ComandoTable;

@Component
public class JDBCComandoRepository implements ComandoRepository{

	@Autowired
	ComandoCrudRepository repo;
	
	@Override
	public List<Comando> listarPorIdEspecialidad(int idEspecialidad) {
		List<Comando>comandos = repo.listComandosActivosByIdEspecialidad(idEspecialidad).stream()
				.map(c -> c.toModel()).collect(Collectors.toList());
		for(Comando c: comandos) {
			c.setSubComandos(repo.listComandosActivosByComandoPadre(c.getId()).stream()
					.map(ct -> ct.toModel()).collect(Collectors.toList()));
		}
		
		return comandos;
	}

	@Override
	public ResponseDTO registrarPorIdEspecialidad(List<RegistroComandoDTO> comandos, int idEspecialidad) {
		try {
			List<ComandoTable> cTables = (List<ComandoTable>) repo.findAll(); 
			for(ComandoTable c: cTables) {
				c.desactivar(); 
			}
			repo.saveAll(cTables); 
			registrarPorIdEspecialidadIdComandoPadre(comandos,idEspecialidad,null); 
			return new ResponseDTO(true); 
		}catch(Exception e) {
			return new ResponseDTO("error", e.getMessage()); 
		}
	}
	
	
	public void registrarPorIdEspecialidadIdComandoPadre(List<RegistroComandoDTO> comandos, int idEspecialidad, Integer idComandoPadre) {
		if(comandos == null || comandos.size() == 0) return; 
		List<ComandoTable> cTables = comandos.stream().map(c -> new ComandoTable(c, idEspecialidad, idComandoPadre))
							.collect(Collectors.toList()); 
		repo.saveAll(cTables); 
		for(int i=0; i<comandos.size();i++) {
			registrarPorIdEspecialidadIdComandoPadre(comandos.get(i).getSubComandos(), idEspecialidad
					, cTables.get(i).getId());
		}
	}
}
