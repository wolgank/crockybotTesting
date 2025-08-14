package com.bot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

	public Usuario(String codigo, String correo) {
		this.codigo = codigo;
		this.correo = correo;
	}
	private String codigo;
	private String correo;
}
