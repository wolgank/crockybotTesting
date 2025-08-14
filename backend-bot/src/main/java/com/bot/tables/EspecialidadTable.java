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

@Table("especialidad")
@NoArgsConstructor
@Setter
@Getter
public class EspecialidadTable{	
	@Id
	private int id;
	private String nombre; 
	private String correo; 
}
