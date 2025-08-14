package com.bot.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comando {
	
	public Comando(int id, String titulo, String respuesta, String nombre, int idEspecialidad,String imagenUrl,
			Integer idComandoPadre) {
		this.id = id;
		this.titulo = titulo;
		this.respuesta = respuesta;
		this.nombre = nombre;
		this.idEspecialidad = idEspecialidad;
		this.idComandoPadre = idComandoPadre;
		this.imagenUrl = imagenUrl;
		this.subComandos = new ArrayList<Comando>();
	}
	private int id;
	private String titulo; 
	private String respuesta; 
	private String nombre; 
	private int idEspecialidad;
	private Integer idComandoPadre;
	private String imagenUrl;
	private List<Comando> subComandos;
}
