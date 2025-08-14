package com.bot.tables;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.bot.model.Comando;
import com.bot.model.Historial;

import lombok.NoArgsConstructor;

@Table("historial")
@NoArgsConstructor
public class HistorialTable {	
	
	
	public HistorialTable(Integer idUsuario, LocalDateTime fecha, Integer idComando, Integer idEspecialidad) {
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.idComando = idComando;
		this.idEspecialidad = idEspecialidad; 
		this.personalizado = false; 
	}
	
	public HistorialTable(Integer idUsuario, LocalDateTime fecha, String mensajePersonalizado, Integer idEspecialidad) {
		this.idUsuario = idUsuario; 
		this.fecha = fecha; 
		this.mensajePersonalizado = mensajePersonalizado;
		this.idEspecialidad = idEspecialidad;  
		this.personalizado = true;
	}
	
	
	@Id
	private int id;
	@Column("id_usuario")
	private Integer idUsuario; 
	private LocalDateTime fecha;
	@Column("id_comando")
	private Integer idComando; 	
	@Column("mensaje_personalizado")
	private String mensajePersonalizado; 
	private Boolean personalizado; 
	@Column("id_especialidad")
	private Integer idEspecialidad; 
}
