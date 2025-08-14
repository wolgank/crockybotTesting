package com.bot.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UsuarioReporteDTO {
	private Integer id;
	private String idUsuarioPlataforma;
	private String codigo;
	private String nombreUsuario;
	private String correo;
	private Boolean verificado;
}
