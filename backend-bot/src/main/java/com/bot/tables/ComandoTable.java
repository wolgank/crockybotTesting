package com.bot.tables;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.bot.dtos.RegistroComandoDTO;
import com.bot.model.Comando;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Table("comando")
@NoArgsConstructor
@Getter
public class ComandoTable {
	@Id
	private int id;
	private String titulo; 
	private String respuesta; 
	private String nombre; 
	@Column("id_especialidad")
	private int idEspecialidad; 
	@Column("id_comando_padre")
	private Integer idComandoPadre;
	@Column("imagen_url")
	private String imagenUrl; 
	@Column("activo")
	private Boolean activo; 
	public Comando toModel() {
		return new Comando(id, titulo, respuesta, nombre, idEspecialidad,imagenUrl, idComandoPadre);
	}
	
	public void desactivar() {
		this.activo = false; 
	}
	
	public ComandoTable(RegistroComandoDTO dto, int idEspecialidad, Integer idComandoPadre) {
		this.titulo = dto.getTitulo(); 
		this.respuesta = dto.getRespuesta(); 
		this.nombre = dto.getNombre(); 
		this.idEspecialidad = idEspecialidad; 
		this.idComandoPadre = idComandoPadre; 
		this.imagenUrl = dto.getImagenUrl();
		this.activo = true; 
	}
}
