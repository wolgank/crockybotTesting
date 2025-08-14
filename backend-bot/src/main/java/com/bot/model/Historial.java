package com.bot.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Historial {
	
	public Historial(int idUsuario, LocalDateTime fecha, int idComando) {
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.idComando = idComando;
	}
	
	private int idUsuario; 
	private LocalDateTime fecha; 
	private int idComando; 	
	private String mensajePersonalizado; 
	private Boolean personalizado;
}
