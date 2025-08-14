package com.bot.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReporteConsultaPersonalizadaDTO {
	private Integer id; 
	private String consulta; 
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime fecha; 
	private String codigo; 
	private String nombreUsuario; 
	private String correo; 
}
