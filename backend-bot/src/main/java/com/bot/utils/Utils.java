package com.bot.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class Utils {
	
	public static LocalDateTime obtenerFechaHoraActual() {
		return LocalDateTime.now(ZoneId.of("America/Lima"));
	}
	
	public static String generarCodigoAleatorio(int len) {
		return RandomStringUtils.random(len, "0123456789");
	}
	
	public static String enmascararCorreo(String correo) {
		if(correo == null || correo == "")
			return null;
		int visibleLength = 3; 
		String[] partes = correo.split("@");
		String parteVisible = partes[0].substring(0,visibleLength);
		String asteriscos = StringUtils.repeat('*', partes[0].length() - visibleLength);
		return parteVisible + asteriscos + "@" + partes[1];
	}
	
	public static String crearRespuesta(List<String> codigos) {
		String cad = "Los siguientes códigos ya se encontraban registrados: " + System.lineSeparator();
		
		for(String c : codigos)
			cad = cad + c + System.lineSeparator();
		
		return cad;
	}
}
