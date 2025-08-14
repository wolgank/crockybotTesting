package com.bot.tables;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.bot.dtos.UsuarioReporteDTO;
import com.bot.model.Usuario;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Table("usuario")
@NoArgsConstructor
@Setter
@Getter
public class UsuarioTable {
	@Id
	private Integer id;
	@Column("id_usuario_plataforma")
	private String idUsuarioPlataforma;
	@Column("codigo")
	private String codigo;
	@Column("nombre_usuario")
	private String nombreUsuario;
	@Column("correo")
	private String correo;
	@Column("id_especialidad")
	private Integer idEspecialidad; 
	private Boolean verificado;

	public Usuario toModel(String codigo, String correo) {
		return new Usuario(codigo, correo);
	}
	
	public UsuarioReporteDTO toReport() {
		return new UsuarioReporteDTO(id, idUsuarioPlataforma, codigo, nombreUsuario,
				correo, verificado);
	}
	
	public UsuarioTable (String codigo, String correo, int idEspecialidad) {
		this.codigo = codigo;
		this.correo = correo;
		this.idEspecialidad = idEspecialidad;
		this.verificado = false;
	}
}
