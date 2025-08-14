package com.bot.tables;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.bot.model.Comando;
import com.bot.model.Historial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table("token")
@NoArgsConstructor
@Setter
@Getter
public class TokenTable implements Persistable<String>{	
	@Id
	@Column("id_autor")
	private String idAutor;
	@Column("nombre_usuario_autor")
	private String nombreUsuarioAutor; 
	@Column("id_usuario_a_verificar")
	private Integer idUsuarioAVerificar; 	
	@Column("numero_intentos")
	private Integer numeroIntentos; 	
	@Column("codigo_verificacion")
	private String codigoVerificacion;
	
	@Transient
	private boolean isNew;
	
	public TokenTable(String idAutor, String nombreUsuarioAutor, Integer idUsuarioAVerificar, Integer numeroIntentos, String codigoVerificacion,
			boolean isNew) {
		this.idAutor = idAutor;
		this.nombreUsuarioAutor = nombreUsuarioAutor;
		this.idUsuarioAVerificar = idUsuarioAVerificar;
		this.codigoVerificacion = codigoVerificacion;
		this.numeroIntentos = numeroIntentos; 
		this.isNew = isNew;
	} 	
	
	public void disminuirNumeroIntentos() {
		this.numeroIntentos--;
	}
	
	@Override
	public String getId() {
		return idAutor; 
	}
	
	@Override
	public boolean isNew() {
		return isNew;
	}

}
