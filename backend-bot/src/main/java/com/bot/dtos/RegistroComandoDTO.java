package com.bot.dtos;

import java.util.List;

import lombok.Getter;

@Getter
public class RegistroComandoDTO {
	private String titulo; 
	private String respuesta; 
	private String nombre; 
	private String imagenUrl;
	private List<RegistroComandoDTO> subComandos; 
}
